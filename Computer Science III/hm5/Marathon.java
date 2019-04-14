// Eugene Triguba
// ytriguba17@ole.augie.edu
// Marathon.java

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

// Desc: Output the name and time of the runner who came in first, as well as
//       the name and time of the runner who came in last in a marathon race
//       (assuming there are no ties).
// Input: A text file named marathon.txt containing the name and time of
//       each participant in the following format (the file has at least 1
//       participant, name is just 1 word with no space, and name and time are
//       separated by tabs, blanks, and newlines):
//       John   2:40
//       Paul   3:20
//       Carl   2:10
// Output: The name and time of the runner who came in first, as well as
//         the name and time of the runner who came in last printed to the screen.
class Marathon
{
	public static void main(String[] args)
	{
        File inputFile = new File("marathon.txt");
        Scanner input = new Scanner("");
        try 
        {
            input = new Scanner(inputFile).useDelimiter("[:\t\r\n ]+");
        }
        catch (FileNotFoundException e)
        {
            System.out.println("marathon.txt was not found in the current directory.");
            System.exit(1);
        }

        Runner winner = new Runner();
        Runner loser = new Runner();
        determineRaceResult(input, winner, loser);

        System.out.println("Winner: " + winner);
        System.out.println("Loser: " + loser);
    }
    
    // Desc: Goes through the input file line by line and determines who is the winner
    //       and loser by comparing raceTimes
    // Pre: input file is in the form of: %s %i:%i where s is a string (the
    //      name), and %i:%i is the hour and minute seperated by a colon.
    // Post: Sets the name and raceTime of the winner and loser inputted to the
    //       winner and loser as determined by going through the runners in input
    public static void determineRaceResult(Scanner input, Runner winner, Runner loser)
    {
        while (input.hasNextLine() && input.hasNext())
        {
            String name = input.next();
            int hour = input.nextInt();
            int minute = input.nextInt();
            Runner nextRunner = new Runner(name, new Time24(hour, minute));
            
            if (nextRunner.compareTo(loser) < 0) 
            {
                winner.setName(name);
                winner.setRaceTime(new Time24(hour, minute));

            }
            else 
            {
                loser.setName(name);
                loser.setRaceTime(new Time24(hour, minute));
            }
        }
    }
}

class Runner 
{
	private String name;
    private Time24 raceTime;
    
    // Post: The Runner object initialized with name = "Unknown" and raceTime = 0:0.
	public Runner()
    {
        this.name = "Unknown";
		this.raceTime = new Time24(0,0);
    }

    // Post: The Runner object initialized with it's name set to name and it's
    //       raceTime set to time
	public Runner(String name, Time24 time)
    {
       	this.name = name;		
		this.raceTime = new Time24(time.getHour(), time.getMinute());	
    }

    // Return: The name of the Runner object
	public String getName()
   	{ 
		return this.name; 
    }
    
    // Return: The raceTime of the Runner object.
	public Time24 getRaceTime()
   	{ 
		return new Time24(this.raceTime.getHour(), this.raceTime.getMinute()); 
    }

    // Post: The Runner object's name set to name
    public void setName(String name)
    {
        this.name = name;
    }

    // Post: The Runner object's raceTime set to time
    public void setRaceTime(Time24 time)
    {
        this.raceTime = time;
    }

    // Pre: input has a line in the following format ready to be read: name hh:mm
    //      where name is a String and hh, mm are integers. The token delimiters of
    //      input have been set to white space characters and the colon by the caller.
	// Post: The line read in from input, the name and the time stored in the Runner object 
    public void read(Scanner input)
    {
        this.name = input.next();
        this.raceTime.readTime(input);
    }

    // Desc: Compare 2 Runner objects based on raceTime
    // Return: 	1 if current object's raceTime > r's raceTime
    //          0 if current object's raceTime == r's raceTime
    //         -1 if current object's raceTime < r's raceTime
    public int compareTo(Runner r)
    {
        int thisRaceTime = this.raceTime.getHour() * 60 + this.raceTime.getMinute();
        int otherRaceTime = r.getRaceTime().getHour() * 60 + r.getRaceTime().getMinute();

        if (thisRaceTime > otherRaceTime) return 1;
        else if (thisRaceTime < otherRaceTime) return -1;
        else return 0;
    }

    // Return: A String object in the form "name hh:mm"
    public String toString()
    {
        return String.format("%s %s", name, raceTime);
    }
}