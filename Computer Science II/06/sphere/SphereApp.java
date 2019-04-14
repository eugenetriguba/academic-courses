// Eugene Triguba
// ytriguba17@ole.augie.edu
// SphereApp.java
// Homework 6: 7-4 MoreOnClasses

// Desc: an app to test the Sphere type class.
public class SphereApp
{
	public static void main(String[] args)
	{
		Sphere testSphere = new Sphere();
		Sphere testSphereInitialized = new Sphere(5.0);
        

        runTest(testSphere);	
        System.out.println();
		runTest(testSphereInitialized);
	}
	
	// Desc: run a single round of tests for methods in testSphere.
	// Post: testSphere's value is set to 10.0
	// Output: prints to the screen the output of each test with
	//   dividers following.
	public static void runTest(Sphere testSphere)
	{
        System.out.println("Initial Values: ");
		printSphereAttributes(testSphere);
		printDivider();
		
		testSetRadius(testSphere);
		printDivider();
		
		testToString(testSphere);
        printDivider();
		
		Sphere comparisonSphere = new Sphere(2.0);
		testCompareTo(testSphere, comparisonSphere);
        printDivider();
        
        testAuthor();
        printDivider();
	}
	
    // Desc: print to the screen the radius and volume attributes
    //   using the getter methods for them.
	// Output: the values of testSphere's radius and volume.
	public static void printSphereAttributes(Sphere testSphere)
	{
		System.out.printf("Radius: %.2f\n", testSphere.getRadius());
		System.out.printf("Volume: %.2f\n", testSphere.getVolume());
    }
	
    // Desc: prints a divider to the screen
    // Output: 15 '-' characters in a row on a new line.
	public static void printDivider()
	{
		System.out.println("---------------");
    }
    
    // Desc: tests the set method in testSphere by setting the value and 
	//   printing it out to the screen.
	// Post: testSphere's radius is set to 10.0
    // Output: an introduction message, a setRadius confirmation message, 
    //   and the attributes of testSphere printed.
	public static void testSetRadius(Sphere testSphere)
	{
        System.out.println("Starting to test the set method.");
        
		testSphere.setRadius(10.0);
        System.out.println("Radius is set to 10.0.");
        
		printSphereAttributes(testSphere);
    }
    
    // Desc: tests the toString method in the Sphere class by comparing
    //   the output using the getters to the output given by toString .
    // Output: an introduction message, the attributes of testSphere printed
    //   by using printSphereAttributes, and the attributes of testSphere 
    //   printed again by using toString.
	public static void testToString(Sphere testSphere)
	{
		System.out.println("Starting to test the toString method.");
        printSphereAttributes(testSphere);
		System.out.println(testSphere.toString());
    }
    
    // Desc: tests the compareTo method by printing to the screen the radis
    //   of testSphere and comparisonSphere then printing to the screen the 
    //   result of compareTo.
	// Output: an introduction message, the radius of testReal and comparisonReal,
	//   and the result of comparing the two using compareTo.
	public static void testCompareTo(Sphere testSphere, Sphere comparisonSphere)
	{
		System.out.println("Starting to test the CompareTo method.");
		System.out.println("testSphere's radius: " + testSphere.getRadius());
		System.out.println("comparisonSphere's radius: " + comparisonSphere.getRadius());

		int result = testSphere.compareTo(comparisonSphere);
		if (result == 0) 
			System.out.println("testSphere and comparisonSphere are equal.");
		else if (result == 1) 
			System.out.println("testSphere is greater than comparisonSphere.");
		else 
			System.out.println("testSphere is less than comparisonSphere.");
    }
    
    // Output: an assertion of what the result should be and the return
    //   value of the author method in class Sphere.
    public static void testAuthor()
    {
        System.out.println("Assertion: should be \"Eugene Triguba\" ");
        System.out.printf("Return value from author method: %s\n", Sphere.author());
    }
}