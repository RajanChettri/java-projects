package bibliothek210;

import java.util.Calendar;
import java.util.List;
import java.util.ArrayList;

/**
 * 
 * @author Sathish Gopalakrishnan
 *         Modified by Stephen Hu.
 * 
 *         The Library class represents a library,
 *         with a collection of items and users.
 * 
 *         The class includes methods for processing checkouts and returns
 *         and other basic operations.
 * 
 */

public class Library {
    // The list of items in the library.
    protected static List<LibraryHolding> libraryItems;
    // Note: internally, list is used, but when object is constructed, it is in
    // ArrayList type.

    private static List<User> users; // The list of users.
    private static int checkoutCounter = 0;

    /**
     * Default constructor that creates empty item and user lists.
     */
    public Library() {
        libraryItems = new ArrayList<LibraryHolding>();
        users = new ArrayList<User>();
    }

    /**
     * Obtain the number of users.
     * 
     * @return int
     *         the number of users in the library system.
     */
    public int getUserCount() {
        return users.size();
    }

    /**
     * Obtain the string representation of the LibraryHolding item.
     * 
     * @return String
     *         the string representation of the LibraryHolding item.
     */
    public String getStringRepresentation( LibraryHolding a ) {
        String stringRep = "";
        stringRep += a;
        return stringRep;
    }
    
    /**
     * Obtain the string representation of the user.
     * 
     * @return String
     *         the string representation of the user item.
     */
    public String getStringRepresentation( User b ) {
        String stringRep = "";
        stringRep += b;
        return stringRep;
    }

    /**
     * Obtain number of items in library.
     * 
     * @param int
     *        number of items in the library.
     *        
     * @return int
     *         the number of items in the library system.
     */
    public int getItemCount() {
        return libraryItems.size(); // Calls List interface size method.
    }

    /**
     * Add a new user to the list of users.
     * 
     * @modifies users
     *           list in Library class.
     * 
     * @param user
     *        to add to the library user list.
     */
    public void addUser( User user ) {
            users.add( user );
    }

    /**
     * Checks if a user is part of this library system.
     * 
     * @param user
     *        to be tested for.
     *        
     * @return boolean 
     *         true if the user is part of the library's user list,
     *         otherwise return false.
     */
    public boolean isUser( User user ) {
        if ( user.toString().equals ( getStringRepresentation( user ) ))  {
            return users.contains( user ); // Will return false if this user is not in the list.
        }
         else
             return false;
    }

    /**
     * Remove a user from the library's user list.
     * 
     * @modifies users
     *           list in Library class.
     * 
     * @param user
     *        to be removed.
     */
    public void removeUser( User user ) {
        if ( isUser( user ) ) {
            users.remove( user );
        }
    }

    /**
     * Add an item to the library's collection.
     * 
     * @modifies libraryItems
     *           list in Library class.
     * 
     * @param item
     *        to be added.
     */
    public void addItem( LibraryHolding item ) {
        libraryItems.add( item );
    }

    /**
     * Return the number of items that have been checked out.
     * 
     * @return int 
     *         number of checked out items.
     */
    public int getCheckedoutCount() {
        return checkoutCounter;
    }

    /**
     * Obtain the number of library items of the chosen type.
     * 
     * @param contentType
     *        is a string that represents the item type.
     *        
     * @return int 
     *         the number of library items that match the content type.
     */
    public int getContentTypeCount( String contentType ) {
        int n = 0; // Initialise the number of types.
        for ( int i = 0; i < libraryItems.size(); i++ ) { 
            // Loop within the libraryItems list.
            LibraryHolding a = ( libraryItems.get( i ) ); 
            // LibraryHolding a is initialised as the object in index i.
            if ( ( a.holdingType() ).equals( contentType ) ) { 
                // If a is the same type as the parameter, increase the counter n.
                n++;
            }
        }
        return n; // Return the number of contentType in the library.
    }

