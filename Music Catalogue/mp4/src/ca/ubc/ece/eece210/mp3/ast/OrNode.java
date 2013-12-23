package ca.ubc.ece.eece210.mp3.ast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import ca.ubc.ece.eece210.mp3.Album;
import ca.ubc.ece.eece210.mp3.Element;
import ca.ubc.ece.eece210.mp3.Catalogue;

/**
 * Searches for albums matching OR criteria.
 * 
 * @author Sathish Gopalakrishnan
 * @modified by Stephen Hu and Vincent Leung
 * 
 */
public class OrNode extends ASTNode {

    public OrNode(Token token) {
        super( token );
    }

    public OrNode(ASTNode firstChild, ASTNode secondChild) {
        super( firstChild, secondChild );
    }

    /**
     * This method interprets the OrNode and returns the set of Elements
     * that satisfies the conditions of the OrNode.
     * 
     * @param argument
     *        the catalogue to search through
     * 
     * @return Set of Elements that satisfy the criteria indicated by the
     *         OrNode.
     */
    @Override
    public Set<Element> interpret( Catalogue argument ) {
        // Gets the elements from its children and then add them together in a
        // single set.
        // Does not need to be recursive since its combining the 2 sets
        // together(and the sets should be recursive)
        List<Element> list1 = new ArrayList<Element>();
        Set<Element> set1 = new HashSet<Element>( list1 );
        set1 = this.children.get( 0 ).interpret( argument );
        // call on the first child's interpret

        List<Element> list2 = new ArrayList<Element>();
        Set<Element> set2 = new HashSet<Element>( list2 );
        set2 = this.children.get( 1 ).interpret( argument );
        // Call on the first child's interpret

        List<Element> unionList = new ArrayList<Element>();
        Set<Element> unionSet = new HashSet<Element>( unionList );
        unionSet = new HashSet<Element>( set1 );
        unionSet.addAll( set2 );
        // Create the set to be returned and add the 2 sets that are returned
        // together.

        Object[] setArray = new Object[unionSet.size()];
        setArray = unionSet.toArray();

        for ( int i = 0; i < unionSet.size(); i++ ) {
            System.out.println( "I have added "
                    + ( (Album) setArray[i] ).getTitle() + " to the OR set." );
        }


        return unionSet;
    }
}
