/*
 * Eugene Triguba <ytriguba17@ole.augie.edu>
 *
 * Exam 2: 30-2
 * Infix.java
 */

/**
 * Models a infix expression.
 *
 * This Infix can convert itself to
 * a postfix expression which we can
 * then evaluate more easily.
 */
public class Infix extends Expression
{
    private String postfixExp;

    /**
     * Pops items off the operator stack until we
     * find an item on the stack with a lower priority
     * than the operand to be pushed
     *
     * @param opToBePushed - the operand to be pushed on stack
     */
    private void popUntilLowerPriority(Symbol opToBePushed)
    {
        if (operatorStack.isEmpty()) return;

        StringBuilder postfixBuilder = new StringBuilder();

        while (operatorStack.size() > 0 && operatorStack.peek().compareTo(opToBePushed) >= 0)
        {
            Symbol topOfStack = operatorStack.pop();
            postfixBuilder.append(topOfStack.getOp() + " ");
        }

        postfixExp += postfixBuilder.toString();
    }

    /**
     * Initializes this Infix by setting:
     *  - expression and postfixExp to an empty string
     *  - operandStack and operatorStack to new empty stacks
     */
    public Infix()
    {
        this("");
    }

    /**
     * Initializes this Infix by setting:
     *  - This Infix's expression to expression
     *  - postfixExp to an empty string
     *  - operandStack and operatorStack to new empty stacks
     */
    public Infix(String expression)
    {
        super(expression);
        this.postfixExp = "";
    }

    /**
     * Convert an infix expression to a postfix expression.
     * This Infix's postfixExp is set to the resulting conversion.
     *
     * @return this Infix's expression in postfix form
     * @throws ArithmeticException if there is too many operands (operator is expected),
     *      there is too many operators (operand is expected), there is no matching left
     *      parenthesis to a right parenthesis, there is no matching right parenthesis
     *      to a left parenthesis, there is an invalid character, or if there is still
     *      an operator with no operand at the end of the expression.
     */
    public String toPostfix()
    {
        /*
         * Rank is used for error checking. It is incremented when
         * we read an operand and decremented when we read an operator.
         *
         * At the end of the processing of an Infix expression that contains
         * only binary operators, rank should be 1. However during the processing
         * of an Infix expression, rank can only be 0 or 1.
         */
        int rank = 0;

        // We save the length to avoid repeated function calls
        int expLength = this.expression.length();

        for (int i = 0; i < expLength; i++)
        {
            char ch = this.expression.charAt(i);

            if (Character.isDigit(ch))
            {
                rank++;
                if (rank > 1) throw new ArithmeticException("Infix: Operator expected");

                String operand = findOperand(this.expression, i);
                postfixExp += operand + " ";
                i += operand.length()-1;

            }
            else if (isOperator(ch))
            {
                rank--;
                if (rank < 0)
                    throw new ArithmeticException("Infix: Operand expected");
                else
                {
                    Symbol op = new Symbol(ch);
                    popUntilLowerPriority(op);
                    operatorStack.push(op);
                }
            }
            else if (ch == '(')
            {
                operatorStack.push(new Symbol(ch));
            }
            else if (ch == ')')
            {
                popUntilLowerPriority(new Symbol(ch));

                if (operatorStack.isEmpty())
                    throw new ArithmeticException("Infix: No matching left parenthesis");
                else
                    operatorStack.pop();
            }
            else if (!Character.isWhitespace(ch))
                throw new ArithmeticException("Infix: Invalid input");
        }

        if (rank != 1)
            throw new ArithmeticException("Infix: Operand expected");
        else
        {
            while (!operatorStack.isEmpty())
            {
                Symbol op = operatorStack.pop();

                if (op.getOp() == '(') throw new ArithmeticException("Infix: Missing ')'");
                else postfixExp += op.getOp() + " ";
            }
        }

        return postfixExp;
    }
}
