/*
 * Eugene Triguba <ytriguba17@ole.augie.edu>
 *
 * Homework 6: 29-3 MyLinkedList
 * Demo.java
 */

import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

class Demo
{
    /**
     * A professor selects 1 from n students for a 5 extra-point bonus. The professor places the
     * students in a circle and then draws a number m (1 <= m <= n) from a hat. The game is
     * played by having the professor walk clockwise around a circle, stopping at every mth
     * student, whom is asked to leave the game. The professor continues to walk clockwise,
     * asking every mth student to leave the game. The last person in the game is the winner.
     *
     * Input: Names of students, one on each line, -oo- to end, entered via the keyboard
     * Output: The random number, and the winner of the game
     *
     * @param args - not used
     */
    public static void main(String[] args)
    {
        Random numberGenerator = new Random();

        System.out.println("Enter names of student, one on each line, -oo- to end: ");
        MyLinkedList<String> studentNames = buildRandomList();

        int totalStudents = studentNames.size();
        int randomNumber = numberGenerator.nextInt(totalStudents) + 1;

        System.out.printf("There are %d students. One of you will get 5 extra points.\n", 
			totalStudents);
        System.out.println("Generated a random number: " + randomNumber);

        josephus(studentNames, randomNumber);
    }

    /**
     * Reads in lines of input from the keyboard and builds a linked list,
     * with the input lines inserted at random positions in the list.
     *
     * Pre: A sequence of lines entered via the keyboard; -oo- is the sentinel
     * @return MyLinkedList<String> of the input lines inserted at random position, 1 line per node.
     */
    public static MyLinkedList<String> buildRandomList()
    {
        Scanner input = new Scanner(System.in);
        ArrayList<String> items = new ArrayList<>();
        MyLinkedList<String> list = new MyLinkedList<>();

        while (input.hasNextLine())
        {
            String line = input.nextLine();
            if (line.equals("-oo-")) break;

            items.add(line);
        }

        Collections.shuffle(items);

        for (String item : items)
            list.add(item);

        return list;
    }

    /**
     * Moves around a list of students circularly and erases
     * every mth students until only 1 remains.
     *\
     * The list is printed each time a student is removed.
     * The winner is printed at the end
     *
     * @param studentNames - a list of names
     * @param randomNumber - represents the number of elements you want to move
     *                       down (every mth student)
     */
    public static void josephus(MyLinkedList<String> studentNames, int randomNumber)
    {
        int index = 0;
        int size = studentNames.size();

        while (size > 1)
        {
            index = addToIndex(index, randomNumber, size);
            studentNames.remove(index);
            size--;

            System.out.println(studentNames);
        }

        System.out.println("The winner is " + studentNames.getFirst());
    }

    /**
     * Adds a number to the index and if the sum is greater
     * than the collection size, it will wrap around to the beginning.
     *
     * @param currentIndex - the starting index
     * @param num - represents the number of elements you want to move down
     * @param collectionSize - the total number of elements in your collection
     * @return the current index moved down num elements
     */
    public static int addToIndex(int currentIndex, int num, int collectionSize)
    {
        // We decrement num here so it works with indexes (0-based).
        // So if you want to move down 5 elements, that would correlate to an index of 4.
        num--;

        if (currentIndex + num > collectionSize - 1)
        {
            currentIndex += num;
            currentIndex %= collectionSize;
        }
        else currentIndex += num;

        return currentIndex;
    }
}
