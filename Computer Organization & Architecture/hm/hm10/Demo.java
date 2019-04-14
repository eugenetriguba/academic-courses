// Eugene Triguba
// ytriguba17@ole.augie.edu
// Demo.java
// Homework 10: Java Bitwise

import java.util.Scanner;

public class Demo
{
    public static void main(String[] args) 
    {
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Enter an integer: ");
        int num = keyboard.nextInt();
        System.out.print("Bit pattern: ");
        BitWise.printbit(num);

        System.out.print("Enter the bit position <0-31> from the "
            + "right you want to clear: ");
        int bitPosition = keyboard.nextInt();
        num = clearBit(num, bitPosition);
        System.out.print("Bit pattern: ");
        BitWise.printbit(num);
        
    }

    // Return: num with the bit at bitPosition set to 0.
    //         if bitPostion is greater than 31 or less
    //         than 0, num is simply returned.
    public static int clearBit(int num, int bitPosition)
    {
        if (bitPosition < 0 || bitPosition > 31) 
            return num;

        int mask = 1;
        mask = mask << bitPosition;
        num = num & ~mask;
        return num;
    }
}