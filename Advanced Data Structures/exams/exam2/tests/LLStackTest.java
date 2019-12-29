/*
 * Eugene Triguba <ytriguba17@ole.augie.edu>
 *
 * Homework 7: 30-1
 * LLStackTest.java
 */

import org.junit.jupiter.api.Test;
import java.util.EmptyStackException;
import static org.junit.jupiter.api.Assertions.*;

class LLStackTest
{

    @Test
    void testClearForEmptyStack()
    {
        LLStack<Integer> stack = new LLStack<>();
        stack.push(1);
        stack.clear();

        assertEquals("[]", stack.toString());
    }

    @Test
    void testSizeForNoItems()
    {
        LLStack<Integer> stack = new LLStack<>();
        assertEquals(0, stack.size());
    }

    @Test
    void testSizeForTwoItems()
    {
        LLStack<Integer> stack = new LLStack<>();
        stack.push(1);
        stack.push(5);
        assertEquals(2, stack.size());
    }

    @Test
    void testIsEmptyForTrue()
    {
        LLStack<Integer> stack = new LLStack<>();
        assertTrue(stack.isEmpty());
    }

    @Test
    void testIsEmptyForFalse()
    {
        LLStack<Integer> stack = new LLStack<>();
        stack.push(1);
        assertFalse(stack.isEmpty());
    }

    @Test
    void testPushForItemAdded()
    {
        LLStack<Integer> stack = new LLStack<>();
        stack.push(1);
        assertEquals(1, stack.size());
    }

    @Test
    void testPushForReturnedItem()
    {
        LLStack<Integer> stack = new LLStack<>();
        Integer item = stack.push(1);
        assertEquals(1, item);
    }

    @Test
    void testPopForEmptyStack()
    {
        LLStack<Integer> stack = new LLStack<>();
        assertThrows(EmptyStackException.class, stack::pop);
    }

    @Test
    void testPopForItemRemoved()
    {
        LLStack<Integer> stack = new LLStack<>();
        stack.push(1);
        stack.pop();
        assertEquals(0, stack.size());
    }

    @Test
    void testPopForReturnedItem()
    {
        LLStack<Integer> stack = new LLStack<>();
        stack.push(1);
        assertEquals(1, stack.pop());
    }

    @Test
    void testPeekForEmptyStack()
    {
        LLStack<Integer> stack = new LLStack<>();
        assertThrows(EmptyStackException.class, stack::peek);
    }

    @Test
    void testPeekForReturnedItem()
    {
        LLStack<Integer> stack = new LLStack<>();
        stack.push(1);
        assertEquals(1, stack.peek());
    }

    @Test
    void testToStringWithEmptyStack()
    {
        LLStack<Integer> stack = new LLStack<>();
        assertEquals("[]", stack.toString());
    }

    @Test
    void testToStringForMultipleItems()
    {
        LLStack<Integer> stack = new LLStack<>();
        stack.push(1);
        stack.push(1);
        stack.push(1);
        assertEquals("[1, 1, 1]", stack.toString());
    }

    @Test
    void testCloneForShallowClone()
    {
        LLStack<Integer> parent = new LLStack<>();
        LLStack<Integer> child;

        parent.push(1);
        parent.push(2);

        child = (LLStack<Integer>) parent.clone();
        child.pop();

        assertEquals(child.size(), parent.size());
    }
}