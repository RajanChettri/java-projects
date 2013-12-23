package ca.ubc.ece.eece210.mp3;

import java.util.List;

/**
 * An abstract class to represent an entity in the catalogue. The element (in
 * this implementation) can either be an album or a genre.
 * 
 * @author Sathish Gopalakrishnan
 * 
 */
public abstract class Element {
    /**
     * Implementing classes with children should use this variable in order to
     * keep track of child instances.
     */
    protected List<Element> myChildren;

    protected Element myParent = null;

    public List<Element> getMyChildren() {
        return myChildren;
    }

    public void setMyChildren( List<Element> myChildren ) {
        this.myChildren = myChildren;
    }

    /**
     * Returns all the children of this entity. They can be albums or genres. In
     * this particular application, only genres can have children. Therefore,
     * this method will return the albums or genres contained in this genre.
     * 
     * @return the children
     */
    public List<Element> getChildren() {
        if ( myChildren == null ) {
            throw new NullPointerException(
                    "I haven't even been initialized yet!" );
        }
        return myChildren;
    }

    /**
     * Adds a child to this entity. Basically, it is adding an album or genre to
     * an existing genre
     * 
     * @param b
     *        the entity to be added.
     */
    public void addChild( Element b ) {
        if ( !hasChildren() ) {
            throw new IllegalStateException( "I'm too young to have children!" );
        }
        if ( myChildren == null ) {
            throw new NullPointerException(
                    "I haven't even been initialized yet!" );
        }
        b.myParent = this;
        myChildren.add( b );

    }

    /**
     * Abstract method to determine if a given entity can (or cannot) contain
     * any children.
     * 
     * @return true if the entity can contain children, or false otherwise.
     */
    public abstract boolean hasChildren();
}