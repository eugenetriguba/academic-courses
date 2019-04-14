/* 
 * Eugene Triguba <ytriguba17@ole.augie.edu>
 * AboveBelowAverage.java
 * 
 * Read in n numbers, compute their average, find 
 * out how many numbers are equal to or above the 
 * average, and how many numbers are below the average.
*/

import java.util.Scanner;

public class AboveBelowAverage
{
	/**
	 * Input: The user enters the total number of integers (say, n) followed by n 
	 * 		  integers via the keyboard when prompted.	
	 * 
	 * Output: The average of the n numbers, the total number of numbers that are 
	 *         equal or above the average followed by those numbers, the total number 
	 *         of numbers that are below the average followed by those numbers.
	 */
	public static void main(String[] args)
	{
		Scanner input = new Scanner(System.in);
		System.out.print("How many numbers? ");
		int size = input.nextInt();

		System.out.printf("Enter %d numbers seperated by white space: ", size);
		int inputtedNums[] = new int[size];
		inputtedNums[0] = input.nextInt();

		double average = inputtedNums[0];
		for (int i = 1; i < size; i++)
		{
			inputtedNums[i] = input.nextInt();
			average += inputtedNums[i];
		}
		average /= size;
		System.out.printf("The average is %.1f\n", average);
		
		int largerCount = 0;
		int smallerCount = 0;
		int[] largerNums = new int[size];
		int[] smallerNums = new int[size];
		for (int i = 0; i < inputtedNums.length; i++)
		{
			if (inputtedNums[i] >= average)
			{
				largerNums[largerCount] = inputtedNums[i];
				largerCount++;
			} 
			else 
			{
				smallerNums[smallerCount] = inputtedNums[i];
				smallerCount++;
			}
		}

		System.out.printf("There are %d input numbers >= to the average: %s\n", 
			largerCount, arrayToString(largerNums));
	}

	public static String arrayToString(int[] arr)
	{
		String result = "[";

		if (arr.length >= 1) result += arr[0];
		for (int i = 1; i < arr.length; i++)
			result += String.format(", %d", arr[i]);

		result += "]";
		return result;
	}
}