// Eugene Triguba
// ytriguba17@ole.augie.edu
// Dice.java
// Homework 5: 7-4 MoreOnClasses

import java.util.Scanner;

// Desc: Test the Dice class by running the toss and accessor methods and 
//   checking the output.
// Output: A prompt to hit the enter key to throw the dice for the outcome.
public class DiceApp
{
	public static void main(String[] args)
	{
		Scanner input = new Scanner(System.in);
		Dice testDice = new Dice();
		
		runTest(input, testDice);
		runTest(input, testDice);
	}
	
	// Desc: Run a test for the Dice object class
	// Post: firstDie and secondDie are randomly set to values in the range [1..6]
	// Output: A prompt to press the enter key to throw the dice, an error message
	//   if a different key is pressed, and an output of the results of that throw.
	public static void runTest(Scanner input, Dice testDice)
	{
		System.out.print("Hit Enter when you are ready to throw your dice.");
		readForEnterKey(input);
		testToss(testDice);
		testAccessors(testDice);
	}
	
	// Desc: Checks if the enter key is pressed by checking if the String 
	//   is still empty. If not, keeps prompting for the enter key to be pressed.
	// Output: If a key other than the enter key is pressed: "Wrong key, press the 
	//   enter key when you're ready."
	public static void readForEnterKey(Scanner input)
	{
		String inputtedKey = input.nextLine();
		while (true)
		{
			if (inputtedKey.isEmpty()) return;
			else 
			{
				System.out.println("Wrong key, press the enter key when you're ready.");
				inputtedKey = input.nextLine();
			}
		}
	}
	
	// Desc: test the Toss method in class Dice by calling toss and printing the output
	// Post: firstDie and secondDie are randomly set to values in the range [1..6]
	// Output: "Throwing...First Die: dieValue, Second Die: dieValue2, Total: totalValue"
	//   where dieValue, dieValue2, and totalValue are replaced with their 
	//   corresponding values.
	public static void testToss(Dice testDice)
	{
		testDice.toss();
		System.out.printf("Throwing...%s\n", testDice.toString());
	}
	
	// Desc: Tests the accessors in class Dice by calling them and printing them 
	//   to the screen.
	// Output: "Testing accessor methods..."
	//		   "First die value: value1"
	//		   "Second die value: value2"
	//		   "Total: value3"
	//   where value1, value2, and value3 are replaced with the corresponding values.
	public static void testAccessors(Dice testDice)
	{
		System.out.println("Testing accessor methods...");
		System.out.printf("First die value: %d\n", testDice.getFirstDieValue());
		System.out.printf("Second die value: %d\n", testDice.getSecondDieValue());
		System.out.printf("Total: %d\n", testDice.getTotal());
	}
}