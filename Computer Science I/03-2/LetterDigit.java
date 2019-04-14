// Eugene Triguba
// ytriguba17@ole.augie.edu
// LetterDigit.java

import java.util.Scanner;

public class LetterDigit
{
	// Desc: Determines the nature of an input character 
	// Input: The user enters a single character via the keyboard.			
	// Output: A message indicating whether the input character is a lowercase 
	//  letter, an uppercase letter, an even digit, an odd digit, or non-alphanumeric.
	public static void main(String[] args)
	{
		System.out.print("Enter a single character: ");

		Scanner input = new Scanner(System.in);
		String inputtedString = input.next();
		char inputChar = inputtedString.charAt(0);
		
		if (Character.isLowerCase(inputChar))
			System.out.println("Character is a lowercase letter."); 
		else if (Character.isUpperCase(inputChar))
			System.out.println("Character is a uppercase letter."); 
		else if (Character.isDigit(inputChar))
		{
			if (inputChar % 2 == 0)
				System.out.println("Character is a even digit."); 
			else
				System.out.println("Character is a odd digit.");
		}
		else
			System.out.println("Character is non-alphanumeric.");
	}
}