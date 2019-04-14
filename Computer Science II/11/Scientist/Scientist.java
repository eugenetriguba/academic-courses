// Eugene Triguba
// ytriguba17@ole.augie.edu
// Scientist.java
// Homework 11: 12 Inheritance

// Class Scientist represents a scientist at a company. A Scientist object
// knows its id, name, salary, and numPub.
public class Scientist extends Employee
{
    private int numPub;

    // Post: Id, name, salary of this Scientist initialized to -1, "unknown", -1.0.
    //       numPub set to 0.
    public Scientist()
    {
        super();
        numPub = 0;
    }

    // Post: Id, name, salary, and numPub of this Scientist initialized to the 
	//       parameters id, name, salary, numPub.
    public Scientist(int id, String name, double salary, int numPub)
    {
        super(id, name, salary);
        this.numPub = numPub;
    }

    // Return: this Scientist object's numPub.
    public int getNumPub()
    {
        return numPub;
    }

    // Post: set this Scientist object's numPub to numPub.
    // Return: this Scientist object.
    public Scientist setNumPub(int numPub)
    {
        this.numPub = numPub;
        return this;
    }

    // Return: The id, name, salary, and numPub of this Scientist in the format:
    //         "ID: id Name: name Salary: salary NumPub: numPub" 
    public String toString()	
    {
        return "ID: " + getId() + " Name: " + getName() + " Salary: " + 
            getSalary() + " NumPub: " + numPub;
    }
}