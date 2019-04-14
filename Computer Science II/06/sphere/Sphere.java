// Eugene Triguba
// ytriguba17@ole.augie.edu
// Sphere.java
// Homework 6: 7-4 MoreOnClasses

// Desc: a Sphere type which can be used to set & get radius, calculate the sphere's 
//   volume, convert itself to a String, check if equal to other spheres or compare 
//   to other spheres, and see the author of the class.
public class Sphere
{
	private double radius;
	private double volume;
	
	// Post: the radius and volume in this Sphere
	//   object are both set to 0.0.
	public Sphere()
	{
		this.radius = 0.0;
		this.volume = 0.0;
	}
	
	// Pre: Radius must be greater than 0
	// Post: the radius in this Sphere object is set to radius
	//   	 the volume in this Sphere object is calculated.
	public Sphere(double radius)
	{
		this.radius = radius;
		this.calculateVolume();
	}
	
	// Pre: Radius must be greater than 0
	// Post: the radius in this Sphere object is set to radius
	// Return: this Sphere object
	public Sphere setRadius(double radius)
	{
		this.radius = radius;
		this.calculateVolume();
		return this;
	}
	
	// Return: the radius of this Sphere object
	public double getRadius()
	{
		return this.radius;
	}
	
	// Return: the volume of this Sphere object
	public double getVolume()
	{
		return this.volume;
	}
	
	// Return: a String containing the radius and volume values.
	public String toString()
	{
		return "Radius: " + this.radius + " | Volume: " + this.volume;
	}
	
	// Return: true if the radius of this Sphere object and obj are equal. 
	//   false otherwise.
	public boolean equals(Sphere comparisonSphere)
	{
		if (this.radius == comparisonSphere.radius) return true;
		else return false;
	}
	
	// Desc: Compares two spheres by checking the value of their radiis. 
	// Return: 0 if both Spheres are equal. 
	// 		   1 if this Sphere object is greater than comparisonSphere. 
	// 		   -1 if this Sphere object is less than comparisonSphere.
	public int compareTo(Sphere comparisonSphere)
	{	
		if (this.radius == comparisonSphere.radius) return 0;
		else if (this.radius > comparisonSphere.radius) return 1;
		else return -1;
	}
	
	// Return: a String containing the author of class Sphere.
	public static String author()
	{
		return "Eugene Triguba";
	}
	
	// Desc: Calculates the volume of this Sphere object by using the 
	//   formula for volume.
	// Pre: radius of this Sphere object > 0
	// Post: volume of this Sphere object is calculated and set.
	private void calculateVolume()
	{
		this.volume = (4.0 / 3.0) * Math.PI * Math.pow(this.radius, 3);
	}
}