import static org.junit.Assert.*;

import java.util.EmptyStackException;

import org.junit.Test;

public class StackMP2Test {

    /*
     * Test boundary case if the first item of the stack can be popped
     * correctly.
     * 
     * @param None.
     * 
     * @return Nothing.
     * 
     * @throws EmptyStackException if stack is empty.
     */
    @Test
    public void testFirstItem() {
        StackMP2 stack_internal = new StackMP2();
        HtmlTag pushTagFirst = new HtmlTag( "body", true );
        HtmlTag pushTagSecond = new HtmlTag( "bold", true );
        stack_internal.push( pushTagFirst );
        stack_internal.push( pushTagSecond );
        HtmlTag popTag;
        try {
            popTag = stack_internal.pop();
            assertEquals( new HtmlTag( "bold", true ), popTag );
        } catch ( EmptyStackException exception ) {
            fail( "Stack is empty." );
        }
    }

    /*
     * Test boundary case if the last item of the stack can be popped correctly.
     * 
     * @param None.
     * 
     * @return Nothing.
     * 
     * @throws EmptyStackException if stack is empty.
     */
    @Test
    public void testLastItem() {
        StackMP2 stack_internal = new StackMP2();
        HtmlTag pushTagFirst = new HtmlTag( "body", true );
        HtmlTag pushTagSecond = new HtmlTag( "bold", true );
        stack_internal.push( pushTagFirst );
        stack_internal.push( pushTagSecond );
        HtmlTag popTag;
        try {
            popTag = stack_internal.pop();
            popTag = stack_internal.pop();
            assertEquals( new HtmlTag( "body", true ), popTag );
        } catch ( EmptyStackException exception ) {
            fail( "Stack is empty." );
        }
    }

    /*
     * Test if the middle item of the stack can be popped correctly.
     * 
     * @param None.
     * 
     * @return Nothing.
     * 
     * @throws EmptyStackException if stack is empty.
     */
    @Test
    public void testMiddleItem() {
        StackMP2 stack_internal = new StackMP2();
        HtmlTag pushTagFirst = new HtmlTag( "body", true );
        HtmlTag pushTagSecond = new HtmlTag( "bold", true );
        HtmlTag pushTagThird = new HtmlTag( "table", true );
        HtmlTag pushTagFourth = new HtmlTag( "html", true );
        HtmlTag pushTagFifth = new HtmlTag( "button", true );
        stack_internal.push( pushTagFirst );
        stack_internal.push( pushTagSecond );
        stack_internal.push( pushTagThird );
        stack_internal.push( pushTagFourth );
        stack_internal.push( pushTagFifth );
        HtmlTag popTag;
        try {
            popTag = stack_internal.pop();
            popTag = stack_internal.pop();
            popTag = stack_internal.pop();
            assertEquals( new HtmlTag( "table", true ), popTag );
        } catch ( EmptyStackException exception ) {
            fail( "Stack is empty." );
        }
    }

    /*
     * Test if the the stack can be detected as being empty correctly.
     * 
     * @param None.
     * 
     * @return Nothing.
     * 
     * @throws EmptyStackException if stack is empty.
     */
    @Test
    public void testEmpty() {
        StackMP2 stack_internal = new StackMP2();
        HtmlTag pushTagFirst = new HtmlTag( "body", true );
        HtmlTag pushTagSecond = new HtmlTag( "bold", true );
        stack_internal.push( pushTagFirst );
        stack_internal.push( pushTagSecond );
        HtmlTag popTag;
        try {
            popTag = stack_internal.pop();
            popTag = stack_internal.pop();
            popTag = stack_internal.pop();
        } catch ( EmptyStackException exception ) {
            assertEquals( 0, 0 );
        }
    }
}
