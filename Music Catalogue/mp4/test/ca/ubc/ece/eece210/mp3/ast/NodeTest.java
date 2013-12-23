package ca.ubc.ece.eece210.mp3.ast;

import static org.junit.Assert.*;
import ca.ubc.ece.eece210.mp3.ast.QueryTokenizer;
import ca.ubc.ece.eece210.mp3.ast.Token;
import ca.ubc.ece.eece210.mp3.Album;
import ca.ubc.ece.eece210.mp3.Element;
import ca.ubc.ece.eece210.mp3.Catalogue;
import ca.ubc.ece.eece210.mp3.Genre;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.Test;
import java.util.ArrayList;
import org.junit.Before;


/**
 * Tests the implementation of all the nodes. Should add the correct albums to
 * their respective sets.
 * 
 * @author Stephen Hu
 * @T.A. Please note that these tests purely test the implementation of the
 *       nodes and not the AST tree.
 *       View queryCatTest for test cases incorporating the implementation of
 *       the queryCat() method.
 */
public class NodeTest {

    Album grand, inBetweenDreams, sidewalks, theFirstDaysOfSpring,
            lastNightOnEarth, beacon;
    Catalogue cat;
    Genre folk, indie, indie_electronicPop, indie_alternative, indie_rock;

    /**
     * Tests the InNode. Albums satisfying the IN condition should be added to
     * the set.
     */
    @Test
    public void testInNode() {
        setupCatalog();
        List<Element> inList = new ArrayList<Element>();
        Set<Element> inSet = new HashSet<Element>( inList );
        List<Token> tokens = QueryTokenizer.tokenizeInput( "in (\"Folk\")" );
        InNode inNode = new InNode( tokens.get( 2 ) );
        inSet = inNode.interpret( cat );

        assertEquals( inSet.contains( inBetweenDreams ), true );
    }


    /**
     * Tests the ByNode. Albums satisfying the BY condition should be added to
     * the set.
     */
    @Test
    public void testByNode() {
        setupCatalog();
        List<Element> byList = new ArrayList<Element>();
        Set<Element> bySet = new HashSet<Element>( byList );
        List<Token> tokens = QueryTokenizer
                .tokenizeInput( "by (\"Matt and Kim\")" );
        // Matt and Kim has two 
        ByNode byNode = new ByNode( tokens.get( 2 ) );
        bySet = byNode.interpret( cat );

        assertEquals( bySet.contains( grand ), true );
        assertEquals( bySet.contains( sidewalks ), true );
    }


    /**
     * Tests the MatchesNode. Albums satisfying the MATCHES condition should be
     * added to the set.
     */
    @Test
    public void testMatchesNode() {
        setupCatalog();
        List<Element> matchesList = new ArrayList<Element>();
        Set<Element> matchesSet = new HashSet<Element>( matchesList );
        List<Token> tokens = QueryTokenizer
                .tokenizeInput( "matches (\"Sidewalks\")" );
        //Sidewalks is under Indie Electronic Pop subgenre
        MatchesNode matchesNode = new MatchesNode( tokens.get( 2 ) );
        matchesSet = matchesNode.interpret( cat );

        assertEquals( matchesSet.contains( sidewalks ), true );
    }


    /**
     * Tests the AndNode. Albums satisfying the AND condition should be added to
     * the set.
     */
    @Test
    public void testAndNode() { // and node needs to have its children
                                // specified, manually add it now, but let that
                                // be automated with querycat
        setupCatalog();
        List<Element> andList = new ArrayList<Element>();
        Set<Element> andSet = new HashSet<Element>( andList );
        // Indie Rock has two albums, only one is by Noah and the Whale. Noah
        // and the Whale has two albums, one in Indie Rock, the other in Indie
        // Alternative
        List<Token> tokens = QueryTokenizer.tokenizeInput( "in (\"Indie\")"
                + "&& by (\"Noah and the Whale\")" );
        AndNode andNode = new AndNode( new InNode( tokens.get( 2 ) ),
                new ByNode( tokens.get( 7 ) ) );
        andSet = andNode.interpret( cat );

        assertEquals( andSet.contains( lastNightOnEarth ), true );
    }


    /**
     * Tests the OrNode. Albums satisfying the OR condition should be added to
     * the set.
     */
    @Test
    public void testOrNode() {
        setupCatalog();
        List<Element> orList = new ArrayList<Element>();
        Set<Element> orSet = new HashSet<Element>( orList );
        List<Token> tokens = QueryTokenizer
                .tokenizeInput( "by (\"Jack Johnson\")"
                        + "|| matches (\"Sidewalks\")" );
        // Jack Johnson's album is under Folk genre. Sidewalks is under Indie
        // Electronic Pop subgenre
        OrNode orNode = new OrNode( new ByNode( tokens.get( 2 ) ),
                new MatchesNode( tokens.get( 7 ) ) );
        orSet = orNode.interpret( cat );

        assertEquals( orSet.contains( sidewalks ), true );
        assertEquals( orSet.contains( inBetweenDreams ), true );
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
