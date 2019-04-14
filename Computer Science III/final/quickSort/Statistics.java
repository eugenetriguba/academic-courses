// Eugene Triguba
// ytriguba17@ole.augie.edu
// Final: 26-2 QuickSort
// Statistics.java

import java.util.ArrayList;
import java.util.Scanner;

public class Statistics
{
    public static void main(String[] args) 
    {
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Enter scores seperated by a space, press enter to end: ");
        Scores testScores = new Scores(keyboard.nextLine());
        keyboard.close();
        
        System.out.println("High: " + testScores.findHigh());
        System.out.println("Low: "  + testScores.findLow());
        System.out.println("Median: " + testScores.findMedian());
        System.out.println("Average: " + testScores.findAverage());
    }
}

// A class to model a list of scores. It can read in a set of 
// scores via the keyboard, find the high, low, medium, and
// and average of this set of scores as well as convert itself
// to a string. The scores are represented as an ArrayList of doubles.
class Scores
{
    ArrayList<Double> scores;

     /**
     * Initializes this Score's object
     * 
     * Post: this Score's scores is initialized to an empty ArrayList
     */
    public Scores()
    {
        scores = new ArrayList<Double>();
    }

    /**
     * Initializes this Score's object
     * 
     * @param input a String containing doubles seperated by whitespace
     *        to fill the this Scores's scores ArrayList
     * Post: this Score's scores is initialized to an empty ArrayList 
     *       and then is filled with the doubles exacted from input 
     */
    public Scores(String input)
    {
        scores = new ArrayList<Double>();
        this.read(input);
    }

    /**
     * Extracts numbers from a String and fills this Score's scores with
     * them.
     * 
     * @param input a String containing doubles seperated by whitespace
     *        to fill the this Scores's scores ArrayList
     * 
     * Post: this Score's scores ArrayList is filled with the doubles 
     *       exacted from input
     */
    public void read(String input)
    {
        Scanner inputtedScores = new Scanner(input);
        while (inputtedScores.hasNext()) 
        {
            double nextScore = Double.parseDouble(inputtedScores.next());
            this.scores.add(nextScore);
        }
    }

    /**
     * Finds the high of this Score's scores. This Score's scores ArrayList is
     * not modified since a copy is used, but QuickSort's findKth is used to
     * find the high.
     * 
     * @return the highest score in this Score's scores ArrayList
     */
    public double findHigh()
    {
        ArrayList<Double> copy = new ArrayList(this.scores);
        int highestIndex = scores.size() - 1;
        QuickSort.findKth(copy, highestIndex);
        return copy.get(highestIndex);
    }

    /**
     * Finds the low of this Score's scores. This Score's scores ArrayList is
     * not modified since a copy is used, but QuickSort's findKth is used to
     * find the low.
     * 
     * @return the lowest score in this Score's scores ArrayList
     */
    public double findLow()
    {
        ArrayList<Double> copy = new ArrayList(this.scores);
        int lowestIndex = 0;
        QuickSort.findKth(copy, lowestIndex);
        return copy.get(lowestIndex);
    }

    /**
     * Finds the median of this Score's scores. This Score's scores ArrayList is
     * not modified since a copy is used, but QuickSort's findKth is used to
     * find the median.
     * 
     * @return the median of this Score's scores ArrayList
     */
    public double findMedian()
    {
        ArrayList<Double> copy = new ArrayList(this.scores);
        int lowestIndex = 0;
        QuickSort.findKth(copy, lowestIndex);
        return copy.get(lowestIndex);
    }

    /**
     * Finds the average of the list of scores
     * 
     * @return the average of the scores 
     *         (sum of each divided by the scores size)
     */
    public double findAverage()
    {
        double sum = 0.0;
        for (Double item : this.scores)
            sum += item.doubleValue();

        return sum / this.scores.size();
    }

    /**
     * Converts the ArrayList scores to a String
     * 
     * @return this scores ArrayList as a String
     *         using the ArrayList toString method.
     */
    public String toString()
    {
        return this.scores;
    }
}