// Eugene Triguba
// ytriguba17@ole.augie.edu
// Time24.java
// Homework 10: Backtracking & Generics

import java.util.Scanner;
import java.util.StringTokenizer;
import java.text.DecimalFormat;
import java.io.Serializable;

// A data structure that stores integer values for hour (0..23) and minute
// (0..59) to represent the time of day in a 24-hour clock 
public class Time24 implements Serializable
{
    private int hour;
    private int minute;

    // Post: hour and minute of this Time24 object both initialized to 0
    public Time24()
    {
        this(0,0);
    }

    // Pre: hour and minute cannot be negative
    // Post: hour and minute of this Time24 object initialized to hour and minute 
    //       respectively. This operation will normalize the time if necessary 
    //       (e.g. 9:75 is stored as 10:15).
    // Throw: IllegalArgumentException if h or m is negative
    public Time24(int hour, int minute)
   	{
      	setTime(hour, minute);
    }
    
    // Desc: Sets the hour and minute of this Time24 object to a particular time
    // Pre: hour and minute cannot be negative
    // Post: hour and minute of this Time24 object set to hour and minute
    //       respectively. This operation will normalize the time if necessary 
    //       (e.g. 9:75 is stored as 10:15).
    // Throw: IllegalArgumentException if h or m is negative
    public void setTime(int hour, int minute)
   	{
        if (hour < 0 || minute < 0) throw new IllegalArgumentException("Time24.setTime: "
            + "argument must not be negative");
        
      	this.hour = hour;
     	this.minute = minute;
        normalizeTime();
   	}

    // Desc: Adds minutes to this Time24 object
    // Pre: minute cannot be negative
    // Post: This Time24 object set to m minutes later. This operation will 
    //       normalize the time if necessary (e.g. 9:75 is stored as 10:15).
    // Throw: IllegalArgumentException if m is negative
    public void addTime(int minute)
   	{
		if (minute < 0) throw new IllegalArgumentException("Time24.setTime: "
            + "argument must not be negative");

		this.minute += minute;
		normalizeTime();
   	}

    // Desc: Read from f the hour and minute for this Time24 object
    // Pre:	f is ready to be read, and the next line contains hh:mm where hh:mm gives 
	// 		a valid 24-hour time
    // Post: The entire line hh:mm read in from f. The hour and minute of
    //       this Time24 object set to hh and mm respectively. The operation will
    //       normalize the time if necessary (e.g. 9:75 is stored as 10:15).
    public void readTime(Scanner f)
    {
        String readInTime = f.nextLine();
        Time24 time = Time24.parseTime(readInTime);
        this.hour = time.getHour();
        this.minute = time.getMinute();
    }

    // Desc: Measures the interval from this Time24 object to another time
    // Return: The interval from this Time24 object to t as a Time24 
    public Time24 interval(Time24 t)
   	{
		int currentTime = this.hour * 60 + this.minute;
		int tTime = t.hour * 60 + t.minute;
		if (tTime < currentTime) tTime += 24 * 60;
		return new Time24(0, tTime - currentTime);

   	}

    // Return: The hour value of this Time24 object
    public int getHour()
   	{ 
        return this.hour; 
    }

    // Return: The minute value of this Time24 object
    public int getMinute()
   	{ 
        return this.minute; 
    }

    // Return: This Time24 object as a String in the form "hh:mm"
    public String toString()
   	{
		DecimalFormat f = new DecimalFormat("00");
		return this.hour + ":" + f.format(minute);
    }

    /** 
     * Check if obj is equal to this Time24
     * Return: if the hour and minute of this Time24 object and obj are equal,
     *         return true. Otherwise, return false. If obj is not an instance of Time24,
     *         return false. */
    public boolean equals(Object obj)
    {
        if (obj instanceof Time24)
        {
            Time24 time = (Time24) obj;
            boolean isHourEql = this.hour == time.hour;
            boolean isMinEql = this.minute == time.minute;

            if (isHourEql && isMinEql) return true;
            else return false;
        }
        else return false;
    }
       
    // Desc: Convert a String to a Time24
    // Pre: time must be in the form "hh:mm" where hh and mm are positive integers 
    // Return: A Time24 object that corresponds to time
    public static Time24 parseTime(String time)				
   	{
		StringTokenizer timeToken = new StringTokenizer(time, ":");
		int hour = Integer.parseInt(timeToken.nextToken());
		int minute = Integer.parseInt(timeToken.nextToken());
		return new Time24(hour, minute);
    }
    
    // Post: Sets the hour value in the range 0 to 23 and the minute value in
    //       the range 0 to 59
    private void normalizeTime()
    {
     int extraHours = this.minute / 60;
     this.minute %= 60;
     this.hour = (this.hour + extraHours) % 24;
 }
}
