// Eugene Triguba
// ytriguba17@ole.augie.edu
// Real.java
// Homework 6: 7-5 MoreOnObjects

// Desc: a Real type which is used as a wrapper class for double.
public class Real
{
	private double value;
	
    // Post: this newly allocated Real object's value initialized to 0.0.
	public Real()
	{
		this.value = 0.0;
	}
	
	// Post: this newly allocated Real object's value initialized to 
    //   value.
	public Real(double value)
	{
		this.value = value;
	}
	
	// Post: this Real object's value set to value. 
	// Return: this Real object
	public Real set(double value)
	{
		this.value = value;
		return this;
	}
	
	// Return: The value of this Real object. 
	public double doubleValue()
	{
		return this.value;
	}
	
	// Return: A String that represents the value of this Real object. 
	public String toString()
	{
		return "" + this.value;
	}
	
	// Return: 
    //   1 if the value of this Real object is greater than the value 
    //     of comparisonReal. 
    //   0 if the value of this Real object is equal to the value of
    //     comparisonReal. 
	//   -1 if the value of this Real object is less than the value of
    //     comparisonReal. 
	public int compareTo(Real comparisonReal)
	{
		if (this.value == comparisonReal.value) return 0;
		else if (this.value > comparisonReal.value) return 1;
		else return -1;
	}
}