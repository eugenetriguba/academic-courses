// Eugene Triguba
// ytriguba17@ole.augie.edu
// Exam 1: Words.java

import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

public class Words
{
    public static void main(String[] args) throws IOException, 
	   ClassNotFoundException
    {
        Scanner input = new Scanner(System.in);
        ArrayList<String> words = new ArrayList<String>();

        System.out.println("Enter a sequence of words seperated by white space.");
        System.out.println("Enter -1 on a line by itself when done.");
        
        while (input.hasNextLine())
        {
            String line = input.nextLine();
            if (line.trim().equals("-1")) break;

            words.add(line);
        }
        input.close();

        ObjectOutputStream out = new ObjectOutputStream(
            new FileOutputStream("words.data")
        );
        out.writeObject(words);
        out.close();

        ObjectInputStream in = new ObjectInputStream(
            new FileInputStream("words.data")
        );
        ArrayList<String> wordsFromIO = (ArrayList<String>) in.readObject();
        in.close();

        System.out.println(wordsFromIO);
    }
}
