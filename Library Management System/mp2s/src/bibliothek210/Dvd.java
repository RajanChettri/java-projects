package bibliothek210;
/*
 * 
 * Modified version of "Book" By Sathish Gopalakrishnan for Dvd.
 * Modified by : Vincent Leung.
 * 		
 */
public class Dvd extends LibraryHolding {
	
	private String title;
	private String director;
	private static final int checkoutDuration = 7;
	
	/**
	 * Constructor for the Dvd class
	 * Sets the title and author
	 * 
	 * @param title: the book title
	 * @param director: the Dvd's author
	 */
	public Dvd( String title, String director ) {
		super( );
		this.title = title;
		this.director = director;
	}
	
	/**
	 * @return the checkout duration in number of days
	 */
	@Override
	public int getCheckoutDuration( ) {
		return checkoutDuration;
	}
	
	@Override
	public String getStringRepresentation( ) {
		return "<dvd>\n<title>"+title+"</title>\n<director>"+director+"</director>\n</dvd>";
	}
	
	@Override
	public String holdingType( ) {
		return "DVD";
	}
	
}
