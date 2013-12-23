/*
 * Implementation of a simple stack for HtmlTags. You should implement this
 * class.
 */

import java.util.ArrayList;
import java.util.EmptyStackException;

public class StackMP2 {
    // An ArrayList to hold HtmlTag objects.
    // Use this to implement StackMP2.
    private ArrayList<HtmlTag> stack_internal;

    public int StackSize; // monitor size of stack

    /*
     * Default constructor that initialises an empty stack.
     * 
     * @param None.
     * requires: Nothing.
     * 
     * @return Nothing.
     * effects: An empty stack is initialised.
     */
    public StackMP2() {
        this.stack_internal = new ArrayList<HtmlTag>();
    }

    /*
     * Push a tag onto the top of the stack.
     * 
     * @param tag: HtmlTag to be added to stack.
     * requires: Nothing.
     * 
     * @return Nothing.
     * effects: Tag is pushed to the top of the stack.
     * StackSize is increased by one.
     */
    public void push( HtmlTag tag ) {
        stack_internal.add( tag );
    }

    /*
     * Removes the tag at the top of the stack. Should throw an exception if the
     * stack is empty.
     * 
     * @param None.
     * requires: Nothing.
     * 
     * @return HtmlTag
     * effects: Tag at the top of the stack is returned and
     * removed from the stack. StackSize is decreased by one.
     * 
     * @throws EmptyStackException if stack is empty.
     */
    public HtmlTag pop() throws EmptyStackException {
        StackSize = stack_internal.size();

        if ( StackSize > 0 ) {
            HtmlTag popTag = stack_internal.get( StackSize - 1 );
            stack_internal.remove( StackSize - 1 );
            return popTag;
        } else {
            throw new EmptyStackException();
        }
    }

    /*
     * Looks at the object at the top of the stack but does not actually remove
     * the object. Should throw an exception if the stack is empty.
     * 
     * @param None.
     * requires: Nothing.
     * 
     * @return HtmlTag at the top of the stack.
     * effects: HtmlTag is returned and left alone. StackSize is not modified.
     * 
     * @throws EmptyStackException if stack is empty.
     */
    public HtmlTag peek() throws EmptyStackException {
        StackSize = stack_internal.size();

        if ( StackSize > 0 ) {
            HtmlTag peekTag = stack_internal.get( StackSize - 1 );
            return peekTag;
        } else {
            throw new EmptyStackException();
        }
    }

    /*
     * Tests if the stack is empty.
     * 
     * @param None.
     * requires: Nothing.
     * 
     * @return State of the stack.
     * effects: Return true if the stack is empty, false if it is not.
     */
    public boolean isEmpty() {
        StackSize = stack_internal.size();

        if ( StackSize < 1 ) {
            return true;
        } else
            return false;
    }
}
