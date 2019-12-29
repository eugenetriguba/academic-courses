/*
 * Eugene Triguba <ytriguba17@ole.augie.edu>
 *
 * Homework 6: 29-3 MyLinkedList
 * MyLinkedList.java
 */

import java.util.*;
import java.io.*;

/**
 * Emulates Java's Built-in LinkedList class
 */
public class MyLinkedList <T> implements MyList<T>, Cloneable, Serializable
{
    // The number of elements in this list
    protected int listSize;

    // The starting node of this list
    protected DNode<T> header;

    /* Helper Functions */

    /**
     * Checks if index is greater than 0 or
     * less than or equal to upperBound.
     *
     * @param index - the index to check if in range
     * @param upperBound - the upper bound of the index
     * @throws IndexOutOfBoundsException - if index is < 0 or index > upperBound
     */
    protected void rangeCheck(int index, int upperBound)
    {
		String message = String.format("MyLinkedList: Index %d out of bounds. " +
                                       "Should be in range 0 to %d. \n", index, upperBound);
		
        if (index < 0 || index > upperBound)
            throw new IndexOutOfBoundsException(message);
    }

    /**
     * Retrieves the DNode at index and returns it.
     *
     * @param index - the index of the node to be retrieved
     * @return The DNode at position index
     * @throws IndexOutOfBoundsException - if (0 <= index < listSize) is not true
     */
    protected DNode<T> nodeAtIndex(int index)
    {
        rangeCheck(index, listSize - 1);
        DNode<T> node = header;

        for (int i = 0; i < index; i++)
            node = node.next;

        return node;
    }

    /**
     * Removes a the DNode referenced by curr
     * from this list and decrements listSize by 1
     *
     * @param curr - the DNode to remove
     */
    protected void remove(DNode<T> curr)
    {
        DNode<T> prevNode = curr.prev;
        DNode<T> nextNode = curr.next;

        prevNode.next = nextNode;
        nextNode.prev = prevNode;
        listSize--;
    }

    /**
     * Adds a DNode with the value of item to the list before
     * the DNode referenced by curr and increments the size of
     * the list by 1.
     *
     * @param curr - the DNode that you want an item before
     * @param item - the item to add before curr
     * @return the DNode that was added
     */
    protected DNode<T> addBefore(DNode<T> curr, T item)
    {
        DNode<T> newNode = new DNode<>(item);
        DNode<T> prevNode = curr.prev;

        newNode.next = curr;
        newNode.prev = prevNode;
        prevNode.next = newNode;
        curr.prev = newNode;

        listSize++;
        return newNode;
    }

    /* End Helper Functions */


    /**
     * Initializes this MyLinkedList instance by
     * setting the size of the list to 0 and
     * initializing a new dummy node for header
     * to point to.
     */
    public MyLinkedList()
    {
        this.listSize = 0;
        header = new DNode<T>();
    }

    /**
     * Returns the number of elements in this list.
     *
     * @return the number of elements in this list
     */
    public int size()
    {
        return listSize;
    }

    /**
     * Checks if the size of the list is 0.
     *
     * @return true if the size of the list is 0; false otherwise.
     */
    public boolean isEmpty()
    {
        return listSize == 0;
    }

    /**
     * Removes all of the elements from this list.
     * The list will be empty after this call returns.
     * The size of the list is set to 0.
     */
    public void clear()
    {
        this.listSize = 0;
        header.next = header;
        header.prev = header;
    }

    /**
     * Appends the specified element to the end of this list.
     *
     * @param item - element to be appended to this list
     * @return true
     */
    public boolean add(T item)
    {
        addBefore(header, item);
        return true;
    }

    /**
     * Removes the first occurrence of the specified element
     * from this list, if it is present. If this list does
     * not contain the element, it is unchanged.
     *
     * @param item - element to be removed from this list, if present
     * @return true if this list contained the specified element; false otherwise.
     */
    public boolean remove(Object item)
    {
        boolean status = false;
        DNode<T> curr;

        for (curr = header.next; curr != header; curr = curr.next)
            if (item.equals(curr.value)) break;

        if (curr != header)
        {
            remove(curr);
            status = true;
        }

        return status;
    }

    /**
     * Removes the element at the specified position in this list.
     * Returns the element that was removed from the list.
     *
     * @param index - the index of the element to be removed
     * @return the element previously at the specified position
     * @throws IndexOutOfBoundsException - if the index is out of range 
										  (index < 0 || index >= size())
     */
    public T remove(int index)
    {
        rangeCheck(index, listSize - 1);

        DNode<T> node = nodeAtIndex(index);
        remove(node);

        return node.value;
    }

    /**
     * Returns an array containing all of the elements in this list
     * in proper sequence (from first to last element).
     *
     * The returned array will be "safe" in that no references to it
     * are maintained by this list. (In other words, this method must
     * allocate a new array). The caller is thus free to modify the
     * returned array.
     *
     * This method acts as bridge between array-based and collection-based APIs.
     *
     * @return an array containing all of the elements in this list in proper sequence
     */
    public Object[] toArray()
    {
        if (header.next == header) return new Object[listSize];

        Object[] arr = new Object[listSize];
        DNode<T> curr = this.header.next;

        for (int i = 0; i < this.listSize; i++)
        {
            arr[i] = curr.value;
            curr = curr.next;
        }

        return arr;
    }

