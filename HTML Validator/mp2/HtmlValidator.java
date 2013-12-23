/*
 * This is the HtmlValidator class. You should implement this class.
 */
import java.util.*;
import java.io.*;
import java.lang.StringBuilder;

public class HtmlValidator {

    // Variables

    private Queue<HtmlTag> newTags;
    private String str;

    public StringBuilder validatedTag = new StringBuilder();

    // Constructors

    /*
     * Default constructor that initialises an empty queue.
     * 
     * @param None.
     * requires: Nothing.
     * 
     * @return Nothing.
     * effects: An empty queue is initialised.
     */
    public HtmlValidator() {
        newTags = new LinkedList<HtmlTag>(); // Creates empty queue.
    }


    /*
     * Initialises a queue with the assigned tags from input queue.
     * 
     * @param Queue<HtmlTag> tags
     * requires: tags parameter is a non-null queue.
     * 
     * @return Nothing.
     * effects: A queue with the input tags is initialised.
     * 
     * @throws IllegalArgumentException if tags is null.
     */
    public HtmlValidator(Queue<HtmlTag> tags) throws IllegalArgumentException {
        if ( tags != null ) {
            newTags = new LinkedList<HtmlTag>(); // Creates empty queue.
            newTags.addAll( tags ); // Add all tags from 'tags' to 'newTags'.
            str = tags.toString();
        } else if ( tags == null ) {
            throw new IllegalArgumentException();
        }
    }

    StackMP2 myStack = new StackMP2(); // Creates an empty stack called myStack.

    // Methods

    /*
     * Adds tags to queue.
     * 
     * @param HtmlTag tags
     * requires: Nothing.
     * 
     * @return Nothing.
     * effects: Input HtmlTags are added to the queue.
     */
    public void addTag( HtmlTag tags ) {
        str += tags.toString();
        newTags = HtmlTag.tokenize( str );
    }


    /*
     * Return the tags in the queue.
     * 
     * @param None.
     * requires: Nothing.
     * 
     * @return newTags
     * effects: The tags in the queue are returned
     */
    public Queue<HtmlTag> getTags() {
        return newTags;
    }


    /*
     * Remove the given tags in the queue.
     * 
     * @param None.
     * requires: Nothing.
     * 
     * @return Nothing.
     * effects: All the tags with the given element is removed from the queue.
     */
    public void removeAll( String element ) {
        str = str.replace( "</" + element + ">", "" );
        str = str.replaceAll( "<" + element + ">", "" );
        newTags = HtmlTag.tokenize( str );
    }


    /*
     * Print out the given number of indents.
     * 
     * @param numIndentation
     * requires: Nothing.
     * 
     * @return Nothing.
     * effects: Input number of white spaces are printed out.
     */
    public static void printWhiteSpace( int numIndentation ) {
        for ( int i = 0; i != numIndentation; i++ ) {
            // Keeps printing spaces until the counter reaches the current
            // number of spaces.
            System.out.print( " " );
        }
    }

    
    /*
     * Print out the given number of indents.
     * 
     * @param numIndentation
     * requires: Nothing.
     * 
     * @return Nothing.
     * effects: Input number of white spaces are printed out.
     * 
     * @modifies validatedTag StringBuilder object gets indentation
     * appended to itself.
     */
    public static void printWhiteSpace( int numIndentation, StringBuilder validatedTag ) {
        for ( int i = 0; i != numIndentation; i++ ) {
            // Keeps printing spaces until the counter reaches the current
            // number of spaces.
            System.out.print( " " );
            validatedTag.append( " " );
        }
    }

    
    /*
     * Prints out the given tag.
     * 
     * @param HtmlTag a
     * requires: Nothing.
     * 
     * @return Nothing.
     * effects: Element of the input tag is printed and a new line is created.
     */
    public static void printTag( HtmlTag a ) {
        System.out.println( a );
    }
    
    
    /*
     * Prints out the given tag.
     * 
     * @param HtmlTag a
     * requires: Nothing.
     * 
     * @return Nothing.
     * effects: Element of the input tag is printed and a new line is created.
     * 
     * @modifies validatedTag StringBuilder object gets input tag
     * appended to itself.
     */
    public static void printTag( HtmlTag a, StringBuilder validatedTag ) {
        System.out.println( a );
        validatedTag.append( a );
    }


