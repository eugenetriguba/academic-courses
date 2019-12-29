/*
 * Eugene Triguba <ytriguba17@ole.augie.edu>
 *
 * Homework 7: 30-1
 * LLStack.java
 */

import java.util.Collection;
import java.util.EmptyStackException;
import java.util.LinkedList;
import java.io.Serializable;

public class LLStack<T> implements Cloneable, Serializable
{
    private LinkedList<T> list;

    /**
     * Stack is initialized to empty
     */
    public LLStack()
    {
        this.list = new LinkedList<T>();
    }

    /**
     * Clears this LLStack's stack
     * of all items
     */
    public void clear()
    {
        list.clear();
    }

    /**
     * Returns the size of the stack
     *
     * @return the number of elements on the stack
     */
    public int size()
    {
        return list.size();
    }

    /**
     * Checks if this stack is empty
     *
     * @return true if this stack has no elements;
     *         false otherwise
     */
    public boolean isEmpty()
    {
        return list.isEmpty();
    }

    /**
     * Add an item on top of the stack
     *
     * @param item - the item to add to the stack
     * @return item
     */
    public T push(T item)
    {
        list.add(item);
        return item;
    }

    /**
     * Removes an item off the top of the stack
     *
     * @return the item that was removed
     * @throws EmptyStackException - if the stack is empty
     */
    public T pop()
    {
        if (isEmpty()) throw new EmptyStackException();
        return list.remove(list.size()-1);
    }

    /**
     * Retrieves, but does not remove, the
     * item on top of the stack
     *
     * @return the item on top of the stack
     * @throws EmptyStackException - if the stack is empty
     */
    public T peek()
    {
        if (isEmpty()) throw new EmptyStackException();
        return list.get(list.size()-1);
    }

    /**
     * Returns a string representation of this stack.
     *
     * The string representation consists of an array of
     * the stack's elements starting at the bottom of the
     * stack to the top, enclosed in square brackets ("[]").
     *
     * Adjacent elements are separated by the characters ", "
     * (comma and space).
     *
     * @return a string representation of this list
     */
    @Override
    public String toString()
    {
        return list.toString();
    }

    /**
     * Returns a shallow copy of this LLStack.
     * (The elements themselves are not cloned.)
     *
     * @return a shallow copy of this LLStack instance
     */
    @Override
    public Object clone()
    {
        LLStack<T> copy = null;
        try
        {
            copy = (LLStack<T>) super.clone();
        }
        catch (CloneNotSupportedException e)
        {
            System.out.println(e.getMessage());
            System.exit(1);
        }

        return copy;
    }
}
