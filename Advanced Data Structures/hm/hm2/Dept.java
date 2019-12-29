// Eugene Triguba
// ytriguba@ole.augie.edu
// Homework 2: 27-3 Java Collections
// Dept.java

import java.util.*;
import java.io.*;

/**
 * Reads in a text file for a university's department's and 
 * number of major's in each department. Then prints it out
 * in a table format.
 * 
 * Input File format:
 * Dept1
 * 50
 * Dept5
 * 25
 * 
 * Output Table Format:
 * Department    Number of Majors
 * ----------  --------------------
 * Dept1       50
 * Dept5       25s
 */
public class Dept
{
    public static void main(String[] args) 
    {
        University uni = new University();
        uni.read("dept.txt");
        System.out.println(uni);
    }
}

class University implements Comparable<University>
{
    private TreeMap<String, Integer> departments;

    /**
     * Initializes this University
     * 
     * Post: departments is initialized to an empty
     *       TreeMap<String, Integer>
     */
    public University()
    {
        departments = new TreeMap<String, Integer>();
    }

    /**
     * Adds an entry containing name and totalMajors to departments
     * 
     * Post: a Map.Entry is created in department with a key of 
     *       name and a value of totalMajors
     */
    public void addDepartment(String name, Integer totalMajors)
    {
        departments.put(name, totalMajors);
    }

    /**
     * Returns the value of a department (number of majors)
     * 
     * @return the value in the department specified by name
     */
    public int getDepartment(String name)
    {
        return departments.get(name);
    }

    /**
     * Reads in a file and updates this University's departments.
     * The program is aborted if fileName is not found in the 
     * current directory.
     * 
     * @param fileName name of the file to be read in
     * 
     * Post: the contents of fileName stored into departments
     * 
     * Note: The incoming file must be in the following format:
     *       
     *       File.txt:
     *       Dept1
     *       50
     *       Dept2
     *       25
     */
    public void read(String fileName)
    {
        try
        {
            Scanner input = new Scanner(new File(fileName));
            while (input.hasNextLine())
            {
                String dept = input.nextLine();
                if (input.hasNextLine())
                {
                    int totalMajors = input.nextInt();
                    input.nextLine();
                    this.addDepartment(dept, totalMajors);
                }
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.printf("Error: %s is not found in the current directory.\n", 
				fileName);
            System.exit(1);
        }
    }

    /**
     * Returns this University's departments as a String
     * in table format
     * 
     * @return Department    Number of Majors
     *         ----------  --------------------
     *         Dept1       50
     *         Dept2       25
     * 
     *         Where Dept1 and Dept2 and their corresponding
     *         number of majors are whatever is in departments.
     */
    public String toString()
    {
        String result = "";
        result += String.format("%10s%20s%n", "Department", 
            "Number of Majors");
        result += String.format("%10s%20s%n", "----------", "------------------");

        Set<Map.Entry<String, Integer>> uniEntrySet = departments.entrySet();
        Iterator<Map.Entry<String, Integer>> iter = uniEntrySet.iterator();

        while (iter.hasNext())
        {
            Map.Entry<String, Integer> entry = iter.next();
            result += String.format("%-10s  %-20s%n", entry.getKey(), entry.getValue());
        }

        return result;
    }

    /**
     * Compares this University to uni by
     * their enrollment total (total number of majors)
     * 
     * @param uni 
     * 
     * @return 0 if this University's enrollment total is equal to uni
     *         1 if this University's enrollment total is greater than uni's
     *        -1 if this University's enrollment total is greater than uni's
     */
    public int compareTo(University uni)
    {
        int thisTotal = this.getEnrollmentTotal();
        int uniTotal = uni.getEnrollmentTotal();

        if (thisTotal < uniTotal) return -1;
        else if (thisTotal > uniTotal) return 1;
        return 0;
    }

    /**
     * Adds up all the number of majors in each department
     * 
     * @return the sum of all the majors in each department
     */
    private int getEnrollmentTotal()
    {
        Set<Map.Entry<String, Integer>> uniEntrySet = departments.entrySet();
        Iterator<Map.Entry<String, Integer>> iter = uniEntrySet.iterator();
        int total = 0;

        while (iter.hasNext())
        {
            Map.Entry<String, Integer> entry = iter.next();
            total += entry.getValue();
        }

        return total;
    }
}