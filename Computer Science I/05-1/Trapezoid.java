// Eugene Triguba
// ytriguba17@ole.augie.edu
// Trapezoid.java
import java.util.Scanner;
public class Trapezoid
{
    // Desc: Finds the area of a trapezoid from user-inputted dimensions 
    // Input: the top, base, and height of a trapezoid as doubles
    // Output: the dimensions & area of the trapezoid
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        System.out.print("Top: ");
        double top = input.nextDouble();
        System.out.print("Base: ");
        double base = input.nextDouble();
        System.out.print("Height: ");
        double height = input.nextDouble();

        double area = trapezoidArea(top, base, height);
        System.out.printf("Area of a trapezoid with top %.2f, base %.2f,"
            + " and height %.2f is %.2f.\n", top, base, height, area);
    }

    // Return: the area of a trapezoid
    public static double trapezoidArea(double top, double base, double height)
    {
        return (.5 * height * (top + base));
    }
}