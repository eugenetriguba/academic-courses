/*
 * Eugene Triguba <ytriguba17@ole.augie.edu>
 *
 * LLQueue.java
 * Homework 7: 30-1 LLQueue
 */


import java.util.LinkedList;
import java.io.Serializable;

public class LLQueue<T> implements Cloneable, Serializable
{
    private LinkedList<T> list;

    /**
     * Queue initialized to empty.
     */
    public LLQueue()
    {
        this.list = new LinkedList<>();
    }

    /**
     * Queue cleared.
     */
    public void clear()
    {
        list.clear();
    }

    /**
     * Finds the size of the Queue
     *
     * @return number of elements in the Queue
     */
    public int size()
    {
        return list.size();
    }

    /**
     * See if this Queue is empty
     *
     * @return true if empty and false if not empty
     */
    public boolean isEmpty()
    {
        return list.isEmpty();
    }

    /**
     * Inserts an item to the back of the queue
     *
     * @param item - the item to be added
     * @return true
     */
    public boolean offer(T item)
    {
        list.add(item);
        return true;
    }

    /**
     * Removes the head of this queue.
     *
     * @return The head of this queue, or null if this queue is empty
     */
    public T poll()
    {
        if (isEmpty()) return null;
        return list.remove(0);
    }

    /**
     * Retrieves, but does not remove, the head
     * of this queue.
     *
     * @return The head of this queue, or null
     *         if this queue is empty
     */
    public T peek()
    {
        if (isEmpty()) return null;
        return list.get(0);
    }

    /**
     * Converts this Queue to a String
     *
     * @return A comma-separated list of elements
     *         enclosed in brackets
     */
    public String toString()
    {
        return list.toString();
    }

    /**
     * Clones this LLQueue
     *
     * @return A shallow copy of this LLQueue.
     */
    public Object clone()
    {
        LLQueue<T> copy = null;

        try
        {
            copy = (LLQueue<T>) super.clone();
        }
        catch (CloneNotSupportedException cnse)
        {
            System.out.println("Clone is not supported");
            System.exit(1);
        }

        copy.list= (LinkedList<T>) list.clone();

        return copy;
    }
}
