// Eugene Triguba
// ytriguba17@ole.augie.edu
// Range.java

import java.util.Scanner;

public class Range
{
	// Input: An integer entered via the keyboard
	// Output: A message printed to the screen telling 
	// you if your integer is between 99 and 201.
	public static void main(String[] args)
	{
		System.out.print("Enter an integer: ");
		
		Scanner input = new Scanner(System.in);
		int inputtedNum = input.nextInt();
		
		System.out.println(inputtedNum >= 100 && inputtedNum <= 200 ? 
			"In [100..200]" : "Not in [100..200]");
	}
}