// Eugene Triguba
// ytriguba17@ole.augie.edu
// Final: 26-2 QuickSort
// Driver.java

import java.util.Random;
import java.util.Vector;

// Test method sort and findKth in class QuickSort
public class Driver
{
    public static void main(String[] args) 
    {
        Random numberGenerator = new Random();

        Integer[] intArr = new Integer[10];
        for (int i = 0; i < 10; i++)
            intArr[i] = numberGenerator.nextInt(50);
		QuickSort.findKth(intArr, 5);
        System.out.println("Find 5th in intArr: " + intArr[5]);
        testSort(intArr);

        Object[] objArr = {"hello", "one", "two", "three", "done"};
		QuickSort.findKth(objArr, 0);
        System.out.println("Find first in objArr: " + objArr[0]);
        testSort(objArr);

        Double[] doubleArr = new Double[10];
        for (int i = 0; i < 10; i++)
            doubleArr[i] = Math.floor(numberGenerator.nextDouble() * 50);
		QuickSort.findKth(doubleArr, 3);
        System.out.println("Find 3rd in doubleArr: " + doubleArr[3]);
        testSort(doubleArr);
	}

	// Desc: tests the sort method in class QuickSort
	// Post: arr is sorted in ascending order
	// Output: print out arr before and after it is sorted, 
	//		   both on newlines.
    public static <T> void testSort(T[] arr)
    {
        System.out.print("Unsorted Array: ");
        printArr(arr);

        QuickSort.sort(arr);

        System.out.print("Sorted Array: ");
        printArr(arr);
        System.out.println();
    }

	// Desc: prints out the contents of an array
	// Output: if arr is empty, "[]". Otherwise, 
	//		   prints the contents of arr in the 
	//		   format: "[ele1, ele2, ele3]"
    public static <T> void printArr(T[] arr)
    {
		if (arr.length == 0)
		{
			System.out.println("[]");
			return;
		}
		
        System.out.print("[" + arr[0]);

        for(int i = 1; i < arr.length; i++)
            System.out.print(", " + arr[i]);
        
        System.out.printf("]\n");
    }
}