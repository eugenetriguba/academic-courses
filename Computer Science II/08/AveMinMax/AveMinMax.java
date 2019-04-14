// Eugene Triguba
// ytriguba17@ole.augie.edu
// AveMinMax.java
// Homework 8: 09-TextIO

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;
import java.util.Iterator;

public class AveMinMax
{
    // Desc: find the average, the minimum, and the maximum of a file called
    //   'number.txt' which contains a set of doubles seperated by white space.
    // Output: the contents of 'number.txt'; the average, min, and max of the
    //   doubles each on a new line.
    public static void main(String[] args) throws FileNotFoundException
    {
        File f = new File("number.txt");
        if (!f.exists())
        {
            System.out.println("File 'number.txt' must exist.");
            System.exit(1);
        }

        Scanner inFile = new Scanner(f);
        Vector<Double> totalDoubles = loadDoublesFrom(inFile);

        printContents(totalDoubles);
        double[] AvgMinMax = findAvgMinMax(totalDoubles);

        System.out.printf("The average is %.2f\n", AvgMinMax[0]);
        System.out.printf("The min is %.1f\n", AvgMinMax[1]);
        System.out.printf("The max is %.1f\n", AvgMinMax[2]);
    }

    // Pre: inFile must be a file ready to be read and only contain
    //   a set of doubles seperated by white space.
    // Return: a Vector of type Double with the doubles from inFile
    //   loaded into it.
    public static Vector<Double> loadDoublesFrom(Scanner inFile)
    {
        Vector<Double> result = new Vector<Double>();
        while (inFile.hasNextDouble()) result.add(inFile.nextDouble());
        return result;
    }

    // Output: prints the contents of doubles to the screen
    public static void printContents(Vector<Double> doubles)
    {
        Iterator<Double> iter = doubles.iterator();

        System.out.print("The file has: ");
        while (iter.hasNext()) System.out.printf("%.1f ", iter.next());
        System.out.println();
    }

    // Desc: Finds the average, min, and max of doubles
    // Return: a double array where
    //   index 0 corresponds to average
    //   index 1 corresponds to min
    //   index 2 corresponds to max
    public static double[] findAvgMinMax(Vector<Double> doubles)
    {
        double[] result = {0.0, 0.0, 0.0};
        Iterator<Double> iter = doubles.iterator();
        double min, max, total;
        min = max = total = iter.next();
        double lastNum = 0.0;

        while (iter.hasNext())
        {
            double number = iter.next();
            if (number > lastNum) max = number;
            if (number < lastNum) min = number;
            total += number;
            lastNum = number;
        }

        double average = total / doubles.size();
        result[0] = average;
        result[1] = min;
        result[2] = max;
        return result;
    }
}