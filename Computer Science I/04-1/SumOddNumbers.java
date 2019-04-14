// Eugene Triguba
// ytriguba17@ole.augie.edu
// SumOddNumbers.java

import java.util.Scanner;

public class SumOddNumbers
{
    // Desc: read in a positive integer n and display the sum 
    //  of the first n odd positive integers, that is, 1, 3, 5, 7, …, n.
    // Input: positive integer n
    // Output: the sum of the first n odd positive integers
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

        int currentNum = 1;
        int oddNumCount = 0;
        int sum = 0;
        while (oddNumCount < posInt)
        {
            sum += currentNum;
            currentNum += 2;
            oddNumCount++;
        }

        System.out.printf("The sum of the first %d positive odd integers is %d.\n", 
			posInt, sum);
    }
}