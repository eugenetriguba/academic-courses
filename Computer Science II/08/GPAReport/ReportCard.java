import java.util.Scanner;
import java.io.PrintWriter;
import java.util.Vector;
import java.util.Iterator;

// Desc: an object modeling a report card which keeps
//   track of the name of whose report card this is,
//   their GP, GPA, total credit hours, total courses,
//   course names, credit hours, and grades. As well as
//   allows that information to be printed to the screen
//   or outputted to a file.
class ReportCard
{
    private String name;
    private double GP;
    private double GPA;
    private double totalCreditHours;
    private int totalCourses;
    private Vector<String> courseNames;
    private Vector<Double> creditHours;
    private Vector<String> grades;
    
    // Post: this ReportCard object's name is set to an empty string.
    //   courseNames, creditHours, and grades each initialized to an empty
    //   Vector of their types.
    //   totalCourses is set to the size of courseNames.
    //   GP, totalCreditHours, and GPA is calculated.
    public ReportCard()
    {
        this.name = "";
        this.courseNames = new Vector<String>();
        this.creditHours = new Vector<Double>();
        this.grades = new Vector<String>();
        this.totalCourses = courseNames.size();
        this.calculateGP();
        this.calculateTotalCreditHours();
        this.calculateGPA();
    }

    // Post: this ReportCard object's name is set to name.
    //   courseNames, creditHours, and grades each initialized to an empty
    //   Vector of their types.
    //   totalCourses is set to the size of courseNames.
    //   GP, totalCreditHours, and GPA is calculated.
    public ReportCard(String name)
    {
        this.name = name;
        this.courseNames = new Vector<String>();
        this.creditHours = new Vector<Double>();
        this.grades = new Vector<String>();
        this.totalCourses = courseNames.size();
        this.calculateGP();
        this.calculateTotalCreditHours();
        this.calculateGPA();
    }

    // Post: this ReportCard object's name is set to name.
    //   this ReportCard object's courseNames set to courseNames
    //   this ReportCard object's creditHours set to creditHours
    //   this ReportCard object's grades set to grades
    //   this ReportCard object's totalCourses is set to the size 
    //     of courseNames.
    //   GP, totalCreditHours, and GPA is calculated.
    public ReportCard(String name, Vector<String> courseNames, 
        Vector<Double> creditHours, Vector<String> grades)
    {
        this.name = name;
        this.courseNames = courseNames;
        this.creditHours = creditHours;
        this.grades = grades;
        this.totalCourses = courseNames.size();
        this.calculateGP();
        this.calculateTotalCreditHours();
        this.calculateGPA();
    }

    // Return: this ReportCard object's name.
    public String getName()
    {
        return this.name;
    }

    // Post: this ReportCard object's name set to name.
    // Return: this ReportCard object.
    public ReportCard setName(String name)
    {
        this.name = name;
        return this;
    }

    // Return: this ReportCard object's GP.
    public double getGP()
    {
        return this.GP;
    }

    // Return: this ReportCard object's GPA.
    public double getGPA()
    {
        return this.GPA;
    }

    // Return: this ReportCard object's totalCreditHours.
    public double getTotalCreditHours()
    {
        return this.totalCreditHours;
    }
    
    // Return: this ReportCard object's totalCourses.
    public int getTotalCourses()
    {
        return this.totalCourses;
    }

    // Pre: obj must be a ReportCard object
    // Return: true if name of this ReportCard == name of obj. False otherwise
    public boolean equals(Object obj)
    {
        ReportCard report = (ReportCard) obj;
		if (this.name.equals(report.name)) return true;
		else return false;
    }

    // Return: 1 if GPA of this ReportCard > GPA of report
    //	      0 if GPA of this ReportCard == GPA of report
    //	     -1 if GPA of this ReportCard < GPA of report
	public int compareTo(ReportCard report)				
	{
		if (this.GPA == report.GPA) return 0;
		else if (this.GPA > report.GPA) return 1;
		else return -1;
    }
    
    // Return: The name and GPA of this ReportCard in the format "Name: name GPA: gpa" 
	public String toString()
	{
        return "Name: " + this.name + " GPA: " + this.GPA;
	}

    // Post: name added to this ReportCard object's courseNames vector
    //   creditHour added to this ReportCard object's creditHours vector
    //   grade added to this ReportCard object's grades vector
    //   totalCourses, GP, totalCreditHours, and GPA are recalculated.
    public void addCourse(String name, double creditHour, String grade)
    {
        this.courseNames.add(name);
        this.creditHours.add(creditHour);
        this.grades.add(grade);

        this.totalCourses = courseNames.size();
        this.calculateGP();
        this.calculateTotalCreditHours();
        this.calculateGPA();
    }

    // Post: item at index in courseNames, creditHours, and grades vector is 
    //   removed; all subsequent items shifted to the left one in each vector.
    //   totalCourses, GP, totalCreditHours, and GPA are recalculated.
    public void removeCourse(int index)
    {
        this.courseNames.remove(index);
        this.creditHours.remove(index);
        this.grades.remove(index);

        this.totalCourses = courseNames.size();
        this.calculateGP();
        this.calculateTotalCreditHours();
        this.calculateGPA();
    }

