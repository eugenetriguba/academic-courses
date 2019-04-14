// Eugene Triguba
// ytriguba17@ole.augie.edu
// StudentDB.java
// Homework 6: Binary IO

import java.util.Iterator;
import java.util.Scanner;
import java.util.Vector;
import java.io.*;

class Student
{
    private int id;
   	private String name;
    private int age;
    
    // Post: Initialize this Student's id to 1, name to "Unknown", and age to
    //      -1.
	public Student() 
    { 
        this(-1, "Unknown", -1);
    }

    // Post: Initialize this Student's id to id, name to name, and age to
    //      age.
	public Student (int id, String name, int age) 
    { 
        this.id = id;
        this.name = name;
        this.age = age;
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

    // Pre: obj must be a Student object.
    // Return: true if id of this Student == id of obj, false otherwise.
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
    
    // Return: The id, name, and age of this Student in the 
    //         format "ID: id - Name: name - Age: age" 
	public String toString()
	{
        return String.format("ID: %d - Name: %s - Age: %d", 
        this.id, this.name, this.age);
    }

    // Pre: inputFile has an int, string, and int as it's next three data items.
    // Post: reads in the next int, string, and int from inputFile and assigns
    //       this Student's id, name, and age to those values.
    // Output: An error message and the stack trace if an IOException occurs.
    public void read(DataInputStream inputFile) 
    { 
		try
		{
	        this.id = inputFile.readInt();
	        this.name = inputFile.readUTF();
	        this.age = inputFile.readInt();
		}
		catch (IOException e)
		{
            System.out.println("Data could not be read.");
            e.printStackTrace();
		}
    }

    // Post: writes out this Student's id (int), name (UTF), and age (int) to
    //       outputFile
    // Output: An error message and the stack trace if an IOException occurs.
	public void write(DataOutputStream outputFile) 
    { 
		try
		{
			outputFile.writeInt(this.id);
	        outputFile.writeUTF(this.name);
	        outputFile.writeInt(this.age);
		}
		catch (IOException e)
		{
            System.out.println("Data could not be written out.");
            e.printStackTrace();
		}
    }
}	

public class StudentDB
{  
	private static Scanner keyboard = new Scanner(System.in);
    
    // Desc: Maintains a database of Student records. The database is 
	//       stored in binary file Student.data
	// Input: User enters commands from keyboard to manipulate database.
	// Output: Database updated as directed by user.
     	public static void main(String[] args) throws IOException
   	{
		Vector<Student> students = new Vector<Student>();
   		File inputFile = new File("Student.data");
		if (inputFile.exists()) loadStudent(students);
		int choice=5;					
		do {
			System.out.println("\t1. Add a Student record");	
			System.out.println("\t2. Remove a Student record");	
   			System.out.println("\t3. Print a Student record");	
   			System.out.println("\t4. Print all Student records");	
   			System.out.println("\t5. Quit");	
			choice = keyboard.nextInt();
			keyboard.nextLine();
   			switch (choice)						
			{
				case 1: addStudent(students); break;		
				case 2: removeStudent(students); break;		
				case 3: printStudent(students); break;		
				case 4: printAllStudent(students); break;		
				default: break;	
			}
		} while (choice != 5);
 		storeStudent(students); 
    }	
    	
	// Input: user enters an integer (id), a string (name), an integer (age) from the 
    //	      keyboard all on separate lines
	// Post: The input record added to students if id does not exist
	// Output: various prompts as well as "Student added" or 
	//		   "Add failed: Student already exists" printed on the screen accordingly
    public static void addStudent(Vector<Student> students) 
	{
        Student stu = new Student();

		System.out.print("Student ID: ");
		stu.setId(Integer.parseInt(keyboard.next()));	
			
		System.out.print("Student name: ");
		stu.setName(keyboard.next());	
					
		System.out.print("Student Age: ");
        stu.setAge(Integer.parseInt(keyboard.next()));		
        
		if (students.contains(stu)) 
			System.out.println("Add failed: Student already exists.");
        else 
        {
			students.add(stu);
            System.out.println("Student added.");
        }
    }
    
	// Input: user enters an integer (id) from the keyboard	
	// Post: The record in students whose id field matches the 
	// 		 input removed from students.
	// Output: various prompts as well as "Student removed" or 
	//		   "Remove failed: Student does not exist" printed 
	//         on the screen accordingly
    public static void removeStudent(Vector<Student> students) 
	{
        System.out.print("Student ID: ");
		int id = Integer.parseInt(keyboard.next());		
				
		Student stu = new Student(id, "", 0);
        if (students.remove(stu)) System.out.println("Student removed");
        else System.out.println("Remove failed: Student does not exist");
    }
    
	// Input: user enters an integer (id) from the keyboard	
	// Output: various prompts as well as the record in students whose id 
	// 		   field matches the input printed on the screen or 
	// 		   "Print failed: Student does not exist" printed on the 
	//			screen accordingly.
    public static void printStudent(Vector<Student> students) 
	{
        System.out.print("Student ID: ");
		int id = Integer.parseInt(keyboard.next());						
		
		Student stu = new Student(id, "", 0);
		int index = students.indexOf(stu);
		if (index != -1)
		{
			stu = students.get(index);
            System.out.println(stu);
		}
        else System.out.println("Print failed: Student does not exist");
    }
    
   	// Output: All records in students printed on the screen.
	public static void printAllStudent(Vector<Student> students) 
	{
        Iterator<Student> iter = students.iterator();
        while (iter.hasNext()) System.out.println(iter.next());
    }
    
  	// Input: Binary file Student.data must exist and contains student records.
	// Post: All records in Student.data loaded into vector students.
    public static void loadStudent(Vector<Student> students) throws IOException
	{
        try
        {
            System.out.println("Loading database...");
            DataInputStream inputFile = new DataInputStream(new FileInputStream("Student.data"));
                   
		    while (inputFile.available() > 0)
		    {
		    	Student stu = new Student();
		    	stu.read(inputFile);
		    	students.add(stu);
            }
            inputFile.close();
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Student.data was not found.");
        }
        catch (EOFException e) {}
	}
	
	// Output: All records in students written to binary file Student.data.
    public static void storeStudent(Vector<Student> students) throws IOException
	{
        System.out.println("Saving database ...");	
        DataOutputStream outputFile = new DataOutputStream(new FileOutputStream("Student.data"));
          
        Iterator<Student> iter = students.iterator();
        while (iter.hasNext()) 
        	iter.next().write(outputFile);
        
        outputFile.close();
	}
}
