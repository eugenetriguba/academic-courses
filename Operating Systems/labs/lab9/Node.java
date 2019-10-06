/*
 * Eugene Triguba <ytriguba17@ole.augie.edu>
 * COSC 310: Operating Systems
 * Homework 9: Deadlock Detection
 */

 /**
  * A node class to model each node in the graph
  * @param <T> T can be any object
  */
public class Node<T>
{
    private T value;
    private Node<T> prev;
    private Node<T> next;
    private int outgoingNodes;

    /**
     * Initializes this Node to null values.
     * outgoingNodes is set to 0.
     */
    public Node()
    {
        this(null, null, null);
    }

    /**
     * Initializes this Node with a value of value.
     * next and prev are set to null.
     * outgoingNodes is set to 0.
     */
    public Node(T value)
    {
        this(value, null, null);
    }

    /**
     * Initializes this Node with the given parameters.
     * outgoingNodes is set to 2 unless prev and/or next is null.
     * Then it is set to 1 or 0 accordingly.
     */
    public Node(T value, Node<T> prev, Node<T> next)
    {
        this.value = value;
        this.prev = prev;
        this.next = next;

        this.outgoingNodes = 0;
        if (this.prev != null) outgoingNodes++;
        if (this.next != null) outgoingNodes++;
    }

    // Retrieve this Node's value
    public T getValue()
    {
        return this.value;
    }

    // Set this Node's value to value.
    public void setValue(T value)
    {
        this.value = value;
    }

    // Retrieve this Node's prev
    public Node<T> getPrev()
    {
        return this.prev;
    }

    // Set this Node's prev to prev.
    // outgoingNodes is incremented if it wasn't previously null.
    public void setPrev(Node<T> prev)
    {
        if (this.prev == null) this.outgoingNodes++;

        this.prev = prev;
    }

    // Retrieve this Node's next
    public Node<T> getNext()
    {
        return this.next;
    }

    // Set this Node's next to next.
    // outgoingNodes is incremented if it wasn't previously null.
    public void setNext(Node<T> next)
    {
        if (this.next == null) this.outgoingNodes++;

        this.next = next;
    }

    // Retrieve this Node's outgoing nodes
    public int getOutgoingNodes()
    {
        return this.outgoingNodes;
    }

    /**
     * Convert a node to a String
     * @return the value of this Node
     */
    public String toString()
    {
        return "" + this.value;
    }

    /**
     * Checks if this Node and item are equal
     * 
     * @return true if this Node and item's values are equal; 
     *         false otherwise.
     */
    public boolean equals(Object item)
    {
        if (item instanceof Node)
        {
            Node<T> node = (Node<T>) item;
            return this.value == node.getValue();
        }

        return false;
    }
}