import javax.swing.*;					
import java.awt.*; 							
import java.awt.event.*;

// A class to handle the interaction between the CalculatoreModel and
// CalculatorView. It handles action events when a user clicks a button. 
public class CalculatorController extends JFrame
{
    private CalculatorModel model;
    private CalculatorView view;
    
    // Post: model is set to a new CalculatorModel.
    //       view is set to a new CalculatorView.
    //       view registers it's own listeners by passed an ActionHandler.
    public CalculatorController()							
    {
        model = new CalculatorModel();
        view = new CalculatorView();
        view.registerListeners(new ActionHandler());			
    }		
    
    // Post: this CalculatorController's model is set to model.
    //       this CalculatorController's view is set to view.
    //       view registers it's own listeners by passed an ActionHandler.
    public CalculatorController(CalculatorModel model, CalculatorView view)							
    {
        this.model = model;
        this.view = view;
        view.registerListeners(new ActionHandler());			
    }	

    public class ActionHandler implements ActionListener
    {
        // Desc: handles the pressing of buttons on the Calculator
        // Post: if +, -, *, or / are pressed, the arithmetic buttons are handled.
        //       sqrt is handled if that button is pressed.
        // Output: Otherwise, print out an error message to the console.
        public void actionPerformed(ActionEvent e)					
        {
            String btnTxt = e.getActionCommand();
            switch (btnTxt)
            {   
                case "+":
                case "-":
                case "*":
                case "/":
                    handleArithmeticButtons(btnTxt); 
                    break;
                case "sqrt":
                    handleSqrtButton();
                    break;
                default:
                    System.err.println(btnTxt + " is an invalid operation.");
            }
        }

        // Desc: Handles the pressing of the arithmetic buttons
        // Input: Reads in operand 1 and operand 2.
        // Output: If dividing by zero is attempted, an input dialog to read in
        //         If operator is invalid, a message is printed to the console.
        //         Displays the result of the expression to the screen.
        // Post: asks the model to add, subtract, multiply, or divide given the
        //       operator, altering it's result property.
        //       Sets the operand 1 and operand 2 is the model.
        private void handleArithmeticButtons(String operator)
        {
            model.setOperand1(readInOperand(1));
            model.setOperand2(readInOperand(2));
            
            switch (operator)
            {
                case "+": 
                    model.add(); 
                    break;
                case "-":
                    model.subtract();
                    break;
                case "*":
                    model.multiply();
                    break;
                case "/":
                    if (model.getOperand2() != 0) model.divide();
                    else  
                    {
                        while (model.getOperand2() == 0)
                        {
                            double operand = CalculatorView.getInputFromDialog(
                            "Operand 2 cannot be zero in a division operation. " + 
                            "Re-enter a positive value.", "0.0");
                            model.setOperand2(operand);
                        }
                        model.divide();
                    }
                    break;
                default: 
                    System.err.println(operator + " is an invalid operator.");
            }
            
            CalculatorView.displayMessage(String.format("%.2f %s %.2f = %.2f", 
                model.getOperand1(), operator, model.getOperand2(), model.getResult()));
        }

        // Input: Read in operand 1 from a input dialog box.
        // Output: Displays the result of the square root via a message dialog
        //         box.
        //         If taking a square root of a negative, an input dialog to 
        //         read in a new operand 1 is popped up.
        // Post: asks the model to take a square root if operand is not
        //       negative.
        private void handleSqrtButton()
        {
            model.setOperand1(readInOperand(1));
            
            while (isNegative(model.getOperand1()))
            {
                double operand = CalculatorView.getInputFromDialog(
                    "Operand cannot be negative when taking a square root. " + 
                    "Re-enter a positive value.", "0.0");
                model.setOperand1(operand);
            }

            model.sqrt();
            CalculatorView.displayMessage(String.format("sqrt(%.2f) = %.2f", 
                model.getOperand1(), model.getResult()));
        }

        // Input: an input dialog box to the screen to read in an operand
        // Return: the operand read in from the dialog box
        private double readInOperand(int operandNum)
        {
            String directions = String.format("Input Operand %d", operandNum);
            double operand = CalculatorView.getInputFromDialog(directions, "0.0");
            return operand;
        }

        // Return: true if num is negative.
        //         false otherwise.
        private boolean isNegative(double num)
        {
            return Double.compare(num, 0.0) < 0;
        }
    }
}