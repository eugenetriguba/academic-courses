/**
 * Eugene Triguba <ytriguba17@ole.augie.edu>
 * Exam 3: 32-3 BSTree
 * Demo.java
 */

import java.util.Random;
import java.util.stream.IntStream;

/**
 * Inserts 1000 randomly generated [0..1999] and distinct integers 
 * into a binary-search tree, prints out the size and height of the
 * tree, searches for 10 randomly generated keys in the tree, and
 * outputs the number of comparisons it took to find that key as 
 * well as the average number of comparisons.
 */
public class Demo
{
    public static void main(String[] args) 
    {
        Random numberGenerator = new Random();
        BSTree<Integer> bsTree = new BSTree<>();

        while (bsTree.size() < 1000)
            bsTree.add(numberGenerator.nextInt(2000));
        
        System.out.println("Size of the tree: " + bsTree.size());
        System.out.println("Height of the tree: " + bsTree.height());

        int successfulSearch = 0;
        int comparisons[] = new int[10];
        while (successfulSearch < 10)
        {
            int key = numberGenerator.nextInt(2000);
            int level = bsTree.findLevelOf(key);

            if (level != -1)
            {
                System.out.printf("Search for %d required %d comparisons.\n", key, level);
                comparisons[successfulSearch] = level;
                successfulSearch++;
            }
        }

        double average = (double) IntStream.of(comparisons).sum() / comparisons.length;
        System.out.println("Average number of comparisons: " + average);
    }
}