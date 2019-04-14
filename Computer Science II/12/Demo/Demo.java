// Eugene Triguba
// ytriguba17@ole.augie.edu
// Demo.java
// Chapter 14-1: First Look Window Progrmaming

import javax.swing.JOptionPane;

public class Demo 
{
    /* A simple calculator for doing addition, subtraction, multiplication, and division.  
    ** 
    ** Input: The user selects +, –, *, / via an input box, then enters 2 numbers 
	**        via an input box.  
    ** Output: The result of the entered expression displayed in a message box. 
    **         Invalid operator, operands, or attempting to divide by zero results
    **         in an error message and displaying the input prompt again.
    */
	public static void main(String[] args)
	{ 
		while (true)
        {
            Calculation calc = getInputForCalculation();
            calc.showResult();
	    }
    }

    /* Get input for one calculation via input dialogs to the window.
    **
    ** Input: String values for operator, operand1, and operand2.
    ** Output: Input dialog windows for operator, operand1, and operand2.
    **         Error windows for a invalid operator and attempting
    **         to divide by zero.
    ** Return: A new Calculation object set to the inputted values.
    */
    public static Calculation getInputForCalculation()
    {
        String operator = (JOptionPane.showInputDialog(null, "Select an operation", 
			"+ - * /"));
        operator = ErrorHandler.invalidOperator(operator);

        String operand1 = JOptionPane.showInputDialog(null, "Operand1", "0.0");
        ErrorHandler.invalidOperand(operand1);

        String operand2 = JOptionPane.showInputDialog(null, "Operand2", "0.0");
        operand2 = ErrorHandler.invalidOperand(operator, operand2);
        
        return new Calculation(Double.parseDouble(operand1), operator, 
			Double.parseDouble(operand2));
    }
}

// A class that models a calculation. The Calculation takes two Double operands and a
// String operator and is able to compute the result.
class Calculation
{
    double operand1;
    double operand2;
    String operator;
    double result;

    // Post: operand1, operand2, and result set to 0.0.
    //       operator set to an empty String.
    public Calculation()
    {
        operand1 = 0.0;
        operand2 = 0.0;
        operator = "";
        result = 0.0;
    }

    // Post: This object's operand1, operator, and operand2 are set to the
    //       inputted values.
    //       Result is computed and set based off the inputted values.
    public Calculation(double operand1, String operator, double operand2)
    {
        this.operand1 = operand1;
        this.operand2 = operand2;
        this.operator = operator;
        computeResult();
    }

    /* Computes the result of the calculation by looking at the operator
    ** and using that operator on the two operands.
    **
    ** Post: This object's result is set to the result of the calculation.
    */
    public void computeResult()
    {
        if (operator.equals("+"))
            result = operand1 + operand2;
        else if (operator.equals("-"))
            result = operand1 - operand2;
        else if (operator.equals("*"))
            result = operand1 * operand2;
        else if (operator.equals("/"))
            result = operand1 / operand2;
    }

    // Output: Displays a message dialog with the results of the calculation in
    //         the format: operand1 operator operand2 = result
    //         Operands and result are formatted to only show 2 decimal places.
    public void showResult()
    {
        String resultMessage = String.format("%.2f %s %.2f = %.2f", 
            operand1, operator, operand2, result);
        JOptionPane.showMessageDialog(null, resultMessage);
    }
}

// A class to handle errors that arise when doing calculations.
// ErrorHandler can check for an invalid operator, operands, and see if you are
// attmepting to divide by zero. 
class ErrorHandler
{
    // Output: An error message dialog and an input dialog to re-enter a new
    //         operator if operator is not a valid operator.    
    // Post: Exits the program if the operator is null. 
    //       If the operator is not one of the four valid choices [+, -, *, /],
    //       an input dialog box will pop up until operator is re-set to a valid
    //       operation.
    // Return: the operator, changed or unchanged depending on whether it was valid.
    public static String invalidOperator(String operator)
    {
        exitIfNull(operator);
        
        while (!operator.equals("+") && !operator.equals("-") && 
        !operator.equals("*") && !operator.equals("/"))
        {
            JOptionPane.showMessageDialog(null, "Operator is not one of the four valid" + 
				" operators (+, -, *, /).", "Invalid Operator", JOptionPane.ERROR_MESSAGE);
            operator = (JOptionPane.showInputDialog(null, "Select an operation" , "+ - * /"));
        }

        return operator;
    }

    // Post: Exits the program if the operand is null. 
    public static void invalidOperand(String operand)
    {
        exitIfNull(operand);
    }

    // Output: An error message dialog and an input dialog to re-enter a new
    //         operand2 if user is attempting to divide by zero.    
    // Post: Exits the program if the operand2 is null. 
    //       Forces the user to re-enter a new value for operand2
    //       if the user is trying to divide by zero.
    // Return: operand2, changed or unchanged depending on whether
    //         division by zero was being attempted.
    public static String invalidOperand(String operator, String operand2)
    {
        exitIfNull(operand2);
        operand2 = dividingByZero(operator, operand2);
        return operand2;
    }

    // Output: An error message dialog and an input dialog to re-enter a new
    //         operand2 if user is attempting to divide by zero.   
    // Post: Forces the user to re-enter a new value for operand2
    //       if the user is trying to divide by zero.
    // Return: operand2, changed or unchanged depending on whether
    //         division by zero was being attempted.
    private static String dividingByZero(String operator, String operand2)
    {
        while (operator.equals("/") && operand2.equals("0"))
        {
            JOptionPane.showMessageDialog(null, "Division is not allowed if the second " + 
				"operand is zero.", "Division by Zero Error", JOptionPane.ERROR_MESSAGE);
            operand2 = JOptionPane.showInputDialog(null, "Enter Operand2" , "0.0");
        }
        return operand2;
    }

    /* If the operator or operand is null, the user has likely clicked cancel to
    ** exit the program.
    **
    ** Post: Kills the program if operatorOrOperand are null.
    */
    private static void exitIfNull(String operatorOrOperand)
    {
        if (operatorOrOperand == null) System.exit(1);
    }
}