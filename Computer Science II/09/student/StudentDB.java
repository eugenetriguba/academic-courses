// Eugene Triguba
// ytriguba17@ole.augie.edu
// StudentDB.java
// Homework 9: 10-2 ThinkingInObjects

import java.util.Iterator;
import java.util.Scanner;
import java.util.Vector;
import java.io.*;

public class StudentDB
{   
	// Desc: Maintains a database of Student records. The database is the text
    //   file Student.txt. 
    // Input: User enters commands from keyboard to manipulate database.
	//	 The database Student.txt, which contains student information as follows:
	//	   id
	//	   name
	//	   age
	//	   id
	//	   name
	//	   age
	//	   …zxf
	// Output: Various messages. Database Student.txt updated as directed by user.
    public static void main(String[] args) throws IOException
   	{
        StudentDatabase db = new StudentDatabase();
        db.load("Student.txt");
		db.modify();
        db.save("Student.txt");
    }
}

// Desc: Models a database of Students which can load in a file of Students, 
//   modify its Vector of students by adding, removing, or updating a Student,
//   print a Student, print all students, and save students Vector to an 
//   external file.
class StudentDatabase
{
    private Vector<Student> students;
    private static Scanner keyboard;

    // Post: this StudentDatabase's students is initialized to an empty Vector.
    //   this StudentDatabase's keyboard is initialized to System.in.
    public StudentDatabase()
    {
        this.students = new Vector<Student>();
        this.keyboard = new Scanner(System.in);
    }

    // Post: this StudentDatabase's students is initialized to students.
    public StudentDatabase(Vector<Student> students)
    {
        this.students = students;
    }

    // Input: Text file fileName must exist and contains student records.
    //   fileName must contain student information in the following format:
	//	   id
	//	   name
	//	   age
	//	   id
	//	   name
	//	   age
	//	   ...
    // Post: All records in fileName loaded into this StudentDatabase's students
    //   Vector if fileName exists.
    public void load(String fileName) throws FileNotFoundException
    {
        File f = new File(fileName);
        if (f.exists())
        {
            System.out.println("Loading database...");
               	
		    Scanner inFile = new Scanner(f);
		    while (inFile.hasNextLine())
		    {
		    	Student stu = new Student();
		    	stu.read(inFile);
		    	students.add(stu);
		    }
		    inFile.close();
        }
    }

    // Desc: prompt the user by showing them a menu of options
    //   and reading in that option to process that action.
    // Input: reads in a choice from the keyboard to select a menu item.
    // Output: shows the menu of available options.
    // Post: Modifies this StudentDatabase's students Vector depending
    //   on the choice by adding, removing, or updating a Student in it.
    public void modify()
    {
        int choice = 6;					
		do {
            showMainMenu();
			choice = keyboard.nextInt();					
   			this.processMainChoice(choice);
        } while (choice > 0 & choice < 6);
    }

	// Input: user enters an integer (id), a string (name), an integer (age)
	//   from the keyboard all on separate lines
	// Post: The input record added to StudentDatabase's students if id does
	//   not exist
	// Output: various prompts as well as "Student added" or "Add failed:
    //   Student already exists" printed on the screen accordingly
    public void add() 
	{
		Student stu = new Student();

		System.out.print("Student ID: ");
		stu.setId(Integer.parseInt(keyboard.next()));	
			
		System.out.print("Student name: ");
		stu.setName(keyboard.next());	
					
		System.out.print("Student Age: ");
        stu.setAge(Integer.parseInt(keyboard.next()));		
        
        System.out.print("Student Major: ");
		stu.setMajor(keyboard.next());
        
		if (this.students.contains(stu)) 
			System.out.println("Add failed: Student already exists.");
        else 
        {
			students.add(stu);
            System.out.println("Student added.");
        }
    }
    
	// Input: user enters an integer (id) from the keyboard	
	// Post: The Student in this StudentDatabase's students is removed whose id
	//   field matches the input from the user.
	// Output: various prompts as well as "Student removed" or "Remove failed:
	//   Student does not exist" printed on the screen accordingly.
    public void remove() 
	{
		System.out.print("Student ID: ");
		int id = Integer.parseInt(keyboard.next());		
				
		Student stu = new Student(id, "", 0, "");
        if (this.students.remove(stu)) System.out.println("Student removed");
        else System.out.println("Remove failed: Student does not exist");
    }

