import javax.swing.*;							
import java.awt.*;
import java.awt.event.ActionListener; 							

// A class to create and handle the changes to the GUI.
public class CalculatorView extends JFrame
{ 
    private JButton btnAdd = new JButton("+");					
	private JButton btnSubtract = new JButton("-");			
	private JButton btnMultiply = new JButton("*");			
    private JButton btnDivide = new JButton("/");	
    private JButton btnSqrt = new JButton("sqrt");
    
    // Post: layout of this JFrame is set to a grid layout of 1 by 5
    //       all buttons are added to this JFrame
    //       Window settings are set
    public CalculatorView()							
    {
        addControls();												
        setWindowSettings();
    }

    // Post: an ActionListner of handler is set to all buttons
	public void registerListeners(ActionListener handler)			
	{
		btnAdd.addActionListener(handler);				
		btnSubtract.addActionListener(handler);				
		btnMultiply.addActionListener(handler);				
        btnDivide.addActionListener(handler);
        btnSqrt.addActionListener(handler);				
    }

    // Output: displays a message dialog in the middle of the
    //         screen of message
    public static void displayMessage(String message)
    {
        JOptionPane.showMessageDialog(null, message);
    }

    // Input: show a input dialog with directions and a placeholderValue
    // Return: the inputted number, parsed as a double
    public static double getInputFromDialog(String directions, String placeholderValue)
    {
        String result = JOptionPane.showInputDialog(null, directions, placeholderValue);
        return Double.parseDouble(result);
    }

    // Post: layout of this JFrame is set to a grid layout of 1 by 5
    //       all buttons are added to this JFrame 
    private void addControls()							
	{
		setLayout(new GridLayout(1,5));				
		add(btnAdd);						
		add(btnSubtract);		
		add(btnMultiply);						
        add(btnDivide);		
        add(btnSqrt);			
    }	

    // Post: Title set to 'Calculator'
    //       Size of this window set to 200px wide, 100px tall
    //       LocationRelativeTo set to null to pop up in the middle
    //       Default closing operation set to exit
    //       Set's the visibility of the window to true
    private void setWindowSettings()
    {
        setTitle("Calculator");						
        setSize(200,100); 		
        setLocationRelativeTo(null);				
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
        setVisible(true);		
    }
}