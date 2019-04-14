// Eugene Triguba
// ytriguba17@ole.augie.edu
// ShapeApp.java
// Homework 11: 12 Inheritance

import java.util.Scanner;

public class ShapeApp
{  
    /* A program that allows the user to create a Rectangle or IsoscelesTriangle. 
    ** The program displays a menu asking for 1. Rectangle, 2. IsoscelesTriangle, 
    ** or 3. Quit. The user enters 1 or 2, followed by the dimensions of the shape.
    ** The program draws the shape on the screen followed by the area and perimeter
    ** of the shape, and then asks the user to enter 1, 2, or 3 again. 
    ** The program will continue until the user selects 3 to quit. 
    **
    ** Input: The user enters 1 or 2 followed by the dimensions of the shape via the keyboard.
    ** Output: For each shape entered by the user, the object displayed on the screen 
	**         followed by the area and perimeter of the shape.
    */
	public static void main(String[] args) 
   	{
		Scanner keyboard = new Scanner(System.in);
		int response = 0;
        Shape2D obj;
        
        while (true)
        {
            System.out.print("1. Rectangle, 2. IsoscelesTriangle, 3. Quit: ");
            response = Integer.parseInt(keyboard.nextLine());

            if (response == 3) break;
            if (response == 2) obj = getIsoscelesTriangle(keyboard);
            else obj = getRectangle(keyboard);

            obj.draw();
            System.out.println(obj);
        }
    }
    
    /* Get the dimensions for an IsoscelesTriangle from the user and return a
    ** new IsoscelesTriangle with those dimensions.
    **
    ** Input:  The base and height for the new IsoscelesTriangle object.
    ** Output: Prompts for the base and height.
    ** Return: A new IsoscelesTriangle initialized with the base and height
    **         specified by the user.
    */
    public static IsoscelesTriangle getIsoscelesTriangle(Scanner keyboard)
    {
        System.out.print("Enter base: ");
        double base = Double.parseDouble(keyboard.nextLine());
        System.out.print("Enter height: ");
        double height = Double.parseDouble(keyboard.nextLine());

        return new IsoscelesTriangle(base, height);
    }

    /* Get the dimensions for a Rectangle from the user and return a
    ** new Rectangle with those dimensions.
    **
    ** Input:  The length and width for the new Rectangle object.
    ** Output: Prompts for the base and height.
    ** Return: A new Rectangle initialized with the length and width
    **         specified by the user.
    */
    public static Rectangle getRectangle(Scanner keyboard)
    {
        System.out.print("Enter length: ");
        double length = Double.parseDouble(keyboard.nextLine());
        System.out.print("Enter width: ");
        double width = Double.parseDouble(keyboard.nextLine());

        return new Rectangle(length, width);
    }
}