// Eugene Triguba
// ytriguba17@ole.augie.edu
// Final: 26-2 QuickSort
// QuickSort.java

import java.util.Collections;
import java.util.List;

public class QuickSort
{
    /**
    Sorts an int array into ascending order
    Post: Elements in arr sorted in ascending order
    */
    public static void sort(Object[] arr)
    {
        sort(arr, 0, arr.length);
    }		
    
    /**
    Sorts an int array into ascending order
    Post: Elements in arr sorted in ascending order
    */
    public static <T extends Comparable<? super T>> void sort(T[] arr)
    {
        sort(arr, 0, arr.length);
    }	

    // Desc: Sorts an int array into ascending order
    // Pre: arr[first]..arr[last-1] contain integers to be sorted
    // Post: arr[first]..arr[last-1] sorted in ascending order 
    public static void sort(Object[] arr, int first, int last)
    {
        if (last - first <= 1) return;		// no element or 1 element in array
        else if (last - first == 2)		// 2 elements in array
        {
            if (((Comparable)arr[last-1]).compareTo(arr[first]) < 0)
            {
                Object temp = arr[last-1];
                arr[last-1] = arr[first];
                arr[first] = temp;
            }
            return;
        }
        else
        {
            int pivotLoc = rearrange(arr, first, last);
            sort(arr, first, pivotLoc);
            sort(arr, pivotLoc +1, last);
        }
    }

    // Desc: Sorts an int array into ascending order
    // Pre: arr[first]..arr[last-1] contain integers to be sorted
    // Post: arr[first]..arr[last-1] sorted in ascending order 
    public static <T extends Comparable<? super T>> void sort(T[] arr, int first, int last)
    {
        if (last - first <= 1) return;
        else if (last - first == 2)
        {
            if (arr[last-1].compareTo(arr[first]) < 0)
            {
                T temp = arr[last-1];
                arr[last-1] = arr[first];
                arr[first] = temp;
            }
            return;
        }
        else
        {
            int pivotLoc = rearrange(arr, first, last);
            sort(arr, first, pivotLoc);
            sort(arr, pivotLoc + 1, last);
        }
    }

    //Pre: arr [first]..arr [last-1] contain integers 
    //Post: arr [first]..arr [pivotLoc-1] <= arr[pivotLoc] <= arr[pivotLoc+1]..arr [last-1]
    //Return: pivotLoc
    private static int rearrange(Object[] arr, int first, int last)
    {
        int mid = (last + first)/2;
        Object pivot = arr[mid];
        arr[mid] = arr[first];
        arr[first] = pivot;
        int scanUp = first + 1;
        int scanDown = last - 1;
        while (true)
        {
            while ((scanUp <= scanDown) && ((Comparable) arr[scanUp]).compareTo(pivot) <= 0)
                scanUp++;
            while ((scanDown >= scanUp) && ((Comparable) arr[scanDown]).compareTo(pivot) >= 0)
                scanDown--;
            if (scanUp > scanDown) break;
            Object temp = arr [scanUp];
            arr [scanUp] = arr [scanDown];
            arr [scanDown] = temp;
            scanUp++;
            scanDown--;
        }
        arr[first] = arr[scanDown];
        arr[scanDown] = pivot;
        return scanDown;
    }

    //Pre: arr [first]..arr [last-1] contain integers 
    //Post: arr [first]..arr [pivotLoc-1] <= arr[pivotLoc] <= arr[pivotLoc+1]..arr [last-1]
    //Return: pivotLoc
    private static <T extends Comparable<? super T>> int rearrange(T[] arr, int first, int last)
    {
        int mid = (last + first)/2;
        T pivot = arr[mid];
        arr[mid] = arr[first];
        arr[first] = pivot;
        int scanUp = first + 1;
        int scanDown = last - 1;
        while (true)
        {
            while ((scanUp <= scanDown) && arr[scanUp].compareTo(pivot) <= 0)
                scanUp++;
            while ((scanDown >= scanUp) && arr[scanDown].compareTo(pivot) >= 0)
                scanDown--;
            if (scanUp > scanDown) break;
            T temp = arr[scanUp];
            arr [scanUp] = arr [scanDown];
            arr [scanDown] = temp;
            scanUp++;
            scanDown--;
        }
        arr[first] = arr[scanDown];
        arr[scanDown] = pivot;
        return scanDown;
    }

