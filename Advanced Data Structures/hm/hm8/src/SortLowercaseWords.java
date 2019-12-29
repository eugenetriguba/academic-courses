import java.io.*;
import java.util.*;

public class SortLowercaseWords
{
    public static void main(String[] args) throws FileNotFoundException
    {
        ArrayList<String> arr = new ArrayList<String>();
        Scanner input = new Scanner(new File("words.txt"));
        while (input.hasNext())
            arr.add(input.next());

        StrRadixSort.strRadixSort(arr);

        for (int i=0; i < arr.size(); i++)
            System.out.println(arr.get(i));
    }
}
