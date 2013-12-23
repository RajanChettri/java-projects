package ca.ubc.ece.eece210.mp3.ast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import ca.ubc.ece.eece210.mp3.Album;
import ca.ubc.ece.eece210.mp3.Catalogue;
import ca.ubc.ece.eece210.mp3.Element;
import ca.ubc.ece.eece210.mp3.Genre;

/**
 * Searches for albums matching MATCHES criteria.
 * 
 * @author Sathish Gopalakrishnan
 * @modified by Stephen Hu and Vincent Leung
 * 
 */
public class MatchesNode extends ASTNode {
    List<Element> albumList = new ArrayList<Element>();
    Set<Element> albumSet = new HashSet<Element>( albumList );
    String stringToken = new String();

    public MatchesNode(Token token) {
        super( token );
        if ( token.getPayload().contains( "\"" ) ) {
            stringToken = sanitizeString( token.getPayload() );
        } else {
            stringToken = token.getPayload();
        }
        this.setArguments( stringToken );
    }

    // Empty constructor
    public MatchesNode() {
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
     * This method interprets the MatchesNode and returns the set of Elements
     * that satisfies the conditions of the MatchesNode.
     * 
     * @param argument
     *        the catalogue to search through
     * 
     * @return Set of Elements that satisfy the criteria indicated by the
     *         MatchesNode.
     */
    @Override
    public Set<Element> interpret( Catalogue argument ) {

        String pattern = this.arguments; // gets the argument for the pattern.
        for ( int i = 0; i < argument.getMyGenres().size(); i++ ) {
            access( argument.getMyGenres().get( i ), pattern );
            // put in the genre into the recursive function to get the albums
            // from all the big genres..
        }
        return albumSet;
    }

    /**
     * This is the recursive function that get's the info from the children's
     * albums,
     * and checks for matches in their title/performer.
     * 
     * @param genre
     * @param pattern
     */
    public void access( Element genre, String pattern ) {
        // Iterate through the genre's children if it has any
        for ( int i = 0; i < genre.getMyChildren().size(); i++ ) {
            if ( genre.getMyChildren().get( i ).hasChildren() == false ) {
                // If it does not have children (is an album)
                String title = ( (Album) ( genre.getMyChildren().get( i ) ) )
                        .getMyTitle();
                // Get the title of the album
                boolean checkTitle = Pattern.matches( title, pattern );
                // Checks if it matches the title of the album
                if ( checkTitle ) { // If either of them matched
                    albumSet.add( genre.getChildren().get( i ) );
                    // Add that album into the set of matching elements
                    System.out.println( "I have added "
                            + ( (Album) genre.getChildren().get( i ) )
                                    .getTitle() + " to the MATCHES set." );
                }
            }
            if ( genre.getMyChildren().get( i ).hasChildren() ) {
                // If it has children (Genre)
                access( genre.getChildren().get( i ), pattern );
                // Check for children in the genre
            }
        }
    }

}
