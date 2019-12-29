/* 
 * Eugene Triguba <ytriguba17@ole.augie.edu>
 * Homework 9: 31-3 BoundedQueue
 */

import java.io.Serializable;    

/**
 * Supports a Queue of objects with a fixed capacity
 */
public class BoundedQueue <T> implements Cloneable, Serializable
{
   	private int capacity;
    private int front;
    private int back;
    private int size;
    private T[] arr;

    /**
     * BoundedQueue initialized to capacity of p elements with size 0,
     * front equal to 1, and back equal to 0.
     * 
     * @param capacity - the amount of items this BoundedQueue can hold
     */
    public BoundedQueue (int capacity)
   	{
        this.capacity = capacity;
        this.back = 0;
        this.front = 1;
        this.size = 0;
     	this.arr = (T[]) new Object[capacity];
    }


    /**
     * Find the size of this BoundedQueue
     * 
     * @return number of elements in the BoundedQueue
     */
   	public int size()
   	{
		return this.size;
    }

    /**
     * Clears all elements in this BoundedQueue
     */
   	public void clear()
   	{
	    this.size = 0;
    }

    /**
     * Checks if this BoundedQueue is empty
     * 
     * @return true if this BoundedQueue is empty; false otherwise.
     */
   	public boolean isEmpty()
   	{
		return this.size == 0;
    }
       
    /**
     * Checks if this BoundedQueue is full
     * 
     * @return true if this BoundedQueue is full; false otherwise.
     */
    public boolean full()
   	{
		return this.size == this.capacity;
    }
       
    /**
     * Inserts an item to the back of this BoundedQueue
     * if it is not full.
     * 
     * @param item - the item to be inserted into this BoundedQueue
     * @return true if it was possible to add the element to this BoundedQueue; 
     *         Otherwise false.
     */
   	public boolean offer(T item)
   	{
        if (full()) return false;
        
		this.back = (this.back + 1) % this.capacity;
        this.arr[this.back] = item;
        size++;

		return true;
    }
       
    /**
     * Removes the head of this BoundedQueue if it
     * is not empty.
     * 
     * @return The head of this BoundedQueue; null if this BoundedQueue is empty
     */
   	public T poll()
   	{
        if (isEmpty()) return null;

        T item = this.arr[front];
        this.front = (front + 1) % capacity;
        size--;
           
		return item;
    }
       
    /**
     * Retrieves, but does not remove, the head
     * of this BoundedQueue.
     * 
     * @return The head of this BoundedQueue; null if this BoundedQueue is empty
     */
   	public T peek()
   	{
        if (isEmpty()) return null;
        
		return this.arr[front];
    }
       
    /**
     * Converts this BoundedQueue to a String
     * 
     * @return A comma-separated list of elements enclosed in brackets.
     */
    public String toString()
   	{
        if (isEmpty()) return "[]";
        
        StringBuilder strBuilder = new StringBuilder("[" + arr[front]);
        for (int i = front + 1; i <= back; i++)
        {
            strBuilder.append(", ");
            strBuilder.append(arr[i]);
        }   
        strBuilder.append("]");
        
        return strBuilder.toString();
    }
       
    /**
     * Clones this BoundedQueue
     * 
     * @return A shallow copy of this BoundedQueue. 
     */
	public Object clone()
	{
		BoundedQueue <T>copy = null;
		try
		{
			copy = (BoundedQueue <T>)super.clone();
		}
		catch (CloneNotSupportedException cnse)
		{ 
        }

		copy.arr = (T[]) this.arr.clone();	
		return copy;
	}
}