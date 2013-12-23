/**
 * 
 */
package ca.ubc.ece.eece210.mp3.ast;

import static org.junit.Assert.*;
import ca.ubc.ece.eece210.mp3.Album;
import ca.ubc.ece.eece210.mp3.Catalogue;
import ca.ubc.ece.eece210.mp3.Genre;
import ca.ubc.ece.eece210.mp3.ast.ASTNode;
import ca.ubc.ece.eece210.mp3.ast.QueryParser;
import ca.ubc.ece.eece210.mp3.ast.QueryTokenizer;
import ca.ubc.ece.eece210.mp3.ast.Token;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class queryCatTest {
    
    Album grand, inBetweenDreams, sidewalks, theFirstDaysOfSpring, lastNightOnEarth, beacon;
    Catalogue cat;
    Genre folk, indie, indie_electronicPop, indie_alternative, indie_rock;
    
	@Test
	public void testComplex() {
            
		setupCatalog();
		String query = "(matches (\"sidewalks\"))";
		
		//assertEquals(*something_here*, cat.queryCat(query));
        //System.out.println(cat.queryCat(query));
                
	}
	
	@Before
    public void setupCatalog() {
        makeAlbums();

        //System.out.println(grand.getPerformer()); //Matt and Kim
        indie = new Genre("Indie");
        folk = new Genre("Folk");
        indie_electronicPop = new Genre("Indie Electronic Pop");
        indie_alternative = new Genre("Indie Alternative");
        indie_rock = new Genre("Indie Rock");
        
        indie_electronicPop.addToGenre(indie);
        indie_alternative.addToGenre(indie);
        indie_rock.addToGenre(indie);
        
        grand.addToGenre(indie_electronicPop);
        sidewalks.addToGenre(indie_electronicPop);
        inBetweenDreams.addToGenre(folk);
        theFirstDaysOfSpring.addToGenre(indie_alternative);
        lastNightOnEarth.addToGenre(indie_rock);
        beacon.addToGenre(indie_rock);

        //System.out.println(((Album)electronicPop.getChildren().get(0)).getPerformer());
        //System.out.println(electronicPop.getChildren().size());

        //System.out.println((indie.getChildren().get(0)));
        

        cat = new Catalogue();
        cat.getMyGenres().add(indie);
        cat.getMyGenres().add(folk);
        
    }
    

        
        public Album makeGrand() {
            ArrayList<String> songList;
            songList = new ArrayList<String>();
            songList.add("Daylight");
            songList.add("Cutdown");
            songList.add("Good Old Fashion Nightmare");
            songList.add("Spare Change");
            songList.add("I Wanna");
            songList.add("Lessons Learned");
            songList.add("Don't Slow Down");
            songList.add("Turn This Boat Around");
            songList.add("Cinders");
            songList.add("I'll Take Us Home");
            Album album = new Album("Grand", "Matt and Kim", songList);

            return album;
        }

        public Album makeInBetweenDreams() {
            ArrayList<String> songList;
            songList = new ArrayList<String>();
            songList.add("Better Together");
            songList.add("Never Know");
            songList.add("Banana Pancakes");
            songList.add("Good People");
            songList.add("No Other Way");
            songList.add("Sitting, Waiting, Wishing");
            songList.add("Staple It Together");
            songList.add("Situations");
            songList.add("Crying Shame");
            songList.add("If I Could");
            songList.add("Breakdown");
            songList.add("Belle");
            songList.add("Do You Remember");
            songList.add("Constellations");
            Album album = new Album("In Between Dreams",
                    "Jack Johnson", songList);

            return album;
        }
        
        public Album makeSidewalks() {
            ArrayList<String> songList;
            songList = new ArrayList<String>();
            songList.add("Block After Block");
            songList.add("AM/FM Sound");
            songList.add("Cameras");
            songList.add("Red Paint");
            songList.add("Where You're Coming From");
            songList.add("Good for Great");
            songList.add("Northeast");
            songList.add("Wires");
            songList.add("Silver Tiles");
            songList.add("Ice Melts");
            Album album = new Album("Sidewalks",
                    "Matt and Kim", songList);

            return album;
        }

        
        public Album makeTheFirstDaysOfSpring() {
            ArrayList<String> songList;
            songList = new ArrayList<String>();
            songList.add("The First Days of Spring");
            songList.add("Our Window");
            songList.add("I Have Nothing");
            songList.add("My Broken Heart");
            songList.add("Instrumental I");
            songList.add("Love of an Orchestra");
            songList.add("Instrumental II");
            songList.add("Stranger");
            songList.add("Blue Skies");
            songList.add("Slow Glass");
            songList.add("My Door is Always Open");
            Album album = new Album("The First Days of Spring",
                    "Noah and the Whale", songList);

            return album;
        }
        
        public Album makeLastNightOnEarth() {
            ArrayList<String> songList;
            songList = new ArrayList<String>();
            songList.add("Life Is Life");
            songList.add("Tonight's the Kind of Night");
            songList.add("L.I.F.E.G.O.E.S.O.N.");
            songList.add("Wild Thing");
            songList.add("Give It All Back");
            songList.add("Just Me Before We Met");
            songList.add("Paradise Stars");
            songList.add("Waiting For My Chance to Come");
            songList.add("The Line");
            songList.add("Old Joy");
            Album album = new Album("Last Night on Earth",
                    "Noah and the Whale", songList);

            return album;
        }
        
        public Album makeBeacon() {
            ArrayList<String> songList;
            songList = new ArrayList<String>();
            songList.add("Next Year");
            songList.add("Handshake");
            songList.add("Wake Up");
            songList.add("Sun");
            songList.add("Someday");
            songList.add("Sleep Alone");
            songList.add("Paradise Stars");
            songList.add("Whe World Is Watching");
            songList.add("Settle");
            songList.add("Spring");
            songList.add("Pyramid");
            Album album = new Album("Beacon",
                    "Two Door Cinema Club", songList);

            return album;
        }

        public void makeAlbums() {
            grand = makeGrand();
            inBetweenDreams = makeInBetweenDreams();
            sidewalks = makeSidewalks();
            theFirstDaysOfSpring = makeTheFirstDaysOfSpring();
            lastNightOnEarth = makeLastNightOnEarth();
            beacon = makeBeacon();
        }


}
