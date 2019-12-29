// Eugene Triguba
// ytriguba@ole.augie.edu
// Homework 2: 27-2 Java Collections
// Demo.java

import java.util.TreeSet;
import java.util.Comparator;

public class Demo
{

    // A Demo to test out the Comparator interface
	public static void main(String[] args) 
	{
        TreeSet<Circle> ts = new TreeSet<Circle>(new ascendingOrder());
        
        ts.add(new Circle(2));
        ts.add(new Circle(1.5));
        ts.add(new Circle(2));
        ts.add(new Circle(5));
        System.out.println(ts);	
        
		if (ts.remove(new Circle(2))) System.out.println("removed");
		else System.out.println("not removed");
	}
}


// A Comparator for Circles where the compareTo
// is set up in a way which will result in the
// Circles sorted in ascending order.
class ascendingOrder implements Comparator<Circle>
{
     // Return: c1 < c2 then -1
     //         c1 > c2 then 1
     //         c1 == c2 then 0
    public int compare(Circle c1, Circle c2)	
	{
        double c1Radius = c1.getRadius();
        double c2Radius = c2.getRadius();

		if (c1Radius < c2Radius) return -1;
        else if (c1Radius > c2Radius) return 1;
        else return 0;
	}
}

// A class modeling a circle. It has a radius, can get it's
// radius, convert itself to a String, and check if it's equal 
// to another Object.
class Circle
{
    private double radius;		
    
    // Post: this Circle's radius set to 0.0
	public Circle()					
	{
		radius = 0.0;	
    }					
    
    // Post: Sets this Circle's radius to radius
	public Circle(double radius)				
	{
		this.radius = radius;
    }
    
    // Return: this Circle's radius
    public double getRadius()
    {
        return radius;
    }

    // Return: this Circle's radius as a String
    //         in the format: "Radius: 5.3"
	public String toString()
	{
		return "Radius: " + radius; 
    }
    
    // Check if this Circle is equal to obj
    // Return: true if this Circle's radius is equal to obj's
    //         false otherwise
	public boolean equals(Object obj)	
	{
		if (obj instanceof Circle)
		{
			Circle c = (Circle) obj;	
            if (radius == c.radius) 
                return true;
		}
		return false;
	}
}
