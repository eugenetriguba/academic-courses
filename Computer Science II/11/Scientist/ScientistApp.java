// Eugene Triguba
// ytriguba17@ole.augie.edu
// ScientistApp.java
// Homework 11: 12 Inheritance

import java.util.Scanner;

public class ScientistApp
{
    /* Reads in attributes of a Scientist via the keyboard, creates a new
    ** Scientist using those inputted attributes, and prints that Scientist to the
    ** screen.
    **
    ** Input: the id, name, salary, and numPub for a new Scientist.
    ** Output: Prompts for id, name, salary, and numPub
    **         Prints out the attributes of the newly created Scientist using
    **         the inputted data.
    */
    public static void main(String[] args)
    {
        Scientist sci = readInScientist();
        System.out.println(sci);
    }

    // Input: the id, name, salary, and numPub for a new Scientist.
    // Output: Prompts for id, name, salary, and numPub
    // Return: a newly created Scientist with the inputted attributes.
    public static Scientist readInScientist()
    {
        Scanner keyboard = new Scanner(System.in);

        System.out.print("Enter id: ");
        int id = Integer.parseInt(keyboard.nextLine());
        System.out.print("Enter name: ");
        String name = keyboard.nextLine();
        System.out.print("Enter salary: ");
        double salary = Double.parseDouble(keyboard.nextLine());
        System.out.print("Enter numPub: ");
        int numPub = Integer.parseInt(keyboard.nextLine());

        return new Scientist(id, name, salary, numPub);
    }
}