/* Eugene Triguba
   ytriguba17@ole.augie.edu
   CelToFah.java */

import java.util.Scanner;

public class CelToFah
{
	// Convert a user-inputted temperature in Celsius to 
	// Fahrenheit and print out the result.
	public static void main(String[] args)
	{
		// Grab user input
		System.out.print("Enter a temperature in Celsius to be converted: ");
		Scanner input = new Scanner(System.in);
		double celsius = input.nextDouble();
		
		// Convert Celsius to Fahrenehit
		double fahrenheit = celsius * (9.0/5.0) + 32.0;
		
		System.out.printf("%.1f degrees in Celsius is %.1f degrees in Fahrenheit.\n",
			 celsius, fahrenheit);
	}
}