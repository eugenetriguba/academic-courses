// Eugene Triguba
// ytriguba17@ole.augie.edu
// StudentDB.java
// Homework 7: RandomAccessFile

import java.util.Iterator;
import java.util.Scanner;
import java.util.Vector;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;
import java.io.RandomAccessFile;

public class StudentDB
{  
    private Scanner keyboard;
    private Vector<Student> students;
    private File inputFile;

    // Pre: fileName should be a binary file and exist
    public StudentDB(String fileName)
    {
        keyboard = new Scanner(System.in);
        students = new Vector<Student>();

        try { inputFile = new File(fileName); }
        catch (FileNotFoundException e) 
        { 
            
        }

		if (inputFile.exists()) this.load();
    }
    	
	// Input: user enters an integer (id), a string (name), an integer (age) from the 
    //	      keyboard all on separate lines
	// Post: The input record added to students if id does not exist
	// Output: various prompts as well as "Student added" or 
	//         "Add failed: Student already exists" printed on the screen accordingly
    public void add() 
	{
        Student stu = new Student();

		System.out.print("Student ID: ");
		stu.setId(Integer.parseInt(keyboard.next()));	
			
		System.out.print("Student name: ");
		stu.setName(keyboard.next());	
					
		System.out.print("Student Age: ");
        stu.setAge(Integer.parseInt(keyboard.next()));		
        
        if (this.students.contains(stu)) 
            System.out.println("Add failed: Student already exists.");
        else 
        {
			this.students.add(stu);
            System.out.println("Student added.");
        }
    }
    
	// Input: user enters an integer (id) from the keyboard	
	// Post: The record in students whose id field matches the input 
	//       removed from students.
	// Output: various prompts as well as "Student removed" or 
	//         "Remove failed: Student does not exist" printed 
	//         on the screen accordingly
    public void remove() 
	{
        System.out.print("Student ID: ");
		int id = Integer.parseInt(keyboard.next());		
				
		Student stu = new Student(id, "", 0);
        if (this.students.remove(stu)) System.out.println("Student removed");
        else System.out.println("Remove failed: Student does not exist");
    }
    
	// Input: user enters an integer (id) from the keyboard	
	// Output: various prompts as well as the record in students whose id field 
	//         matches the input printed on the screen or "Print failed: Student 
	//         does not exist" printed on the screen accordingly
    public void print() 
	{
        System.out.print("Student ID: ");
		int id = Integer.parseInt(keyboard.next());						
		
		Student stu = new Student(id, "", 0);
		int index = this.students.indexOf(stu);
		if (index != -1)
		{
			stu = this.students.get(index);
            System.out.println(stu);
		}
        else System.out.println("Print failed: Student does not exist");
    }
    
   	// Output: All records in students printed on the screen.
	public void printAll() 
	{
        Iterator<Student> iter = this.students.iterator();
        while (iter.hasNext()) System.out.println(iter.next());
    }
    
  	// Input: Binary file Student.data must exist and contains student records.
  	// Post: All records in this StudentDB's inputFile loaded into it's students
  	//       vector.
    public void load()
	{
        try
        {
            System.out.println("Loading database...");
            RandomAccessFile inputFile = new RandomAccessFile(this.inputFile, "r");
            int position = 0;

		    while (position < inputFile.length())
		    {
                Student stu = new Student();
                stu.read(inputFile);
                students.add(stu);
                position += stu.size();
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Student.data was not found.");
            e.printStackTrace();
        }
        catch (IOException e)
        {
            System.out.println("An input/output exception occurred.");
            e.printStackTrace();
        }
    } 

    public void modify()
    {
        int choice = 5;					
		do {
			this.printMenu();
			choice = this.keyboard.nextInt();
			this.keyboard.nextLine();
   			switch (choice)						
			{
				case 1: this.add(); break;		
				case 2: this.remove(); break;		
				case 3: this.print(); break;		
				case 4: this.printAll(); break;		
				default: break;	
			}
		} while (choice != 5);
    }
    
	// Output: All records in students written to binary file Student.data.
    public void store()
	{
        try
        {
            System.out.println("Saving database ...");	
            RandomAccessFile outputFile = new RandomAccessFile("Student.data", "rw");
            outputFile.setLength(0);
            
            Iterator<Student> iter = this.students.iterator();
            while (iter.hasNext()) iter.next().write(outputFile);
            
            outputFile.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public void printMenu()
    {
        System.out.println("\t1. Add a Student record");	
        System.out.println("\t2. Remove a Student record");	
        System.out.println("\t3. Print a Student record");	
        System.out.println("\t4. Print all Student records");	
        System.out.println("\t5. Quit");	
    }

    // Desc: Maintains a database of Student records. The database is 
	//       stored in binary file Student.data
	// Input: User enters commands from keyboard to manipulate database.
	// Output: Database updated as directed by user.
    public static void main(String[] args)
   	{
        StudentDB db = new StudentDB("Student.data");
		db.modify();
 		db.store(); 
    }	
}

class Student
{
    private int id;
   	private char[] name;
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
        this.name = stringToCharArray(name);
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
        this.name = stringToCharArray(name);
    }

    // Return: Name of this Student
    public String getName() 
    { 
        return new String(name).trim(); 				
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
        this.id, new String(name).trim(), this.age);
    }

    // Pre: inputFile has an int, string, and int as it's next three data items.
    // Post: reads in the next int, string, and int from inputFile and assigns
    //       this Student's id, name, and age to those values.
    // Output: An error message and the stack trace if an IOException occurs.
    public void read(RandomAccessFile inputFile) 
    { 
		try
		{
            this.id = inputFile.readInt();
            for(int i = 0; i < this.name.length; i++)
                this.name[i] = inputFile.readChar();
                
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
	public void write(RandomAccessFile outputFile) 
    { 
		try
		{
            outputFile.writeInt(this.id);

            for(int i = 0; i < this.name.length; i++)
                outputFile.writeChar(this.name[i]);
                
	        outputFile.writeInt(this.age);
		}
		catch (IOException e)
		{
            System.out.println("Data could not be written out.");
            e.printStackTrace();
		}
    }

    // Desc: size of the data items in this Student object in bytes
    //       50 (25 * 2) bytes for the name and 4 bytes each for 
    //       the id and age
    // Return: the integer 58
    public static int size()
    {
        return 58;
    }

    // Desc:
    // Return: 
    private char[] stringToCharArray(String word)
    {
        final int MAX_SIZE = 25;
        char[] result = new char[MAX_SIZE];
        int wordLength = word.length();

        if (wordLength > MAX_SIZE) wordLength = MAX_SIZE;

        for (int i = 0; i < wordLength; i++)
            result[i] = word.charAt(i);

        for (int i = wordLength; i < MAX_SIZE; i++)
            result[i] = ' ';

        return result;
    }
}	