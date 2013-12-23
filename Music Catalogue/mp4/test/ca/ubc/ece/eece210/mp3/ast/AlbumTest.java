package ca.ubc.ece.eece210.mp3.ast;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import ca.ubc.ece.eece210.mp3.ast.Album;
import ca.ubc.ece.eece210.mp3.ast.Catalogue;
import ca.ubc.ece.eece210.mp3.ast.Genre;

public class AlbumTest {

    Album a1, a2, a3;
    Catalogue cat;
    Genre g1, g2;

    public Album makeKingKhanAndBBQ() {
        ArrayList<String> songList;
        songList = new ArrayList<String>();
        songList.add("Waddlin around");
        songList.add("Fish fight");
        songList.add("Too much in love");
        songList.add("Love you so");
        songList.add("Learn my language");
        Album album = new Album("presents", "King Khan and BBQ", songList);

        return album;
    }

    public Album makeWhiteLight() {
        ArrayList<String> songList;
        songList = new ArrayList<String>();
        songList.add("White Light/White Heat");
        songList.add("The Gift");
        songList.add("Lady Godiva's Operation");
        songList.add("Here She Comes Now");
        songList.add("I Heard Her Call My Name");
        songList.add("Sister Ray");
        Album album = new Album("White Light/White Heat",
                "The Velvet Underground", songList);

        return album;
    }

    public Album makeTremendousEfforts() {
        ArrayList<String> songList;
        songList = new ArrayList<String>();
        songList.add("Pass the Chutney");
        songList.add("Loved on Look");
        songList.add("Empty the Chamber");
        songList.add("The Last of the Good");
        songList.add("One Million Songs");
        songList.add("Ridge Runner Rag");
        songList.add("Before I Wake");
        Album album = new Album("Tremendous Efforts", "The Sadies", songList);

        return album;
    }

    public void makeAlbums() {
        a1 = makeKingKhanAndBBQ();
        a2 = makeWhiteLight();
        a3 = makeTremendousEfforts();
    }

    @Before
    public void setupCatalog() {
        makeAlbums();

        g1 = new Genre("Doowop");
        a1.addToGenre(g1);

        g2 = new Genre("Rock");
        g1.addToGenre(g2);

        a2.addToGenre(g2);
        a3.addToGenre(g2);

        cat = new Catalogue();
        cat.myGenres.add(g2);
    }

    @Test
    public void testAddAlbumToGenre() {
        // Test addToGenre
        assertTrue(g1.getChildren().size() == 1);
        assertTrue(g1.getChildren().get(0).equals(a1));

        // Test AlbumEquals
        assertTrue(g1.getChildren().get(0).equals(makeKingKhanAndBBQ()));
    }

    @Test
    // genres equal
    public void testGenre() {
        Genre genre0 = new Genre("Test0");
        Genre genre1 = new Genre("Test1");

        assertFalse(genre0.equals(genre1));

        Genre genre2 = new Genre("Test0");
        genre0.addChild(makeKingKhanAndBBQ());
        genre2.addChild(makeKingKhanAndBBQ());

        assertTrue(genre0.equals(genre2));

        genre2.addChild(makeWhiteLight());
        assertFalse(genre0.equals(genre2));
    }

    @Test
    // albums equal
    public void testAlbum() {
        Album a1 = makeKingKhanAndBBQ();
        Album a2 = makeKingKhanAndBBQ();

        assertTrue(a1.equals(a2));

        Album a3 = makeTremendousEfforts();
        assertFalse(a1.equals(a3));
    }

    @Test(expected = IllegalStateException.class)
    public void testAlbumAddChildException() {
        Album a1 = makeWhiteLight();
        a1.addChild(makeTremendousEfforts());
    }

    @Test
    // Write and read catalogues
    public void sampleTestCase() {
        cat.saveCatalogueToFile("testCatalogue.xml");

        Catalogue cat2 = new Catalogue("testCatalogue.xml");

        assertEquals(cat, cat2);

        cat2.saveCatalogueToFile("testCatalogue2.xml");
        
        cat2.myGenres.get(0).addChild(makeWhiteLight());
        assertFalse (cat.equals(cat2));

        // Test files are equal
        String strCat = null, strCat2 = null;
        try {
            strCat = AlbumTest.readFile("testCatalogue.xml",
                    StandardCharsets.UTF_8);
            strCat2 = AlbumTest.readFile("testCatalogue2.xml",
                    StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            fail();
        }
        assertEquals(strCat, strCat2);
    }

    // Helper method to read file to string
    static String readFile(String path, Charset encoding) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return encoding.decode(ByteBuffer.wrap(encoded)).toString();
    }
}
