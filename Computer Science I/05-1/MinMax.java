// Eugene Triguba
// ytriguba17@ole.augie.edu
// MinMax.java
import java.util.Scanner;
public class MinMax
{
    // Desc: Finds the min and max of 3 user-inputted integers
    // Input: 3 integers
    // Output: Max and min of those 3 integers
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        System.out.print("Please enter 3 integers seperated by white space: ");
        int num1 = input.nextInt();
        int num2 = input.nextInt();
        int num3 = input.nextInt();

        int min = findMin(num1, num2, num3);
        int max = findMax(num1, num2, num3);
        System.out.printf("Minimum of %d, %d, and %d is %d\n", num1, num2, 
            num3, min);
        System.out.printf("Maximum of %d, %d, and %d is %d\n", num1, num2, 
            num3, max);
    }

    // Return: Min of x, y, and z
    public static int findMin(int x, int y, int z)
    {
        return Math.min(Math.min(x, y), z);
    }

    // Return: Max of x, y, and z
    public static int findMax(int x, int y, int z)
    {
        return Math.max(Math.max(x, y), z);
    }
}