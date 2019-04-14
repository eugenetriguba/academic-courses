import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class BlackJackController implements BlackJackConstants
{
    private BlackJackModel model; 
    private BlackJackView view;

    // Post: this BlackJackController's model set to a new model.
    //       this BlackJackController's view set to a new view.
    //       listeners are registers with the view
    public BlackJackController()
    {
        this(new BlackJackModel(), new BlackJackView());
        registerListeners();
    }

    // Post: this BlackJackController's model set to model.
    //       this BlackJackController's view set to view.
    //       listeners are registers with the view
    public BlackJackController(BlackJackModel model, BlackJackView view)
    {
        this.model = model;
        this.view = view;
        registerListeners();
    }

    // Post: attaches ActionHandler to the buttons in the view 
    //       as a ActionListener
    private void registerListeners()
    {
        view.addButtonListeners(new ActionHandler());
    }

    // Post: deal a card to player
    //       updates the score in the view
    //       handle if the player is busted
    // Output: displays the player's card to the window.
    private void playerTurn()
    {
        model.dealCard(PLAYER);
        displayPlayerCards();
        view.updateScore(PLAYER, model.calculateTotal(PLAYER));

        handleBusted(PLAYER);
    }

    // Post: deal a card to the dealer while the total is less
    //       than 17
    //       Updates the score in the view
    //       handle if the dealer busts
    // Output: displays the dealer's cards to the window
    private void dealerTurn()
    {
        int dealerTotal = model.calculateTotal(DEALER);
        while (dealerTotal < 17) 
        {
            model.dealCard(DEALER);
            dealerTotal = model.calculateTotal(DEALER);
        }
        displayDealerCards(false);
        view.updateScore(DEALER, model.calculateTotal(DEALER));

        handleBusted(DEALER);
    }

    private void handleBusted(int playerOrDealer)
    {
        if (model.busted(playerOrDealer))
        {
            if (playerOrDealer == DEALER) view.displayMessage("Dealer busted!");
            else view.displayMessage("Player busted!");

            view.enableButton("Hit", false);
            view.enableButton("Stay", false);
            view.enableButton("New", true);
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
        int playerTotal = model.calculateTotal(PLAYER);
        int dealerTotal = model.calculateTotal(DEALER);

        if (model.busted(PLAYER)) view.displayMessage("Dealer won!");
        else if (model.busted(DEALER)) view.displayMessage("Player won!");
        else
        {
            if (playerTotal > dealerTotal) view.displayMessage("Player won!");
            else  if (playerTotal < dealerTotal) view.displayMessage("Dealer won!");
            else view.displayMessage("Tie!");
        }

        view.enableButton("Hit", false);
        view.enableButton("Stay", false);
        view.enableButton("New", true);
    }

    // Post: this BlackJackController's model set to a new model.
    //       this BlackJackController's view set to a new view.
    //       listeners are registers with the view
    private void resetGame()
    {
        model = new BlackJackModel();
        view = new BlackJackView();
        registerListeners();
    }

    // Output: The cards in the BlackJackModel's player Vector displayed in the
    //         window.
    private void displayPlayerCards()
    {
        Vector<String> player = model.getPlayer();
        JLabel[] cards = view.getLblPlayerCards();
        displayCards(player, cards);
    }

    private void displayDealerCards(boolean hideSecondCard)
    {
        Vector<String> dealer = model.getDealer();
        JLabel[] cards = view.getLblDealerCards();
        ImageIcon firstCard = new ImageIcon("cardImages/" + dealer.firstElement() + ".gif");

        if (hideSecondCard) cards[0].setIcon(firstCard);
        else displayCards(dealer, cards);
    }

    private void displayCards(Vector<String> handOfCards, JLabel[] cardIcons)
    {
        Iterator<String> iter = handOfCards.iterator();
        int i = 0;
    
        while (iter.hasNext()) 
        {
            ImageIcon icon = new ImageIcon("cardImages/" + iter.next() + ".gif");
            cardIcons[i].setIcon(icon);
            ++i;
        }
    }

    private class ActionHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
            String buttonText = e.getActionCommand();

            switch (buttonText)
            {
                case "Deal": 
                    handleDealButton();
                    break;
                case "Hit":
                    playerTurn();
                    break;
                case "Stay":
                    dealerTurn();
                    whoWon();
                    break;
                case "New":
                    resetGame();
                    break;
                default: 
                    System.err.println(buttonText + 
                        " is an invalid action.");
            }
        }

        private void handleDealButton()
        {
            displayPlayerCards();
            displayDealerCards(true);
            view.updateScore(PLAYER, model.calculateTotal(PLAYER));
            view.updateScore(DEALER, model.getFirstCardScore(DEALER));

            view.enableButton("Deal", false);
            view.enableButton("Hit", true);
            view.enableButton("Stay", true);
        }
    }
}