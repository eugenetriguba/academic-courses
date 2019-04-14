// Eugene Triguba
// ytriguba17@ole.augie.edu
// Final: 27-1 Java Collections
// ReverseUsingLinkedList.java

import java.util.LinkedList;
import java.util.Scanner;

public class ReverseUsingLinkedList
{
    public static void main(String[] args) 
    {
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Enter a sequence of integers: ");
        Scanner inputtedInts = new Scanner(keyboard.nextLine());

        LinkedList<Integer> list = new LinkedList<Integer>();
        while(inputtedInts.hasNext())
            list.add(0, inputtedInts.nextInt());
        
        System.out.println(list);
    }
}