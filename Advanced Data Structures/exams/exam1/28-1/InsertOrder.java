// Eugene Triguba
// ytriguba17@ole.augie.edu
// Exam 1: InsertOrder.java

import java.util.*;
import java.io.*;

public class InsertOrder
{
    /**
     * Adds an item to list while keeping the list in ascending order
     * based on CompareTo
     * <p>
     * Pre:  Elements of list are in ascending ordering
     * Post: item added to list; all elements in list remain in ascending ordering.
     */
    public static <T extends Comparable<? super T>> void insertOrder(List<T> list, T item)
    {
        int listSize = list.size();
        int index = listSize;

        for (int i = 0; i < listSize; i++)
        {
            if (item.compareTo(list.get(i)) < 0)
            {
                index = i;
                break;
            }
        }

        list.add(index, item);
    }

    public static void main(String[] args)
    {
        try
        {
            ArrayList<String> grads = new ArrayList<String>();
            Scanner input = new Scanner(new File("gradlist.txt"));
            while (input.hasNext())
            {
                String grad = input.nextLine();
                insertOrder(grads, grad);
            }
            input.close();
            
            System.out.println("Graduate List: ");
            Iterator<String> iter = grads.iterator();
            while (iter.hasNext()) 
                System.out.println("\t" + iter.next());
        }
        catch (FileNotFoundException e)
        {
            System.out.println("gradlist.txt was not found in the current directory.");
            System.exit(1);
        }
    }   
}