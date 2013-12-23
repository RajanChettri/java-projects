package ca.ubc.ece.eece210.mp3.ast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ca.ubc.ece.eece210.mp3.Catalogue;
import ca.ubc.ece.eece210.mp3.Element;
import ca.ubc.ece.eece210.mp3.Album;

/**
 * Searches for albums matching BY criteria.
 * 
 * @author Sathish Gopalakrishnan
 * @modified by Stephen Hu and Vincent Leung
 * 
 */
public class ByNode extends ASTNode {

    List<Element> albumList = new ArrayList<Element>();
    Set<Element> albumSet = new HashSet<Element>( albumList );
    String stringToken = new String();

    public ByNode(Token token) {
        super( token );

        if ( token.getPayload().contains( "\"" ) ) {
            stringToken = sanitizeString( token.getPayload() );
        } else {
            stringToken = token.getPayload();
        }
        this.setArguments( stringToken );
    }

    // Empty constructor
    public ByNode() {
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
     * This method interprets the ByNode and returns the set of Elements
     * that satisfies the conditions of the ByNode.
     * 
     * @param argument
     *        the catalogue to search through
     * 
     * @return Set of Elements that satisfy the criteria indicated by the
     *         ByNode.
     */
    @Override
    public Set<Element> interpret( Catalogue argument ) {
        for ( int i = 0; i < argument.getMyGenres().size(); i++ ) {
            access( argument.getMyGenres().get( i ) );
            // If it's a genre, then call function again
        }
        return albumSet;
    }

    /**
     * The recursive function that is used to access every album and check the
     * album's performer.
     * 
     * @param genre
     */
    public void access( Element genre ) {

        for ( int i = 0; i < genre.getChildren().size(); i++ ) {
            if ( genre.getChildren().get( i ).hasChildren() ) {
                // If its a genre
                access( genre.getChildren().get( i ) );
                // Call function again
            } else if ( genre.getChildren().get( i ).hasChildren() == false ) {
                // If it's an album
                if ( ( (Album) genre.getChildren().get( i ) ).getPerformer()
                        .equals( stringToken ) ) {
                    // If the performer is equal.
                    albumSet.add( genre.getChildren().get( i ) );
                    System.out.println( "I have added "
                            + ( (Album) genre.getChildren().get( i ) )
                                    .getTitle() + " to the BY set." );
                }
            }
        }
        return;
    }
}
