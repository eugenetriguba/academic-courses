import java.util.*;

public class StrRadixSort
{

    /**
     * Sorts an array in ascending order
     *
     * @param arr
     */
    public static void strRadixSort(ArrayList<String> arr)
    {
        if (arr.isEmpty()) return;

        Queue<String>[] subQ = (Queue<String>[]) new LinkedList[27];
        int maxStringLength = findMaxStringLength(arr);
        appendBlanks(arr, maxStringLength);

        for (int i = 0; i < subQ.length; i++)
            subQ[i] = (Queue<String>) new LinkedList<String>();

        for (int pass = 0; pass < maxStringLength; pass++)
        {
            distribute(arr, subQ, pass);
            collect(subQ, arr);
        }
    }

    // Pre : 	arr contains integers to be distributed.
    //	pass >=0
    // Post : 	The elements of arr pushed into one of the subQ[0..9] based on the pass digit from the
    //	right of each element.  E.g. if pass is 0, then 789 is pushed into subQ[9], if pass is 1, then
    //	789 is pushed into subQ[8].
    private static void distribute(ArrayList<String> arr, Queue<String>[] subQ, int pass)
    {
        int power=(int) Math.pow(10, pass);
        for (int i = 0; i < arr.size(); i++)
            subQ[arr.get(i) / power % 10].offer(arr.get(i));
    }
    // Post : 	Each subQ is emptied starting from subQ[0], then subQ[1], …, subQ[9].  The elements
    //	popped are sequentially placed in arr[0], arr[1], and so on
    private static void collect(Queue<String>[] subQ, ArrayList<String> arr)
    {
        int index = 0;
        for (int i = 0; i < 27; i++)
            while (!subQ[i].isEmpty())
            {
                arr.set(index, subQ[i].poll());
                index++;
            }
    }

    private static <T extends Comparable> int findMaxStringLength(ArrayList<String> arr)
    {
        String maxString = Collections.max(arr, new compareStringLengths());
        return maxString.length();
    }

    private static void appendBlanks(ArrayList<String> arr, int maxStrLength)
    {
        for (String word : arr)
        {
            StringBuilder strBuilder = new StringBuilder(word);

            for (int i = word.length(); i < maxStrLength; i++)
                strBuilder.append(" ");

            word = strBuilder.toString();
        }
    }
}

class compareStringLengths implements Comparator<String>
{
    @Override
    public int compare(String obj1, String obj2)
    {
        return Integer.compare(obj1.length(), obj2.length());
    }
}