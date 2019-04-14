// Eugene Triguba
// ytriguba17@ole.augie.edu
// Homework 9: Recursion
// IntToEng.java

import java.util.Scanner;

public class IntToEng
{
    // Input: a positive integer
    // Output: each number in the integer translated to english words
    //   ex: 123 = "one two three"
    public static void main(String[] args) 
    {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a positive integer: ");
        int num = input.nextInt();
        System.out.println(intToEnglish(num));
    }   

    // Desc: Converts a positive integer to English.  
	// Pre:	n must be positive.  
    // Return:  A string consists of English words of n.  For example, 
    // if n=372, intToEnglish returns the String "three seven two".
    public static String intToEnglish(int n) 
    { 
        if (n < 10)
            switch (n)
            {
                case 0: return "zero ";
                case 1: return "one ";
                case 2: return "two ";
                case 3: return "three ";
                case 4: return "four ";
                case 5: return "five ";
                case 6: return "six ";
                case 7: return "seven ";
                case 8: return "eight ";
                case 9: return "nine ";
            }
        
        return intToEnglish(n / 10) + intToEnglish(n % 10);
    } 
}