    // Desc: load in a report card from inFile in the format:
    //   Student name (a string such as John Johnson)
    //	 Number of course (an int such as 2)
    //	 Course Name (a string such as Computer Science I)
    //	 Credit hours (an int such as 4)
    //	 Grade (any one of A, B, C, D, F)
    //	 Course Name (e.g. Introduction to Art)
    //	 Credit hours 
    //	 Grade
    // Post: this ReportCard object's name is set to name
    //     from inFile. total number of courses is read from 
    //     inFile.
    //   Each course name, credit hour, and grade is loaded
    //     in and added as a course to this ReportCard object.
    //   2 + (3 * totalCourses) lines are read from inFile.
    // Pre: inFile is a text file ready to be read 
    public void load(Scanner inFile)
    {
        this.name = inFile.nextLine();
        int totalCourses = Integer.parseInt(inFile.nextLine());
        for (int i = 0; i < totalCourses; ++i)
        {
            String courseName = inFile.nextLine();
            int creditHour = Integer.parseInt(inFile.nextLine());
            String grade = inFile.nextLine();
            this.addCourse(courseName, creditHour, grade);
        }
    }

    // Output: A report in the following format is written to outFile:
    //	 Name		    Courses			      Credit Hours	Grade	
    //	 -----------------------------------------------------------------------------------
    //	 John Johnson	Computer Science I	  4		        A
    //	 		        Introduction to Art	  3		        B
    //	 GPA: 3.57
    // Pre: outFile is a text file ready to be written
    public void write(PrintWriter outFile)
    {
        boolean studentNamePrinted = false;
        
        for (int i = 0; i < this.totalCourses; i++)
        {
            String courseName = this.courseNames.get(i);
            double creditHour = this.creditHours.get(i);
            String grade = this.grades.get(i);

            if (studentNamePrinted) 
            {
                outFile.printf("%-20s%-25s%-15.0f%-5s\n", 
                "", courseName, creditHour, grade);
            }
            else 
            {
                outFile.printf("%-20s%-25s%-15.0f%-5s\n", 
                    this.name, courseName, creditHour, grade); 
                studentNamePrinted = true;
            }
        }

        outFile.printf("GPA: %5.2f\n" , this.GPA);
    }

    // Output: A report header in the following format is written to outFile:
    //	 Name		    Courses			      Credit Hours	Grade	
    //	 -----------------------------------------------------------------------------------
    public static void writeHeader(PrintWriter outFile)
    {
        outFile.printf("%-20s%-25s%-15s%-5s\n", 
            "Name", "Courses", "Credit Hours", "Grade");
        outFile.println("-----------------------------------------------------------------");
    }

    // Output: A report header in the following format is printed to the screen:
    //	 Name		    Courses			      Credit Hours	Grade	
    //	 -----------------------------------------------------------------------------------
    public static void printHeader()
    {
        System.out.printf("%-20s%-25s%-15s%-5s\n", 
            "Name", "Courses", "Credit Hours", "Grade");
        System.out.println("-----------------------------------------------------------------");
    }

    // Output: A report in the following format is output to the screen:
    //	 John Johnson	Computer Science I	  4		        A
    //	 		        Introduction to Art	  3		        B
    //	 GPA: 3.57
    public void print()
    {
        boolean studentNamePrinted = false;
        
        for (int i = 0; i < this.totalCourses; i++)
        {
            String courseName = this.courseNames.get(i);
            double creditHour = this.creditHours.get(i);
            String grade = this.grades.get(i);

            if (studentNamePrinted) 
            {
                System.out.printf("%-20s%-25s%-15.0f%-5s\n", 
                "", courseName, creditHour, grade);
            }
            else 
            {
                System.out.printf("%-20s%-25s%-15.0f%-5s\n", 
                    this.name, courseName, creditHour, grade); 
                studentNamePrinted = true;
            }
        }

        System.out.printf("GPA: %5.2f\n" , this.GPA);
    }

    // Pre:	grade represents a valid grade, 
    //   valid grades: A+, A, A-, B+, B, B-, C+, C, C-, D+, D, D-, or F
    // Return: the grade point of grade
    private static double findPoint(String grade)
    {
        switch (grade)
        {
            case "A+": return 4.0;
            case "A": return 4.0;
            case "A-": return 3.7;
            case "B+": return 3.3;
            case "B": return 3.0;
            case "B-": return 2.7;
            case "C+": return 2.4;
            case "C": return 2.0;
            case "C-": return 1.7;
            case "D+": return 1.3;
            case "D": return 1.0;
            case "D-": return 0.7;
            default: return 0.0;
        }
    }

    // Post: this ReportCard object's GPA is calculated and set.
    private void calculateGPA()
    {
        if (this.totalCourses == 0) this.GPA = 0.0;
        else this.GPA = this.GP / this.totalCreditHours;
    }

    // Post: this ReportCard object's GP is calculated and set.
    private void calculateGP()
    {
        double result = 0.0;

        if (this.totalCourses != 0)
        {
            for (int i = 0; i < this.totalCourses; i++)
            {
                result += findPoint(this.grades.get(i)) * this.creditHours.get(i);
            }
        }
        
        this.GP = result;
    }

    // Post: this ReportCard object's totalCreditHours is calculated and set.
    private void calculateTotalCreditHours()
    {
        double total = 0.0;

        if (this.totalCourses != 0)
        {
            Iterator<Double> iter = this.creditHours.iterator();
            while (iter.hasNext()) total += iter.next();
        }

        this.totalCreditHours = total;
    }
}