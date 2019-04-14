// Eugene Triguba
// ytriguba17@ole.augie.edu
// TestSort.java
// Homework 1: 6-3 Sorting & Searching

public class TestSort
{
	public static void main(String[] args)
	{
		// Test BubbleSortD
	   	int[] testArr={3,5,6,2,4};		
		Sort.bubbleSortD(testArr);
		for (int i = 0; i < testArr.length; ++i)
			System.out.print(testArr[i] + " ");
			// Assertion: 6 5 4 3 2
		System.out.println();
		
		// Test SelectionSortD
		int[] testArr2 = {3,5,6,2,4};		
		Sort.selectionSortD(testArr2);			
		for (int j = 0; j < testArr2.length; ++j)	
			System.out.print(testArr2[j]+" ");
			// Assertion: 6 5 4 3 2
		System.out.println();
		
		// Test InsertionSortD
		int[] testArr3 = {3, 4, 6, 9, 5};		
		Sort.insertionSortD(testArr3);			
		for (int j = 0; j< testArr3.length; ++j)	
		    System.out.print(testArr3[j]+" ");
			// Assertion: 9 6 5 4 3
		System.out.println();
		
	}
	
}