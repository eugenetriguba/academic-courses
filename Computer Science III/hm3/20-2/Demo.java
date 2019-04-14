// Eugene Triguba
// ytriguba17@ole.augie.edu
// Demo.java
// Homework 3: 20-2

import java.util.Scanner; 

public class Demo
{
	// Desc: Display up to 5 random integers
	// Input: A number totalElements entered via the keyboard indicating how
	// 		  many random integers
	// Output: totalElements random integers in the range [0..100) displayed on
	// 		   the screen
	public static void main(String[] args)
	{
		int[] randomNums = new int[5];
		int totalElements = 0;
        Scanner keyboard = new Scanner(System.in);	

		while (true)
		{
			try 	
            {
				totalElements = getNumOfElements(randomNums, keyboard);
				printArray(randomNums, totalElements);
				break;
		    }
			catch(NumberFormatException e)
			{
				System.out.println("Must enter an integer!");
			}
			catch(negativeNumberException e)
			{
				System.out.println("negativeNumberException: " 
					+ "A negative number is not a valid input."); 
			}
			catch(zeroException e)
			{
				System.out.println("zeroException: " 
					+ "Zero is not a valid input."); 
			}
			catch(tooBigException e)
			{
				System.out.println("tooBigException: "
					+ "A number greater than 5 is not a valid input."); 
			}
		}
    }

	public static int getNumOfElements(int[] randomNums, Scanner keyboard) 
		throws negativeNumberException, zeroException, tooBigException
	{
		System.out.print("How many random numbers would you like to see? ");
		String input = keyboard.nextLine();
        int numOfElements = Integer.parseInt(input);
        
		if (numOfElements < 0) throw new negativeNumberException();
		else if (numOfElements == 0) throw new zeroException();
		else if (numOfElements > 5) throw new tooBigException();

        
		for (int i = 0; i < numOfElements; ++i)
			randomNums[i] = (int) (Math.random() * 100);
        
		return numOfElements;
    }
	
	// Desc: Print the contents of an array
	// Output: n number of integers in arr in the format: "%d  %d  %d  ..."
	public static void printArray(int[] arr, int n)
	{
		String result = "";
		for(int i = 0; i < n; ++i)
		{
			result += arr[i];
			result += "  ";
		}
		System.out.println(result);
	}
}

// Output: a message to the screen indicating a negative number is not a valid
// 		   input.
class negativeNumberException extends Exception 
{
   	public negativeNumberException()
   	{
      	super("A negative number is not a valid input.");
   	}
}

// Output: a message to the screen indicating zero is not a valid input.
class zeroException extends Exception 
{
   	public zeroException()
   	{
      	super("Zero is not a valid input.");
   	}
}

// Output: a message to the screen indicating the number inputted is too big.
class tooBigException extends Exception 
{
   	public tooBigException()
   	{
      	super("The number inputted is too big.");
   	}
}