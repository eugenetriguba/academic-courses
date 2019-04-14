// Eugene Triguba
// ytriguba17@ole.augie.edu
// CharacterType.java
import java.util.Scanner;
public class CharacterType
{
    // Input: a character 
    // Output: the character type
    public static void main(String[] args)
    {
        System.out.print("Please enter a character: ");
        Scanner input = new Scanner(System.in);
        String inputtedString = input.next();
        char firstChar = inputtedString.charAt(0);

        System.out.printf("The input character is %s\n", charType(firstChar));
    }

    // Return: Whether the character inputted is a letter,
    //  digit, or non-alphanumeric.
    public static String charType(char c)
    {
        if (Character.isLetter(c)) return "letter";
        else if (Character.isDigit(c)) return "digit";
        else return "non-alphanumeric";
    }
}