// Eugene Triguba
// ytriguba17@ole.augie.edu
// DutyApp.java
// Chapter 13: Interface

import java.util.Scanner;

public class DutyApp
{
    /*  A program which allows the user to create either a Student or a
    **  Professor. The program displays a menu asking for 1. Student, 2.
    **  Professor, 3. Quit. The user enters 1 or 2, followed by the id or name
    **  of the person. The program prints the duty of the person, and then asks
    **  the user to enter 1, 2, or 3 again. The program will continue until the
    **  user selects 3 to quit. 
    **
    **  Input:  The user enters 1 or 2 followed by the id or name of the person
    **          via the keyboard.
    **  Output: For each person entered by the user, the duty of the person
    **          displayed on the screen 
    */
	public static void main(String[] args) 
    {
        Scanner keyboard = new Scanner(System.in);
        int response = 0;
        Duty person;

        do
        {
            System.out.print("1. Student, 2. Professor, 3. Quit: ");
            response = Integer.parseInt(keyboard.nextLine());

            if (response == 1) 
            {
                person = getStudent(keyboard);
                System.out.println(person.getDuty());
            }
            else if (response == 2) 
            {
                person = getProfessor(keyboard);
                System.out.println(person.getDuty());
            }

        } while (response != 3);
    }

    // Input: The user enters the id of the Student via the keyboard.
    // Post: One line read from keyboard
    // Return: The Student with the inputted id.
    public static Student getStudent(Scanner keyboard)
    {
        System.out.print("Enter ID: ");
        int id = Integer.parseInt(keyboard.nextLine());

        return new Student(id);
    }
    
    // Input: The user enters the name of the Professor via the keyboard.
    // Post: One line read from keyboard
    // Return: The Professor with the inputted name.
    public static Professor getProfessor(Scanner keyboard)
    {
        System.out.print("Enter name: ");
        String name = keyboard.nextLine();

        return new Professor(name);
    }

}

// An interface to describe the duties of a person.
interface Duty			
{
    public String getDuty();	
}

// A class that models a student which has an ID and can tell you what his/her
// duty is.
class Student implements Duty
{
    private int id;

    // Post: id set to -1
    public Student()
    {
        id = -1;
    }

    // Post: this object's id is set to id.
    public Student(int id)
    {
        this.id = id;
    }

    // Return: id of this object.
    public int getId()
    {
        return id;
    }

    // Post: set the id of this object to id.
    // Return: this Student object.
    public void setId(int id)
    {
        this.id = id;
    }

    // Return: the id of the Student and his/her duty.
    public String getDuty()
    {
        return "Duty of the student with an id of " + id + " is to study 40 hours a week";
    }
}

// A class that models a Professor which has a name and can tell you what his/her
// duty is.
class Professor implements Duty
{
    private String name;

    // Post: name set to unknown.
    public Professor()
    {
        name = "unknown";
    }

    // Post: this object's name is set to name.
    public Professor(String name)
    {
        this.name = name;
    }

    // Return: the name of this object.
    public String getName()
    {
        return name;
    }

    // Post: this object's name set to name.
    // Return: this Professor object.
    public void setName(String name)
    {
        this.name = name;
    }

    // Return: the name of the Professor and his/her duty.
    public String getDuty()
    {
        return "Duty of Professor " + name + " is to return homework on time";
    }
}


