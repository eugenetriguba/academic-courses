// Eugene Triguba
// ytriguba17@ole.augie.edu
// Part4.java
// Homework 11: 24-3 Generics

import java.util.*;
import java.io.*; 

class Part4
{
    /**
     * Reads in the name and time of runners from a text file and stores them in
     * a Vector as Entry< String, Time24>s. Sorts the Vector by their name then
     * prints it out. Then asks the user to input a name of a runner from the
     * keyboard to look up their name and time. The user may keep doing so until
     * they enter q to quit. 
     * 
     * @param args args is not used
     */
	public static void main(String[] args)
	{
        Scanner keyboard = new Scanner(System.in);
        Vector<Entry<String, Time24>> runners = new Vector<Entry<String, Time24>>();
        fillVectorFromFile(runners, "data.txt");
        
        Collections.sort(runners);
        Iterator<Entry<String, Time24>> iter = runners.iterator();
        while (iter.hasNext()) System.out.println(iter.next());
        
        System.out.println("Search for a runner (Enter q to quit)");
        while (true)
		{
			System.out.print("Enter name: "); 
            String name = keyboard.nextLine();
            if (name.equals("q")) break;

            Entry<String, Time24> temp = new Entry<String, Time24>(name, new Time24());
            int searchResult = Collections.binarySearch(runners, temp);
            if (searchResult < 0) System.out.println("No such person");
            else System.out.println(runners.elementAt(searchResult));
        }
    }

    /**
     * Fills a Vector with Entry< String, Time24> objects by reading from a text
     * file.
     * 
     * @param entryVector the Vector to be filled from the text file
     * @param fileName the name of a text file. The file must start with a
     * String on the first line, a Time24 (in the format 0:00) on the second
     * line, and so on repeating.
     * Example: George
     *          1:10
     *          Paul
     *          5:40
     * 
     * @throws FileNotFoundException: program is killed if fileName doesn't
     * exist in the current directory
     */
    public static void fillVectorFromFile(Vector<Entry<String, Time24>> entryVector, 
		String fileName)
    {
        Scanner input = null;
        try 
        { 
            input = new Scanner(new File(fileName)); 
        }
        catch(FileNotFoundException e) 
        { 
            System.out.println("The file %s was not found in the current directory.");
            System.out.println("Program will be aborted. Please try again.");
            System.exit(1);
        }
        
		while (input.hasNextLine())
		{
			String name = input.nextLine();
			String time = input.nextLine();
			entryVector.add(new Entry<String, Time24>(name.trim(), Time24.parseTime(time)));
        }

        input.close();
    }
}
