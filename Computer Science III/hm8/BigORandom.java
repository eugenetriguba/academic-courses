// Eugene Triguba
// ytribuba17@ole.augie.edu
// BigORandom.java
// Homework 8: BigO

import java.util.Random;
import java.util.Scanner;

public class BigORandom
{
    public static void main(String[] args) 
    {
        Scanner input = new Scanner(System.in);
        StopWatch timer = new StopWatch();
      	Random numberGenerator = new Random();
	    final int SIZE = 80000;
   		int[] arrOriginal = new int[SIZE];		
   		int[] arrSorted = new int[SIZE];
		for (int i = 0; i < SIZE; i++)
         			arrOriginal[i] = numberGenerator.nextInt(100000);
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
