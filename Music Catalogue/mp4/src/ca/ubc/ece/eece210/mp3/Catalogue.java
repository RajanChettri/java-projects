package ca.ubc.ece.eece210.mp3;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import ca.ubc.ece.eece210.mp3.ast.QueryTokenizer;
import ca.ubc.ece.eece210.mp3.ast.QueryParser;
import ca.ubc.ece.eece210.mp3.ast.Token;
import ca.ubc.ece.eece210.mp3.ast.ByNode;
import ca.ubc.ece.eece210.mp3.ast.InNode;
import ca.ubc.ece.eece210.mp3.ast.MatchesNode;
import ca.ubc.ece.eece210.mp3.ast.OrNode;
import ca.ubc.ece.eece210.mp3.ast.ASTNode;
import ca.ubc.ece.eece210.mp3.ast.AndNode;
import ca.ubc.ece.eece210.mp3.ast.TokenType;

// import ca.ubc.ece.eece210.mp3.Element;

/**
 * Container class for all the albums and genres. Its main responsibility is to
 * save and restore the collection from a file.
 * 
 * @author Sathish Gopalakrishnan
 * 
 */
public final class Catalogue {
    ArrayList<Genre> myGenres;

    /**
     * Builds a new, empty catalogue.
     */
    public Catalogue() {
        myGenres = new ArrayList<>();
    }

    /**
     * Builds a new catalogue and restores its contents from the given file.
     * 
     * @param fileName
     *        the file from where to restore the library.
     */
    public Catalogue(String fileName) {
        XMLDecoder d;
        try {
            d = new XMLDecoder( new BufferedInputStream( new FileInputStream(
                    fileName ) ) );
        } catch ( FileNotFoundException e ) {
            e.printStackTrace();
            return;
        }
        Object result = d.readObject();
        d.close();

        myGenres = ( (Catalogue) result ).myGenres;
    }

    public ArrayList<Genre> getMyGenres() {
        return myGenres;
    }

    public void setMyGenres( ArrayList<Genre> genres ) {
        myGenres = genres;
    }

    /**
     * Saved the contents of the catalog to the given file.
     * 
     * @param fileName
     *        the file where to save the library
     */
    public void saveCatalogueToFile( String fileName ) {
        XMLEncoder e;
        try {
            e = new XMLEncoder( new BufferedOutputStream( new FileOutputStream(
                    fileName ) ) );
        } catch ( FileNotFoundException e1 ) {
            System.out.println( "Error creating XMLEncoder for " + fileName );
            e1.printStackTrace();
            return;
        }
        e.writeObject( this );
        e.close();
    }

    /**
     * Returns a list of Albums that satisfy the criteria given in
     * the input string to this method
     * 
     * @param query
     *        the input string containing all the conditions to
     *        be satisfied by the list of albums to be output
     */
    public List<Album> queryCat( String query ) {

        // Input string is tokenized
        List<Token> tokenizedString = new ArrayList<Token>();
        tokenizedString = QueryTokenizer.tokenizeInput( query );

        // It is passed into the parser
        QueryParser StringParser = new QueryParser( tokenizedString );
        // Need to create the node with the proper token argument if binary
        // Run getRoot() on the parser and assign it to an ASTNode
        ASTNode tokenizedAST = StringParser.getRoot();

        for ( int i = 0; i < tokenizedString.size(); i++ ) {
            if ( tokenizedAST.getText().equals( "by" ) ) {
                tokenizedAST = new ByNode( tokenizedString.get( i + 2 ) );
            } else if ( tokenizedAST.getText().equals( "in" ) ) {
                tokenizedAST = new InNode( tokenizedString.get( i + 2 ) );
            }
        }

        // Run interpret() on AST.
        Set<Element> interpretedQuery = tokenizedAST.interpret( this );
        // Interpret returns a set of elements

        // Convert the interpretedQuery from a set of elements to a list of
        // elements
        List<Element> elementList = new ArrayList<Element>( interpretedQuery );

        // Convert the list of elements to a list of albums which will then be
        // returned
        List<Album> albumList = new ArrayList<Album>();
        for ( int i = 0; i < elementList.size(); i++ ) {
            albumList.add( (Album) elementList.get( i ) );
        }

        // Return this album list
        return albumList;
    }

    /**
     * Return the hashcode for this class.
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ( ( myGenres == null ) ? 0 : myGenres.hashCode() );
        return result;
    }

    /**
     * Two catalogues are equal if each catalogue's myGenres are equal.
     * 
     * This tests for equality recursively, since ArrayList tests for equality
     * on each element, and Genre tests for equality by checking if its children
     * are equal.
     */
    @Override
    public boolean equals( Object obj ) {
        if ( this == obj )
            return true;
        if ( obj == null )
            return false;
        if ( getClass() != obj.getClass() )
            return false;
        Catalogue other = (Catalogue) obj;
        if ( myGenres == null ) {
            if ( other.myGenres != null )
                return false;
        } else if ( !myGenres.equals( other.myGenres ) )
            return false;
        return true;
    }

}
