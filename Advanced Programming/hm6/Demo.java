// Eugene Triguba <ytriguba17@ole.augie.edu>
// Advanced Programming: Homework 6

import java.util.Scanner;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// Prints all employees of a particular department.
public class Demo
{
    public static void main(String[] args)
    {
        Employee[] arr = {
            new Employee("Jason", "Red", 50000, "IT"),
            new Employee("Ashley", "Green", 76000, "IT"),
            new Employee("Matthew", "Indigo", 35870.5, "Sales"),
            new Employee("James", "Indigo", 47000.77, "Marketing"),
            new Employee("Luke", "Indigo", 62000, "IT"),
            new Employee("Jason", "Blue", 32000, "Sales"),
            new Employee("Wendy", "Brown", 42360.4, "Marketing")
        };

        Scanner input = new Scanner(System.in);
        System.out.print("Enter department: ");
        String dept = input.nextLine();

        Arrays.stream(arr).filter(
            employee -> employee.getDepartment().equals(dept)
        ).forEach(System.out::println);
    }
}