    /**
     * Inserts the specified element at the specified position in this list.
     *
     * @param index - index at which the specified element is to be inserted
     * @param item - element to be inserted
     */
    public void add(int index, T item)
    {
        rangeCheck(index, listSize);
        DNode<T> node;

        if (index == listSize) node = header;
        else node = nodeAtIndex(index);

        addBefore(node, item);
    }

    /**
     * Inserts the specified element at the beginning of this list.
     *
     * @param item - the element to add
     */
    public void addFirst(T item)
    {
        add(0, item);
    }

    /**
     * Appends the specified element to the end of this list.
     *
     * @param item - the element to add
     */
    public void addLast(T item)
    {
        add(listSize, item);
    }

    /**
     * Returns the index of the first occurrence of the specified
     * element in this list, or -1 if this list does not contain
     * the element.
     *
     * @param item - element to search for
     * @return the index of the first occurrence of the specified element
     *         in this list; -1 if this list does not contain the element.
     */
    public int indexOf(Object item)
    {
        int index = 0;

        for (DNode<T> curr = header.next; curr.next != header; curr = curr.next)
        {
            if (item.equals(curr.value))
                return index;

            index++;
        }

        return -1;
    }

    /**
     * Returns true if this list contains the specified element.
     *
     * @param item - element whose presence in this list is to be tested
     * @return true if this list contains the specified element; false otherwise.
     */
    public boolean contains(Object item)
    {
        return indexOf(item) >= 0 ? true : false;
    }

    /**
     * Returns the element at the specified position in this list.
     *
     * @param item - index of the element to return
     * @return the element at the specified position in this list
     */
    public T get(int index)
    {
        rangeCheck(index, listSize - 1);
        return nodeAtIndex(index).value;
    }

    /**
     * Replaces the element at the specified position in this 
	 * list with the specified element.
     *
     * @param index - index of the element to replace
     * @param item - element to be stored at the specified position
     * @return the element previously at the specified position
     */
    public T set(int index, T item)
    {
        rangeCheck(index, listSize-1);
        DNode<T> node = nodeAtIndex(index);

        T oldValue = node.value;
        node.value = item;

        return oldValue;
    }

    /**
     * Retrieves and removes the first element of this list.
     *
     * @return the value of the head of this list
     * @throws NoSuchElementException - if this list is empty
     */
    public T removeFirst()
    {
        if (listSize == 0)
            throw new NoSuchElementException("MyLinkedList removeFirst(): list empty");

        T value = header.next.value;
        remove(header.next);
        return value;

    }

    /**
     * Retrieves and removes the last element of this list.
     *
     * @return the value of the last element of this list
     */
    public T removeLast()
    {
        if (listSize == 0)
            throw new NoSuchElementException("MyLinkedList removeLast(): list empty");

        T value = header.prev.value;
        remove(header.prev);
        return value;
    }

    /**
     * Retrieves, but does not remove, the first element of this list.
     *
     * @return the first element of this list
     * @throws NoSuchElementException - if this list is empty
     */
    public T getFirst()
    {
        if (listSize == 0) 
			throw new NoSuchElementException("MyLinkedList getFirst(): list empty");
		
        return header.next.value;
    }

    /**
     * Retrieves, but does not remove, the last element of this list.
     *
     * @return the last element of this list
     * @throws NoSuchElementException - if this list is empty
     */
    public T getLast()
    {
        if (listSize == 0) 
			throw new NoSuchElementException("MyLinkedList getLast(): list empty");
		
        return header.prev.value;
    }

    /**
     * Returns a string representation of this list.
     *
     * The string representation consists of an array of
     * the list's elements starting at the first element
     * referenced by the header node to the end of the list,
     * enclosed in square brackets ("[]").
     *
     * Adjacent elements are separated by the characters ", "
     * (comma and space).
     *
     * @return a string representation of this list
     */
    public String toString()
    {
        Object[] arr = toArray();
        if (arr.length == 0) return "[]";

        StringBuilder str = new StringBuilder("[" + arr[0]);
        for (int i = 1; i < arr.length; i++)
        {
            str.append(", ");
            str.append(arr[i]);
        }
        str.append("]");

        return str.toString();
    }

    /**
     * Returns a shallow copy of this LinkedList.
     * (The elements themselves are not cloned.)
     *
     * @return a shallow copy of this MyLinkedList instance
     */
    public Object clone()
    {
        MyLinkedList<T> copy = null;
        try
        {
            copy = (MyLinkedList<T>)super.clone();
        }
        catch (CloneNotSupportedException cnse)
        {
        }

        copy.listSize = 0;
        copy.header = new DNode<T>();
        DNode<T> curr = this.header.next;

        while (curr != header)
        {
            copy.add(curr.value);
            curr = curr.next;
        }

        return copy;
    }
}
