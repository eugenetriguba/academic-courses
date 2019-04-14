// Eugene Triguba
// ytriguba17@ole.augie.edu
// Sum.java

import java.util.Scanner;

public class Sum
{
    // Desc: read in a positive integer n and display the sum 
    //  of the first n positive integers, that is, 1, 2, 3, …, n.
    // Input: positive integer n
    // Output: the sum of the first n positive integers
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a positive integer: ");
        int posInt = input.nextInt();

        while (posInt <= 0)
        {
            System.out.print("You must enter a positive integer: ");
            posInt = input.nextInt();
        }

        int lcv = 1;
        int sum = 0;
        while (lcv <= posInt)
        {
            sum += lcv;
            lcv++;
        }

        System.out.printf("The sum of integers from 1 to %d is %d.\n", posInt, sum);
    }
}