    /**
     * Process an item checkout.
     * 
     * @param item
     *        being checked out.
     *        
     * @param user
     *        who is checking the item out.
     *           
     * @modifies itemList
     *           of user in User class.  
     *           
     * @modifies status 
     *           of item in LibraryHolding class.
     *        
     * @return boolean 
     *         true if the checkout succeeded, false otherwise.
     *         If the checkout is successful then the state of the item
     *         changes to CheckedOut and the user is added to the item
     *         as its current holder. Similarly the item is added to the
     *         list of items in the user's possession.
     */
    public boolean checkout( LibraryHolding item, User user ) {
        if ( ( libraryItems.contains( item ) ) && ( isUser( user ) ) ) {
            if ( item.checkOut( user ) ) {
                // If checkout boolean is true, add to user's checked out List
                user.addToList( item );
                checkoutCounter++;
                return true;
            }
            else {
                return false;
            }

        } else {
            return false;
        }
    }


    /**
     * Process an item checkout given the item's ID and the user's ID.
     * 
     * @param holdingID
     *        of item to be checked out.
     *        
     * @param userID
     *        of user to checkout item.
     *    
     * @modifies itemList
     *           of user in User class. 
     *           
     * @modifies status 
     *           of item in LibraryHolding class.
     *        
     * @return boolean 
     *         true if the checkout succeeded, false otherwise.
     *         If the checkout is successful then the state of the item
     *         changes to CheckedOut and the user is added to the item
     *         as its current holder. Similarly the item is added to the
     *         list of items in the user's possession.
     */
    public boolean checkout( int holdingID, int userID ) {
        Boolean status = false; // Defaulted to false.
        for ( int i = 0; i < users.size(); i++ ) { 
            // Searches for the correct user.
            
            if ( userID == ( users.get( i ) ).getUserId() ) { 
                // If the user is found then look in libraryItems for the item.
                for ( int j = 0; j < libraryItems.size(); j++ ) { 
                    
                    if ( holdingID == ( libraryItems.get( j ).getHoldingId() ) ) { 
                    // If item is found.
                        
                        if ( isUser( users.get( i ) ) ) { // Is it a user?
                            
                            if ( ( libraryItems.get( j ) ).checkOut( users.get( i ) ) ) { 
                                // Check if the user can checkout the book.
                                ( users.get( i ) ).addToList( libraryItems.get( j ) ); 
                                // Add the book to the user's list.
                                checkoutCounter++; // Increase the checkout counter.
                                status = true; // Successfully checked out.
                            }

                            else {
                                status = false; // failed to checkout the book
                            }
                        }
                    }
                }
            }
        }
        return status; 
        // Return true if successfully checked out the book/dvd -- otherwise, false.
    }


    /**
     * Return a checked out book and remove it from the user's checked out list.
     * 
     * @param item
     *        to be returned.
     *        
     * @modifies itemList
     *           list of user's items in User class.        
     *        
     * @return boolean
     *         true if item returned successfully, false otherwise.
     */
    public boolean processReturn( LibraryHolding item ) {
        User a = item.getCurrentUser();
        Boolean status = false;
        if ( ( libraryItems.contains( item ) ) ) {

            if ( item.processReturn() ) { 
                // If checkout boolean is true, remove from user's checked out List.
                checkoutCounter--;
                a.processReturn( item );
                status = true;
            }
        }
        return status;
    }

    /**
     * Process an item's return given the item's ID
     * 
     * @param holdingID
     *        of item to be returned.
     *        
     * @modifies itemList
     *           list of user's items in User class.
     *
     * @return boolean
     *         true if item successfully returned, false otherwise.
     */
    public boolean processReturn( int holdingID ) {
        Boolean status = false;

        for ( int j = 0; j < libraryItems.size(); j++ ) {
            if ( holdingID == ( libraryItems.get( j ).getHoldingId() ) ) { 
            // Check if item is in library list.
                User a = ( libraryItems.get( j ) ).getCurrentUser();
                if ( ( libraryItems.contains( libraryItems.get( j ) ) ) ) {

                    if ( ( libraryItems.get( j ) ).processReturn() ) { 
                        // If checkout boolean is true, remove from user's checkedout list.
                        checkoutCounter--;
                        a.processReturn( libraryItems.get( j ) );
                        status = true;
                    }
                }
                return status;
            }
        }
        return status;
    }
}
