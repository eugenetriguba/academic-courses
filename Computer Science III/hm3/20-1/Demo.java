// Eugene Triguba
// ytriguba17@ole.augie.edu
// Demo.java
// Homework 3: 20-1

import java.util.Scanner;
import java.util.InputMismatchException;

class Demo				
{
    // Desc: Prints the sum of two integers entered from the keyboard
	public static void main(String[] args)				
   	{
        System.out.println("Enter 2 integers to sum, one on each line: ");	
        int num1 = getIntFromUser();
        int num2 = getIntFromUser();

        String sumMessage = String.format("The sum of %d and %d is %d", 
			num1, num2, (num1 + num2));
		System.out.println(sumMessage);
    }
    
    // Desc: Grabs an integer via the keyboard by forcing the user to enter a valid integer
    // Input: an integer from the keyboard
    // Output: If an InputMismatchException occurs, a message to the console indicating so.
    // Return: the integer the user inputted
    public static int getIntFromUser()
    {
        Scanner keyboard = new Scanner(System.in);
        int result = 0;

        do
        {
            try
            {
                result = keyboard.nextInt();
                break;
            }
            catch (InputMismatchException e)
            {
                System.out.println("Error: input couldn't be converted to a integer." 
					+ " Please re-enter.");
                keyboard.next();
            }
        } while (true);

        return result;
    }
}
