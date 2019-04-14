// Eugene Triguba
// ytriguba17@ole.augie.edu
// MultiplicationTable.java

public class MultiplicationTable
{  
    // Output: prints half a multiplication table
    //   that goes up to 12 to the screen.
  	public static void main(String[] args) 
	{
		System.out.println("           Multiplication Table"); // 11 blanks
        System.out.print("     "); // 5 blanks
        for (int j = 1; j <= 12; j++)
            System.out.printf("%5s", j);
        System.out.println("\n----------------------------------------------"
			+ "-------------------");
        
		for (int row = 1; row <= 12; row ++) 
		{
            if (row < 10) System.out.print(row + "  : ");
            else System.out.print(row + " : ");
                
            for (int column = 1; column <= row; column++)
                System.out.printf("%5s", row * column);
            
            System.out.println();
    	}
  	}
}