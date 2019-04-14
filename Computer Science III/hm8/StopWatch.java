
// Class StopWatch supports objects representing a stop watch for measuring the
// time required to execute a process.  Time is measured in seconds.
public class StopWatch
{
    private long startTime, stopTime;

    // Desc: Initializes this StopWatch object
    // Post: startTime and stopTime of this StopWatch object initialized to 0
	public StopWatch()
    {
        startTime = stopTime = 0;
    }

    // Desc: tarts this StopWatch
    // Post: startTime of this StopWatch set to the current system clock time in 
    // nanosecond
    public void start()
    {
        startTime = System.nanoTime();
    }

    // Desc: Stops this StopWatch
    // Post: stopTime of this StopWatch set to the current system clock time in 
    //       nanosecond
    // Return: Interval of time from start to stop measured in seconds.
    public double stop()
    {
        stopTime = System.nanoTime();
        return (stopTime - startTime)/1000000000.0;
   	}
}
