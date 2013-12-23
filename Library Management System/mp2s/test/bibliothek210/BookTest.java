package bibliothek210;

import static org.junit.Assert.*;

import org.junit.Test;

public class BookTest {

    /**
     * Test book string representation.
     */
	@Test
	public void testBookStringRep() {
		Book book = new Book( "The Snows of Kilimanjaro", "Ernest Hemingway" );
		assertEquals( true, 
				book.getStringRepresentation().equals("<book>\n<title>The Snows of Kilimanjaro</title>\n<author>Ernest Hemingway</author>\n</book>") );
	}

	/**
     * Test book checkout duration.
     */
	@Test
	public void testBookCheckoutDuration() {
		Book book = new Book( "The Snows of Kilimanjaro", "Ernest Hemingway" );
		assertEquals( 14, book.getCheckoutDuration() );		
	}
	
	/**
     * Test DVD string representation.
     */
	@Test
    public void testDVDStringRep() {
        Dvd dvd = new Dvd( "Aliens", "James Cameron" );
        assertEquals( true, 
                dvd.getStringRepresentation().equals("<dvd>\n<title>Aliens</title>\n<director>James Cameron</director>\n</dvd>") );
    }

	/**
     * Test DVD checkout duration.
     */
	@Test
    public void testDVDCheckoutDuration() {
	    Dvd dvd = new Dvd( "Aliens", "James Cameron" );
        assertEquals( 7, dvd.getCheckoutDuration() );     
    }
	
}
