// Eugene Triguba
// ytriguba17@ole.augie.edu
// Encryption.java

import java.util.Scanner;
import java.io.*;

class Encryption				
{						
	private static final int ASCII_ZERO = 48;
	private static final int ASCII_NINE = 57;
	private static final int ASCII_LOWERCASE_A = 97;
	private static final int ASCII_LOWERCASE_Z = 122;
	private static final int ASCII_UPPERCASE_A = 65;
	private static final int ASCII_UPPERCASE_Z = 90;

	public static void main(String[] args) throws FileNotFoundException
   	{	
		Scanner keyboard = new Scanner(System.in);
		char choice = '1';
		System.out.println("1. Encrypt a file");
		System.out.println("2. Decrypt a file");
		choice = keyboard.nextLine().charAt(0);
		switch (choice)
		{
			case '1': encryptFile(); break;
			case '2': decryptFile(); break;
		}

		keyboard.close();
	}

	// Input: User enters a file name with the extension via the keyboard
	// Return: the file name as a String
	public static String getFileName()
	{
		Scanner keyboard = new Scanner(System.in);
		System.out.print("Enter file name: ");
		String fileName = keyboard.nextLine();
		keyboard.close();
		return fileName;
	}
	   
	// Desc: Encrypts a file.
	// Input: The user supplies the name of a disk file via the keyboard 
	//        and the file must exist.
	// Output: The text in the specified file encrypted and written 
	//         to "encrypted.txt".  
	// Throw: FileNotFoundException if the input source file does not 
	//        exist, or encrypted.txt cannot be created
	public static void encryptFile() throws FileNotFoundException
	{
		String fileName = getFileName();
		Scanner input = new Scanner(new File(fileName));
		input.useDelimiter("");
		PrintWriter output = new PrintWriter("encrypted.txt");

		while (input.hasNext())
		{
			char token = input.next().charAt(0);
			token = encryptToken(token);
			System.out.println("token: " + token);
			output.print(token);
		}

		input.close();
		output.close();
	}

	// Desc: Encrypt a character to another secret character using an 
	//       algorithm that chooses what to convert it to based on whether 
	//       it is a number, letter, or something else.
	// Return: the encrypted token  
	public static char encryptToken (char token)
	{
		int result = token;

		if (result >= 48 && result <= 57) result = convertNum(result);
		else if (result >= 65 && result <= 90) result = convertUpperCase(result);
		else if (result >= 97 && result <= 122) result = convertLowerCase(result);
		else result -= 1;

		return (char)(result);
	}

	// Desc: Decrypts a file.
	// Input: The user supplies the name of a disk file via the keyboard 
	//        and the file must exist.
	// Output: The text in the specified file decrypted and written to 
	//         "decrypted.txt".  
	// Throw: FileNotFoundException if the input source file does not exist, 
	//        or decrypted.txt cannot be created
	public static void decryptFile() throws FileNotFoundException
	{
		String fileName = getFileName();
		Scanner input = new Scanner(new File(fileName));
		input.useDelimiter("");		
		PrintWriter output = new PrintWriter("decrypted.txt");

		char token = 'a';
		while (input.hasNext())
		{
			token = input.next().charAt(0);
			token = decryptToken(token);
			output.print(token);
		}

		input.close();
		output.close();
	}

	// Desc: Decrypts a token doing the inverse of the encryption algorithm.
	// Return: the decrypted token.
	public static char decryptToken(char token)
	{
		int result = token;

		if (result >= 48 && result <= 57) result = inverseConvertNum(result);
		else if (result >= 65 && result <= 90) result = inverseConvertUpperCase(result);
		else if (result >= 97 && result <= 122) result = inverseConvertLowerCase(result);
		else result += 1;

		return (char)(result);
	}

	// Desc: Every digit converted to its predecessor 
	//       except 0 which gets converted to 9.
	// Return: asciiNum - 1 unless asciiNum is equal to 48 
	//         (zero in ascii), it get's converted to 57 (nine in ascii).
	public static int convertNum(int asciiNum)
	{
		if (asciiNum == ASCII_ZERO) return ASCII_NINE;
		else return asciiNum - 1;
	}

	// Desc: Every uppercase letter converted to its successor 
	//       except Z which gets converted to A.
	// Return: asciiLetter + 1 unless asciiLetter is equal to 90 
	//        (Z in ascii), it get's converted to 65 (A in ascii).
	public static int convertUpperCase(int asciiLetter)
	{
		if (asciiLetter == ASCII_UPPERCASE_Z) return ASCII_UPPERCASE_A;
		else return asciiLetter + 1;
	}

	// Desc: Every lowercase letter converted to its successor 
	//       except z which gets converted to a.
	// Return: asciiLetter + 1 unless asciiLetter is equal to 122 
	//         (z in ascii), it get's converted to 97 (a in ascii).
	public static int convertLowerCase(int asciiLetter)
	{
		if (asciiLetter == ASCII_LOWERCASE_Z) return ASCII_LOWERCASE_A;
		else return asciiLetter + 1;
	}

	// Desc: Every digit converted to its successor 
	//       except 9 which gets converted to 0.
	// Return: asciiNum + 1 unless asciiNum is equal to 57 
	//         (nine in ascii), it get's converted to 48 (zero in ascii).
	public static int inverseConvertNum(int asciiNum)
	{
		if (asciiNum == ASCII_NINE) return ASCII_ZERO;
		else return asciiNum + 1;
	}

	// Desc: Every uppercase letter converted to its predecessor 
	//       except A which gets converted to Z.
	// Return: asciiLetter - 1 unless asciiLetter is equal to 65 
	//        (A in ascii), it get's converted to 90 (Z in ascii).
	public static int inverseConvertUpperCase(int asciiLetter)
	{
		if (asciiLetter == ASCII_UPPERCASE_A) return ASCII_UPPERCASE_Z;
		else return asciiLetter - 1;
	}

	// Desc: Every lowercase letter converted to its predecessor 
	//       except a which gets converted to z.
	// Return: asciiLetter - 1 unless asciiLetter is equal to 97 
	//         (a in ascii), it get's converted to 122 (z in ascii).
	public static int inverseConvertLowerCase(int asciiLetter)
	{
		if (asciiLetter == ASCII_LOWERCASE_A) return ASCII_LOWERCASE_Z;
		else return asciiLetter - 1;
	}
}
