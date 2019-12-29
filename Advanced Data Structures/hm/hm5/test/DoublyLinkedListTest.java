import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DoublyLinkedListTest
{

    @Test
    void testToStringForNull()
    {
        DNode<String> node = new DNode<>();
        assertEquals("null", DoublyLinkedList.toString(node));
    }

    @Test
    void testToStringForOneValue()
    {
        DNode<String> front = new DNode<>();
        DoublyLinkedList.insertFirst(front, "item1");
        assertEquals("[item1]", DoublyLinkedList.toString(front));
    }

    @Test
    void testToStringForMultipleValues()
    {
        DNode<String> front = new DNode<>();
        DoublyLinkedList.insertFirst(front, "item2");
        DoublyLinkedList.insertFirst(front, "item1");
        assertEquals("[item1, item2]", DoublyLinkedList.toString(front));
    }

    @Test
    void seqSearch()
    {
    }

    @Test
    void insertAfter()
    {
    }

    @Test
    void insertBefore()
    {
    }

    @Test
    void remove()
    {
    }

    @Test
    void testSizeForEmpty()
    {
        DNode<String> front = new DNode<>();
        assertEquals(0, DoublyLinkedList.size(front));
    }

    @Test
    void testSizeForOne()
    {
        DNode<String> header = new DNode<>();
        DoublyLinkedList.insertFirst(header, "item 1");
        assertEquals(1, DoublyLinkedList.size(header));
    }

    @Test
    void testSizeForMultiple()
    {
        DNode<String> header = new DNode<>();
        DoublyLinkedList.insertFirst(header, "item");
        DoublyLinkedList.insertFirst(header, "item");
        DoublyLinkedList.insertFirst(header, "item");
        assertEquals(3, DoublyLinkedList.size(header));
    }

    @Test
    void testRemoveFirstWithNoItems()
    {

    }

    @Test
    void removeLast()
    {
    }

    @Test
    void insertFirst()
    {
    }

    @Test
    void insertLast()
    {
    }
}