
import java.util.Scanner;

// Desc:	Display a user-specified number of cards from a deck of cards.
//Input:	The user enters a character (N/S) signaling whether the deck should be shuffled or not, 
//	and an integer specifying the number of cards to be dealt via the keyboard.
//Output:A hand consisting of the user-specified number of cards dealt from a deck of cards.
public class DeckOfCardsApp
{  
    public static void main(String[] args) 
    {
        Scanner keyboard = new Scanner(System.in);
        DeckOfCards deck = new DeckOfCards();

        System.out.print("New deck or shuffled deck? N/S: ");
        char response = keyboard.nextLine().charAt(0);
        if (response == 'S' || response == 's') deck.shuffle();

        System.out.print("How many cards do you want?");
        int numOfCards = keyboard.nextInt();
        for (int i = 0; i < numOfCards; ++i)
            System.out.println(deck.deal());
    }
}
