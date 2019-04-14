// Eugene Triguba
// ytriguba17@ole.augie.edu
// Student.java
// Homework 9: 10-2 ThinkingInObjects

import java.util.Scanner;
import java.io.PrintWriter;

public class Student
{
    private int id;
    private String name;
    private int age;
    private String major;
    
    // Post: name, major, age, and id of this Student initialized to "unknown",
    //   "unknown" -1, -1.
	public Student () 
    { 
        this.name = "unknown"; 
        this.major = "unknown";
        this.age = -1; 
        this.id = -1;
    }

    // Post: Id, name, age of this Student initialized to the parameters id,
    //   name, age, and major.
	public Student (int id, String name, int age, String major) 
    { 
        this.id = id; 
        this.name = name; 
        this.age = age;
        this.major = major;
    }

    // Post: Id of this Student set to id.
	public void setId(int id) 
    { 
        this.id = id; 
    }

    // Return: Id of this Student
   	public int getId() 
    { 
        return this.id; 
    }

    // Post: Name of this Student set to name.
	public void setName(String name) 
    { 
        this.name = name;
    }

    // Return: Name of this Student
    public String getName() 
    { 
        return this.name; 				
    }

    // Post: Age of this Student set to age
	public void setAge(int age) 
    { 
        this.age = age; 
    }

    // Return: Age of this Student
   	public int getAge()
    { 
        return this.age; 
    }

    // Post: major of this Student set to major.
    public void setMajor(String major)
    {
        this.major = major;
    }

    // Return: major of this Student
    public String getMajor()
    {
        return this.major;
    }
    
    // Pre: obj must be a Student object.
    // Return: true if id of this Student == id of obj, false otherwise
    public boolean equals(Object obj)	
    {
        Student stu = (Student) obj;
		if (this.id == stu.id) return true;
		else return false;
    }
    
    // Return: 1 if id of this Student > id of stu
    //         0 if id of this Student == id of stu
    //        -1 if id of this Student < id of stu
	public int compareTo(Student stu)				
	{
		if (this.id == stu.id) return 0;
		else if (this.id > stu.id) return 1;
		else return -1;
    }
    
    // Return: The id, name, age, and major of this Student in the format "ID:
    //   id Name: name Age: age" 
	public String toString()
	{
        return "ID: "+ this.id +" Name: " + this.name + " Age: " + this.age + 
            " Major: " + this.major;
    }
    
	// Pre: inFile is a text file ready to be read with 4 lines of input: id,
	//   name, age, and major.
	// Post: The 4 lines of input from inFile read into this Student object.
	public void read(Scanner inFile) 
    { 
        this.id = Integer.parseInt(inFile.nextLine());
        this.name = inFile.nextLine();
        this.age = Integer.parseInt(inFile.nextLine());
        this.major = inFile.nextLine();
    }

	// Pre: outFile is a text file ready to be written 
	// Post: This Student object written to outFile as 4 lines: id, name, age,
	//   and major.
	public void write(PrintWriter outFile) 
    { 
        outFile.println(this.id);
        outFile.println(this.name);
        outFile.println(this.age);
        outFile.println(this.major);
    }
}
