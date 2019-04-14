// Eugene Triguba
// ytriguba17@ole.augie.edu
// Final: 27-1 Java Collections
// ReverseUsingLinkedList.java

import java.util.Stack;
import java.util.Scanner;

public class ReverseUsingStack
{
    public static void main(String[] args) 
    {
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Enter a sequence of integers: ");
        Scanner inputtedInts = new Scanner(keyboard.nextLine());

        Stack<Integer> stack = new Stack<Integer>();
        while(inputtedInts.hasNext())
            stack.push(inputtedInts.nextInt());

        while (!stack.isEmpty())
            System.out.printf("%d ", stack.pop());
        System.out.println();
    }
}