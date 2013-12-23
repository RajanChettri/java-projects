package bibliothek210;

import java.util.ArrayList;
import java.util.List;

public class User {
    // A new userID is assigned for each new user object created.
    private static int nextUserId = 1;
    protected final String name;
    protected final int userId;
    protected List<LibraryHolding> itemList;

    /**
     * Constructor for a user object.
     * 
     * @param name
     *        of the user.
     * 
     * @modifies userID
     *           increments the UserId for next user with a new valid id number.
     */
    public User(String name) {
        userId = nextUserId;
        nextUserId++;
        this.name = name;
        itemList = new ArrayList<LibraryHolding>();
    }

    /**
     * Gets the user's name.
     * 
     * @return name
     *         of the user.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the user's identification number.
     * 
     * @return userId
     *         of the user.
     */
    public int getUserId() {
        return this.userId;
    }

    /**
     * Add an item to the user's list of checked out items.
     * 
     * @param item
     *        that is added to the user's list of checked out items.
     * 
     * @modifies itemList
     *           list of items in User class.
     * 
     * @requires item
     *           is available to be checked out to user.
     */
    public void addToList( LibraryHolding item ) {
        if (item != null) {
            itemList.add( item );
        }
        else throw new NullPointerException();
    }

    /**
     * Check if the user has an item.
     * 
     * @param item
     *        item to be checked.
     *        
     * @return boolean
     *        true if the item is in the list and false otherwise.
     */
    public boolean hasItem( LibraryHolding item ) {
        if ( item.toString().equals( getStringRepresentation( item ) ) ) {
            return itemList.contains( item ); 
            // Will return false if this user is not in the list.
        } else
            return false;
    }

    /**
     * Obtain the string representation of the user.
     * 
     * @return String
     *         the string representation of the user item.
     */
    public String getStringRepresentation( LibraryHolding a ) {
        String stringRep = "";
        stringRep += a;
        return stringRep;
    }

    /**
     * Process an item's return for a user by removing the item from
     * the user's list of checked out items.
     * 
     * @param item
     *        that is being returned by the user.
     *        
     * @modifies itemList
     *           list of items in User class.
     *           
     * @modifies status 
     *           of item in LibraryHolding class
     * 
     * @return boolean
     *         true if the item was correctly returned, false otherwise.
     */
    public boolean processReturn( LibraryHolding item ) {
        Boolean currentStatus = false;
        if ( itemList.contains( item )
                && ( item.getStatus() ).equals( "CheckedOut" ) ) {
            itemList.remove( item );
            currentStatus = true;
        }
        return currentStatus;
    }
}
