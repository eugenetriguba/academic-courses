/*
 * Eugene Triguba <ytriguba17@ole.augie.edu>
 *
 * Homework 10: 32-4 BSIterator
 * Demo.java
 */

import java.util.Iterator;

/*
 * Tests out MyLinkedList's Iterator
 */
public class Demo
{
    public static void main(String[] args) 
    {
        MyLinkedList<Integer> list = new MyLinkedList<>();

        for (int i = 0; i < 20; i++)
            list.add(i);

        Iterator<Integer> iter = list.iterator();

        System.out.print("List Contents: ");
        while (iter.hasNext())
        {
            Integer num = iter.next();

            if (num == 12) iter.remove();
            else System.out.print(num + " ");
        }

        System.out.println();
    }
}