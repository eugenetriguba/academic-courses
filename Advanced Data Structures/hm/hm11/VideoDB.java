// Eugene Triguba <ytriguba17@ole.augie.edu>
// VideoDB.java
// Homework 11

//Desc:	Maintain inventory for a video store. The video store operates 
//  on an honor system.  That is, the
//	video store only maintains the inventory.  It trusts that a 
//  customer will always return a film,
//	and therefore, the program does not keep rental records.
//Input:	The database file video.txt has the inventory in the 
//          following format:
//		title:int
//		title:int
//		…
//		Each line represents the title of the film and the number of copies in 
//.     inventory.  Note that a film
//		title could contain blanks.  Thus, we use only colons and newlines to 
//      separate tokens in the data
//		file. This file should be created before the program is run for the first time.
//	In an interactive loop the clerk inputs whether the customer wishes to rent a film, 
// return a film, or
//	whether business is over for the day.
//Output:A menu asking the clerk for input.
//	At the beginning of the business day, the program outputs the list of films in inventory.
//	At the end of the business day, the program outputs the list of films remaining in inventory.
//	The database video.txt is updated at the end of each day.

import java.util.*;
import java.io.*;

class Video implements Comparable<Video>
{
    private String title;
    private int copies;

    public Video(String title, int copies)
    {
        this.title = title;
        this.copies = copies;
    }
    public int getCopies()
    {
        return copies;
    }
    public String getTitle()
    {
        return title;
    }
    //Post:	copies incremented by n if n is positive, copies decremented 
	//.     by n if n is negative
    public void adjustCopies(int adjustment)
    {
        copies += adjustment;
    }
    //Return:	A string that has the form "title:copies"
    public String toString()
    {
        return title + ":" + copies;
    }
    //Return:	-1 if this.title < c.title, 0 if =, 1 if >.
    public int compareTo(Video c)
    {
        return title.compareTo(c.title);
    }
    //Return:	true if this.title == c.title, false otherwise
    public boolean equals(Object obj)
    {
        Video v = (Video) obj;
        return title.compareTo(v.title) == 0;
    }
}

public class VideoDB
{
    public static void main(String[] args) throws IOException
    {
        Scanner f = new Scanner(System.in);
        MyTreeSet<Video> inventory = new MyTreeSet<Video>();
        loadDB(inventory);
        System.out.println("Inventory:");
        display(inventory);

        while (true)
        {
            System.out.print("1. Rent  2. Return  3. Done -- Your choice:");
            int task = f.nextInt();
            f.nextLine();
            if (task == 3) break;
            System.out.print("Enter film name:");
            String filmName= f.nextLine();
            if (task == 2) returnTransaction(inventory, filmName);
            else rentalTransaction(inventory, filmName);
        }
        System.out.println("Films Remaining in Inventory:");
        display(inventory);
        updateDB(inventory);
    }

    // Input: Video.txt
    // Post: Video.txt loaded in inventory
    public static void loadDB(MyTreeSet<Video> inventory)
    {
        try
        {
            Scanner file = new Scanner(new File("Video.txt"));
            file.useDelimiter("[:\n]+");

            while (file.hasNext())
            {
                String title = file.next();
                int copies = 0;
                if (file.hasNext()) copies = Integer.parseInt(file.next());

                inventory.add(new Video(title, copies));
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Video.txt was not found when trying to load the DB.");
            System.out.println("Exiting..");
            System.exit(1);
        }
    }

    // Post: All records in t printed on the screen
    public static void display(MyTreeSet<Video> t)
    {
        Iterator iter = t.iterator();

        while (iter.hasNext())
            System.out.println("\t" + iter.next());
    }

    // Desc: process returning of a movie.
    // Post: the film added to inventory if film does not exist,
    //       or its copies count incremented.
    public static void returnTransaction(MyTreeSet<Video> inventory, String filmName)
    {
        if (!inventory.contains(new Video(filmName, 0)))
        {
            inventory.add(new Video(filmName, 1));
        }
        else
        {
            Video v = null;
            Iterator iter = inventory.iterator();
            while (iter.hasNext())
            {
                v = (Video) iter.next();
                if (v.getTitle().equals(filmName)) break;
            }

            v.adjustCopies(1);
            if (v.getCopies() == 0) inventory.remove(v);
        }
    }

    // Desc: process renting of a movie.
    // Post: the film is deleted from inventory if it exists in inventory, and
    //       its count is 1, or its count decremented if it exists in inventory,
    //       and its count is > 1.
    // Output: If the film is not in inventory, a message is printed on the
    //         screen to that effect.
    public static void rentalTransaction(MyTreeSet<Video> inventory, String filmName)
    {
        if (!inventory.contains(new Video(filmName, 0)))
        {
            System.out.printf("%s is not in the inventory.\n", filmName);
        }
        else
        {
            Video v = null;
            Iterator iter = inventory.iterator();
            while (iter.hasNext())
            {
                v = (Video) iter.next();
                if (v.getTitle().equals(filmName)) break;
            }

            v.adjustCopies(-1);
            if (v.getCopies() == 0) inventory.remove(v);
        }
    }

    // Post: All records in inventory saved in video.txt
    public static void updateDB(MyTreeSet<Video> inventory)
    {
        try
        {
            PrintWriter output = new PrintWriter("Video.txt");

            for (Video v : inventory)
                output.println(v);

            output.close();
        }
        catch (IOException e)
        {
            System.out.println("Video.txt was not found when trying to update the DB.");
            System.out.println("Exiting..");
            System.exit(1);
        }
    }
}
