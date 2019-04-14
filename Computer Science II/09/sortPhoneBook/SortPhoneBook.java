// Eugene Triguba
// ytriguba17@ole.augie.edu
// SortPhoneBook.java
// Homework 9: 10-1 Vector

import java.util.Scanner;
import java.util.Vector;
import java.util.Iterator;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

// Desc: Sorts a file of phone entries.
// Input: "phone.txt" contains phone entries in the following format:
//	 Name1 (a String like "John Johnson")
//	 Phone1 (a String like "605-222-2222")
//	 Name2
//	 Phone2
//	 …
//	 NameN
//	 PhoneN
// Output: "phoneSorted.txt" contains entries from "phone.txt" sorted in
//   ascending order based on names. 
public class SortPhoneBook
{  
    public static void main(String[] args) throws FileNotFoundException
	{		
		PhoneBook book = new PhoneBook();		
		book.load("phone.txt");
		book.sort();
		book.write("phoneSorted.txt");
	}
}

class PhoneBook
{  
	private Vector<String> names;				
	private Vector<String> phoneNumbers;				
    
    // Post: names and phoneNumbers initialized to an empty Vector of type
    // String.
	public PhoneBook()
	{
        this.names = new Vector<String>();
        this.phoneNumbers = new Vector<String>();
    }
    
    // Desc: Load the phone entries from a file into the PhoneBook object.
    // Input: A file with filename fileName which contains phone entries in the
    //   following format:
    //	   Name1 (a String like "John Johnson")
    //	   Phone1 (a String like "605-222-2222")
    //	   Name2
    //	   Phone2
    //	   …
    //	   NameN
    //	   PhoneN
    // Post: The names from fileName loaded in this PhoneBook's names Vector. The
    //   phoneNumbers from fileName loaded in this PhoneBook's phoneNumbers Vector,
    //   so that the 2 Vectors are parallel.
	public void load(String fileName) throws FileNotFoundException		
	{
        File f = new File(fileName);
        if (!f.exists())
        {
            System.out.printf("%s must exist.\n", fileName);
            System.exit(1);
        }

        Scanner inFile = new Scanner(f);
        while (inFile.hasNextLine())
        {
            this.names.add(inFile.nextLine());
            this.phoneNumbers.add(inFile.nextLine());
        }
        inFile.close();
    }
    
    // Desc: Sorts the PhoneBook object based on names using bubbleSort.
    // Post: The names Vector sorted in ascending order. The names and
    //   phoneNumbers Vectors remain parallel.
	public void sort()			
	{
		bubbleSort();
    }
    
    // Desc: Write the phoneNumbers entries in the PhoneBook object to a file.
    // Pre: fileName is the filename of the data file to be written. 
    // Output: The phoneNumbers entries in the PhoneBook object written to the
    //   file with filename fileName in the following format:
    //	   Name1 (a String like "John Johnson")
    //	   Phone1 (a Stirng like "605-222-2222")
    //	   Name2
    //	   Phone2
    //	   …
    //	   NameN
    //	   PhoneN
	public void write(String fileName) throws FileNotFoundException		
	{
        File f = new File(fileName);
        if (f.exists())
        {
            System.out.printf("%s must not exist.\n", fileName);
            System.exit(2);
        }

        PrintWriter outFile = new PrintWriter(f);
        for (int i = 0; i < this.names.size(); ++i)
        {
            outFile.println(this.names.get(i));
            outFile.println(this.phoneNumbers.get(i));
        }
        outFile.close();
    }

    // Post: the PhoneBooks's names Vector sorted alphabetically. 
    //   the PhoneBook's phoneNumbers Vector parallel to names.
    private void bubbleSort()
    {
        for (int pass = 1; pass < this.names.size(); ++pass)
            for (int i = 0; i < this.names.size() - pass; ++i)
            {
                int nameComparison = this.names.get(i).compareTo(this.names.get(i + 1));
                if (nameComparison > 0) 
                {
                    swap(this.names, i, i + 1);
                    swap(this.phoneNumbers, i, i + 1);
                }
            }
    }

    // Post: swaps the item at eleOneIndex in collection with the item at
    //   eleTwoIndex in collection.
    // Return: true if the swap was successful. False otherwise.
    private boolean swap(Vector<String> collection, int eleOneIndex, int eleTwoIndex)
    {
        String eleOne = collection.get(eleOneIndex);
        String eleTwo = collection.get(eleTwoIndex);

        if (!collection.contains(eleOne) || !collection.contains(eleTwo))
            return false;

        collection.set(eleOneIndex, eleTwo);
        collection.set(eleTwoIndex, eleOne);
        return true;
    }
}
