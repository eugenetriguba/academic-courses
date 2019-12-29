// Eugene Triguba
// ytriguba17@ole.augie.edu
// Homework 4: SinglyLinkedList
// IntegerDB.java

import java.util.Scanner;

/**
 * A driver to test out the
 * SinglyLinkedList class
 */
public class IntegerDB
{
    public static void main(String[] args)
    {
        Node<Integer> front = new Node<>();
        Scanner input = new Scanner(System.in);

        printMenu();
        while (input.hasNextInt())
        {
            int command = input.nextInt();
            switch (command)
            {
                case 1: front = buildList();
                        printList(front);
                        break;
                case 2: System.out.print("Enter a key: ");
                        front = SinglyLinkedList.insertFirst(front, input.nextInt());
                        printList(front);
                        break;
                case 3: System.out.print("Enter a key: ");
                        front = SinglyLinkedList.insertLast(front, input.nextInt());
                        printList(front);
                        break;
                case 4: System.out.print("Insert: ");
                        int item = input.nextInt();
                        System.out.print("After: ");
                        int after = input.nextInt();

                        Node<Integer> result= SinglyLinkedList.seqSearch(front, after);
                        if (result == null)
                            System.out.println(item + " not in list.  Insert failed.");
                        else
                        {
                            SinglyLinkedList.insertAfter(result, item);
                            System.out.println(result.value + " inserted after " + item);
                        }
                        printList(front);
                        break;
                case 5: front = SinglyLinkedList.removeFirst(front);
                        System.out.println("First item removed.");
                        printList(front);
                        break;
                case 6: front = SinglyLinkedList.removeLast(front);
                        System.out.println("Last item removed.");
                        printList(front);
                        break;
                case 7: System.out.print("Enter the item to remove: ");
                        front = SinglyLinkedList.remove(front, input.nextInt());
                        printList(front);
                        break;
                case 8: System.out.print("Enter a key to search for: ");
                        Node<Integer> node = SinglyLinkedList.seqSearch(front, 
							input.nextInt());
                        if (node == null) System.out.println("Item not found");
                        else System.out.println(node.value + " was found");

                        printList(front);
                        break;
                case 9: printList(front);
                        break;
                case 10: System.exit(0);
            }
            printMenu();
        }
    }

    /**
     * Prints the toString of front
     *
     * @param front the front node of the list
     * @param <T> T can be any object
     */
    public static <T> void printList(Node<T> front)
    {
        System.out.println(SinglyLinkedList.toString(front));
    }

    /**
     * Prints a menu of operations for
     * a singly linked list
     */
    public static void printMenu()
    {
        System.out.println("1. Build list");
        System.out.println("2. Insert key as first");
        System.out.println("3. Insert key as last");
        System.out.println("4. Insert key after item");
        System.out.println("5. Remove first");
        System.out.println("6. Remove last");
        System.out.println("7. Remove item");
        System.out.println("8. Search key in list");
        System.out.println("9. Print list");
        System.out.println("10. Exit");
    }

    /**
     * Builds a linked list from user input
     *
     * Pre: A sequence of integers entered via keyboard; -1 is sentinel
     * @return the front node to a linked list of the integers
     */
    public static Node<Integer> buildList()
    {
        Node<Integer> front = new Node<>();
        Scanner input = new Scanner(System.in);
        System.out.print("Enter integers separated by whitespace, -1 to end: ");

        while (input.hasNextInt())
        {
            int num = input.nextInt();
            if (num == -1) break;

            front = SinglyLinkedList.insertLast(front, num);
        }

        return front;
    }
}