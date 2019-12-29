// Eugene Triguba
// ytriguba17@ole.augie.edu
// Homework 1: 27-1 Java Collections
// ReverseUsingLinkedList.java

import java.util.Stack;
import java.util.Scanner;

public class ReverseUsingStack
{
    public static void main(String[] args) 
    {
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Enter a sequence of integers: ");
        Scanner input = new Scanner(keyboard.nextLine());

        Stack<Integer> stack = new Stack<Integer>();
        while(input.hasNext())
            stack.push(input.nextInt());

        while (!stack.isEmpty())
            System.out.printf("%d ", stack.pop());
        System.out.println();
    }
}