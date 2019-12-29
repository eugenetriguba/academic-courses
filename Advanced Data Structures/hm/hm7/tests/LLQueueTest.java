/*
 * Eugene Triguba <ytriguba17@ole.augie.edu>
 *
 * LLQueueTest.java
 * Homework 7: 30-1 LLQueue
 */

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LLQueueTest
{
    @Test
    void testClearForNoItems()
    {
        LLQueue<Integer> queue = new LLQueue<>();
        queue.offer(1);
        queue.clear();
        assertEquals(0, queue.size());
    }

    @Test
    void testSizeForTwoItems()
    {
        LLQueue<Integer> queue = new LLQueue<>();
        queue.offer(1);
        queue.offer(2);
        assertEquals(2, queue.size());
    }

    @Test
    void testIsEmptyForTrue()
    {
        LLQueue<Integer> queue = new LLQueue<>();
        assertTrue(queue.isEmpty());
    }

    @Test
    void testIsEmptyForFalse()
    {
        LLQueue<Integer> queue = new LLQueue<>();
        queue.offer(1);
        assertFalse(queue.isEmpty());
    }

    @Test
    void testOfferForAddedItem()
    {
        LLQueue<Integer> queue = new LLQueue<>();
        queue.offer(1);
        assertEquals(1, queue.size());
    }

    @Test
    void testPollForRemovedItem()
    {
        LLQueue<Integer> queue = new LLQueue<>();
        queue.offer(1);
        queue.offer(2);
        queue.poll();
        assertEquals(1, queue.size());
    }

    @Test
    void testPeekForCorrectItem()
    {
        LLQueue<Integer> queue = new LLQueue<>();
        queue.offer(1);
        queue.offer(2);
        assertEquals(1, queue.peek());
    }

    @Test
    void testToStringForEmptyQueue()
    {
        LLQueue<Integer> queue = new LLQueue<>();
        assertEquals("[]", queue.toString());
    }

    @Test
    void testToStringForTwoItems()
    {
        LLQueue<Integer> queue = new LLQueue<>();
        queue.offer(1);
        queue.offer(2);
        assertEquals("[1, 2]", queue.toString());
    }

    @Test
    void testCloneForShallowCopy()
    {
        LLQueue<Integer> parent = new LLQueue<>();
        LLQueue<Integer> child;

        parent.offer(1);
        parent.offer(2);

        child = (LLQueue<Integer>) parent.clone();
        child.poll();

        assertEquals(2, parent.size());
    }
}