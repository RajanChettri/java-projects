package bibliothek210;

import static org.junit.Assert.*;

import org.junit.Test;

public class LibraryTest {
    /**
     * Test number of users is correct.
     */
	@Test
	public void testUserCount( ) {
		Library lib = new Library( );
		User u1 = new User("U1");
		User u2 = new User("U2");
		lib.addUser( u1 );
		lib.addUser( u2 );	
		assertEquals( 2, lib.getUserCount() );
	}
	
	
	/**
     * Test number of checked out items is correct.
     */
	@Test
	public void testCheckedoutCount( ) {
		Library lib = new Library( );
		User u1 = new User("U1");
		User u2 = new User("U2");
		lib.addUser( u1 );
		lib.addUser( u2 );
		
		Book book = new Book ("Test", "Test Author");
		lib.addItem(book);
		lib.checkout(book, u1);
		assertEquals( 1, lib.getCheckedoutCount() );
	}

	
	/**
     * Test number of books is correct.
     */
	@Test
	public void testContentTypeCount1( ) {
		
		Book b1 = new Book ( "Book 1", "Author 1" );
		Book b2 = new Book ( "Book 2", "Author 2" );
		Library lib = new Library( );
		lib.addItem( b1 );
		lib.addItem( b2 );
		
		int n = lib.getContentTypeCount( "Book" );
		
		assertEquals( 2, n );		
	}
	
	
	/**
     * Test number of DVDs is correct.
     */
	@Test
	public void testContentTypeCount2( ) {
		
		Library lib = new Library( );
		Dvd d1 = new Dvd ( "Dvd 1", "Director 1" );
		Dvd d2 = new Dvd ( "Dvd 2", "Director 2" );
		Dvd d3 = new Dvd ( "Dvd 3", "Director 3" );
		lib.addItem( d1 );
		lib.addItem( d2 );
		lib.addItem( d3 );
		int n = lib.getContentTypeCount( "DVD" );
		
		assertEquals( 3, n );		
	}
	
	/**
     * Test number of users is correct.
     */
	@Test
	public void testAddDuplicateUser( ) {
		User u = new User( "Test User" );
		Library lib = new Library( );
		lib.addUser( u );
		lib.addUser( u );
		lib.removeUser( u ); // Will only remove the first duplicate. 
		
		assertEquals( true, lib.isUser( u )); 
		// True because the original non-duplicate is not removed.
	}

	/**
     * Test checking out and returning book with user and item arguments.
     */
	@Test
	public void testCheckoutAndReturn( ) {
		Library lib = new Library( );
		User u = new User( "Test User" );
		LibraryHolding b = new Book ( "Grapes of Wrath", "John Steinbeck" );
		lib.addItem( b );
		lib.addUser( u );
		lib.checkout( b, u );
		assertEquals(2, lib.getCheckedoutCount()); 
		//Should be 2 now because this is the 2nd item checked out in the test.
		assertEquals( true, lib.processReturn(b) );
	}
	
	
	/**
     * Test checking out and returning book using IDs.
     */
	@Test
	public void testCheckoutAndReturnWithID( ) {
		Library lib = new Library( );
		Book book = new Book("Test", "Author");
		User vincent = new User("Vincent");
		int b = book.getHoldingId(), u = vincent.getUserId();
		lib.addItem(book);
		lib.addUser(vincent);
		lib.checkout(b, u);
		int n = lib.getCheckedoutCount();
		assertEquals(n, 2);    
		// Should be 2, since I took out a book and then I took out another book in the previous test but returned it.
		assertEquals( true, lib.processReturn(b) );
	}
	
}
