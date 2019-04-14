// Eugene Triguba
// ytriguba17@ole.augie.edu
// BlackJack.java
// Homework 10: 11 Composition

import java.util.Iterator;
import java.util.Vector;
import java.util.Scanner;

public class BlackJack
{
    private DeckOfCards deck;
    private Vector<String> dealer;
    private Vector<String> player;

    /* The BlackJack object properly initialized.
    **
    ** Post: This BlackJack object's dealer and player initialized
    **        to empty vectors.
    **        This BlackJack object's deck is initialized to a new deck.
    **        The deck is shuffled.
    **        Two cards are added to this player Vector.
    **        Two cards are added to this dealer Vector.
    */
    public BlackJack()
    {
        deck = new DeckOfCards();
        dealer = new Vector<String>();
        player = new Vector<String>();

        deck.shuffle();
        dealer.add(deck.deal());
        dealer.add(deck.deal());
        player.add(deck.deal());
        player.add(deck.deal());
    }

    /* Plays 1 game of BlackJack.
    ** 
    ** Input:  'H' or 'S' from the user to indicate whether the user wants to hit
    **          or stay
    **
    ** Output: Various messages indicating the progress of the game and who won.
    **
    ** Post: This BlackJack object's dealer Vector has cards added to it until
    **        the total is greater than 17.
    **        This BlackJack object's player Vector has cards added to it if
    **        the player decides to hit.
    */
    public void play() 
    {
        displayPlayer();
        displayDealer(true);

        playerTurn();
        dealerTurn();
        whoWon();
    }

    /* Plays the dealer's turn by adding cards to the dealer's hand until the
    ** total is greater than 17. Then displays the dealer's hand, total, and
    ** kills the program if the dealer busted.
    ** 
    ** Post: This BlackJack object's dealer Vector has cards added to it until
    **        the total is greater than or equal to 17.
    **
    ** Output: The total cards in the dealer Vector printed to the screen along
    **          with the total score of those cards.
    **          If the dealer busted, a message will be displayed to the screen.
    */
    private void dealerTurn()
    {
        int dealerTotal = calculateTotal(dealer);
        while (dealerTotal < 17) 
        {
            dealer.add(deck.deal());
            dealerTotal = calculateTotal(dealer);
        }

        displayDealer(false);
        System.out.printf("Dealer has %d. ", dealerTotal);
        
        if (busted(dealer))
        {
            System.out.println("Dealer busted. You won.");
            System.exit(0);
        }
    }

    /* Plays the player's turn by displaying the player's hand, prompting the
    ** user if they would like to hit or stay, and killing the program if the
    ** player busted.
    **
    ** Input: The character 'h' or 's' to denote hit or stay. 
    **         Case does not matter. Loops until the user chooses to stay or busts.
    **
    ** Output: The cards the player has in hand and the total of those cards.
    **          A prompt to choose whether to hit or stay.
    **          If the player busted, a message will be displayed to the screen.
    **
    ** Post: this BlackJack object's player Vector has cards added to it if
    **        the user chooses to hit.
    */
    private void playerTurn()
    {
        Scanner keyboard = new Scanner(System.in);
        char choice = '\u0000';

        while (choice != 's')
        {
            displayPlayer();
            System.out.printf("You have %d. Hit of stay H/S: ", calculateTotal(player));
            choice = keyboard.nextLine().trim().toLowerCase().charAt(0);

            if (choice == 'h') 
                player.add(deck.deal());

            if (busted(player)) 
            {
                displayPlayer();
                System.out.println("You busted. Dealer won.");
                System.exit(0);
            }
        }
    }

    /* Calculates the totals for this BlackJack object's player Vector and
    ** dealer Vector. Checks which is bigger to see who won or if there is
    ** a tie.
    **
    ** Output: a message printed to the screen saying whether the player or
    **          dealer won and if there was a tie.
    */
    private void whoWon()
    {
        int playerTotal = calculateTotal(player);
        int dealerTotal = calculateTotal(dealer);

        if (playerTotal > dealerTotal) System.out.println("You won. Bye Bye.");
        else if (dealerTotal > playerTotal) System.out.println("Dealer won. Bye Bye.");
        else System.out.println("Tie! Bye Bye.");
    }

    /* Calculates the total score of the cards in Vector v.
    **
    ** Param v: a Vector containing cards in the form "RankSuit".
    ** Return: The numeric total score of all the cards in v.
    */
    private static int calculateTotal(Vector<String> v)
    {
        int sum = 0;

        Iterator<String> iter = v.iterator();
        while (iter.hasNext())
            sum += findScore(iter.next());

        return sum;
    }

    /* Calculates the total score of the cards in Vector v to see if the
    ** score is over 21.
    **
    ** Param v: a Vector containing cards in the form "RankSuit".
    ** Return: True if the total of the cards in v is greater than 21.
    **          False otherwise.
    */
    private static boolean busted(Vector<String> v)
    {
        int total = calculateTotal(v);
        if (total > 21) return true;
        else return false;
    }

    /* Displays the player's hand.
    **
    ** Output: the contents of this BlackJack object's player Vector displayed
    **          on the screen.
    */
    private void displayPlayer()
    {
        System.out.println("Your hand: ");
        Iterator<String> iter = player.iterator();
        while (iter.hasNext()) 
            System.out.printf("\t %s \n", iter.next());
    }

    /* Displays the dealer's hand.
    **
    ** Param firstTurn: a boolean to decide if the dealer's second card should
    **                   be hidden. It is used on the first turn.
    **
    ** Output: If firstTurn is true, the first card in this BlackJack object's
    **          dealer Vector is displayed and the second card is hidden.
    **          Otherwise, the entire contents of this BlackJack's dealer Vector
    **          displayed on the screen. 
    */
    private void displayDealer(boolean firstTurn)
    {
        System.out.println("Dealer hand: ");
        if (firstTurn)
        {
            System.out.printf("\t %s \n", dealer.firstElement());
            System.out.printf("\t %s \n", "********");
        }
        else
        {
            Iterator<String> iter = dealer.iterator();
            while (iter.hasNext()) 
                System.out.printf("\t %s \n", iter.next());
        }
    }

    /* Finds the score of card by extracting the rank.
    **
    ** Param card: a card in the form "RankSuit".
    ** Return: if the rank of card is:
    **          'Ace' returns 11
    **          'King', 'Queen', or 'Jack' returns 10
    **          Each rank in the range [2..10] returns that number.
    **            e.g. 'Ten' returns the integer 10
    **          0 is returned if card doesn't have a valid rank.
    */
    private static int findScore(String card)
    {
        String rank = extractRank(card);
        switch (rank)
        {
            case "Ace":   return 11;
            case "King":  return 10;
            case "Queen": return 10;
            case "Jack":  return 10;
            case "Ten":   return 10;
            case "Nine":  return 9;
            case "Eight": return 8;
            case "Seven": return 7;
            case "Six":   return 6;
            case "Five":  return 5;
            case "Four":  return 4;
            case "Three": return 3;
            case "Two":   return 2;
            default:      return 0;
        }
    }

    /* Extracts the rank from a card.
    **
    ** Param card: a card in the form "RankSuit".
    ** Return: the rank of the card with the first letter capitilized
    **          e.g. "Ace"
    */
    private static String extractRank(String card)
    {
        String rank = "";
        rank += card.charAt(0);

        for (int i = 1; Character.isLowerCase(card.charAt(i)); ++i)
        {
            if (i >= card.length()) break;

            char letter = card.charAt(i);
            rank += letter;
        }

        return rank;
    }
}
