// Eugene Triguba
// ytriguba17@ole.augie.edu
// TestBox.java
// Homework 5: 7-3 UserDefinedClassTypes

// Desc: This program tests the Box class by creating two boxes, one without
//   initial values and one with, and tests the getters and setters in each
//   of them.
public class TestBox
{
	public static void main(String[] args)
	{
		Box noArgsBox = new Box();
		runTest(noArgsBox, 5.0, 5.0, 5.0);
		
		System.out.println();
		
		Box argsBox = new Box(1.0, 1.0, 1.0);
		runTest(argsBox, 10.0, 10.0, 10.0);
	}
	
	// Pre: length > 0, width > 0, height > 0
	// Post: Length property of testBox = length
	//		 Width property of testBox = width
	//		 Height property of testBox = height
	//       Volume and surface area is calculated for testBox.
	// Output: Prints out the box properties at start to the screen. Each time after
	//   the length, width, and height property is set, a message is printed to the screen 
	//   to inform the user what it's been set to and the box state is reprinted.
	public static void runTest(Box testBox, double length, double width, double height)
	{	
		System.out.println("Initial Box State: ");
		printBoxState(testBox);
		
		System.out.printf("Length is set to %.1f\n", testBox.setLength(length).getLength());
		printBoxState(testBox);
		
		System.out.printf("Width is set to %.1f\n", testBox.setWidth(width).getWidth());
		printBoxState(testBox);

		System.out.printf("Height is set to %.1f\n", testBox.setHeight(height).getHeight());
		printBoxState(testBox);
	}
	
	// Output: print testBox's properties followed by a divider.
	public static void printBoxState(Box testBox)
	{
		System.out.println(testBox.toString());
		printDivider();
	}
	
	// Output: print a divider to the screen on a new line.
	public static void printDivider()
	{
		System.out.println("----------");
	}
}