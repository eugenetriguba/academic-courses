// Eugene Triguba
// ytriguba17@ole.augie.edu
// UpsideDownTriangle.java

import java.util.Scanner;

public class UpsideDownTriangle
{  
    // Input: An integer numberOfLines indicating how many lines you
    //   want the triangle to be. Prompts until you enter an
    //   integer in the range [1-15].
    // Output: prints an upside down triangle numberOfLines lines 
    //   tall to the screen.
  	public static void main(String[] args) 
	{
        System.out.print("Size of triangle [1-15]: ");
        Scanner input = new Scanner(System.in);
        int numberOfLines = input.nextInt();

        while (numberOfLines < 1 || numberOfLines > 15)
        {
            System.out.print("1-15 please: ");
            numberOfLines = input.nextInt();
        }

        for (int row = 0; row < numberOfLines; row++)
        {
            for (int spaces = 0; spaces < row; spaces++)
                System.out.print(" ");

            for (int column = 0; column < numberOfLines - row; column++)
                System.out.print("* ");
            
            System.out.println();
        }
  	}
}