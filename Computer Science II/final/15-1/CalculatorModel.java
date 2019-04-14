
// A class to handle the logic behind a Calculator.
public class CalculatorModel
{
    private double operand1;
    private double operand2;
    private double result;

    // Post: result, operand1, and operand2 set to 0.0
    public CalculatorModel()
    {
        result = 0.0;
        operand1 = 0.0;
        operand2 = 0.0;
    }

    // Return: this CalculatorModel's operand1
    public double getOperand1()
    {
        return operand1;
    }

    // Return: this CalculatorModel's operand2
    public double getOperand2()
    {
        return operand2;
    }

    // Return: this CalculatorModel's result
    public double getResult()
    {
        return result;
    }

    // Post: set this CalculatorModel's operand1 to operand
    public void setOperand1(double operand)
    {
        operand1 = operand;
    }

    // Post: set this CalculatorModel's operand2 to operand
    public void setOperand2(double operand)
    {
        operand2 = operand;
    }

    // Post: set result to the sum of operand1 and operand2
    public void add()
    {
        result = operand1 + operand2;
    }

    // Post: set result to the difference of operand1 and operand2
    public void subtract()
    {
        result = operand1 - operand2;
    }

    // Post: set result to the product of operand1 and operand2
    public void multiply()
    {
        result = operand1 * operand2;
    }

    // Post: set result to the quotient of operand1 and operand2
    public void divide()
    {
        result = operand1 / operand2;
    }

    // Post: set result to the square root of operand1
    public void sqrt()
    {
        result = Math.sqrt(operand1);
    }
}