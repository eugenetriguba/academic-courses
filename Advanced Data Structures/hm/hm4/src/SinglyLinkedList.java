// Eugene Triguba
// ytriguba17@ole.augie.edu
// Homework 4: SinglyLinkedList
// SinglyLinkedList.java

public class SinglyLinkedList<T>
{
    /**
     * Converts the values of the nodes in the list
     * to a String
     *
     * Pre: front points to the first node in a linked list,
     *      whose last node must have a null reference
     *
     * @param front the front node of the list
     * @param <T> T can be any object
     * @return "null" if front is null; All the values in the list
     *          in the form [value1, value2, ...];
     */
    public static <T> String toString(Node<T> front)
    {
        if (front == null) return "null";

        Node<T> frontCopy = front;

        String result = "[" + front.value;
        while (frontCopy.next != null)
        {
            frontCopy = frontCopy.next;
            result += ", " + frontCopy.value;
        }
        result += "]";

        return result;
    }

    /**
     * Searches through a linked list for key starting at front
     *
     * Pre: front points to the first node in a linked list,
     *      whose last node must have a null reference
     *
     * @param front the front node of the list
     * @param key the item you want to search for in the list
     * @param <T> T can be any object
     * @return null if key not in list; Otherwise, a reference to the
     *         node in list that matches key
     */
    public static <T> Node<T> seqSearch (Node<T> front, T key)
    {
        Node<T> node = front;
        while (node != null)
        {
            if (node.value.equals(key))
                return node;

            node = node.next;
        }

        return null;
    }

    /**
     * Inserts a new node with a value of item
     * after node
     *
     * Pre:	node cannot be null
     * Post: item inserted after the node referenced
     *       by node
     *
     * @param node the new node will be inserted after this node
     * @param item the value of the new node to be inserted
     * @param <T> T can be any object
     * @return The new node that was inserted
     */
    public static <T> Node<T> insertAfter(Node<T> node, T item)
    {
        Node<T> newNode = new Node<>(item);
        newNode.next = node.next;
        node.next = newNode;
        return newNode;
    }

    /**
     * Remove a node from a linked list
     *
     * Post: the first occurrence of a node with the value of item is
     *       removed from the list, if it exists.
     *
     * @param front the front node of the list
     * @param item the value of the node to be removed
     * @param <T> T can be any object
     * @return Since the first element can be the element removed, front
     *         might change. Thus the method returns front. The caller must
     *         assign the return value to the list pointer.
     */
    public static <T> Node<T> remove(Node<T> front, T item)
    {
        Node<T> curr = front, prev = null;

        while (curr != null)
        {
            if (curr.value.equals(item))
            {
                if (prev == null) front = front.next;
                else prev.next = curr.next;
                break;
            }
            else
            {
                prev = curr;
                curr = curr.next;
            }
        }

        return front;
    }

    /**
     * Gets the node with the largest value
     * from the list
     *
     * @param front the front node of the list
     * @param <T> T can be any object
     * @return The node with the largest value;
     *         null if front is null.
     */
    public static <T extends Comparable<? super T>> Node<T> getMaxNode(Node<T> front)
    {
        if (front == null) return null;

        Node<T> maxNode = front, curr = front.next;

        while (curr != null)
        {
            if (maxNode.value.compareTo(curr.value) < 0)
                maxNode = curr;

            curr = curr.next;
        }

        return maxNode;
    }

    /**
     * Remove the first node from a list
     *
     * Post: First element removed from the
     *       list referenced by front.
     *
     * @param front the front node of the list
     * @param <T> T can be any object
     * @return the updated front reference
     */
    public static <T> Node<T> removeFirst(Node<T> front)
    {
        if (front == null) return null;

        front = front.next;
        return front;
    }

    /**
     * Removes the last item from a linked list
     *
     * Pre: front cannot be null
     * Post: Last element removed from list.
     *
     * @param front the front node of the list
     * @param <T> T can be any object
     * @return Since the first element can be the element removed,
     *         front might change. Thus the method returns front
     */
    public static <T> Node<T> removeLast(Node<T> front)
    {
        if (front.next == null)
        {
            front = null;
            return front;
        }

        Node<T> curr = front.next;
        Node<T> prev = front;

        while (curr.next != null)
        {
            prev = curr;
            curr = curr.next;
        }
        prev.next = null;

        return front;
    }

    /**
     * Inserts an item to the beginning of a linked list
     *
     * Post: item inserted as first element of list.
     *
     * @param front the front node of the list
     * @param item the item to be inserted
     * @param <T> T can be any object
     * @return The updated front reference
     */
    public static <T> Node<T> insertFirst(Node<T> front, T item)
    {
        Node<T> newNode = new Node<>(item);
        newNode.next = front;
        front = newNode;
        return front;
    }

    /**
     * Inserts an item to the end of a linked list
     *
     * Post: item inserted as last element of list.
     *
     * @param front the front node of the list
     * @param item the item to be inserted
     * @param <T> T can be any object
     * @return Since item can be inserted as the first element,
     *         front might change. Thus the method returns front.
     */
    public static <T> Node<T> insertLast(Node<T> front, T item)
    {
        Node<T> newNode = new Node<>(item);

        if (front == null || front.value == null)
        {
            front = newNode;
            return front;
        }

        Node<T> curr = front;
        while (curr.next != null)
        {
            curr = curr.next;
        }
        curr.next = newNode;

        return front;
    }
}
