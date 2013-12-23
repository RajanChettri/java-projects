package ca.ubc.ece.eece210.mp3.ast;
import static org.junit.Assert.*;
import ca.ubc.ece.eece210.mp3.Album;
import ca.ubc.ece.eece210.mp3.Catalogue;
import ca.ubc.ece.eece210.mp3.Genre;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;


/**
 * Tests the implementation of all the nodes and queries. Should add the correct albums to
 * their respective lists.
 * 
 * @author Stephen Hu
 */
public class queryCatTest {
    Album grand, inBetweenDreams, sidewalks, theFirstDaysOfSpring, lastNightOnEarth, beacon;
    Catalogue cat;
    Genre folk, indie, indie_electronicPop, indie_alternative, indie_rock;
    
    
    /**
     * Tests a complex query involving binary nodes and parent nodes.
     */
    @Test
    public void testComplex() {
        setupCatalog();
        String query = "matches (\"In Between Dreams\") && matches (\"In Between Dreams\") || matches (\"Beacon\")";
        List<Album> complexList = cat.queryCat(query);
        // Should only add two albums to the list.
       assertEquals(complexList.contains(inBetweenDreams), true);
       assertEquals(complexList.contains(beacon), true);
    }
    
    /**
     * Tests IN. Albums satisfying the IN condition should be added to
     * the list.
     */
    @Test
    public void testIn() {
        setupCatalog();
        String query = "in (\"Indie Rock\")";
        List<Album> inList = cat.queryCat(query);
        // There are two albums in Indie Rock
        assertEquals(inList.contains(beacon), true);
        assertEquals(inList.contains(lastNightOnEarth), true); 
    }
    
    
    /**
     * Tests BY. Albums satisfying the BY condition should be added to
     * the list.
     */
    @Test
    public void testBy() {
        setupCatalog();
        String query = "by (\"Matt and Kim\")";
        List<Album> byList = cat.queryCat(query);
        // Matt and Kim has two albums under Indie Electronic Pop
        assertEquals(byList.contains(sidewalks), true);
        assertEquals(byList.contains(grand), true);
    }
    
    
    /**
     * Tests MATCHES. Albums satisfying the MATCHES condition should be
     * added to the list.
     */
	@Test
	public void testMatches() {
		setupCatalog();
		String query = "matches (\"In Between Dreams\")";
		List<Album> complexList = cat.queryCat(query);
		// In Between Dreams is in Folk
		assertEquals(complexList.contains(inBetweenDreams), true);     
	}
	
	/**
     * Tests OR. Albums satisfying the OR condition should be added to
     * the list.
     */
	@Test
    public void testOr() {
        setupCatalog();
        String query = "matches (\"Grand\") || matches (\"Beacon\")";
        List<Album> complexList = cat.queryCat(query);
        // Beacon is under Indie Rock, Grand is under Indie Electronic Pop
        assertEquals(complexList.contains(beacon), true);
        assertEquals(complexList.contains(grand), true);
    }
    
	
	/**
     * Tests AND. Albums satisfying the AND condition should be added to
     * the list.
     */
    @Test
    public void testAnd() {
        setupCatalog();
        String query = "matches (\"Sidewalks\") && matches (\"Sidewalks\")";
        List<Album> complexList = cat.queryCat(query);
        // Sidewalks is in Indie Electronic Pop
        // Note that the set implementation automatically removes duplicates.
        assertEquals(complexList.contains(sidewalks), true); 
    }
	

    /**
     * Set up the catalogue with genres, subgenres, and albums.
     */
    @Before
    public void setupCatalog() {
        makeAlbums();

        indie = new Genre( "Indie" );
        folk = new Genre( "Folk" );
        indie_electronicPop = new Genre( "Indie Electronic Pop" );
        indie_alternative = new Genre( "Indie Alternative" );
        indie_rock = new Genre( "Indie Rock" );

        indie_electronicPop.addToGenre( indie );
        indie_alternative.addToGenre( indie );
        indie_rock.addToGenre( indie );

        grand.addToGenre( indie_electronicPop );
        sidewalks.addToGenre( indie_electronicPop );
        inBetweenDreams.addToGenre( folk );
        theFirstDaysOfSpring.addToGenre( indie_alternative );
        lastNightOnEarth.addToGenre( indie_rock );
        beacon.addToGenre( indie_rock );

        cat = new Catalogue();
        cat.getMyGenres().add( indie );
        cat.getMyGenres().add( folk );
    }


