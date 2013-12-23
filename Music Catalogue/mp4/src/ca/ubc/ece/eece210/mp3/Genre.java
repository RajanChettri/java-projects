package ca.ubc.ece.eece210.mp3;

import java.util.ArrayList;

/**
 * Represents a genre (or collection of albums/genres).
 * 
 * @author Sathish Gopalakrishnan
 * 
 */
public final class Genre extends Element {
    private String myName;

    /**
     * Empty constructor for XMLEncoder
     */
    public Genre() {
    }

    /**
     * Creates a new genre with the given name.
     * 
     * @param name
     *        the name of the genre.
     */
    public Genre(String name) {
        // A Genre can have children, either sub-genres or albums
        myChildren = new ArrayList<Element>();

        myName = name;
    }

    /**
     * Not implemented. Use Catalogue.saveCatalogueToFile() instead
     * 
     * @param stringRepresentation
     *        unused
     * @throws UnsupportedOperationException
     *         always
     * @return unused
     */
    public static Genre restoreCollection( String stringRepresentation ) {
        throw new UnsupportedOperationException(
                "Using XMLEncoder instead of this." );
    }

    /**
     * Not implemented. Use Catalogue.saveCatalogueToFile() instead
     * 
     * @throws UnsupportedOperationException
     *         always
     * @return unused
     */
    public String getStringRepresentation() {
        throw new UnsupportedOperationException(
                "Using XMLEncoder instead of this." );
    }

    /**
     * Adds the given album or genre to this genre
     * 
     * @param b
     *        is the parent, will receive the caller as its child
     */
    public void addToGenre( Element b ) {
        b.addChild( this );
    }

    public String getMyName() {
        return myName;
    }

    public void setMyName( String myName ) {
        this.myName = myName;
    }

    /**
     * Returns true, since a genre can contain other albums and/or genres.
     */
    @Override
    public boolean hasChildren() {
        return true;
    }

    /**
     * Return the hashcode for this class.
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( myName == null ) ? 0 : myName.hashCode() )
                + ( ( myChildren == null ) ? 0 : myChildren.hashCode() );
        return result;
    }

    /**
     * Two genres are equal if their names are equal, and if their children are
     * equal.
     */
    @Override
    public boolean equals( Object obj ) {
        if ( this == obj )
            return true;
        if ( obj == null )
            return false;
        if ( getClass() != obj.getClass() )
            return false;
        Genre other = (Genre) obj;
        if ( myName == null ) {
            if ( other.myName != null )
                return false;
        } else if ( !myName.equals( other.myName ) )
            return false;
        if ( myChildren == null ) {
            if ( other.myChildren != null )
                return false;
        } else if ( !myChildren.equals( other.myChildren ) )
            return false;

        return true;
    }
}