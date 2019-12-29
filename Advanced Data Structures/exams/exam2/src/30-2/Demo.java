/*
 * Eugene Triguba <ytriguba17@ole.augie.edu>
 *
 * Exam 2: 30-2
 * Demo.java
 */

import java.util.Scanner;

public class Demo
{
    /**
     * A simple calculator
     *
     * Input: The keyboard provides an infix expression on
     *        a line whose operators can only be *, +, -, /, %, ^,
     *        and whose operands can only be single-digit integers.
     *
     * Output: The integer value of the infix expression
     *         printed to the screen.
     *
     * @param args not used
     */
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter infix expression, newline to end:");
        String line = input.nextLine();

        while (!line.equals(""))
        {
            try
            {
                Infix infix = new Infix(line);
                Postfix postfix = new Postfix(infix.toPostfix());

                System.out.println("\t Postfix: " + postfix.getExp());
                System.out.println("\t Value: " + postfix.evaluate());
            }
            catch (ArithmeticException e)
            {
                System.out.println("\t " + e.getMessage());
            }

            line = input.nextLine();
        }
    }
}
