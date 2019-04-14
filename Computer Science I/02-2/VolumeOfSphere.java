/* Eugene Triguba
   ytriguba17@ole.augie.edu
   VolumeOfSphere.java */

public class VolumeOfSphere
{
	// Calculates and prints out the volume of 
	// a sphere with a radius of 5.3
	public static void main(String[] args)
	{
		double volume = 0.0;
		double radius = 5.3;
		
		// Formula for the volume of a sphere
		volume = 4.0/3.0 * Math.PI * Math.pow(radius, 3);
		
		System.out.printf("The volume of a sphere with a radius of " + 
			"%.2f equals %.2f\n", radius, volume);
	}
}