/* Eugene Triguba
   ytriguba17@ole.augie.edu
   InchesToYdFtIn.java */

import java.util.Scanner;

public class InchesToYdFtIn
{
	// Convert a user-inputted amount of inches to 
	// yards, feet, and inches.
	public static void main(String[] args)
	{
		// Grab user input
		System.out.print("Enter a integer for the total inches: ");
		Scanner input = new Scanner(System.in);
		int initialInches = input.nextInt();
		
		// Input validation
		if (initialInches <= 0) {
			System.out.println("Error: total inches should be a positive integer.");
			return;
		}
		
		// Conversions
		int yardInInches = 36;
		int feetInInches = 12;
		
		int totalInches = initialInches;
		
		int totalYards = totalInches / yardInInches;
		totalInches -= totalYards * yardInInches;
		
		int totalFeet = totalInches / feetInInches;
		totalInches -= totalFeet * feetInInches;
		
		System.out.printf("%d inches =  %d yard(s), %d feet, and %d inches\n",
			 initialInches, totalYards, totalFeet, totalInches);
	}
}