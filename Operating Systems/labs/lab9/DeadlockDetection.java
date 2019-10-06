/*
 * Eugene Triguba <ytriguba17@ole.augie.edu>
 * COSC 310: Operating Systems
 * Homework 9: Deadlock Detection
 */

import java.util.Scanner;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * Builds the graph in figure 6-5 and checks
 * if there is a deadlock based on the given 
 * user input.
 */
public class DeadlockDetection
{
    public static void main(String[] args) 
    {
        Scanner input = new Scanner(System.in);
        boolean exit = false;

        while (!exit)
        {
            System.out.print("Enter a command [ex: a < R]: ");
            exit = runCommand(input.nextLine());
        }
        
        input.close();
    }

    /**
     * Runs a command.
     * 
     * If the command is not "quit", it will try to parse
     * the command and run a deadlock check.
     * 
     * @param command - the command to run
     * @return true if the command is "quit"; false otherwise.
     */
    public static boolean runCommand(String command)
    {
        if (command.equals("quit")) return true;
        else 
        {
            String[] tokens = command.split(" ");
            char first = tokens[0].charAt(0);
            char second = tokens[2].charAt(0);

            deadlockCheck(buildGraph(first));
            deadlockCheck(buildGraph(second));  
        }

        return false;
    }

    /**
     * Constructs a graph like figure 6-5 in the textbook.
     * 
     * @param head - the node you would like back to use as the head node
     * @return the head node specified by head; null if it does not exist in the graph.
     */
    public static Node<Character> buildGraph(char head)
    {
        char[] totalLetters = {'R', 'a', 'S', 'c', 'f', 'W', 'd', 'U', 'g', 'b', 'T', 'e', 'V'};
        HashMap<Character, Node<Character>> nodes = new HashMap<>();

        for (char letter : totalLetters)
            nodes.put(letter, new Node<Character>(letter));

        nodes.get('R').setNext(nodes.get('a'));
        nodes.get('a').setNext(nodes.get('S'));
        nodes.get('c').setNext(nodes.get('S'));
        nodes.get('f').setNext(nodes.get('S'));
        nodes.get('W').setNext(nodes.get('f'));
        nodes.get('d').setPrev(nodes.get('S'));
        nodes.get('d').setNext(nodes.get('T'));
        nodes.get('b').setNext(nodes.get('T'));
        nodes.get('T').setNext(nodes.get('e'));
        nodes.get('e').setNext(nodes.get('V'));
        nodes.get('V').setNext(nodes.get('g'));
        nodes.get('g').setNext(nodes.get('U'));
        nodes.get('U').setNext(nodes.get('d'));
        
        return nodes.containsKey(head) ? nodes.get(head) : null;
    }

    /**
     * Checks if there is a deadlock in the graph starting at head
     * 
     * @param head - the head node to start at
     * @return true if there is a deadlock; false otherwise.
     */
    public static boolean deadlockCheck(Node<Character> head)
    {
        LinkedList<Node<Character>> path = new LinkedList<>();
        Node<Character> curr = head;
        Node<Character> prev = null;

        System.out.printf("Starting node: %s\n", curr);
        path.add(head);

        while (curr.getOutgoingNodes() > 0)
        {
            prev = curr;
            curr = curr.getNext();
            path.add(curr);

            System.out.printf("%s has an outgoing node -- following to: %s\n",
                prev, curr);

            if (duplicatesPresent(path, curr))
            {
                System.out.printf("\t ***List already contains node %s\n", curr);
                System.out.printf("\t ***Deadlock Detected! Node List L = %s\n", path);
                return true;
            }
        }


        while (path.size() > 1)
            System.out.printf("%s has no outgoing nodes -- returning to: %s\n", path.removeLast(), path.getLast());

        System.out.printf("Path starting at %s contains no cycles. Next Node...\n\n", head);

        return false;
    } 

    /**
     * Checks if item is in list more than once.
     * 
     * @param <T>  - T can be any object.
     * @param list - the list to check for duplicates in
     * @param item - the item to check for duplicates of
     * 
     * @return true if item is in the list more than once; False otherwise.
     */
    public static <T> boolean duplicatesPresent(LinkedList<T> list, T item)
    {
        return list.indexOf(item) != list.lastIndexOf(item);
    }
}