// Eugene Triguba
// ytriguba17@ole.augie.edu
// Sort.java
// Homework 1: 6-3 Sorting & Searching

public class Sort
{
	public static void main(String[] args) {}
	
	// Post: Elements in arr sorted in descending order
	public static void bubbleSortD(int[] arr)                    
	{ 
		for (int pass = 1; pass < arr.length; ++pass)
			for (int j = 0; j < arr.length - pass; ++j)
				if (arr[j] < arr[j+1]) 
				{
					int temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
				}	
		
	}    
	
	// Post: Elements in arr sorted in descending order
	public static void selectionSortD(int[] arr)                 
	{          
		for (int pass = 0; pass < arr.length - 1; ++pass)
			{
				int index = pass;
				for (int j = pass + 1; j < arr.length; ++j)
					if (arr[j] > arr[index]) index = j;
				
				int temp = arr[index];
				arr[index] = arr[pass];
				arr[pass] = temp;
			}
		
	}     
	                                             
	// Post: Elements in arr sorted in descending order
	public static void insertionSortD(int[] arr)                 
	{        
		for (int pass = 1; pass < arr.length; ++pass)
			{
				int pulled = arr[pass];
				int i = pass - 1;
				while (i >= 0)
				{
					if (arr[i] < pulled)
					{
						arr[i + 1] = arr[i];
						i--;
					}
					else break;
				}
				arr[i + 1] = pulled;
			}
			
	}     
}                                             