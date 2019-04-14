// Eugene Triguba
// ytriguba17@ole.augie.edu
// Homework 9: Recursion
// Palindrome.java

public class Palindrome
{
    // Output: prints to the screen whether testWord is a palindrome
    public static void main(String[] args) 
    {
        String testWord = "aaracecar";
        System.out.printf("%s is a palindrome: %b\n", testWord, 
            isPalindrome(testWord, 2, testWord.length() - 1));
    }

    // Desc: Determines if a string is a palindrome. A palindrome is a string
	//       that reads the same forward and backward.
	// Pre:	s consists of lowercase letters only.  
	// Return: true if the string s in index [start, last) is a palindrome.
    public static boolean isPalindrome(String input)
    {
        return isPalindrome(input, 0, input.length() - 1);
    }

    // Desc: Determines if a string is a palindrome. A palindrome is a string
	//       that reads the same forward and backward.
	// Pre:	s consists of lowercase letters only.  
	// Return: true if the string s in index [start, last) is a palindrome.
    public static boolean isPalindrome(String input, int start, int last)
    { 
        if (start >= last) return true;
        if (input.charAt(start) != input.charAt(last)) return false; 

        if (start < last + 1)
            return isPalindrome(input, ++start, --last);

        return false;
    }
}