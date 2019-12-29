/*
 * Eugene Triguba <ytriguba17@ole.augie.edu>
 * Homework 9: 31-5 TestLLPriorityQueue
 */

import java.util.Comparator;

/**
 * Test LLPriorityQueue's Constructors
 */
public class TestLLPriorityQueue
{
    public static void main(String[] args)
    {
        LLPriorityQueue<Integer> minQueue = new LLPriorityQueue<>();
        LLPriorityQueue<Integer> maxQueue = new LLPriorityQueue<>(new MaxQueue<>());

        for (int i = 0; i < 10; i++)
        {
            minQueue.offer(i);
            maxQueue.offer(i);
        }

        System.out.println("Min Queue: " + minQueue);
        System.out.println("Max Queue: " + maxQueue);
    }
}

/**
 * Implements a MaxQueue when used as the Comparator
 */
class MaxQueue<Integer extends Comparable<Integer>> implements Comparator<Integer>
{
    public int compare(Integer item1, Integer item2)
    {
        if (item1.compareTo(item2) > 0) return -1;
        else if (item1.compareTo(item2) < 0) return 1;
        else return 0;
    }
}