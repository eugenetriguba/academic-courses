/*
 * Eugene Triguba <ytriguba17@ole.augie.edu>
 * Advanced Data Structures: Final
 * 35-2 HashMap
 * Demo.java
 */

import java.util.Iterator;

public class Demo
{
    public static void main(String[] args)
    {
        MyHashMap<String, Integer> map = new MyHashMap<>();

        for (int i = 0; i < 10; i++)
            map.put("" + i, i);

        MyHashMap<String, Integer> copy = (MyHashMap<String, Integer>) map.clone();

        // Should be the same
        System.out.println(map);
        System.out.println(copy);

        map.put("1", 10);

        // Change should be reflected in both
        System.out.println(map);
        System.out.println(copy);

        // Print out all items
        Iterator<MyMap.Entry<String,Integer>> iter = copy.entrySet().iterator();
        while (iter.hasNext())
            System.out.print(iter.next() + " ");

        System.out.println();

        // Remove all items from copy
        iter = copy.entrySet().iterator();
        MySet<MyMap.Entry<String,Integer>> entrySet = copy.entrySet();
        while (iter.hasNext())
			entrySet.remove(iter.next());

        // map should still have all its items
        // but copy should be empty
        System.out.println(map);
        System.out.println(copy);
    }
}