    //Pre: arr [first]..arr [last-1] contain integers 
    //Post: arr [first]..arr [pivotLoc-1] <= arr[pivotLoc] <= arr[pivotLoc+1]..arr [last-1]
    //Return: pivotLoc
    private static <T extends Number & Comparable<? super T>> int rearrange(List<T> arr, 
		int first, int last)
    {
        int mid = (last + first)/2;
        T pivot = arr.get(mid);
        arr.set(mid, arr.get(first));
        arr.set(first, pivot);
        int scanUp = first + 1;
        int scanDown = last - 1;
        while (true)
        {
            while ((scanUp <= scanDown) && arr.get(scanUp).compareTo(pivot) <= 0)
                scanUp++;
            while ((scanDown >= scanUp) && arr.get(scanDown).compareTo(pivot) >= 0)
                scanDown--;
            if (scanUp > scanDown) break;
            T temp = arr.get(scanUp);
            arr.set(scanUp, arr.get(scanDown));
            arr.set(scanDown, temp);
            scanUp++;
            scanDown--;
        }
        arr.set(first, arr.get(scanDown));
        arr.set(scanDown, pivot);
        return scanDown;
    }

    /**
    Find the kth largest element in an int array <p>
    <b>Post:</b><br>The elements in arr has been rearranged in such a way that arr[k] 
    now contains the kth largest element.  
    */
    public static void findKth(Object[] arr, int k)
    {
            findKth(arr, 0, arr.length, k);
    }	
    
    /**
    Find the kth largest element in an int array <p>
    <b>Post:</b><br>The elements in arr has been rearranged in such a way that arr[k] 
    now contains the kth largest element.  
    */
    public static <T extends Comparable<? super T>> void findKth(T[] arr, int k)
    {
            findKth(arr, 0, arr.length, k);
    }	

    /**
    Find the kth largest element in an int array <p>
    <b>Post:</b><br>The elements in arr has been rearranged in such a way that arr[k] 
    now contains the kth largest element.  
    */
    public static <T extends Number & Comparable<? super T>> void findKth(List<T> arr, int k)
    {
            findKth(arr, 0, arr.size(), k);
    }	

    //Pre: 	arr[first]..arr[last-1] contain integers 
    //	    k must be in [first..last-1]
    //Post:	The elements in arr has been rearranged in such a way that arr[k] now contains the kth 
    //	largest element
    public static void findKth(Object[] arr, int first, int last, int k)
    {
        int pivotLoc = rearrange(arr, first, last);	
        if (pivotLoc == k) return;
        else if (pivotLoc>k) findKth(arr, first, pivotLoc, k);
        else findKth (arr, pivotLoc +1, last, k);
    }

    //Pre: 	arr[first]..arr[last-1] contain integers 
    //	    k must be in [first..last-1]
    //Post:	The elements in arr has been rearranged in such a way that arr[k] now contains the kth 
    //	largest element
    public static <T extends Comparable<? super T>> void findKth(T[] arr, int first, int last, int k)
    {
        int pivotLoc = rearrange(arr, first, last);	
        if (pivotLoc == k) return;
        else if (pivotLoc>k) findKth(arr, first, pivotLoc, k);
        else findKth (arr, pivotLoc +1, last, k);
    }

    //Pre: 	arr[first]..arr[last-1] contain integers 
    //	    k must be in [first..last-1]
    //Post:	The elements in arr has been rearranged in such a way that arr[k] now contains the kth 
    //	largest element
    public static <T extends Number & Comparable<? super T>> void findKth(List<T> arr, 
		int first, int last, int k)
    {
        int pivotLoc = rearrange(arr, first, last);	
        if (pivotLoc == k) return;
        else if (pivotLoc>k) findKth(arr, first, pivotLoc, k);
        else findKth (arr, pivotLoc +1, last, k);
    }
}