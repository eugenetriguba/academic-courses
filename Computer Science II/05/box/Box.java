// Eugene Triguba
// ytriguba17@ole.augie.edu
// Box.java
// Homework 5: 7-3 UserDefinedClassTypes

// Desc: a box type which can be used to set & get length, width, and height
//   as well as calculate the box's volume and surface area.
public class Box
{
	private double length;
	private double width;
	private double height;
	private double volume;
	private double surfaceArea;
	
	// Post: length, width, and height of the Box object 
	//	 is set to 0.0.
	public Box()
	{
		this.length = this.width = this.height = 
			this.volume = this.surfaceArea = 0.0;
	}
	
	// Pre: length > 0, width > 0, height > 0
	// Post: length, width, and height of the Box object initialized
	//	 to the length, width, and height inputted. Volume and surface
	//	 area of the Box object is calculated.
	public Box(double length, double width, double height)
	{
		this.length = length;
		this.width = width;
		this.height = height;
		this.calcVolumeAndSurfaceArea();
	}
	
	// Pre: length > 0
	// Post: Length of the Box object set to length; Volume and
	//	 surface area of the box recalculated if width and height have been set.
	// Return: the implicit argument passed to setLength
	public Box setLength(double length)
	{
		this.length = length;
		if (this.width > 0.0 && this.height > 0.0) this.calcVolumeAndSurfaceArea();
		return this;
	}
	
	// Pre: width > 0
	// Post: Width of the Box object set to length; Volume and
	//	 surface area of the box recalculated if length and height have been set.
	// Return: the implicit argument passed to setWidth
	public Box setWidth(double width)
	{
		this.width = width;
		if (this.length > 0.0 && this.height > 0.0) this.calcVolumeAndSurfaceArea();
		return this;
	}
	
	// Pre: height > 0
	// Post: Height of the Box object set to length; Volume and
	//	 surface area of the box recalculated if length and width have been set.
	// Return: the implicit argument passed to setHeight
	public Box setHeight(double height)
	{
		this.height = height;
		if (this.length > 0.0 && this.width > 0.0) this.calcVolumeAndSurfaceArea();
		return this;
	}
	
	// Return: length of the Box object
	public double getLength()
	{
		return this.length;
	}
	
	// Return: width of the Box object
	public double getWidth()
	{
		return this.width;
	}
	
	// Return: height of the Box object
	public double getHeight()
	{
		return this.height;
	}
	
	// Return: volume of the Box object
	public double getVolume()
	{
		return this.volume;
	}
	
	// Return: surface area of the Box object
	public double getSurfaceArea()
	{
		return this.surfaceArea;
	}
	
	// Output: a String with all properties of the Box object.
	//   Each on a new line in the format "Property: value".
	public String toString()
	{
		return "Length: " + this.getLength() + "\n"
	     + "Width: " + this.getWidth() + "\n"
		 + "Height: " + this.getHeight() + "\n"
		 + "Volume: " + this.getVolume() + "\n"
		 + "Surface area: " + this.getSurfaceArea();
	}
	
	// Post: volume and surface area of the Box object recalculated.
	private void calcVolumeAndSurfaceArea()
	{
		this.volume = this.length * this.width * this.height;
		
		this.surfaceArea = 2 * (this.width * this.length + 
			this.height * this.length + this.height * this.width);
	}
}