// A DeckOfCards object represents a deck of ordinary playing cards. The top
// card is dealt each time the method deal is called.  A dealt card will not be
// reused until the DeckOfCards is shuffled.

import java.util.Random;

public class DeckOfCards
{
    private int top;
    private Suit[] suit;
	private Rank[] rank;
	
	private enum Suit { DIAMOND, CLUB, HEART, SPADE };						
	private enum Rank { ACE, TWO, THREE, FOUR, FIVE, SIX, 
		SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING };


    // Post: This DeckOfCards initialized to a new deck; the cards are ordered 
    //   from AceSpade to KingDiamond as in a new deck; top initialized to 0.
	public DeckOfCards()
	{
		this.rank = new Rank[52];
		this.suit = new Suit[52];

		for (int i = 0; i < 4; ++i)
		{
			this.rank[i * 13 + 0] = Rank.ACE;
			this.rank[i * 13 + 1] = Rank.TWO;
			this.rank[i * 13 + 2] = Rank.THREE;
			this.rank[i * 13 + 3] = Rank.FOUR;
			this.rank[i * 13 + 4] = Rank.FIVE;
			this.rank[i * 13 + 5] = Rank.SIX;
			this.rank[i * 13 + 6] = Rank.SEVEN;
			this.rank[i * 13 + 7] = Rank.EIGHT;
			this.rank[i * 13 + 8] = Rank.NINE;
			this.rank[i * 13 + 9] = Rank.TEN;
			this.rank[i * 13 + 10] = Rank.JACK;
			this.rank[i * 13 + 11] = Rank.QUEEN;
			this.rank[i * 13 + 12] = Rank.KING;
		}

		for (int i = 0; i < 13; ++i)
            this.suit[i] = Suit.SPADE;

		for (int i = 13; i < 26; ++i)
            this.suit[i] = Suit.HEART;


		for (int i = 26; i < 39; ++i)
            this.suit[i] = Suit.CLUB;

		for (int i = 39; i < 52; ++i)
            this.suit[i] = Suit.DIAMOND;
		
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
		String result, rank, suit = "";
		
		if (this.top < 52) 
		{
			rank = this.rank[this.top].toString(); 
			suit = this.suit[this.top].toString();
			result = properlyCapitalize(rank) + properlyCapitalize(suit);
		}
		else result = "NoMoreCard";
		
		this.top++;
		return result;
	}
	
	private static String properlyCapitalize(String word)
	{
		String firstLetter = word.charAt(0) + "";
		String restOfWord = word.substring(1).toLowerCase();
		return firstLetter.concat(restOfWord);
	}

    // Desc: Swaps the values of two elements in a String array.
    // Post: arr[firstIndex] and arr[secondIndex] swap values.
    private static <T> void swap(T[] arr, int firstIndex, int secondIndex)
    {
        T temp = arr[firstIndex];
		arr[firstIndex] = arr[secondIndex];
		arr[secondIndex] = temp;
    }
}