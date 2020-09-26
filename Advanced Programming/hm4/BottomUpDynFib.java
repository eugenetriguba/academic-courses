// Eugene Triguba <ytriguba17@ole.augie.edu>
// Advanced Programming: Homework 4

import java.util.Scanner;
import java.util.Vector;
import java.util.InputMismatchException;
import java.math.BigInteger;

class BottomUpDynFib
{
    public static void main(String[] args) throws InvalidFibonacciTerm
    {
        Scanner input = new Scanner(System.in);

        int fibNum;
        do {
            System.out.print("Enter a integer >= 0: ");

            try {
                fibNum = input.nextInt();
            } catch (InputMismatchException e){
                System.out.println("error: must be a number. Try again.");
                input.nextLine();
                continue;
            }

            if (fibNum < 0) {
                System.out.println("error: must be a integer that is >= 0. Try again.");
                continue;
            }

            break;
        } while (true);

        System.out.printf("%dth fibonacci term Bottom Up Dynamic Programming: %d\n", fibNum, dynFib(fibNum));
    }

    /**
     * Calculate fibonacci using a bottom-up
     * dynamic programming approach.
     *
     * Args:
     *   n - The nth fibonacci number to retrieve.
     *
     * Throws:
     *   InvalidFibonacciTerm if n is less than 0
     *
     * Returns:
     *   the nth fibonacci number in the sequence.
     */
    public static BigInteger dynFib(int n) throws InvalidFibonacciTerm
    {
        if (n < 0) throw new InvalidFibonacciTerm("n cannot be less than 0");
        if (n == 0) return new BigInteger("0");

        BigInteger[] fib = new BigInteger[n + 1];
        fib[0] = new BigInteger("0");
        fib[1] = new BigInteger("1");

        for (int i = 2; i <= n; i++) {
            fib[i] = fib[i - 2].add(fib[i - 1]);
        }

        return fib[n];
    }
}

class InvalidFibonacciTerm extends Exception
{
    public InvalidFibonacciTerm(String errorMessage) {
        super(errorMessage);
    }
}