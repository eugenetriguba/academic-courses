/* Eugene Triguba
   ytriguba17@ole.augie.edu
   CelToFah.java */

public class CelToFah
{
	// Convert Celsius to Fahrenheit
	public static void main(String[] args)
	{
		double celsius = 32.0;
		double fahrenheit = 0.0;
		
		// Formula to convert Celsius to Fahrenehit
		fahrenheit = celsius * (9.0/5.0) + 32.0;
		
		System.out.println(celsius + " degrees in Celsius is " + fahrenheit 
			+ " degrees in Fahrenheit.");
	}
}