// Eugene Triguba
// ytriguba17@ole.augie.edu
// BlackJack.java
// 16-1 ButtonAndLabel

public class BlackJack
{  
    // Desc: Play 1 game of black jack.
    // Post: a BlackJackModel, BlackJackView, and 
    //       BlackJackController are created.
    public static void main(String[] args) 
   	{
        BlackJackModel model = new BlackJackModel();
        BlackJackView view = new BlackJackView();
        BlackJackController controller = new BlackJackController(model, view);
	}
}
