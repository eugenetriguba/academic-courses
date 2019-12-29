// Eugene Triguba
// ytriguba@ole.augie.edu
// Exam 1: MyArrayListTest.java

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MyArrayListTest
{
    @Test
    void testToStringForEmpty()
    {
        MyArrayList<Integer> list = new MyArrayList<>();
        assertEquals("[]", list.toString());
    }

    @Test
    void testToStringForCorrectFormat()
    {
        MyArrayList<Integer> list = new MyArrayList<>();
        list.add(5);
        list.add(10);
        assertEquals("[5, 10]", list.toString());
    }

    @Test
    void testIndexOfDoesNotExist()
    {
        MyArrayList<Integer> list = new MyArrayList<>();
        int result = list.indexOf(1);
        assertEquals(-1, result);
    }

    @Test
    void testContainsForFound()
    {
        MyArrayList<Integer> list = new MyArrayList<>();
        list.add(5);
        assertEquals(true, list.contains(5));
    }

    @Test
    void testContainsForNotFound()
    {
        MyArrayList<Integer> list = new MyArrayList<>();
        assertEquals(false, list.contains(5));
    }

    @Test
    void testIsEmptyForTrue()
    {
        MyArrayList<Integer> list = new MyArrayList<>();
        assertEquals(true, list.isEmpty());
    }

    @Test
    void testIsEmptyForFalse()
    {
        MyArrayList<Integer> list = new MyArrayList<>();
        list.add(1);
        assertEquals(false, list.isEmpty());
    }

    @Test
    void testArrayIsCleared()
    {
        MyArrayList<Integer> list = new MyArrayList<>();

        list.add(1);
        list.add(5);
        list.clear();

        assertEquals(0, list.size());
    }

    @Test
    void testToArrayForSameSize()
    {
        MyArrayList<Integer> list = new MyArrayList<>();
        list.add(5);
        list.add(10);
        Object[] objList = list.toArray();

        int objListSize = 0;
        for (int i = 0; i < objList.length; i++)
        {
            if (objList[i] == null) break;
            else objListSize++;
        }

        assertEquals(true, list.size() == objListSize);
    }

    @Test
    void testToArrayForAReturnedObjectArray()
    {
        MyArrayList<Integer> list = new MyArrayList<>();
        Object[] objList = list.toArray();

        assertEquals(true, objList.getClass().getName().equals("[Ljava.lang.Object;"));
    }

    @Test
    void testRemoveForAboveBoundsError()
    {
        MyArrayList<Integer> list = new MyArrayList<>();
        assertThrows(IndexOutOfBoundsException.class, ()-> {
            list.remove(2);
        });
    }

    @Test
    void testRemoveForBelowBoundsError()
    {
        MyArrayList<Integer> list = new MyArrayList<>();
        assertThrows(IndexOutOfBoundsException.class, ()-> {
            list.remove(-2);
        });
    }

    @Test
    void testRemoveForCorrectSize()
    {
        MyArrayList<Integer> list = new MyArrayList<>();
        list.add(1);
        list.remove(0);
        assertEquals(0, list.size());
    }

    @Test
    void testRemoveForTheReturnedElement()
    {
        MyArrayList<Integer> list = new MyArrayList<>();
        list.add(1);
        Integer ele = list.remove(0);
        assertEquals(1, ele);
    }

    @Test
    void testRemoveForTheReturnedBoolean()
    {
        MyArrayList<Integer> list = new MyArrayList<>();
        list.add(1);
        assertEquals(true, list.remove(new Integer(1)));
    }

    @Test
    void testAddForDeletionInMiddle()
    {
        MyArrayList<Integer> list = new MyArrayList<>();

        for (int i = 0; i < 10; i++)
            list.add(i);

        list.remove(5);
        assertEquals(6, list.get(5));
    }

    @Test
    void testAddForInsertionInMiddle()
    {
        MyArrayList<Integer> list = new MyArrayList<>();

        for (int i = 0; i < 10; i++)
            list.add(i);

        list.add(5, 10);
        assertEquals(10, list.get(5));
    }

    @Test
    void testAddForAboveBoundsError()
    {
        MyArrayList<Integer> list = new MyArrayList<>();
        assertThrows(IndexOutOfBoundsException.class, ()-> {
            list.add(2, 20);
        });
    }

    @Test
    void testAddForBelowBoundsError()
    {
        MyArrayList<Integer> list = new MyArrayList<>();
        assertThrows(IndexOutOfBoundsException.class, ()-> {
            list.add(-1, 20);
        });
    }

    @Test
    void testAddForIncreasingCapacity()
    {
        MyArrayList<Integer> list = new MyArrayList<>();
        for (int i = 0; i < 11; i++)
            list.add(1);

        assertEquals(11, list.size());
    }

    @Test
    void testAddForElementAtEndOfList()
    {
        MyArrayList<Integer> list = new MyArrayList<>();
        list.add(2);
        list.add(20);
        list.add(5);
        assertEquals(5, list.get(2));
    }

    @Test
    void testOneArgAddForReturnValue()
    {
        MyArrayList<Integer> list = new MyArrayList<>();
        boolean status = list.add(2);
        assertEquals(true, status);
    }

    @Test
    void testAddForCorrectSize()
    {
        MyArrayList<Integer> list = new MyArrayList<>();
        list.add(1);
        list.add(5);
        assertEquals(2, list.size());
    }

    @Test
    void testGetForSingleElement()
    {
        MyArrayList<Integer> list = new MyArrayList<>();
        list.add(5);

        Integer ele = list.get(0);
        assertEquals(5, ele);
    }

    @Test
    void testGetForBelowBounds()
    {
        MyArrayList<Integer> list = new MyArrayList<>();
        list.add(1);
        assertThrows(IndexOutOfBoundsException.class, ()-> {
            list.get(-1);
        });
    }


    @Test
    void testGetForAboveBounds()
    {
        MyArrayList<Integer> list = new MyArrayList<>();
        list.add(1);
        assertThrows(IndexOutOfBoundsException.class, ()-> {
            list.get(1);
        });
    }

    @Test
    void testSetThatTheElementReplacedIsReturned()
    {
        MyArrayList<Integer> list = new MyArrayList<>();
        list.add(15);

        Integer ele = list.set(0, 5);
        assertEquals(15, ele);
    }

    @Test
    void testSetThatElementIsReplaced()
    {
        MyArrayList<Integer> list = new MyArrayList<>();
        list.add(25);

        Integer ele = list.set(0, 20);
        assertEquals(20, list.get(0));
    }

    @Test
    void testSetAboveBounds()
    {
        MyArrayList<Integer> list = new MyArrayList<>();
        list.add(9);
        assertThrows(IndexOutOfBoundsException.class, ()-> {
            list.set(4, 5);
        });
    }

    @Test
    void testSetBelowBounds()
    {
        MyArrayList<Integer> list = new MyArrayList<>();
        list.add(9);
        assertThrows(IndexOutOfBoundsException.class, ()-> {
            list.set(-1, 5);
        });
    }

    @Test
    void testSetAtEndOfList()
    {
        MyArrayList<Integer> list = new MyArrayList<>();
        list.add(9);
        assertThrows(IndexOutOfBoundsException.class, ()-> {
            list.set(1, 5);
        });
    }
}