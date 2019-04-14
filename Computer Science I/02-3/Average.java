/* Eugene Triguba
   ytriguba17@ole.augie.edu
   Average.java */

public class Average
{
	// Calculates the average of 11, 22, 31, 42, 51, 
	// and prints it out.
	public static void main(String[] args)
	{
		
		int num1 = 11, num2 = 22, num3 = 31, num4 = 42, num5 = 51;
		double average = (double)(num1 + num2 + num3 + num4 + num5) / 5;
		
		System.out.println("Average of " + num1 + ", " + num2 + ", " + num3
			 + ", " + num4 + ", and " + num5 + " is " + average + ".");
	}
}