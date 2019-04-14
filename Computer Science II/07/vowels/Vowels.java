// Eugene Triguba
// ytriguba17@ole.augie.edu
// Vowels.java
// Homework 7: 08 String

import java.util.Scanner;

public class Vowels
{

    // Desc: Reads a line from the keyboard and displays the number of vowels in
    //   the input line. The program continues to process another line and so on
    //   until the user selects to quit. 
    // Input: Repeatedly, a line entered from the keyboard followed by either
    //   'y' or 'n' to repeat. 
    // Output: The number of each vowel (lowercase and uppercase combined) in
    //   the input line just read printed on the screen, along with appropriate
    //   prompt messages.
    public static void main(String[] args)
    {
        Scanner keyboard = new Scanner(System.in);
        boolean continuePlaying = true;

        while (continuePlaying)
        {
            System.out.print("Enter a line: ");
            String input = keyboard.nextLine().toLowerCase().trim();
            
            int[] totalVowels = numOfVowelsIn(input);
            printTotalVowels(totalVowels);

            System.out.print("Again (y/n): ");
            char answer = keyboard.nextLine().trim().charAt(0);
            if (Character.toLowerCase(answer) == 'n') continuePlaying = false;
        }

        keyboard.close();
    }

    // Desc: loops through every token in phrase keeping track of how many of
    //   each vowel there is in phrase.
    // Return: an int array containing the number of each vowel contained in
    //   phrase ordered alphabetically.
    //   e.g. [5, 5, 3, 4, 6] would mean: 5 a's, 5 e's, 3 i's, 4 o's, and 6 u's.
    public static int[] numOfVowelsIn(String phrase)
    {
        int[] result = {0, 0, 0, 0, 0};

        for (char token : phrase.toCharArray())
            if (isVowel(token)) trackVowel(result, token);
            
        return result;
    }

    // Output: the total number of each vowel on a new line.
    public static void printTotalVowels(int[] totalVowels)
    {
        char[] vowels = {'a', 'e', 'i', 'o', 'u'};
        int vowelIndex = 0;

        for (int total : totalVowels)
        {
            System.out.printf("There are %d %c's\n", total, vowels[vowelIndex]);
            vowelIndex++;
        }
    }

    // Desc: check if letter is a vowel. 
    // Return: the lowercase version of letter if the char is 'a', 'e', 'i',
    //   'o', or 'u'. Otherwise, return false.
    public static boolean isVowel(char letter)
    {
        char lowerCaseLetter = Character.toLowerCase(letter);
        switch (lowerCaseLetter)
        {
            case 'a':
            case 'e':
            case 'i':
            case 'o':
            case 'u': return true;
            default: return false;
        }
    }

    // Post: adds one to the corresponding position for vowel in arr.
    //   If vowel is not one of the five english vowels, nothing happens.
    //   arr[0] corresponds to 'a'
    //   arr[1] corresponds to 'e'
    //   arr[2] corresponds to 'i'
    //   arr[3] corresponds to 'o'
    //   arr[4] corresponds to 'u'
    public static void trackVowel(int[] arr, char vowel)
    {
        switch (vowel)
        {
            case 'a': arr[0] += 1; break;
            case 'e': arr[1] += 1; break;
            case 'i': arr[2] += 1; break;
            case 'o': arr[3] += 1; break;
            case 'u': arr[4] += 1; break;
        }
    }

}
