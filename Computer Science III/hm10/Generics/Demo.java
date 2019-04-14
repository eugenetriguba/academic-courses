// Eugene Triguba
// ytriguba17@ole.augie.edu
// Demo.java
// Homework 10: Backtracking & Generics

import java.util.Vector;

public class Demo
{
    // Desc: Tests the equals method on Time24
    // Output: messages for if various objects are equal (using Time24's equals)
    public static void main(String[] args) 
    {
        Time24 time1 = new Time24(10, 20);
        Time24 time2 = new Time24(10, 20);
        Time24 time3 = new Time24(9, 00);
        Object obj = new Object();
        Vector<String> vec = new Vector<String>();
        vec.add("hello");

        System.out.printf("%s and %s are equal: %b\n", time1, time2, time1.equals(time2));
        System.out.printf("%s and %s are equal: %b\n", time1, time3, time1.equals(time3));
        System.out.printf("%s and %s are equal: %b\n", time1, vec, time1.equals(vec));
        System.out.printf("%s and %s are equal: %b\n", obj, time2, time2.equals(obj));
    }
}