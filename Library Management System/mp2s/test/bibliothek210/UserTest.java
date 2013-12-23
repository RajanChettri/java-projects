package bibliothek210;

import static org.junit.Assert.*;

import org.junit.Test;

public class UserTest {

	@Test
	/**
	 * Test for getName and getUserId
	 */
	public void testOneUser( ) {
		
		User user = new User( "Sathish" );
		assertEquals( true , user.getName( ).equals("Sathish") );
		// Since Sathish is the first user, userId must be one.
		assertEquals( true , user.getUserId( )==(1)); 
	}
	
	@Test
	/**
	 * Test for addToList and hasItem
	 */
	public void test2( ) {
		
		User user = new User( "Sathish" );
		Book book = new Book( "Test", "Test Author" );
		Book book2 = book;
		user.addToList( book );
		assertEquals( true, user.hasItem( book2 ));
	}

}
