package ca.ubc.ece.eece210.mp3;

import java.util.ArrayList;

/**
 * 
 * @author Sathish Gopalakrishnan
 * 
 *         This class contains the information needed to represent
 *         an album in our application.
 * 
 */

public final class Album extends Element {

    public String getMyTitle() {
        return myTitle;
    }

    public void setMyTitle( String myTitle ) {
        this.myTitle = myTitle;
    }

    public String getMyAuthor() {
        return myAuthor;
    }

    public void setMyAuthor( String myAuthor ) {
        this.myAuthor = myAuthor;
    }

    public ArrayList<String> getMySongList() {
        return mySongList;
    }

    public void setMySongList( ArrayList<String> mySongList ) {
        this.mySongList = mySongList;
    }

    public Genre getMyGenre() {
        return myGenre;
    }

    public void setMyGenre( Genre myGenre ) {
        this.myGenre = myGenre;
    }

    private String myTitle;
    private String myAuthor;
    private ArrayList<String> mySongList;
    private Genre myGenre;

    /**
     * Default constructor
     */
    public Album() {
    }

    /**
     * Builds an album with the given title, performer and song list
     * 
     * @param title
     *        the title of the album
     * @param author
     *        the performer
     * @param songlist
     *        the list of songs in the album
     */
    public Album(String title, String performer, ArrayList<String> songlist) {
        // An album may not have children
        myChildren = null;

        myTitle = title;
        myAuthor = performer;
        mySongList = songlist;
        myGenre = null;
    }

    /**
     * Not supported. Use Catalogue.saveCatalogueToFile() instead.
     * 
     * @param stringRepresentation unused
     * 
     * @throws UnsupportedOperationException always
     * 
     * @return the string representation
     */
    public Album(String stringRepresentation) {
        throw new UnsupportedOperationException(
                "Use XMLEncoder in catalogue instead!" );
    }

    /**
     * Not supported. Use Catalogue.saveCatalogueToFile() instead.
     * 
     * @throws UnsupportedOperationException always
     * 
     * @return the string representation
     */
    public String getStringRepresentation() {
        throw new UnsupportedOperationException(
                "Use XMLEncoder in catalogue instead!" );
    }

    /**
     * Add the book to the given genre
     * 
     * @param genre
     *        the genre to add the album to.
     */
    public void addToGenre( Genre genre ) {
        myGenre = genre;
        genre.addChild( this );
    }

    /**
     * Returns the genre that this album belongs to.
     * 
     * @return the genre that this album belongs to
     */
    public Genre getGenre() {
        return myGenre;
    }

    /**
     * Returns the title of the album
     * 
     * @return the title
     */
    public String getTitle() {
        return myTitle;
    }

    /**
     * Returns the performer of the album
     * 
     * @return the performer
     */
    public String getPerformer() {
        return myAuthor;
    }

    /**
     * An album cannot have any children.
     * 
     * @return always false
     */
    @Override
    public boolean hasChildren() {
        return false;
    }
    
    /**
     * Return the hashcode for this class.
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ( ( myAuthor == null ) ? 0 : myAuthor.hashCode() );
        result = prime * result
                + ( ( myTitle == null ) ? 0 : myTitle.hashCode() );
        return result;
    }

    /**
     * Two albums are equal if myAuthor are equal and
     * myTitle are equal
     */
    @Override
    public boolean equals( Object obj ) {
        if ( this == obj )
            return true;
        if ( obj == null )
            return false;
        if ( getClass() != obj.getClass() )
            return false;
        Album other = (Album) obj;
        if ( myAuthor == null ) {
            if ( other.myAuthor != null )
                return false;
        } else if ( !myAuthor.equals( other.myAuthor ) )
            return false;
        if ( myTitle == null ) {
            if ( other.myTitle != null )
                return false;
        } else if ( !myTitle.equals( other.myTitle ) )
            return false;
        return true;
    }
}
