// Eugene Triguba
// ytriguba17@ole.augie.edu
// DoublyLinkedList.java

public class DoublyLinkedList<T>
{
    /**
     * Converts this DoublyLinkedList to a string
     *
     * Pre: header points to the header node of a circular doubly-linked list
     * @return "null" if list is empty; "[data1, data2, …]" otherwise
     */
    public static <T> String toString(DNode<T> header)
    {
        if (header.next == header)
            return "null";

        DNode<T> node = header.next;
        String result = "[" + node.value;
        while (node.next != header)
        {
            node = node.next;
            result += ", " + node.value;
        }
        result += "]";

        return result;
    }

    /**
     * Sequentially search through a linked list
     *
     * Pre: header points to the header node of a
     *      circular doubly-linked list
     *
     * @param header starting node of the list
     * @param key item to be searched for in the list
     * @param <T> T can be any Object
     * @return null if key not in list; the first DNode
     *         that has the value of key otherwise
     */
    public static <T> DNode<T> seqSearch(DNode<T> header, T key)
    {
        DNode<T> node = header.next;
        while (node != header)
        {
            if (node.value.equals(key))
                return node;

            node = node.next;
        }
        return null;
    }

    /**
     * Insert a node with the value of item
     * after node
     *
     * Post: item inserted after the node referenced by node
     *
     * @param node the node to insert after
     * @param item the value of the node to insert after node
     * @param <T> T can be any Object
     * @return the new inserted node
     */
    public static <T> DNode<T> insertAfter(DNode<T> node, T item)
    {
        DNode<T> newNode=new DNode<>(item);

        newNode.next = node.next;
        newNode.prev = node;
        node.next.prev = newNode;
        node.next = newNode;

        return newNode;
    }

    /**
     * Insert a node with the value of item
     * before node
     *
     * @param node the node to insert before
     * @param item the value of the node to insert after node
     * @param <T> T can be any Object
     * @return the new inserted node
     */
    public static <T> DNode<T> insertBefore(DNode<T> node, T item)
    {
        DNode<T> newNode = new DNode<T>(item);

        newNode.next = node;
        newNode.prev = node.prev;
        node.prev.next = newNode;
        node.prev = newNode;

        return newNode;
    }

    /**
     * Remove a node from a list
     *
     * Pre: node points to a data node
     *      in this DoublyLinkedList
     * Post: node removed from list
     *
     * @param node the node to remove
     * @param <T> T can be any Object
     */
    public static <T> void remove(DNode<T> node)
    {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    /**
     * Finds the size of a list
     *
     * Pre: header points to the header node of
     *      a circular doubly-linked list
     *
     * @param header starting node of the list
     * @param <T> T can be any Object
     * @return The number of data nodes in the list
     */
    public static <T> int size(DNode<T> header)
    {
        DNode<T> node = header;
        int size = 0;

        while (node.next != header)
        {
            size++;
            node = node.next;
        }

        return size;
    }

    // Pre: list referenced by header cannot be empty
    // Post: First element removed from list.

    /**
     * Remove the first node from a list
     *
     * Pre: list referenced by header cannot be empty
     * Post: First element removed from list.
     *
     * @param header starting node of the list
     * @param <T> T can be any Object
     */
    public static <T> void removeFirst(DNode<T> header)
    {

        remove(header.next);
    }

    /**
     * Removes the last node from a list
     *
     * Pre: list referenced by header cannot be empty
     * Post: Last element removed from list.
     *
     * @param header starting node of the list
     * @param <T> T can be any Object
     */
    public static <T> void removeLast(DNode<T> header)
    {

        remove(header.prev);
    }

    /**
     * Inserts a node with the value of item
     * in the first position in the list
     *
     * Post: item inserted as first element of list.
     *
     * @param header starting node of the list
     * @param item the value of the node to insert first
     * @param <T> T can be any Object
     */
    public static <T> void insertFirst(DNode<T> header, T item)
    {
        // newNode is the first item in the list
        if (header.next == null)
        {
            DNode<T> newNode = new DNode<>(item);
            newNode.prev = header;
            newNode.next = null;
            header.prev = newNode;
            header.next = newNode;
        }
        else insertAfter(header, item);
    }

    /**
     * Inserts a node with the value of item
     * at the end of a list
     *
     * Post: item inserted as last element of list.
     *
     * @param header starting node of the list
     * @param item the value of the node to insert last
     * @param <T> T can be any Object
     */
    public static <T> void insertLast(DNode<T> header, T item)
    {
        insertBefore(header, item);
    }
}
