// Eugene Triguba
// ytriguba17@ole.augie.edu
// Rsa.java
// Homework 2: Handout 0-6
import java.lang.Math;
import java.util.Scanner;

/**
 * Class Rsa encrypts and decrypts binary bit strings.
 */
class Rsa
{
    public static String encrypt(String bitString, int e, int n)
    {
        int decimalNumber = Integer.parseInt(bitString, 2);
        int result = (int)(Math.pow(decimalNumber, e)) % n;
        return Integer.toString(result, 2);
    }

    public static String decrypt(String bitString, int d, int n)
    {
        int decimalNumber = Integer.parseInt(bitString, 2);
        int result = (int)(Math.pow(decimalNumber, d)) % n;
        return Integer.toString(result, 2);
    }

    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter bit string: ");
        String bitString = input.nextLine().trim();

        System.out.print("Encrypt or decrypt? (e/d): ");
        char answer = input.next().trim().charAt(0);

        if (Character.toLowerCase(answer) == 'e') {
            System.out.print("Enter e: ");
            int e = input.nextInt();
            input.nextLine();
            System.out.print("Enter n: ");
            int n = input.nextInt();
            input.nextLine();

            System.out.printf("%s encrypted is %s\n", bitString, Rsa.encrypt(bitString, e, n));
        } else if (Character.toLowerCase(answer) == 'd') {
            System.out.print("Enter d: ");
            int d = input.nextInt();
            input.nextLine();
            System.out.print("Enter n: ");
            int n = input.nextInt();
            input.nextLine();

            System.out.printf("%s decrypted is %s\n", bitString, Rsa.encrypt(bitString, d, n));
        } else {
            System.out.println("I don't understand.");
        }
    }
}