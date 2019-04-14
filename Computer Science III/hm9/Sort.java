// Eugene Triguba
// ytriguba17@ole.augie.edu
// Homework 9: Recursion
// Sort.java

import java.util.Arrays;

public class Sort
{
    // Output: prints out a sorted array of integers
    public static void main(String[] args) 
    {
        int[] arr = {12, 11, 13, 5, 6}; 
        insertionSort(arr, arr.length); 
        System.out.println(Arrays.toString(arr));
    }     
    
    // Post: arr sorted in ascending order
    public static void insertionSort(int[] arr, int high) 
    { 
        if (high <= 1) return; 
       
        insertionSort(arr, high - 1); 
       
        int last = arr[high - 1]; 
        int j = high - 2; 

        while (j >= 0 && arr[j] > last) 
        { 
            arr[j + 1] = arr[j]; 
            j--; 
        } 
        arr[j + 1] = last;
    } 
}