    // Desc: update a Student in this StudentDatabase's students Vector.
    // Input: user enters an integer (the id) from the keyboard to find which
    //   Student to update; reads in a choice from the keyboard to select a menu
    //   item.
    // Output: prompts the user; shows the menu of available options;
    //   shows a confirmation message.
    // Post: Modifies the Student from the StudentDatabase's students Vector
    //   with the inputted ID. ID, name, age, or major may be updated depended on
    //   the selected option.
    public void update()
    {
        System.out.print("Student ID: ");
        int id = Integer.parseInt(keyboard.next());	
        Student stu = new Student(id, "", 0, "");
        
        if (this.students.contains(stu)) 
        {
            stu = this.students.get(this.students.indexOf(stu));
            showUpdateMenu();
            processUpdateChoice(stu);
            System.out.println("Student updated");
        }
        else System.out.println("Update failed: Student doesn't exist.");
    }
    
	// Input: user enters an integer (id) from the keyboard	
	// Output: prompt to input id; Student in this StudentDatabase's
	//   students Vector whose id field matches the inputted id or "Print failed:
	//   Student does not exist" printed on the screen if a Student with the
	//   inputted id cannot be found.
    public void print() 
	{
		System.out.print("Student ID: ");
		int id = Integer.parseInt(keyboard.next());						
		
		Student stu = new Student(id, "", 0, "");
		int index = this.students.indexOf(stu);
		if (index != -1)
		{
			stu = this.students.get(index);
            System.out.println(stu);
		}
        else System.out.println("Print failed: Student does not exist");
    }
    
   	// Output: All records in this StudentDatabase's students Vector printed on
   	//   the screen.
	public void printAll() 
	{
		Iterator<Student> iter = this.students.iterator();
        while (iter.hasNext()) System.out.println(iter.next());
    }
    
	// Output: A confirmation that the database is being saved; All items in
	//   this StudentDatabase's students Vector written to fileName.
    public void save(String fileName) throws FileNotFoundException
	{
        System.out.println("Saving database ...");	
          
        PrintWriter outFile = new PrintWriter(fileName);
        Iterator<Student> iter = this.students.iterator();
        while (iter.hasNext()) iter.next().write(outFile);
		outFile.close();
    }

    // Output: print a menu of available options to the screen for the main
    //   database menu such as:
    //     1. Add a Student record
    //     2. Remove a Student record
    //     3. Update a Student record
    //     4. Print a Student record
    //     5. Print all Student records
    //     6. Quit
    private static void showMainMenu()
    {
        System.out.println("\t1. Add a Student record");	
        System.out.println("\t2. Remove a Student record");	
        System.out.println("\t3. Update a Student record");	
        System.out.println("\t4. Print a Student record");	
        System.out.println("\t5. Print all Student records");	
        System.out.println("\t6. Quit");
    }

    // Post: if choice is
    //   1: a student is added to this StudentDatabase's students Vector. 
    //   2: a student is removed from this StudentDatabase's students Vector.
    //   3: the user is prompted for what to update and updates the student
    //     accordingly a student in this StudentDatabase's students Vector.
    //   4: a student is printed from this StudentDatabase's students Vector. 
    //   5: all students are printed from this StudentDatabase's students Vector.
    //   6: nothing happens (user signifies they want to quit).
    private void processMainChoice(int choice)
    {
        switch (choice)						
        {
            case 1: this.add(); break;		
            case 2: this.remove(); break;	
            case 3: this.update(); break;	
            case 4: this.print(); break;		
            case 5: this.printAll(); break;		
            default: break;	
        }
    }

    // Output: print a menu of available options to the screen for the update
    //   student menu such as:
    //     1. Update ID
    //     2. Update name
    //     3. Update age
    //     4. Update major
    private static void showUpdateMenu()
    {
        System.out.println("\t1. Update ID");	
        System.out.println("\t2. Update name");	
        System.out.println("\t3. Update age");	
        System.out.println("\t4. Update major");
    }

    // Desc: reads in and processes a choice from the user.
    // Input: enter in a integer to correspond to a menu item
    //   1: the new ID for stu
    //   2: the new name for stu
    //   3: the new age for stu
    //   4: the new major for stu
    // Output: prompt for the id, name, age, or major depending on the choice
    //   from the user.
    // Post: updates the id, name, age, or major of stu to the inputted value.
    private void processUpdateChoice(Student stu)
    {
        int choice = Integer.parseInt(keyboard.next());
        switch (choice)			
        {
            case 1: 
                System.out.print("Enter a new ID: ");
                int id = Integer.parseInt(keyboard.next());	
                stu.setId(id);
                break;		
            case 2: 
                System.out.print("Enter a new name: ");
                String name = keyboard.next();	
                stu.setName(name);
                break;		
            case 3: 
                System.out.print("Enter new age: ");
                int age = Integer.parseInt(keyboard.next());	
                stu.setAge(age);
                break;			
            case 4: 
                System.out.print("Enter new major: ");
                String major = keyboard.next();	
                stu.setMajor(major);
                break;		
            default: break;	
        }
    }
}