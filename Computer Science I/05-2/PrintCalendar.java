//Example 5-2-11
//PrintCalendar.java
import java.util.Scanner;
public class PrintCalendar 
{
	//Desc: Print a monthly calendar for any month not before the year 1800
	//Input: month (1-12) and year (>=1800) entered from the keyboard
    //Output: A monthly calendar for the input month and year
  	public static void main(String[] args) 
    {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter full year (e.g., 2001): ");
        int year = input.nextInt();
        System.out.print("Enter month in number between 1 and 12: ");
        int month = input.nextInt();
        printMonth(year, month);
    }
      
	//Pre: year>=1800, month in [1..12]
    //Output: A monthly calendar for month and year
    public static void printMonth(int year, int month) 
    {
        printMonthTitle(year, month);	
        printMonthBody(year, month);	 
    }		
      			
	//Pre: year>=1800, month in [1..12]
    //Output:        Month Year
    //	---------------------------------------
    //	 Sun Mon Tue Wed Thu Fri Sat
    public static void printMonthTitle(int year, int month) 
    {
    	System.out.println("         " + getMonthName(month) + " " + year);
    	System.out.println("-----------------------------");		//29 –'s
    	System.out.println(" Sun Mon Tue Wed Thu Fri Sat");
    }
      
	//Pre: month in [1..12]
    //Return: if month is 1, "January"
    //  if month is 2, "February"
	//  …
    //  if month is 12, "December"
    public static String getMonthName(int month) 
    {
		switch (month)
		{
			case 1: return "January";
			case 2: return "February";
			case 3: return "March";
			case 4: return "April";
			case 5: return "May";
			case 6: return "June";
			case 7: return "July";
			case 8: return "August";
			case 9: return "September";
			case 10: return "October";
			case 11: return "November";
			case 12: return "December";
			default: return "Error: Month should be in range [1-12]";
		}
    }
      
	//Pre: year>=1800, month in [1..12]
    //Output: The monthly calendar for month and year.  E.g.
    //		 1    2    3    4    5    6
    //	  7    8    9   10   11   12   13
    //	 14   15   16   17   18   19   20
    //	 21   22   23   24   25   26   27
    //	 28   29   30
    public static void printMonthBody(int year, int month) 
    {
		//0 for Sun, 1 for Mon, …, 6 for Sat
        int startDay = getStartDay(year, month);
        int numberOfDaysInMonth = getNumberOfDaysInMonth(year, month);
        for (int i = 0; i < startDay; i++)
            System.out.printf("%4c", ' ');			
        for (int i = 1; i <= numberOfDaysInMonth; i++) 
        {
            System.out.printf("%4d",  i);	
            if ((i+startDay)%7 == 0) System.out.println();
        }
        System.out.println();
    }
      
	//Pre: year>=1800, month in [1..12]
    //Return: the start day of month/1/year; 0 for Sun, 1 for Mon, …, 6 for Sat
  	public static int getStartDay(int year, int month) 
    {
    	final int START_DAY_FOR_JAN_1_1800 = 3;
    	int total = getTotalDaysFrom1_1_1800(year, month);
    	return (total + START_DAY_FOR_JAN_1_1800) % 7;
  	}
	
	//Pre: year>=1800, month in [1..12]
    //Return: the total number of days from January 1, 1800 to but 
	// excluding 1/month/year
  	public static int getTotalDaysFrom1_1_1800(int year, int month) 
    {
        return 1;
  	}
	//Pre: year>=1800, month in [1..12]
    //Return: the number of days in month of year
    public static int getNumberOfDaysInMonth(int year, int month) 
    {
        return 1;
  	}
    //Return: true if year is a leap year, false otherwise
    public static boolean isLeapYear(int year) 
    {
        return true;
  	}
}
