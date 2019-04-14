// Eugene Triguba
// ytriguba17@ole.augie.edu
// LastName.java

import java.util.Scanner;

public class LastName
{
	// Input: Your last name with the first letter capitalized.
	// Output: Whether your last name starts with a vowel or not,
	// printed to the screen.
	public static void main(String[] args)
	{
		System.out.print("Enter your last name: ");
		
		Scanner input = new Scanner(System.in);
		String lastName = input.next();
		char firstLetter = lastName.charAt(0);
		
		if (firstLetter == 'A' || firstLetter == 'E' || firstLetter == 'I' 
			|| firstLetter == 'O' || firstLetter == 'U')
			System.out.println("Your last name starts with a vowel.");
		
		if (!(firstLetter == 'A' || firstLetter == 'E' || firstLetter == 'I' 
			|| firstLetter == 'O' || firstLetter == 'U'))
			System.out.println("Your last name does not start with a vowel");
		
	}
}