// Eugene Triguba
// ytriguba17@ole.augie.edu
// Calculator.java
// 15-1 EventDrivenProgramming

// A class with a single method, main, used to run the Calculator program by
// creating a model, view, and controller.	
public class Calculator
{
	public static void main(String[] args)
   	{
        CalculatorModel model = new CalculatorModel();
        CalculatorView view = new CalculatorView();
        CalculatorController controller = new CalculatorController(model, view);
    }
}
