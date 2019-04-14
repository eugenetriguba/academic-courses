// Eugene Triguba
// ytriguba17@ole.augie.edu
// RealApp.java
// Homework 6: 7-5 MoreOnObjects

// Desc: an app to test the Real type class.
public class RealApp
{
	public static void main(String[] args)
	{
		Real testReal = new Real();
		Real testRealInitialized = new Real(5.0);
		
		runTest(testReal);	
		System.out.println();
		runTest(testRealInitialized);
	}
	
	// Desc: run a single round of tests for methods in testReal.
	// Post: testReal's value is set to 10.0
	// Output: prints to the screen the output of each test with
	//   dividers following.
	public static void runTest(Real testReal)
	{	
		printRealValue(testReal);
		printDivider();
		
		testSet(testReal);
		printDivider();
		
		testDoubleValue(testReal);
		printDivider();
		
		Real comparisonReal = new Real(2.0);
		testCompareTo(testReal, comparisonReal);
		printDivider();
	}
	
	// Desc: print out the value of testReal to the screen by using 
	//   its toString method.
	// Output: "Value: testRealValue" where testRealValue is replaced
	//   with its actual value.
	public static void printRealValue(Real testReal)
	{
		System.out.printf("Value: %s\n", testReal.toString());
	}
	
	// Desc: tests the set method in testReal by setting the value and 
	//   printing it out.
	// Post: testReal's value is set to 10.0
	// Output: an introduction message, a set confirmation message, 
    //   and the value of testReal printed.
	public static void testSet(Real testReal)
	{
		System.out.println("Starting to test the set method.");

		testReal.set(10.0);
		System.out.println("testReal is set to 10.0.");

		printRealValue(testReal);
	}
	
	// Desc: tests the doubleValue method in testReal by comparing
    //   its return value to its toString method's return value .
	// Output: an introduction message, the value of testReal using its
	//   toString, and the value from testReal's doubleValue method.
	public static void testDoubleValue(Real testReal)
	{
		System.out.println("Starting to test the doubleValue method.");
        printRealValue(testReal);
		System.out.printf("Value from doubleValue method: %.1f\n", testReal.doubleValue());
	}
	
	// Desc: tests the compareTo method by printing out both values to the screen
	//   and seeing what the comparison results to.
	// Output: an introduction message, the value of testReal and comparisonReal,
	//   and the result of comparing the two.
	public static void testCompareTo(Real testReal, Real comparisonReal)
	{
		System.out.println("Starting to test the CompareTo method.");
		System.out.println("testReal value: " + testReal.toString());
		System.out.println("comparisonReal value: " + comparisonReal.toString());

		int result = testReal.compareTo(comparisonReal);
		if (result == 0) System.out.println("testReal and comparisonReal are equal.");
		else if (result == 1) System.out.println("testReal is greater than comparisonReal.");
		else System.out.println("testReal is less than comparisonReal.");
	}
	
    // Desc: prints a divider to the screen
    // Output: 15 '-' characters in a row on a new line.
	public static void printDivider()
	{
		System.out.println("---------------");
	}
	
}