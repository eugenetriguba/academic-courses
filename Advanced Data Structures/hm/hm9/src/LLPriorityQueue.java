/* 
 * Eugene Triguba <ytriguba17@ole.augie.edu>
 * Homework 9: 31-5 LLPriorityQueue
 */

import java.util.Comparator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.io.Serializable;

/**
 * Supports a minimum priority queue of objects.
 * That is, if x and y are in a LLPriorityQueue 
 * and x.compareTo(y) < 0, then x has higher priority than y.
 */
public class LLPriorityQueue<T> implements Cloneable, Serializable
{
    private LinkedList<T> list;
    private Comparator<? super T> comparator;
       
    /**
     * LLPriorityQueue initialized to empty.
     * Comparator set to null.
     */
    public LLPriorityQueue()
    {
        this(null);

    }

    /**
     * The LLPriorityQueue initialized to empty. 
     * It's elements are ordered according to the specified comparator c.
     * 
     * @param comparator - the comparator you would like to use;
     *                     null to use default compareTo
     */
    public LLPriorityQueue(Comparator<? super T> comparator)
    {
        this.list = new LinkedList<>();
        this.comparator = comparator;
    }
    
    /**
     * Find the size of this LLPriorityQueue
     * 
     * @return number of elements in the LLPriorityQueue
     */
    public int size()
    {
        return this.list.size();
    }

    /**
     * LLPriorityQueue cleared.
     */
    public void clear()
    {
        this.list.clear();
    }

    /**
     * Checks if this LLPriorityQueue is empty
     * 
     * @return true if this LLPriorityQueue is empty; false otherwise.
     */
    public boolean isEmpty()
    {
        return this.list.isEmpty();
    }
    
    /**
     * Inserts an item at the back of the subqueue with 
     * the same priority as the element to be added.  
     * 
     * @param item - the item to be inserted into this LLPriorityQueue
     * @return true
     */
    public boolean offer(T item)
    {
        ListIterator<T> iter = this.list.listIterator();	

        if (this.comparator == null)
        {
            while (iter.hasNext())
                if(((Comparable) item).compareTo(iter.next()) < 0)
                {
                    iter.previous();	
                    break;
                }
        }
        else 
        {
            while (iter.hasNext())
                if (comparator.compare(item, iter.next()) < 0)
                {
                    iter.previous();
                    break;
                }
        }

        iter.add(item);				
        return true;
    }
    
    /**
     * Removes the head of this LLPriorityQueue if it
     * is not empty.
     * 
     * @return The head of this LLPriorityQueue; 
     *         null if this LLPriorityQueue is empty
     */
    public T poll()
    {
        if (isEmpty()) return null;

        return this.list.removeFirst();
    }
    
    /**
     * Retrieves, but does not remove, the head
     * of this LLPriorityQueue.
     * 
     * @return The head of this LLPriorityQueue; 
     *         null if this LLPriorityQueue is empty
     */
    public T peek()
    {
        if (isEmpty()) return null;
        
        return this.list.getFirst();
    }
    
    /**
     * Converts this LLPriorityQueue to a String
     * 
     * @return A comma-separated list of elements enclosed in brackets.
     */
    public String toString()
    {
        return this.list.toString();
    }
    
    /**
     * Clones this LLPriorityQueue
     * 
     * @return A shallow copy of this LLPriorityQueue. 
     */
    public Object clone()
    {
        LLPriorityQueue <T> copy = null;

        try
        {
            copy = (LLPriorityQueue <T>) super.clone();
        }
        catch (CloneNotSupportedException cnse)
        { 
        }
        
        copy.list = (LinkedList<T> )this.list.clone();
        return copy;
    }
}