    /**
     * Create Grand album with appropriate artist and songs.
     */
    public Album makeGrand() {
        ArrayList<String> songList;
        songList = new ArrayList<String>();
        songList.add( "Daylight" );
        songList.add( "Cutdown" );
        songList.add( "Good Old Fashion Nightmare" );
        songList.add( "Spare Change" );
        songList.add( "I Wanna" );
        songList.add( "Lessons Learned" );
        songList.add( "Don't Slow Down" );
        songList.add( "Turn This Boat Around" );
        songList.add( "Cinders" );
        songList.add( "I'll Take Us Home" );
        Album album = new Album( "Grand", "Matt and Kim", songList );
        return album;
    }


    /**
     * Create In Between Dreams album with appropriate artist and songs.
     */
    public Album makeInBetweenDreams() {
        ArrayList<String> songList;
        songList = new ArrayList<String>();
        songList.add( "Better Together" );
        songList.add( "Never Know" );
        songList.add( "Banana Pancakes" );
        songList.add( "Good People" );
        songList.add( "No Other Way" );
        songList.add( "Sitting, Waiting, Wishing" );
        songList.add( "Staple It Together" );
        songList.add( "Situations" );
        songList.add( "Crying Shame" );
        songList.add( "If I Could" );
        songList.add( "Breakdown" );
        songList.add( "Belle" );
        songList.add( "Do You Remember" );
        songList.add( "Constellations" );
        Album album = new Album( "In Between Dreams", "Jack Johnson", songList );
        return album;
    }


    /**
     * Create Sidewalks album with appropriate artist and songs.
     */
    public Album makeSidewalks() {
        ArrayList<String> songList;
        songList = new ArrayList<String>();
        songList.add( "Block After Block" );
        songList.add( "AM/FM Sound" );
        songList.add( "Cameras" );
        songList.add( "Red Paint" );
        songList.add( "Where You're Coming From" );
        songList.add( "Good for Great" );
        songList.add( "Northeast" );
        songList.add( "Wires" );
        songList.add( "Silver Tiles" );
        songList.add( "Ice Melts" );
        Album album = new Album( "Sidewalks", "Matt and Kim", songList );
        return album;
    }


    /**
     * Create The First Days of Spring album with appropriate artist and songs.
     */
    public Album makeTheFirstDaysOfSpring() {
        ArrayList<String> songList;
        songList = new ArrayList<String>();
        songList.add( "The First Days of Spring" );
        songList.add( "Our Window" );
        songList.add( "I Have Nothing" );
        songList.add( "My Broken Heart" );
        songList.add( "Instrumental I" );
        songList.add( "Love of an Orchestra" );
        songList.add( "Instrumental II" );
        songList.add( "Stranger" );
        songList.add( "Blue Skies" );
        songList.add( "Slow Glass" );
        songList.add( "My Door is Always Open" );
        Album album = new Album( "The First Days of Spring",
                "Noah and the Whale", songList );
        return album;
    }


    /**
     * Create Last Night on Earth album with appropriate artist and songs.
     */
    public Album makeLastNightOnEarth() {
        ArrayList<String> songList;
        songList = new ArrayList<String>();
        songList.add( "Life Is Life" );
        songList.add( "Tonight's the Kind of Night" );
        songList.add( "L.I.F.E.G.O.E.S.O.N." );
        songList.add( "Wild Thing" );
        songList.add( "Give It All Back" );
        songList.add( "Just Me Before We Met" );
        songList.add( "Paradise Stars" );
        songList.add( "Waiting For My Chance to Come" );
        songList.add( "The Line" );
        songList.add( "Old Joy" );
        Album album = new Album( "Last Night on Earth", "Noah and the Whale",
                songList );
        return album;
    }


    /**
     * Create Beacon album with appropriate artist and songs.
     */
    public Album makeBeacon() {
        ArrayList<String> songList;
        songList = new ArrayList<String>();
        songList.add( "Next Year" );
        songList.add( "Handshake" );
        songList.add( "Wake Up" );
        songList.add( "Sun" );
        songList.add( "Someday" );
        songList.add( "Sleep Alone" );
        songList.add( "Paradise Stars" );
        songList.add( "Whe World Is Watching" );
        songList.add( "Settle" );
        songList.add( "Spring" );
        songList.add( "Pyramid" );
        Album album = new Album( "Beacon", "Two Door Cinema Club", songList );
        return album;
    }


    /**
     * Construct all the albums.
     */
    public void makeAlbums() {
        grand = makeGrand();
        inBetweenDreams = makeInBetweenDreams();
        sidewalks = makeSidewalks();
        theFirstDaysOfSpring = makeTheFirstDaysOfSpring();
        lastNightOnEarth = makeLastNightOnEarth();
        beacon = makeBeacon();
    }
}
