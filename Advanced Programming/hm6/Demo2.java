// Eugene Triguba <ytriguba17@ole.augie.edu>
// Advanced Programming: Homework 6
// 38-3 Functional Interfaces

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Demo2
{
    public static void main(String[] args)
    {
        Employee[] employees = {
            new Employee("Jason", "Red", 5000, "IT"),
            new Employee("Ashley", "Green", 7600, "IT"),
            new Employee("Matthew", "Indigo", 3587.5, "Sales"),
            new Employee("James", "Indigo", 4700.77, "Marketing"),
            new Employee("Luke", "Indigo", 6200, "IT"),
            new Employee("Jason", "Blue", 3200, "Sales"),
            new Employee("Wendy", "Brown", 4236.4, "Marketing")
        };

        List<Employee> list = Arrays.asList(employees);
        System.out.println("\nEmployees by department: ");

        Map<String, List<Employee>> employeesByDepartment = list.stream().collect(
            Collectors.groupingBy(Employee::getDepartment)
        );
        employeesByDepartment.forEach((department, employeesInDepartment) -> {
            System.out.printf("\n%s\n", department);
            
            employeesInDepartment.stream().sorted(
                Comparator.comparing(Employee::getSalary)
            ).forEach(employee -> {
                System.out.printf("  %s\n", employee);
            });
        });
    }
}