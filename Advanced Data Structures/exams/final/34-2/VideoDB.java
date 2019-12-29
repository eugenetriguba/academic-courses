/* 
 * Eugene Triguba <ytriguba17@ole.augie.edu>
 * Advanced Data Structures: Final
 * 34-2: VideoDB.java
 */

import java.util.*;
import java.io.*;

/**
 * Maintain inventory for a video store. The video store operates on an honor system.
 * That is, the video store only maintains the inventory. It trusts that a customer
 * will always return a film, and therefore, the program does not keep rental records.
 * 
 * The database file video.txt has the inventory in the following format:
 *      title:int
 *      title:int
 *      …
 * 
 *      Each line represents the title of the film and the number of copies in
 *      inventory. Note that a film title could contain blanks. Thus, we use only 
 *      use colons and newlines to separate tokens in the data file. This file should 
 *      be created before the program is run for the first time. 
 * 
 * In an interactive loop the clerk inputs whether the customer wishes to rent a 
 * film, return a film, or whether business is over for the day.
 * 
 * Output: A menu asking the clerk for input.
 *         At the beginning of the business day, the program outputs the list of films 
 *         in inventory. At the end of the business day, the program outputs the list 
 *         of films remaining in inventory. The database video.txt is updated at the 
 *         end of each day.
 */
public class VideoDB
{
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        MyTreeMap<String, Integer> inventory = new MyTreeMap<>();
        int task = 0;

        loadDB(inventory);
        System.out.println("Inventory: ");
        display(inventory);

        do
        {
            System.out.printf("1. Rent \n2. Return \n3. Done -- \nYour choice: ");

            task = input.nextInt();
            input.nextLine();

            if (task == 1)
            {
                System.out.print("Enter film name:");
                String filmName= input.nextLine();
                rentalTransaction(inventory, filmName);
            }
            else if (task == 2)
            {
                System.out.print("Enter film name:");
                String filmName= input.nextLine();
                returnTransaction(inventory, filmName);
            }
        }
        while (task != 3);

        System.out.println("Films Remaining in Inventory: ");
        display(inventory);
        updateDB(inventory);

        input.close();
    }

    // Input: Video.txt
    // Post: Video.txt loaded in inventory
    public static void loadDB(MyTreeMap<String, Integer> inventory)
    {
        try
        {
            Scanner file = new Scanner(new File("Video.txt"));
            file.useDelimiter("[=\n\r]+");

            while (file.hasNext())
            {
                String title = file.next();
                int copies = 0;
                if (file.hasNext()) copies = Integer.parseInt(file.next());

                inventory.put(title, copies);
            }

            file.close();
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Video.txt was not found when trying to load the DB.");
            System.out.println("Exiting..");
            System.exit(1);
        }
    }

    /**
     * Prints all records in inventory to the screen.
     * Current MyTreeMap has no iterator so we do a workaround.
     * 
     * @param inventory - the MyTreeMap to output to the screen
     */
    public static void display(MyTreeMap<String, Integer> inventory)
    {
        MySet<MyMap.Entry<String,Integer>> entrySet = inventory.entrySet();
        Iterator<MyMap.Entry<String,Integer>> iter = entrySet.iterator();

        while (iter.hasNext())
            System.out.println(iter.next());
    }

    // Desc: process returning of a movie.
    // Post: the film added to inventory if film does not exist,
    //       or its copies count incremented.
    public static void returnTransaction(MyTreeMap<String, Integer> inventory, String filmName)
    {
        if (inventory.containsKey(filmName))
        {
            int copies = inventory.get(filmName);
            copies++;
            inventory.put(filmName, copies);
            inventory.put(filmName, 1);
        }
        else 
        {
            inventory.put(filmName, 1);
        }
    }

    // Desc: process renting of a movie.
    // Post: the film is deleted from inventory if it exists in inventory, and
    //       its count is 1, or its count decremented if it exists in inventory,
    //       and its count is > 1.
    // Output: If the film is not in inventory, a message is printed on the
    //         screen to that effect.
    public static void rentalTransaction(MyTreeMap<String, Integer> inventory, String filmName)
    {
        if (inventory.containsKey(filmName))
        {
            int copies = inventory.get(filmName);
            copies--;

            if (copies == 0) 
                inventory.remove(filmName);
            else
                inventory.put(filmName, copies);
        }
        else
        {
            System.out.printf("%s is not in the inventory.\n", filmName);
        }
    }

    // Post: All records in inventory saved in video.txt
    public static void updateDB(MyTreeMap<String, Integer> inventory)
    {
        try
        {
            PrintWriter output = new PrintWriter("Video.txt");
            MySet<MyMap.Entry<String,Integer>> entrySet = inventory.entrySet();
            Iterator<MyMap.Entry<String,Integer>> iter = entrySet.iterator();

            while (iter.hasNext())
                output.println(iter.next());

            output.close();
        }
        catch (IOException e)
        {
            System.out.println("Video.txt was not found when trying to update the DB.");
            System.out.println("Exiting..");
            System.exit(2);
        }
    }
}
