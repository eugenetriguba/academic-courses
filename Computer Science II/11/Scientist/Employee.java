
// Class Employee supports objects representing employees working in a typical
// company. Each Employee object knows his/her id, name, and salary.
public class Employee
{
    private int id;					
    private String name;				
    private double salary;		

    // Post: Id, name, salary of this Employee initialized to -1, "unknown", -1.0 
    public Employee()				
    {
        id = -1;	
        name = "unknown";	
        salary = -1.0;	
    }

    //Post:	Id, name, and salary of this Employee initialized to the parameters id, name, and salary
    public Employee(int id, String name, double salary)	
    {
        this.id=id;	
        this.name=name;	
        this.salary= salary;	
    }

    //Post:	Id of this Employee set to id
    public void setId(int id)			
    {
        this.id=id;	
    }

    //Post:	Salary of this Employee set to salary
    public void setSalary(double salary)		
    {
        this.salary= salary;	
    }

    //Post:	Name of this Employee set to name
    public void setName(String name)		
    {
        this.name=name;	
    }

    //Return:	Id of this Employee 
    public int getId()				
    {
        return id;			
    }

    //Return:	Salary of this Employee 
    public double getSalary()			
    {
        return salary;
    }

    //Return:	Name of this Employee 
    public String getName()			
    {
        return name;
    }

    //Return:The id, name, salary of this Employee in the format "ID: id Name: name Salary: salary" 
    public String toString()	
    {
        return "ID: " + id + " Name: " + name + " Salary: " + salary;
    }

    //Return:	true if id of this Employee is == id of obj, false otherwise
    public boolean equals(Object obj)	
    {
        if (obj instanceof Employee)
        {
            Employee emp = (Employee) obj;
            if (this.id == emp.id) return true;
            else return false;
        }
        else return false;
    }
    //Return:	1 if id of this Employee is > id of emp
    //	0 if id of this Employee is == id of emp
    //	-1 if id of this Employee is < id of emp
    public int compareTo(Employee emp)
    {
        if (id>emp.id) return 1;
        else if (id<emp.id) return -1;
        else return 0;
    }
} 
