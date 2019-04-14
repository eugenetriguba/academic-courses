// Eugene Triguba
// ytribuba17@ole.augie.edu
// BigOAscending.java
// Homework 8: BigO

import java.util.Scanner;

public class BigOAscending
{
    public static void main(String[] args) 
    {
        Scanner input = new Scanner(System.in);
        StopWatch timer = new StopWatch();
	    final int SIZE = 640000;
   		int[] arrOriginal = new int[SIZE];		
   		int[] arrSorted = new int[SIZE];
		for (int i = 0; i < SIZE; i++)
         			arrOriginal[i] = i;
        System.out.println("Array size: " + SIZE);

        IntArray.copyArr(arrOriginal, arrSorted);
        timer.start();
        HWSort.sortA(arrSorted);		
        System.out.printf("%s%.6f\n", "Sort A: ", timer.stop());

		IntArray.copyArr(arrOriginal, arrSorted);
        timer.start();
        HWSort.sortB(arrSorted);			
        System.out.printf("%s%.6f\n", "Sort B: ", timer.stop());

		IntArray.copyArr(arrOriginal, arrSorted);
        timer.start();
        HWSort.sortC(arrSorted);			
        System.out.printf("%s%.6f\n", "Sort C: ", timer.stop());
    }
}
