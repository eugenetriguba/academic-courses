/* Eugene Triguba
   ytriguba17@ole.augie.edu
   TonsPoundsOunces.java */

public class TonsPoundsOunces
{
	// Find total tons, pounds, and ounces from a
	// fixed amount of ounces.
	public static void main(String[] args)
	{
		int initialOunces = 54321;
		// Running totals
		int totalTons = 0;
		int totalPounds = 0;
		int totalOunces = 0;
		// Conversions
		int tonInOunces = 32000;
		int poundInOunces = 16;
		
		totalOunces = initialOunces;
		
		// Find total tons & subtract from total ounces
		totalTons = totalOunces / tonInOunces;
		totalOunces -= totalTons * tonInOunces;
		
		// Find total gallons & subtract from total ounces
		totalPounds = totalOunces / poundInOunces;
		totalOunces -= totalPounds * poundInOunces;
		
		System.out.println(initialOunces + " ounces = " + totalTons + " tons(s), " 
			+ totalPounds + " pounds(s), and " + totalOunces + " ounce(s)");
	}
}