/* Eugene Triguba
   ytriguba17@ole.augie.edu
   SphereVolume.java */

import java.util.Scanner; 

public class SphereVolume
{
	// Calculates the volume of a sphere with a user-inputted
	// radius.
	public static void main(String[] args)
	{
		// Grab user input
		System.out.print("Enter a radius: ");
		Scanner input = new Scanner(System.in);
		double radius = input.nextDouble();
		
		// Input validation
		if (radius <= 0) {
			System.out.println("Error: radius should be a positive real number.");
			return;
		}
		
		// Calculate the volume of a sphere
		double volume = 4.0/3.0 * Math.PI * Math.pow(radius, 3);
		
		System.out.printf("The volume of a sphere with a radius of %.2f" +
			" equals %.2f\n", radius, volume);
	}
}