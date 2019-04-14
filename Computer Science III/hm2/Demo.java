// Eugene Triguba
// ytriguba17@ole.augie.edu
// Demo.java

import myJavaLib.util.Conversion;
import java.util.Scanner;

class Demo						
{	

    // Desc: Converts F to C or C to F depending on the user's choice, user
    //       inputs the degree, and the degree is converted and printed to the
    //       screen.
   	public static void main(String[] args)
	{
        int menuChoice = getMenuInput();
        double temp = getTempInput();
        
        printResult(menuChoice, temp);
    }
    
    // Desc: Display a menu and get the choice from the user via the keyboard.
    //       NumberFormatException is not handled.
    // Input: An int via the keyboard corresponding to the individual
    //        menu items
    // Output: Menu items printed to the console
    // Return: an int corresponding to the menu choice
    public static int getMenuInput()
    {
        System.out.println("Temperature Converter Menu");
        System.out.println("1. F to C");
        System.out.println("2. C to F");

        Scanner keyboard = new Scanner(System.in);
        String input = keyboard.nextLine();
        int choice = Integer.parseInt(input);
        keyboard.close();

        return choice;
    }

    // Desc: Display a prompt and to get the temperature via the keyboard.
    //       NumberFormatException is not handled.
    // Input: A Double via the keyboard
    // Output: a prompt to enter in the degree
    // Return: the temperature as a Double
    public static double getTempInput()
    {
        System.out.println("Enter in the degree: ");

        Scanner keyboard = new Scanner(System.in);
        String input = keyboard.nextLine();
        double temp = Double.parseDouble(input);
        keyboard.close();

        return temp;
    }

    // Desc: calculates and prints the result to the screen
    // Output: the inputted degree, and it's corresponding converted degree
    //         printed to the screen
    public static void printResult(int menuChoice, double temp)
    {
        String outputMessage = "";
        int fahToCel = 1;

        if (menuChoice == fahToCel) 
            outputMessage = String.format("%.2fF = %.2fC", temp, Conversion.fahToCel(temp));
        else 
            outputMessage = String.format("%.2fC = %.2fF", temp, Conversion.celToFah(temp));

        System.out.println(outputMessage);
    }
}
