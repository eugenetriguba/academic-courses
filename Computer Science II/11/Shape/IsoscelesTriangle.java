// Eugene Triguba
// ytriguba17@ole.augie.edu
// IsoscelesTriangle.java
// Homework 11: 12 Inheritance

// Class IsoscelesTriangle models an isosceles triangle that knows its base,
// height, side length, and can draw itself to the screen.
public class IsoscelesTriangle extends Shape2D
{
    private double base;
    private double height;
    private double sideLength;

    // Post: Area, perimeter, base, height, and sideLength of this 
    //       object initialized to 0.0.
    public IsoscelesTriangle()
    {
        super();
        base = 0.0;
        height = 0.0;
        sideLength = 0.0;
    }

    /* Post: If height is positive, this object's height is set to height.
    **       Otherwise, this object's height is set to 0.0.
    **       If base is positive, this object's base is set to base.
    **       Otherwise, this object's base is set to 0.0.
    **       sideLength is calculated and set.
    **       Area and perimeter are calculated and set.
    */
    public IsoscelesTriangle(double base, double height)
    {
        super();
        setBase(base);
        setHeight(height);
    }

    // Return: the base of this IsoscelesTriangle object.
    public double getBase()
    {
        return base;
    }

    // Return: the height of this IsoscelesTriangle object.
    public double getHeight()
    {
        return height;
    }

    // Return: the sideLength of this IsoscelesTriangle object.
    public double getSideLength()
    {
        return sideLength;
    }

    /* Post: If base is positive, this object's base is set to base.
    **       Otherwise, this object's base is set to 0.0.
    **       sideLength is calculated and set.
    **       Area and perimeter are calculated and set.
    ** Return: this IsoscelesTriangle object.
    */
    public IsoscelesTriangle setBase(double base)
    {
        if (base > 0.0) this.base = base;
        else 
        {
            System.out.printf("%.2f is invalid because it is less than 0.0.\n", base);
            System.out.println("Base has been set to 0.0 instead.");
            this.base = 0.0;
        }
        calcSideLength();
        calcAreaAndPerimeter();
        return this;
    }

    /* Post: If height is positive, this object's height is set to height.
    **       Otherwise, this object's height is set to 0.0.
    **       sideLength is calculated and set.
    **       Area and perimeter are calculated and set.
    ** Return: this IsoscelesTriangle object.
    */
    public IsoscelesTriangle setHeight(double height)
    {
        if (height > 0.0) this.height = height;
        else 
        {
            System.out.printf("%.2f is invalid because it is less than 0.0.\n", height);
            System.out.println("Height has been set to 0.0 instead.");
            this.height = 0.0;
        }
        calcSideLength();
        calcAreaAndPerimeter();
        return this;
    }

    // Return: True if obj is a IsoscelesTriangle and has a base and height
    //         equal to this IsoscelesTriangle object.
    //         False otherwise.
    public boolean equals(Object obj)
    {
        if (obj instanceof IsoscelesTriangle)
        {
            IsoscelesTriangle triangle = (IsoscelesTriangle) obj;
            if (base == triangle.base && height == triangle.height) return true;
            else return false;
        }
        else return false;
    }

    // Return: "Base: value Height: value Side Length: value Area: value Perimeter: value"
    //         where value is replaced with the actual values of this object.
    public String toString()
    {
        return "Base: " + truncateDecimal(base) + " Height: " + truncateDecimal(height) + 
            " Side Length: " + truncateDecimal(sideLength) + " Area: " + 
			truncateDecimal(area) + " Perimeter: " + truncateDecimal(perimeter);
    }

    /* Draw this IsoscelesTriangle to the screen using star characters.
    **
    ** Output: This IsoscelesTriangle object printed to the screen using its
    **         height dimension.
    **         e.g. if height is 5, draw would print:
    **              *
    **             * *
    **            * * *
    **           * * * *
    **          * * * * *
    */
    public void draw()
    {
        int height = (int) this.height;
        for (int row = height - 1; row >= 0; --row)	
		{						
   			for (int blank = 0; blank < row; ++blank)						
                System.out.print(" ");	
                
   			for (int star = 0; star < height - row; ++star)						
                System.out.print("* ");	
                
			System.out.println();
		}
    }

    /* Truncate a digit to two decimal places by multiplying the digit by 100.0,
    ** converting the digit to an int, and converting the digit back to a double by
    ** dividing the digit by 100.0.
    **
    ** Return: digit truncated to two decimal places
    */
    private double truncateDecimal(double digit)
    {
        int temp = (int) (digit * 100.0);
        double truncatedDigit = ((double) temp) / 100.0;
        return truncatedDigit;
    }

    /* Calculate the two equal side lengths of this IsoscelesTriangle object
    ** using its base and height.
    **
    ** Post: If this IsoscelesTriangle object's base and height are set to a
    **       value greater than 0.0, sideLength is calculated and set. 
    **       Otherwise, sideLength is set to 0.0.
    */
    private void calcSideLength()
    {
        if (base <= 0.0 || height <= 0.0) sideLength = 0.0;
        else sideLength = .5 * (Math.sqrt(Math.pow(base, 2) + (4 * Math.pow(height, 2))));
    }

    // Post: If this object's base and height are greater than 0.0, 
    //       area and perimeter are calculated and set.
    //       Otherwise, area and perimeter are set to 0.0.
    private void calcAreaAndPerimeter()
    {
        if (base <= 0.0 || height <= 0.0) 
        {
            area = 0.0;
            perimeter = 0.0;
        }
        else
        {
            area = (height * base) / 2;
            perimeter = (2 * sideLength) + base;
        }
    }
}