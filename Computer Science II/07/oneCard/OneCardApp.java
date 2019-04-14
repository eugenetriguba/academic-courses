// Desc: Play the game of OneCard. The game has 2 players, the user and the
//   computer. The user is dealt 1 card, the computer is dealt 1 card.  The user
//   has the option to change the card once.  
//   The winner is the player with the higher rank card (Ace>King>Queen>…>3>2).
//   The suit is not relevant.
// Input: The user enters a character (Y/N) signaling whether the user wants
//   to change the card. The user enters a character (Y/N) signaling whether the
//   user wants to play again.
// Output:The user's card, followed by a message asking the user if the user
//   wants to change the card, followed optionally the user's new card, followed
//   by the computer's card, followed by the result of the game, followed by a
//   message asking the user if the user wants to play again. 
import java.util.Scanner;
class OneCardApp
{  
    public static void main(String[] args) 
    {
        Scanner f=new Scanner(System.in);
        while (true)
        {
            OneCard.play();
            
            System.out.print("Play again? (y/n): ");
            char ch=f.nextLine().charAt(0);
            if (ch=='N' || ch=='n') break;
        }
    }
}
