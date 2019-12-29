/**
 * Eugene Triguba <ytriguba17@ole.augie.edu>
 * Advanced Data Structures: Final
 * 35-3: HashSet
 * Demo.java
 */

// 1.	For HashSet, each insert is O(1), each search is O(1).
// 2.	For TreeSet, each insert is O(log n), each search is O(log n).
// 3.	For array, each insert is O(1), each search is O(n) assuming sequential search.
// 4.	For insert, array is fastest, then HashSet, then TreeSet.
// 5.	For search, HashSet is fastest, then TreeSet, then array.  


import java.util.*;
import java.io.*;

public class Demo
{
    public static final String SEARCH_TERM = "abandon";
    public static final String FILENAME = "dict.txt";

	public static void main(String[] args)
	{
		Vector<String> v = new Vector<String>();
        loadVector(v, FILENAME);
        Collections.shuffle(v);
		System.out.println("Number of words is " + v.size());

		TreeSet<String> tSet = new TreeSet<String>();
        double seconds = loadTreeSet(v, tSet);
		System.out.printf("\tBuilt TreeSet in %.3f seconds\n", seconds);

		HashSet<String> hSet = new HashSet<String>();
        seconds = loadHashSet(v, hSet);
		System.out.printf("\tBuilt HashSet in %.3f seconds\n", seconds);

		String[] arr = new String[v.size()];
        seconds = loadArray(v, arr);
		System.out.printf("\tBuilt array in %.3f seconds\n", seconds);

        seconds = searchTreeSet(v, tSet);
		System.out.printf("\tSearch TreeSet in %.8f seconds\n", seconds);

        seconds = searchHashSet(v, hSet);
		System.out.printf("\tSearch HashSet in %.8f seconds\n", seconds);

        seconds = searchArray(v, arr);
		System.out.printf("\tSearch array in %.4f seconds\n", seconds);
    }
    
	public static void loadVector(Vector<String> v, String filename)
	{
        try
        {
            Scanner input = new Scanner(new File(filename));
        
            while (input.hasNextLine())
                v.add(input.nextLine());
        }
        catch (FileNotFoundException e)
        {
            System.out.println(filename + " was not found in the current directory.");
            System.exit(1);
        }
    }
    
	public static double loadTreeSet (Vector<String> v, TreeSet<String> tSet) 
	{
        StopWatch time = new StopWatch();
        time.start();
        
        for (String word : v)
            tSet.add(word);

        return time.stop();
    }
    
	public static double loadHashSet (Vector<String> v, HashSet<String> hSet) 
	{
        StopWatch time = new StopWatch();
        time.start();
        
        for (String word : v)
            hSet.add(word);

        return time.stop();
    }
    
	public static double loadArray (Vector<String> v, String[] arr) 
	{
        StopWatch time = new StopWatch();
        time.start();
        int length = v.size();
        
        for (int i = 0; i < length; i++)
            arr[i] = v.get(i);

        return time.stop();
    }
    
	public static double searchTreeSet (Vector<String> v, TreeSet<String> tSet) 
	{
        StopWatch time = new StopWatch();
        time.start();
        
        tSet.contains(SEARCH_TERM);

        return time.stop();
    }
    
	public static double searchHashSet (Vector<String> v, HashSet<String> hSet) 
	{
        StopWatch time = new StopWatch();
        time.start();
        
        hSet.contains(SEARCH_TERM);

        return time.stop();
    }
    
	public static double searchArray(Vector<String> v, String[] arr) 
	{
        StopWatch time = new StopWatch();
        time.start();
        
        seqSearch(arr, SEARCH_TERM);

        return time.stop();
    }
    
    public static int seqSearch(String[] arr, String key)                 
    {                                                  
        int index = 0;
        while (index <arr.length)          
                if (key.equals(arr[index])) return index;           
                else index ++;                            
        return -1;                              
    }                                                  
}

/**
 * Class StopWatch supports objects representing a stop watch for measuring the time 
 * required to execute a process. Time is measured in seconds.
 */
class StopWatch
{
    private long startTime, stopTime;

    /**
    Initializes this StopWatch object<p>
    <b>Post:</b><br> startTime and stopTime of this StopWatch object initialized to 0<p>
    */
	public StopWatch()
    {
        startTime = stopTime = 0;
    }

    /**
    Starts this StopWatch <p>
    <b>Post:</b><br> startTime of this StopWatch set to the current system clock time in 
    nanosecond <p>
    */
    public void start()
    {
        startTime = System.nanoTime();
    }

    /**
    Stops this StopWatch <p>
    <b>Post:</b><br> stopTime of this StopWatch set to the current system clock time in 
    nanosecond <p>
    <b>Return:</b><br>Interval of time from start to stop measured in seconds.
    */
    public double stop()
    {
        stopTime = System.nanoTime();
        return (stopTime - startTime)/1000000000.0;
   	}
}
