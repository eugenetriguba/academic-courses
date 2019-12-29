/*
 * Eugene Triguba <ytriguba17@ole.augie.edu>
 *
 * Exam 2: 30-2
 * Expression.java
 */

import java.util.Stack;

/**
 * An abstract representation of an Expression and what you would need to
 * evaluate that expression for the classes that extend it.
 */
abstract class Expression
{
    protected String expression;
    protected Stack<Symbol> operatorStack;
    protected Stack<Double> operandStack;

    /**
     * Checks if ch is a supported operator
     *
     * Options: +, -, *, /, %, ^
     *
     * @param ch - represents the operator
     * @return true if ch is a supported operator; false otherwise.
     */
    protected boolean isOperator(char ch)
    {
        return ch == '+' || ch == '-' || ch == '*' ||
               ch == '%' || ch == '/' || ch == '^';
    }

    /**
     * Performs an arithmetic function on
     * two operands
     *
     * @param left - the left operand
     * @param right - the right operand
     * @param op - the operator
     *
     * @return the result of left op right
     * @throws ArithmeticException if you try to divide by 0 or take 0 to the 0th power
     */
    protected double compute(double left, double right, char op)
    {
        switch(op)
        {
            case '+':  return left + right;
            case '-':  return left - right;
            case '*':  return left * right;
			
            case '%': if (right == 0) 
						  throw new ArithmeticException("Expression: divide by 0");
                      return left % right;
					  
            case '/': if (right == 0) 
						  throw new ArithmeticException("Expression: divide by 0");
                      return left / right;
					  
            case '^':  if (right == 0 && left == 0)
                           throw new ArithmeticException("Expression: 0^0");
                       return Math.pow(left, right);
					   
            default: return 0;
        }
    }

    /**
     * Removes and returns the operand on top
     * of the operand stack
     *
     * @return the operand on top of the operand stack
     * @throws ArithmeticException if the stack is empty
     *      (aka there must too many operators then)
     */
    protected double getOperand()
    {
        if (operandStack.isEmpty())
            throw new ArithmeticException("Expression: Too many operators");

        return operandStack.pop();
    }

    /**
     * Finds an operand in the expression
     *
     * We start at the character at index and loop down the expression
     * until we hit two decimal points (runtime error), the end of the expression,
     * or until we get to the operator.
     *
     * @param exp - the expression we are finding the operand in
     * @param index - the index of expression for the start of the operand
     * @return the operand
     * @throws RuntimeException if there is more than one decimal point in an operand
     */
    protected String findOperand(String exp, int index)
    {
        StringBuilder operand = new StringBuilder();
        char digit = exp.charAt(index);
        boolean decimalPresent = false;
        int expLength = exp.length();


        while ((Character.isDigit(digit) || digit == '.') && expLength > index)
        {
            operand.append(digit);
            index++;

            if (expLength > index)
            {
                if (digit == '.' && decimalPresent)
                {
					String message = "Expression: Should only be one decimal "
						+ "point per operand";
					
                    throw new RuntimeException(message);
                }
                else if (digit == '.') decimalPresent = true;

                digit = exp.charAt(index);
            }
        }

        return operand.toString();
    }

    /**
     * Initializes this Expression by setting:
     *  - expression to an empty string
     *  - operandStack and operatorStack to new empty stacks
     */
    public Expression()
    {
        this("");
    }

    /**
     * Initializes this Expression by setting:
     *  - expression to an empty string
     *  - operandStack and operatorStack to new empty stacks
     */
    public Expression(String expression)
    {
        setExp(expression);
    }

    /**
     * Retrieves the expression and returns it
     *
     * @return this Expression's expression
     */
    public String getExp()
    {
        return expression;
    }

    /**
     * Sets this Expressions expression to exp.
     * Clears operator stack and operand stack.
     *
     * @param exp - the expression to change this
     *            Expression's expression to
     */
    public void setExp(String exp)
    {
        this.expression = exp;
        this.operatorStack = new Stack<Symbol>();
        this.operandStack = new Stack<Double>();
    }
}