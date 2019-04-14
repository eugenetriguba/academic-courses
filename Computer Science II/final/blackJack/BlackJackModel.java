import java.util.Vector;
import java.util.Iterator;
import javax.swing.JOptionPane;

class BlackJackModel implements BlackJackConstants
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
    public BlackJackModel()
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

    // Return: this BlackJackModel's dealer
    public Vector<String> getDealer()
    {
        return dealer;
    }

    // Return: this BlackJackModel's player
    public Vector<String> getPlayer()
    {
        return player;
    }

    // Return: this BlackJackModel's deck
    public DeckOfCards getDeck()
    {
        return deck;
    }

    // Post: adds a card in the player or dealer Vector
    public void dealCard(int playerOrDealer)
    {
        if (playerOrDealer == PLAYER) player.add(deck.deal());
        else dealer.add(deck.deal());
    }

    // Desc: Calculates the totals for both players. Checks which is bigger to
    // see who won or if there is a tie.
    // Return: an integer constant depicting what the outcome is
    public int whoWon()
    {
        int playerTotal = calculateTotal(PLAYER);
        int dealerTotal = calculateTotal(DEALER);

        if (playerTotal > dealerTotal) return PLAYER;
        else  if (playerTotal < dealerTotal) return DEALER;
        else return TIE;
    }



    // Desc: Calculates the total score of the cards of player or dealer.
    // Return: The numeric total score of all the cards of player or dealer.
    public int calculateTotal(int playerOrDealer)
    {
        int sum = 0;
        Iterator<String> iter;

        if (playerOrDealer == PLAYER) iter = player.iterator();
        else iter = dealer.iterator();

        while (iter.hasNext())
            sum += findScore(iter.next());

        return sum;
    }

    // Return: the score of the player or dealer's first card.
    public int getFirstCardScore(int playerOrDealer)
    {
        if (playerOrDealer == DEALER) return findScore(dealer.get(0));
        else return findScore(player.get(0));
    }

    // Desc: Calculates the total score of the cards to see if the
    //       score is over 21.
    // Return: True if the total of the cards is greater than 21.
    //          False otherwise.
    public boolean busted(int playerOrDealer)
    {
        int total = calculateTotal(playerOrDealer);
        if (total > 21) return true;
        else return false;
    }

    // Desc: Finds the score of card by extracting the rank.
    // Pre: card is in the form "RankSuit".
    // Return: if the rank of card is:
    //          'Ace' returns 11
    //          'King', 'Queen', or 'Jack' returns 10
    //          Each rank in the range [2..10] returns that number.
    //            e.g. 'Ten' returns the integer 10
    //          0 is returned if card doesn't have a valid rank.
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

    // Desc: Extracts the rank from a card by adding the first uppercase letter
    //       to a new String and looping, adding each char to the new String,
    //       until another uppercase letter is reached.
    // Pre: card is in the form "RankSuit".
    // Return: the rank of the card with the first letter capitilized
    //          e.g. "Ace"
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