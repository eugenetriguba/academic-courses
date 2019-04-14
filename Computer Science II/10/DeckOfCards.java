// A DeckOfCards object represents a deck of ordinary playing cards. The top
// card is dealt each time the method deal is called.  A dealt card will not be
// reused until the DeckOfCards is shuffled.

import java.util.Random;

public class DeckOfCards
{
    private int top;
    private String[] suit;
    private String[] rank;

    // Post: This DeckOfCards initialized to a new deck; the cards are ordered 
    //   from AceSpade to KingDiamond as in a new deck; top initialized to 0.
	public DeckOfCards()
	{
		this.rank = new String[52];
		this.suit = new String[52];

		for (int i = 0; i < 4; ++i)
		{
			this.rank[i * 13 + 0] = "Ace";
			this.rank[i * 13 + 1] = "Two";
			this.rank[i * 13 + 2] = "Three";
			this.rank[i * 13 + 3] = "Four";
			this.rank[i * 13 + 4] = "Five";
			this.rank[i * 13 + 5] = "Six";
			this.rank[i * 13 + 6] = "Seven";
			this.rank[i * 13 + 7] = "Eight";
			this.rank[i * 13 + 8] = "Nine";
			this.rank[i * 13 + 9] = "Ten";
			this.rank[i * 13 + 10] = "Jack";
			this.rank[i * 13 + 11] = "Queen";
			this.rank[i * 13 + 12] = "King";
		}

		for (int i = 0; i < 13; ++i)
            this.suit[i] = "Spade";

		for (int i = 13; i < 26; ++i)
            this.suit[i] = "Heart";


		for (int i = 26; i < 39; ++i)
            this.suit[i] = "Club";

		for (int i = 39; i < 52; ++i)
            this.suit[i] = "Diamond";
		
		this.top = 0;
    }
    
    //Post:	This DeckOfCards thoroughly shuffled; top set to 0.
	public void shuffle()
	{
		Random numberGenerator = new Random();

		for (int i = 0; i < 10000; ++i)
		{
			int x = numberGenerator.nextInt(52);
			int y = numberGenerator.nextInt(52);

            swap(this.rank, x, y);
            swap(this.suit, x, y);
		}

		this.top = 0;
	}
    // Post: top incremented by 1.
    // Return: The top card of this DeckOfCards as a String such as "AceHeart", "TwoSpade", 
    //	"TenDiamond", "KingClub". If top >=52, "NoMoreCard" is returned.
	public String deal()
	{
		String result = "";
		
		if (this.top < 52) result = this.rank[this.top] + this.suit[this.top];
		else result = "NoMoreCard";
		
		this.top++;
		return result;
    }
    
    // Return: the top card's suit
    public String getSuit()
    {
        return this.suit[this.top];
    }

    // Return: the top card's rank
    public String getRank()
    {
        return this.rank[this.top];
    }

    // Desc: Swaps the values of two elements in a String array.
    // Post: arr[firstIndex] and arr[secondIndex] swap values.
    private static void swap(String[] arr, int firstIndex, int secondIndex)
    {
        String temp = arr[firstIndex];
		arr[firstIndex] = arr[secondIndex];
		arr[secondIndex] = temp;
    }
}
