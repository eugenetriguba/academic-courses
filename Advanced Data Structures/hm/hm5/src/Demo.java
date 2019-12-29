// Eugene Triguba
// ytriguba17@ole.augie.edu
// Demo.java

import java.util.Random;
import java.util.Scanner;

public class Demo
{
    /**
     * A professor selects 1 from n students for a 5 extra-point bonus.
     * The professor places the students in a circle and then draws a
     * number m (1<=m<=n) from a hat.  The game is played by having the
     * professor walk clockwise around a circle, stopping at every mth
     * student, whom is asked to leave the game.  The professor continues
     * to walk clockwise, asking every mth student to leave the game.
     * The last person in the game is the winner.
     *
     * Input: The number of students followed by names of students one on
     *        each line entered via the keyboard
     *
     * Output: The random number m, and the winner of the game
     *
     * @param args not used
     */
    public static void main(String[] args)
    {
        Random generator = new Random();

        System.out.println("Enter names of students, one on each line (-oo- to end): ");
        DNode<String> header = buildList();

        System.out.println("One of you will get 5 extra points.");
        int randomNum = generator.nextInt(DoublyLinkedList.size(header)) + 1;
        System.out.println("Generating random number ....." + randomNum);

        josephus(header, randomNum);
    }

    /**
     * A loop moves around the list of n elements circularly
     * and erases every mth students until only 1 remains.
     *
     * Pre:	header points to the list; n is length of list;
     *      m is a random number
     *
     * Output: The list is printed each time a student is removed;
     *         The winner is printed at the end
     *
     * @param header the front of the linked list
     * @param randomNum the number that decides every mth student
     */
    public static void josephus(DNode<String> header, int randomNum)
    {
        int totalStudents = DoublyLinkedList.size(header);
        DNode<String> curr = header.next;
        System.out.println(DoublyLinkedList.toString(header));

        for (int i = 1; i < totalStudents; i++)
        {
            for (int j = 1; j < randomNum - 1; j++)
            {
                if (curr.next == header) curr = curr.next;
                curr = curr.next;
            }
            System.out.print("Delete " + curr.value);
            if (curr.next == header) curr = curr.next;
            curr = curr.next;
            DoublyLinkedList.remove(curr.prev);
            if (curr.value == null) curr = curr.next;
            System.out.println(DoublyLinkedList.toString(header));
        }

        System.out.println("Student " + curr.value + " wins the bonus");
    }

    /**
     * Builds a linked list by reading in names
     * from the keyboard
     *
     * Pre: A sequence of names entered via keyboard, one on each line; -oo- is sentinel
     *
     * @return A linked list of the names
     */
    public static DNode<String> buildList()
    {
        Scanner input = new Scanner(System.in);
        DNode<String> front = new DNode<>();

        while (input.hasNext())
        {
            String name = input.next();
            if (name.equals("-oo-")) break;

            DoublyLinkedList.insertLast(front, name);
        }

        return front;
    }
}
