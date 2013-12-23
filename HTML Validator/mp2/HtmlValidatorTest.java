import static org.junit.Assert.*;

import java.util.EmptyStackException;
import java.util.LinkedList;
import java.util.Queue;

import org.junit.Test;

public class HtmlValidatorTest {

    
    /*
     * Test if validate method outputs correctly based on a valid Html.
     * Also tests if constructor HtmlValidator(Queue<HtmlTag> tags)
     * copies valid queue correctly. printTag and printWhiteSpace are
     * also tested by this test.
     * 
     * @param None.
     * 
     * @return Nothing.
     */ 
    @Test
    public void testValidateValid() {

        String inputString = "<!doctype>, <!-- -->, <html>, <head>, <title>, </title>,"
                       + " <meta>, <link>, </head>, <body>, <p>, <a>, </a>, </p>,"
                       + " <p>, <img>, </p>, </body>, </html>, <i>, </i>";

        HtmlValidator inputTag = new HtmlValidator( HtmlTag.tokenize( inputString ) );

        String expectedString = "<!doctype>"
                              + "<!-- -->"
                              + "<html>"
                              + "    <head>"
                              + "        <title>"
                              + "        </title>"
                              + "        <meta>"
                              + "        <link>"
                              + "    </head>"
                              + "    <body>"
                              + "        <p>"
                              + "            <a>"
                              + "            </a>"
                              + "        </p>"
                              + "        <p>"
                              + "            <img>"
                              + "        </p>"
                              + "    </body>"
                              + "</html>"
                              + "<i>"
                              + "</i>"
                              + "This Html is Valid.";
    
        String outputString = inputTag.validate(); 
        assertEquals( expectedString, outputString ); 
        // validate() method should output matching string.
    }
    
    /*
     * Test if validate method outputs correctly based on an invalid Html.
     * Also tests if constructor HtmlValidator(Queue<HtmlTag> tags)
     * copies valid queue correctly.
     * 
     * @param None.
     * 
     * @return Nothing.
     */
    @Test
    public void testValidateInvalid() {

        String inputString = "<!doctype>, <!-- -->, <html>, <body>, <head>, <title>, </title>,"
                       + " <meta>, <link>, </head>, <body>, <p>, <a>, </a>, </p>,"
                       + " <p>, <img>, </p>, </body>, </html>, <i>, </i>";

        HtmlValidator inputTag = new HtmlValidator( HtmlTag.tokenize( inputString ) );

        String expectedString = "<!doctype>"
                              + "<!-- -->"
                              + "<html>"
                              + "    <body>"
                              + "        <head>"
                              + "            <title>"
                              + "            </title>"
                              + "            <meta>"
                              + "            <link>"
                              + "        </head>"
                              + "        <body>"
                              + "            <p>"
                              + "                <a>"
                              + "                </a>"
                              + "            </p>"
                              + "            <p>"
                              + "                <img>"
                              + "            </p>"
                              + "        </body>"
                              + "ERROR unexpected tag: </html>";
    
        String outputString = inputTag.validate(); 
        assertEquals( expectedString, outputString ); 
        // validate() method should output matching string.
    }
    
    
    /*
     * Test constructor HtmlValidator(Queue<HtmlTag> tags)
     * when null queue is copied
     * 
     * @param None.
     * 
     * @return Nothing.
     * 
     * @throw IllegalArgumentException if inputQueue is null.
     */
    @Test
    public void copyQueueInvalid() {
        Queue<HtmlTag> inputQueue = null;
        try {
            HtmlValidator inputTag = new HtmlValidator( inputQueue );
        } catch ( IllegalArgumentException exception ) {
            assertEquals( 0, 0 );
            System.out.println( "Tags from input queue are invalid." );
        }
    }
    
    
    /*
     * Test when a valid tag is added to queue.
     * 
     * @param None.
     * 
     * @return Nothing.
     */
    @Test
    public void addTagValid()
    {
     // Creates a string of tags, then creates a
        // queue out of the tags and validates them.
        String inputString = "<!doctype>, <!-- -->, <html>, <head>, <title>, </title>,"
                + " <meta>, <link>, </head>, <body>, <p>, <a>, </a>, </p>,"
                + " <p>, <img>, </p>, </html>, <i>, </i>, <body>";

        String expectedOutput = "[<!doctype>, <!-- -->, <html>, <head>, <title>, </title>,"
                + " <meta>, <link>, </head>, <body>, <p>, <a>, </a>, </p>,"
                + " <p>, <img>, </p>, </html>, <i>, </i>, <body>, <head>]";
        
        HtmlValidator inputTag = new HtmlValidator( HtmlTag.tokenize( inputString ) );
        
        inputTag.addTag( HtmlTag.parse( "head" ) );
        // Adds a tag to the bottom of the queue.
        
        Queue<HtmlTag> myTags = inputTag.getTags();
        // Gets the tags of A and puts it into myTags.

        
        String compare = myTags.toString();
        System.out.println( compare );
        
        assertEquals( compare, expectedOutput );
    }
    
    /*
     * Test when a tag is removed from queue.
     * 
     * @param None.
     * 
     * @return Nothing.
     */
    @Test
    public void removeAllValid()
    {
     // Creates a string of tags, then creates a
        // queue out of the tags and validates them.
        String inputString = "<!doctype>, <!-- -->, <html>, <head>, <title>, </title>,"
                + " <meta>, <link>, </head>, <body>, <p>, <a>, </a>, </p>,"
                + " <p>, <img>, </p>, </html>, <i>, </i>, <body>";

        String expectedOutput = "[<!doctype>, <!-- -->, <html>, <head>, <title>, </title>,"
                + " <meta>, <link>, </head>, <body>, <p>, <a>, </a>, </p>,"
                + " <p>, <img>, </p>, </html>, <i>, </i>, <body>]";
        
        HtmlValidator inputTag = new HtmlValidator( HtmlTag.tokenize( inputString ) );
        
        inputTag.addTag( HtmlTag.parse( "button" ) );
        // Adds a tag to the bottom of the queue.
        
        inputTag.removeAll( "button" );
        // Removes all the tags with button in it.
        
        Queue<HtmlTag> myTags = inputTag.getTags();
        // Gets the tags of A and puts it into myTags.

        
        String compare = myTags.toString();
        System.out.println( compare );
        
        assertEquals( compare, expectedOutput );
    }
}

