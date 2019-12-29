/*
 * Eugene Triguba <ytriguba17@ole.augie.edu>
 *
 * Exam 2: 30-2
 * Postfix.java
 */

/**
 * A Postfix class to evaluate postfix
 * expressions.
 */
public class Postfix extends Expression
{

    /**
     * Initializes this Postfix by setting:
     *  - expression to an empty string
     *  - operandStack and operatorStack to new empty stacks
     */
    public Postfix()
    {
        super();
    }

    /**
     * Initializes this Postfix by setting:
     *  - expression to an empty string
     *  - operandStack and operatorStack to new empty stacks
     */
    public Postfix(String expression)
    {
        super(expression);
    }

    /**
     * Evaluates a postfix expression and
     * returns the result.
     *
     * @return the result of this Postfix's expression evaluated
     * @throws ArithmeticException if there is an improper char or too many operands
     */
    public double evaluate()
    {
        for (int i = 0; i < this.expression.length(); i++)
        {
            char ch = this.expression.charAt(i);

            if (Character.isDigit(ch))
            {
                String operand = findOperand(expression, i);
                operandStack.push(Double.parseDouble(operand));
                i+= operand.length()-1;
            }
            else if (isOperator(ch))
            {
                double right = getOperand();
                double left = getOperand();
                operandStack.push(compute(left, right, ch));
            }
            else if (!Character.isWhitespace(ch))
                throw new ArithmeticException("Postfix: Improper char");
        }

        double expValue = operandStack.pop();

        if (!operandStack.isEmpty())
            throw new ArithmeticException("Postfix: Too many operands");

        return expValue;
    }
}
