package ca.ubc.ece.eece210.mp3.ast;

import java.util.HashSet;
import java.util.Set;
import ca.ubc.ece.eece210.mp3.Album;
import ca.ubc.ece.eece210.mp3.Catalogue;
import ca.ubc.ece.eece210.mp3.Element;


/**
 * Searches for albums matching AND criteria.
 * 
 * @author Sathish Gopalakrishnan
 * @modified by Stephen Hu and Vincent Leung
 * 
 */
public class AndNode extends ASTNode {
    public AndNode(Token token) {
        super( token );
    }

    /**
     * Constructor allowing two children to be added to the AND node. Used for
     * the NodeTest, not necessary for actual implementation in queryCat.
     * 
     * @param firstChild, secondChild
     *        child leaf ASTNodes to be added to the AND node
     */
    public AndNode(ASTNode firstChild, ASTNode secondChild) {
        super( firstChild, secondChild );
    }

    /**
     * This method interprets the ANDNode and returns the set of Elements
     * that satisfies the conditions of the ANDNode.
     * 
     * @return Set of Elements that satisfy the criteria indicated by the
     *         ANDNode.
     */
    @Override
    public Set<Element> interpret( Catalogue argument ) {
        // Does not need to be recursive since it's just looking for the
        // intersection of the 2 children(which are recursive).
        Set<Element> set1 = new HashSet<Element>( this.children.get( 0 )
                .interpret( argument ) );

        Set<Element> set2 = new HashSet<Element>( this.children.get( 1 )
                .interpret( argument ) );

        Set<Element> intersectionSet = new HashSet<Element>( set1 );
        intersectionSet.retainAll( set2 );

        Object[] setArray = new Object[intersectionSet.size()];
        setArray = intersectionSet.toArray();
        for ( int i = 0; i < intersectionSet.size(); i++ ) {
            System.out.println( "I have added "
                    + ( (Album) setArray[i] ).getTitle() + " to the AND set." );
        }
        return intersectionSet;
    }
}
