// Eugene Triguba
// ytriguba17@ole.augie.edu
// Homework 6: 2-4
// 
// This program allows you to check whether a DFSM accepts or rejects a given input
// as long as we specify the start start, final states, and transitions of the DFSM.
import java.util.Scanner;
import java.util.Vector;

public class DFSM
{
    private Vector<String> transitions;
    private String startState;
    private Vector<String> finalStates;

    public DFSM(String startState, Vector<String> finalStates, Vector<String> transitions)
    {
        this.startState = startState;
        this.finalStates = finalStates;

        // Format: "state,letter,state" 
        // which means (start state, input letter) => final state 
        this.transitions = transitions;
    }

    // isAccepted checks whether input is accepted
    // or rejected. If a character not in the DFSM's alphabet 
    // is included in the input, the result will always be false.
    //
    // Param input: Any string.
    // Return: True if input is accepted for this DFSM; false otherwise.
    public boolean isAccepted(String input)
    {
        String currentState = this.startState;

        for (char c : input.toCharArray()) 
        {
            boolean validTransitionFound = false;
            
            for (String transition : this.transitions) 
            {
                String[] temp = transition.split(",");
                String transStartState = temp[0];
                String transLetter = temp[1];
                String transEndState = temp[2];

                if (transStartState.equals(currentState) && 
                    transLetter.equals(Character.toString(c))) 
                {
                    validTransitionFound = true;
                    currentState = transEndState;
                    break;
                }
            }

            if (!validTransitionFound) 
                return false;
        }

        return this.finalStates.contains(currentState);
    }

    public static void main(String[] args) 
    {
        Scanner keyboard = new Scanner(System.in);

        System.out.print("Start state: ");
        String startState = keyboard.nextLine();

        System.out.println("Final states (one per line, 0 to end):");
        Vector<String> finalStates = new Vector<>();
        for (String finalState = keyboard.nextLine(); !finalState.equals("0");)
        {
            finalStates.add(finalState);
            finalState = keyboard.nextLine();
        }

        System.out.println("Transitions (one per line, state,letter,state, 0,0,0 to end):");
        Vector<String> transitions = new Vector<>();
        for (String transition = keyboard.nextLine(); !transition.equals("0,0,0");)
        {
            if (transition.split(",").length != 3) 
            {
                System.out.println("Invalid transition. Try again. Format: state,letter,state");
                continue;
            }

            transitions.add(transition);
            transition = keyboard.nextLine();
        }

        DFSM dfsm = new DFSM(startState, finalStates, transitions);
        System.out.print("Enter input string (done to end): ");
        for (String input = keyboard.nextLine(); !input.equals("done"); )
        {
            System.out.println(dfsm.isAccepted(input) ? "Accept" : "Reject");
            System.out.print("Enter input string: ");
            input = keyboard.nextLine();
        }

        keyboard.close();
    }
}