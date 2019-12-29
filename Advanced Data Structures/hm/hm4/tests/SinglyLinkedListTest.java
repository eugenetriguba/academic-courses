
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SinglyLinkedListTest
{
    @Test
    void testInsertFirstForInsertingItem()
    {
        Node<Integer> front = new Node<>();
        SinglyLinkedList.insertFirst(front, 10);
        assertEquals(10, front.next.value);
    }

    @Test
    void toString()
    {
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
    void remove()
    {
    }

    @Test
    void getMaxNode()
    {
    }

    @Test
    void removeFirst()
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