    /*
     * Validate the queue; indents appropriately if an opening tag is used.
     * Return the output as a string.
     * 
     * @param None.
     * requires: Nothing.
     * 
     * @return String
     * effects: Prints out each tag in a new line. Each non-closing tag is
     * increases level of indentation. The output in the console is returned
     * as a string.
     * 
     * @modifies validatedTag StringBuilder object gets output valid/invalid
     * text result appended to itself.
     */
    public String validate() {

        Iterator<HtmlTag> i = newTags.iterator();
        // Iterator i equals the iterator from newTags.
        int numIndentation = 0;
        // Declares and initialises the counter for the indentations.

        while ( i.hasNext() ) { // While there is another element to in i.
            HtmlTag tempTag = (HtmlTag) i.next();
            // Variable tempTag is assigned the next tag.

            if ( tempTag.isSelfClosing() ) {
                // If the tempTag is a self closing tag.
                printWhiteSpace( numIndentation, validatedTag );
                // Print out the current number of indentations (whitespaces).
                printTag( tempTag, validatedTag ); // Then print out the self closing tag.

            } else if ( !tempTag.isOpenTag() ) { // If tempTag is not an
                                                 // openTag.
                if ( tempTag.matches( myStack.peek() ) ) {
                    // If the tempTag matches the tag on top of the stack.
                    myStack.pop();
                    // Pop the top of the stack (remove the top of the stack).
                    numIndentation -= 4; // Reduce whitespace by 4.
                    printWhiteSpace( numIndentation, validatedTag );
                    // Print out the whitespaces.
                    printTag( tempTag, validatedTag ); // Print out the tag.

                } else if ( !tempTag.matches( myStack.peek() ) ) {
                    // If the tempTag does not match the tag in the stack.
                  
                    System.out.println( "ERROR unexpected tag: " + tempTag.toString() ); // Error message.
                    validatedTag.append( "ERROR unexpected tag: " + tempTag.toString() );
                    String validatedTagString = validatedTag.toString();
                   
                    
                    return validatedTagString;
                }
            } else { // If the tempTag is an open tag.
                printWhiteSpace( numIndentation, validatedTag ); // Print out the whitespaces.
                printTag( tempTag, validatedTag ); // Then print the tag.
                numIndentation += 4; // Then increase the whitespace count by 4.
                myStack.push( tempTag ); // Push the tag into myStack.
            }
        }
        
        while (!i.hasNext() && !myStack.isEmpty()) {
        // If end of HTML file is reached but 
        // there are still unclosed tags in stack.
            System.out.println( "Error unclosed tag: " + myStack.peek() ); // Prints error for unclosed tag
            validatedTag.append( "Error unclosed tag: " + myStack.peek() );
            myStack.pop();  // Removes the tag on the top of the stack and the loop
            // iterates to give the error for the next unclosed tag
            // and goes on until myStack is empty and all errors have been handled  
        }
        
        if ( numIndentation == 0 ) {
            // After checking the queue, and if no errors occurred and the
            // number of indentations equals 0.
            System.out.println( "This Html is Valid." );
            validatedTag.append( "This Html is Valid." );
            String validatedTagString = validatedTag.toString();
            return validatedTagString;
        } else { // If the number of indentations does not equal 0, then the
                 // html is not valid because there was a tag that didn't match.
            System.out.println( "This Html is NOT Valid." );
            validatedTag.append( "This Html is NOT Valid." );
            String validatedTagString = validatedTag.toString();
            return validatedTagString;
        }
    }
}
