// Eugene Triguba
// ytriguba17@ole.augie.edu
// OneCard.java
// Homework 7: 08 String

import java.util.Scanner;

public class OneCard
{
	// Desc: Play the game of OneCard. The game has 2 players, the user and the
	//   computer. The user is dealt 1 card, the computer is dealt 1 card. The
	//   user has the option to change the card once. The winner is the player
	//   with the higher rank card (Ace>King>Queen>…>3>2).  The suit is not
	//   relevant.
	// Input: The user enters a character (Y/N) signaling whether the user
	//   wants to change the card.
	// Output: The user's card, followed by a message asking the user if he
	//   wants to change the card, followed optionally the user's new card,
	//   followed by the computer's card, followed by the result of the game
    public static void play()
    {
        DeckOfCards userCards = new DeckOfCards();
        DeckOfCards dealerCards = new DeckOfCards();
        userCards.shuffle();
        dealerCards.shuffle();
        String userTopCard = userCards.deal();
        String dealerTopCard = dealerCards.deal();

        System.out.printf("Your card: %s\n", userTopCard);
        System.out.print("Change card (y/n): ");

        Scanner keyboard = new Scanner(System.in);
        char answer = keyboard.nextLine().trim().charAt(0);
        if (Character.toLowerCase(answer) == 'y') 
        {
            userTopCard = userCards.deal();
            System.out.printf("Your card: %s\n", userTopCard);
        }

        System.out.printf("Dealer's card: %s\n", dealerTopCard);

        whoWon(userTopCard, dealerTopCard);
    }

    // Desc: Finds who won the game by comparing userCard and dealerCard's rank.
    // Output: print to the screen who won the game
    private static void whoWon(String userCard, String dealerCard)
    {
        int userRank = findRank(userCard);
        int dealerRank = findRank(dealerCard);

        if (userRank > dealerRank) System.out.println("You won.");
        else if (userRank == dealerRank) System.out.println("You tied.");
        else System.out.println("Dealer won.");
    }

    // Desc: Find the numerical rank of card.
    // Return: if the rank of card is:
    //   'Ace' returns 14
    //   'King' returns 13
    //   'Queen' returns 12
    //   'Jack' returns 11
    //    Each rank that is a number returns that number.
    //    e.g. 'Ten' returns the integer 10
    //    If 1 is returned, card doesn't have a valid rank.
    private static int findRank(String card)
    {
        String rank = extractRank(card);
        switch (rank)
        {
            case "Ace":   return 14;
            case "King":  return 13;
            case "Queen": return 12;
            case "Jack":  return 11;
            case "Ten":   return 10;
            case "Nine":  return 9;
            case "Eight": return 8;
            case "Seven": return 7;
            case "Six":   return 6;
            case "Five":  return 5;
            case "Four":  return 4;
            case "Three": return 3;
            case "Two":   return 2;
            default:      return 1;
        }
    }

    // Desc: Extracts the rank from a card that's in the form "RankSuit"
    // Return: the rank of the card with the first letter capitilized
    //   e.g. "Ace"
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