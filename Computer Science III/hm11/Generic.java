// Eugene Triguba
// ytriguba17@ole.augie.edu
// Generic.java
// Homework 11: Generics

public class Generic
{
    /**
     * Sorts an array of Objects in ascending order using Selection Sort.
     * 
     * @param arr the array to be sorted. The actual element type of arr 
     * must bea Comparable.
     * 
     * Post: The elements in arr sorted in ascending order based on the 
     * orderingas defined by the compareTo method provided by the element 
     * type.
     */
    public static void selectionSort(Object[] arr) 
    {
        int min;
        Object temp;                                  	     
   		for (int pass = 0; pass < arr.length - 1; ++pass)      
   		{   
         	min = pass;                                      	
         	for (int i = pass + 1; i < arr.length; ++i)  	            		
			if (((Comparable)arr[i]).compareTo(arr[min]) < 0) min=i;     
             	
			temp = arr[min];
			arr[min] = arr[pass];	
			arr[pass] = temp;	
   		}                                            
    }

    /**
     * Sorts an array of elements of type T in ascending order using 
     * Selection Sort.
     * 
     * @param <T> T must be a Comparable<T> or 
     * Comparable<some superclass of T>
     * @param arr the array to be sorted
     * 
     * Post: the elements in arr sorted in ascending order based on 
     * the ordering as defined by the compareTo method provided by the 
     * element type
     */
    public static <T extends Comparable<? super T>> void selectionSort(T[] arr) 
    {
        int min;
        T temp;                                  	     
   		for (int pass = 0; pass < arr.length - 1; ++pass)      
   		{   
         	min = pass;                                      	
         	for (int i = pass+1; i < arr.length; ++i)  	            		
				if (arr[i].compareTo(arr[min]) < 0) min = i;  
               	
			temp = arr[min];	
			arr[min] = arr[pass];	
			arr[pass] = temp;	
   		}                                            
    }

    /**
     * Searchs through arr for key using Binary Search.
     * 
     * @param arr the array to be sorted. The actual element type of arr must be
     * a Comparable. Elements in arr sorted in ascending order based on compareTo
     * provided by the arr element type.
     * @param key the Object you're searching for in arr
     * @return index of key in arr if found, -1 if not found
     */
    public static int binSearch(Object[] arr, Object key) 
    {
        int low = 0, high = arr.length - 1, mid;
        Object midValue;

  	    while (low <= high)
      	{
            mid = (low + high) / 2;                   		
            midValue = arr[mid];
            int comparisionResult = ((Comparable) key).compareTo(midValue);
         	if (comparisionResult == 0) return mid;       
         	else if (comparisionResult < 0) high = mid - 1;       
         	else low = mid + 1;
      	}
      	return -1;        
    }

    /**
     * Searchs through arr for key using Binary Search.
     * 
     * @param <T> T must be a Comparable<T> or Comparable<some superclass of T>
     * @param arr the array to be sorted. Dlements in arr sorted in ascending
     * order based on compareTo provided by class T 
     * @param key the T you're searching for in arr
     * @return index of key in arr if found, -1 if not found
     */
    public static <T extends Comparable<? super T>> int binSearch(T[] arr, T key) 
    {
        int low = 0, high = arr.length-1, mid; 	
      	T midValue;    
  	    while (low <= high)                	
      	{
            mid = (low + high) / 2;                   		
         	midValue = arr[mid];
         	if (key.compareTo(midValue) == 0) return mid;       
         	else if (key.compareTo(midValue) < 0) high = mid - 1;       
         	else low = mid + 1;
      	}
      	return -1;              
    }

    /**
     * Searches a portion of an Object array for a key using sequential search
     * 
     * @param arr the array to be sorted. The element type of arr should have
     * method equals overridden
     * @param key the object you're searching for in arr
     * @return index of key in arr if found, -1 if not found
     */
	public static int seqSearch(Object[] arr, Object key)                 
	{                                                  	
        int index = 0;
          
  	    while (index <arr.length)          	
            if (arr[index].equals(key)) return index;           	
            else index ++;       

  	    return -1;                              	
	}

    /**
     * Converts an array to a String
     * 
     * @pre The element type of arr should have method toString overridden
     * @param arr the array to be sorted
     * @return A string in the format of [ele1, ele2, …] where ele's are the
     * elements of arr converted to a string using the elements' toString method.  
     * Return [] if the array has no element.  Return null if arr is null.
     */
	public static String arrToString(Object[] arr)
	{
		if (arr == null) return "null";
		else if (arr.length == 0) return "[]";
		String str = "[" + arr[0];
		for (int i = 1; i < arr.length; i++)
			str +=  ", " + arr[i];
		str += "]";
		return str;
	}

    /**
     * Sorts arr in ascending order using Bubble Sort.
     * 
     * @param <T> T must be a Comparable<T> or Comparable<some superclass of T>
     * @param arr the array to be sorted
     * 
     * Post: the elements in arr sorted in ascending order based on the ordering
     * as defined by the compareTo method provided by the element type
     */
    public static <T extends Comparable<? super T>> void bubbleSort (T[] arr) 
    {
        for (int i = 0; i < arr.length-1; i++) 
            for (int j = 0; j < arr.length-i-1; j++) 
                if (arr[j].compareTo(arr[j+1]) == 1) 
                {
                    T temp = arr[j]; 
                    arr[j] = arr[j+1]; 
                    arr[j+1] = temp; 
                } 
    }

    /**
     * Sorts arr in ascending order using Bubble Sort.
     * 
     * @param arr the array to be sorted. The actual element type of arr must be
     * a Comparable.
     * 
     * Post: the elements in arr sorted in ascending order based on the ordering
     * as defined by the compareTo method provided by the element type
     */
    public static void bubbleSort(Object[] arr) 
    {
        for (int i = 0; i < arr.length-1; i++) 
            for (int j = 0; j < arr.length-i-1; j++) 
                if (((Comparable) arr[j]).compareTo(arr[j+1]) == 1) 
                { 
                    Object temp = arr[j]; 
                    arr[j] = arr[j+1]; 
                    arr[j+1] = temp; 
                } 
    }

    /**
     * Sorts arr in ascending order using Insertion Sort.
     * 
     * @param <T> T must be a Comparable<T> or Comparable<some superclass of T>
     * @param arr the array to be sorted
     * 
     * Post: the elements in arr sorted in ascending order based on the ordering
     * as defined by the compareTo method provided by the element type
     */
    public static <T extends Comparable<? super T>> void insertionSort (T[] arr) 
    {
        for (int pass = 1; pass < arr.length; pass++)
	    {
            T pulled = arr[pass];
            int i = pass - 1;
            while (i >= 0)
            {
                if (arr[i].compareTo(pulled) == 1)
                {
                    arr[i + 1] = arr[i];
                    i--;
                }
                else break;
            }
            arr[i + 1] = pulled;
	    }
    }

    /**
     * Sorts arr in ascending order using Insertion Sort.
     * 
     * @param arr the array to be sorted. The actual element type of arr must be
     * a Comparable.
     * 
     * Post: the elements in arr sorted in ascending order based on the ordering
     * as defined by the compareTo method provided by the element type
     */
    public static void insertionSort(Object[] arr) 
    {
        for (int pass = 1; pass < arr.length; pass++)
	    {
            Object pulled = arr[pass];
            int i = pass - 1;
            while (i >= 0)
            {
                if (((Comparable) arr[i]).compareTo(pulled) == 1)
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
