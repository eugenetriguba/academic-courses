/* Eugene Triguba
   ytriguba17@ole.augie.edu
   InchesToYdFtIn.java */

public class InchesToYdFtIn
{
	// Convert 356 inches to yards, feet, and inches.
	public static void main(String[] args)
	{
		int initialInches = 356;
		// Running totals
		int totalYards = 0;
		int totalFeet = 0;
		int totalInches = 0;
		// Conversions
		int yardInInches = 36;
		int feetInInches = 12;
		
		totalInches = initialInches;
		
		// Find total yards & subtract from total inches
		totalYards = totalInches / yardInInches;
		totalInches -= totalYards * yardInInches;
		
		// Find total feet & subtract from total inches
		totalFeet = totalInches / feetInInches;
		totalInches -= totalFeet * feetInInches;
		
		System.out.println(initialInches + " inches = " + totalYards + " yard(s), " 
			+ totalFeet + " feet, and " + totalInches + " inches");
	}
}