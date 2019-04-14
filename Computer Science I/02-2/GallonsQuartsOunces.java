/* Eugene Triguba
   ytriguba17@ole.augie.edu
   GallonsQuartsOunces.java */

public class GallonsQuartsOunces
{
	// Find total gallons, quarts, and ounces from a
	// fixed amount of ounces.
	public static void main(String[] args)
	{
		int initialOunces = 200;
		// Running totals
		int totalGallons = 0;
		int totalQuarts = 0;
		int totalOunces = 0;
		// Conversions
		int gallonInOunces = 128;
		int quartInOunces = 32;
		
		totalOunces = initialOunces;
		
		// Find total gallons & subtract from total ounces
		totalGallons = totalOunces / gallonInOunces;
		totalOunces -= totalGallons * gallonInOunces;
		
		// Find total quarts & subtract from total ounces
		totalQuarts = totalOunces / quartInOunces;
		totalOunces -= totalQuarts * quartInOunces;
		
		System.out.println(initialOunces + " ounces = " + totalGallons + " gallon(s), " 
			+ totalQuarts + " quart(s), and " + totalOunces + " ounce(s)");
	}
}