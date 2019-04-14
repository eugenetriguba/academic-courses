
// The IntArray class contains useful methods to manipulate int arrays.
public class IntArray
{
    /**
    Sorts an int array using bubble sort<p>
    <b>Post:</b><br> arr sorted in ascending order <p>
    */
    public static void bubbleSort(int[] arr)                    
    {                                                  			
        int pass, i, temp;                                           
        for (pass = 1; pass<arr.length; ++pass)       	
                for (i = 0; i< arr.length -pass; ++i)        	  	
                        if (arr[i] > arr[i+1])    	 
                {				
                    temp=arr[i];      	
                    arr[i]=arr[i+1];   			
                    arr[i+1]=temp;   			
                }		
    } 

    /**
    Sorts an int array using selcetion sort<p>
    <b>Post:</b><br> arr sorted in ascending order <p>
    */
    public static void selectionSort(int[] arr)                 
    {                                                  			
        int pass, min, i, temp;                                       
        for (pass=0; pass<arr.length-1; ++pass)      
        {   
                    min=pass;                                      		
                    for (i=pass+1; i< arr.length; ++i)  	
                if (arr[i]<arr[min])  	
                    min=i;      			
            temp=arr[min];			
            arr[min]=arr[pass];	
            arr[pass]=temp;	
        }                                           		
    }

    /**
    Sorts an int array using insertion sort<p>
    <b>Post:</b><br> arr sorted in ascending order <p>
    */
    public static void insertionSort(int[] arr)                 
    {                                                  
        int pass, pulled, i;	
        for (pass=1; pass<arr.length; ++pass)         
        {                                            
            pulled=arr[pass];                  		
            i=pass-1;                                 	
            while (i>=0) 	
            {                                      
                if (pulled<arr[i])    	
                {                                
                        arr[i+1]=arr[i];                  	
                        i=i-1;                        	
                }                                
                else break;                  		
            }                                      		
            arr[i+1]=pulled;                            	
        }                                              				  
    }

    /**
    Searches an int array using sequential search for a key<p>
    <b>Return:</b><br>index of key in arr if found, -1 if not found<p>
    */
    public static int seqSearch (int[] arr, int key)                 
    {                                                  	
        int index=0;
        while (index <arr.length)        	
            if (key==arr[index]) 		
                return index;           		
            else index ++;      

        return(-1);                              		
    }

    /**
    Searches an int array using binary search for a key<p>
    <b>Pre:</b><br> arr sorted in ascending order <p>
    <b>Return:</b><br>index of key in arr if found, -1 if not found<p>
    */
    public static int binSearch (int[] arr, int key)                 
    {                                                  
        int low = 0, high = arr.length - 1, mid; 	
        while (low <= high)                	
        {                                          
            mid=(low + high) / 2;                   		
            if (key == arr[mid])		
            return mid;            		
            else if (key < arr[mid]) 		
            high = mid - 1;        		
            else low = mid + 1;                         		
        }                                          
        return (-1);                             		
    }

    /**
    Copies and int array to another int array<p>
    <b>Pre:</b><br>source and destination have the same length<p>
    <b>Post:</b><br>destination is an exact copy of source<p>
    */
    public static void copyArr(int[] source, int[] destination)
    {
        for (int j=0; j< source.length; ++j)	
            destination [j]= source [j];
    }
}
