/*
 * Eugene Triguba <ytriguba17@ole.augie.edu>
 * Advanced Programming
 * 35-2 HashMap
 * Demo.java
 */
import java.util.Iterator;

// Test Driver for MyHashMap
public class Demo {
    public static void main(String[] args) {
        MyHashMap<String, Integer> map = new MyHashMap<>();
        for (int i = 0; i < 10; i++) {
            map.put("" + i, i);
        }
        MyHashMap<String, Integer> copy = (MyHashMap<String, Integer>) map.clone();

        System.out.println("Both should have same elements.");
        printMapInfo(map, copy);

        map.put("1", 10);
        copy.remove("5");
        System.out.println("put 1=10 original and removed 5 from copy");
        printMapInfo(map, copy);

        Iterator<MyMap.Entry<String, Integer>> iter = copy.entrySet().iterator();
        while (iter.hasNext()) {
            iter.next();
            iter.remove();
        }
        System.out.println("Removed all items from copy, map should still have all items.");
        printMapInfo(map, copy);
    }

    private static void printMapInfo(MyHashMap<String, Integer> map, MyHashMap<String, Integer> copy) {
        System.out.println("Original map toString(): " + map);
        System.out.println("Original map size(): " + map.size());

        System.out.println("Copy map toString(): " + copy);
        System.out.println("Copy map size(): " + copy.size());
        System.out.println();
    }
}
