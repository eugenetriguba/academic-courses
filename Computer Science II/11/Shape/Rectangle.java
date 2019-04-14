class Rectangle extends Shape2D
{
    protected double length;
    protected double width;

    /* This Rectangle object properly initialized.
    **
    ** Post: Length of this Rectangle object set to 0.0.
    **        Width of this Rectangle object set to 0.0.
    **        Area and perimeter set to 0.0.
    */
    public Rectangle()
    {
        super();
        length = 0.0;
        width = 0.0;
    }

    /* This Rectangle object properly initialized.
    **
    ** Param length, width: must be positive values.
    **
    ** Post: Length of this Rectangle object set to length.
    **       Width of this Rectangle object set to width.
    **       Area and perimeter are calculated and set.
    */
    public Rectangle(double length, double width)
    {
        super();
        setLength(length)
        setWidth(width)
    }

    // Return: the length of this Rectangle object.
    public double getLength()
    {
        return length;
    }

    // Return: the width of this Rectangle object.
    public double getWidth()
    {
        return width;
    }

    /* Sets the length of this Rectangle object.
    **
    ** Param length: must be a positive value
    ** Post: If length is positive, this object's length is set to length.
    **       Otherwise, this object's length is set to 0.0.
    **       Area and perimeter are calculated and set.
    ** Return: this Rectangle object.
    */
    public Rectangle setLength(double length)
    {
        if (length > 0.0) this.length = length;
        else 
        {
            System.out.printf("%.2f is invalid because it is less than 0.0.\n", length);
            System.out.println("Length has been set to 0.0 instead.");
            this.length = 0.0;
        }
        calcAreaAndPerimeter();
        return this;
    }

    /* Param width: a positive value to set the width of this object to.
    ** Post: If width is positive, this object's width is set to width.
    **       Otherwise, this object's width is set to 0.0.
    **       Area and perimeter are calculated and set.
    ** Return: this Rectangle object.
    */
    public Rectangle setWidth(double width)
    {
        if (width > 0.0) this.width = width;
        else 
        {
            System.out.printf("%.2f is invalid because it is less than 0.0.\n", width);
            System.out.println("Width has been set to 0.0 instead.");
            this.width = 0.0;
        }
        calcAreaAndPerimeter();
        return this;
    }

    /* Param:  obj should be a Rectangle otherwise false will always be
    **         returned.
    ** Return: True if obj is a Rectangle and has a length and width
    **         equal to this Rectangle object.
    **         False otherwise.
    */
    public boolean equals(Object obj)
    {
        if (obj instanceof Rectangle)
        {
            Rectangle rect = (Rectangle) obj;
            if (length == rect.length && width == rect.width) return true;
            else return false;
        }
        else return false;
    }

    // Return: "Length: value Width: value Area: value Perimeter: value"
    //          where value is replaced with the actual values of this object.
    public String toString()
    {
        return "Length: " + length + " Width: " + width + " Area: " + area + 
            " Perimeter: " + perimeter;
    }

    /* Draw this Rectangle to the screen using star characters.
    **
    ** Output: This Rectangle object printed to the screen using its
    **         length and width dimensions.
    **         e.g. if length is 3 and width is 5, draw would print:
    **         *****
    **         *****
    **         *****
    */
    public void draw()
    {
        for (int length = 0; length < this.length; ++length)	
		{						
            for (int width = 0; width < this.width; ++width)
                System.out.print("*");
                
			System.out.println();
		}
    }

    /* Post: If this object's length and width are greater than 0.0, 
    **        area and perimeter are calculated and set.
    **        Otherwise, area and perimeter are set to 0.0.
    */
    private void calcAreaAndPerimeter()
    {
        if (length <= 0.0 || width <= 0.0) 
        {
            area = 0.0;
            perimeter = 0.0;
        }
        else
        {
            area = length * width;
            perimeter = (length * 2) + (width * 2);
        }
    }
}