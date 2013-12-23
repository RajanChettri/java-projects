package ca.ubc.ece.eece210.mp3.ast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ca.ubc.ece.eece210.mp3.Album;
import ca.ubc.ece.eece210.mp3.Catalogue;
import ca.ubc.ece.eece210.mp3.Element;
import ca.ubc.ece.eece210.mp3.Genre;


/**
 * Searches for albums matching IN criteria.
 * 
 * @author Sathish Gopalakrishnan
 * @modified by Stephen Hu and Vincent Leung
 * 
 */
public class InNode extends ASTNode {

    List<Element> albumList = new ArrayList<Element>();
    Set<Element> albumSet = new HashSet<Element>( albumList );
    String stringToken = new String();

    // Keep in mind that tokens are maps
    public InNode(Token token) {
        super( token );
        if ( token.getPayload().contains( "\"" ) ) {
            stringToken = sanitizeString( token.getPayload() );
        } else {
            stringToken = token.getPayload();
        }
        this.setArguments( stringToken );
    }

    // Empty constructor
    public InNode() {
    }

    /**
     * This method removes the quotes around a string.
     * 
     * @param input
     *        string to sanitize
     * 
     * @return string
     *         with quotation marks removed
     */
    public String sanitizeString( String input ) {
        StringBuilder removeQuotes = new StringBuilder( input );
        int start = removeQuotes.indexOf( "\"" ) + 1;
        int end = removeQuotes.lastIndexOf( "\"" );
        String cleanString = removeQuotes.substring( start, end );
        return cleanString;
    }

    /**
     * This method interprets the InNode and returns the set of Elements
     * that satisfies the conditions of the InNode.
     * 
     * @param argument
     *        the catalogue to search through
     * 
     * @return Set of Elements that satisfy the criteria indicated by the
     *         InNode.
     */
    @Override
    public Set<Element> interpret( Catalogue argument ) {
        for ( int i = 0; i < argument.getMyGenres().size(); i++ ) {
            if ( argument.getMyGenres().get( i ).getMyName()
                    .equals( stringToken ) ) {
                // If the genre in the highest hierarchy matches
                access( argument.getMyGenres().get( i ) );
                // Call access
            } else
                findMyGenre( argument.getMyGenres().get( i ) );
            // Look in it's subgenres
        }
        return albumSet;
    }

    /**
     * Finds the genre that contains the name of the stringToken and then calls
     * access when there is the match.
     * 
     * @param genre
     */
    public void findMyGenre( Element genre ) {
        for ( int i = 0; i < genre.getChildren().size(); i++ ) {
            if ( genre.getChildren().get( i ).hasChildren() ) {
                // If the genre's child can have children (Genre)
                if ( ( (Genre) genre.getMyChildren().get( i ) ).getMyName()
                        .equals( stringToken ) ) {
                    access( genre.getMyChildren().get( i ) );
                } else
                    findMyGenre( genre.getMyChildren().get( i ) );
                // Call the function again if its a genre
            }
        }
    }

    /**
     * The recursive function that gets all the albums inside a genre including
     * their subgenres's albums.
     * 
     * @param genre
     */
    public void access( Element genre ) {
        for ( int i = 0; i < genre.getChildren().size(); i++ ) {
            if ( genre.getChildren().get( i ).hasChildren() ) {
                // If the genre's child can have children(Genre)
                access( genre.getChildren().get( i ) );
                // Call the function again if its a genre
            } else if ( genre.getChildren().get( i ).hasChildren() == false ) {
                // If the genre's child can't have children(Album)
                albumSet.add( genre.getChildren().get( i ) );
                // Add the child into matching elements.
                System.out.println( "I have added "
                        + ( (Album) genre.getChildren().get( i ) ).getTitle()
                        + " to the IN set." );
            }
        }
        return;
    }

}
