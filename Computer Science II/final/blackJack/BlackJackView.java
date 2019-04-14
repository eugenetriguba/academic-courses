import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.*;

// BlackJackView creates a GUI for a Black Jack game
public class BlackJackView extends JFrame implements BlackJackConstants
{
    private JLabel[] lblPlayerCards = setupCards();
    private JLabel[] lblDealerCards= setupCards();
    private JPanel pnlCardIcons = new JPanel(new GridLayout(2, 7));

    private JButton btnDeal = new JButton("Deal");
    private JButton btnHit = new JButton("Hit");
    private JButton btnStay = new JButton("Stay");
    private JButton btnNew = new JButton("New");
    private JPanel pnlButtons = new JPanel(new GridLayout(1, 4)); 

    private JLabel lblPlayerScoreDesc = new JLabel("Player Score: ");
    private JLabel lblPlayerScoreNum = new JLabel("0");
    private JPanel pnlPlayerScore = new JPanel(new FlowLayout());

    private JLabel lblDealerScoreDesc = new JLabel("Dealer Score: ");
    private JLabel lblDealerScoreNum = new JLabel("0");
    private JPanel pnlDealerScore = new JPanel(new FlowLayout());

    private JPanel pnlBottomSection = new JPanel();

    public BlackJackView()
    {
        pnlCardIconsAddControls();
        add(pnlCardIcons, BorderLayout.CENTER);

        pnlButtonsAddControls();
        pnlPlayerScoreAddControls();
        pnlDealerScoreAddControls();
        pnlBottomSectionAddControls();
        add(pnlBottomSection, BorderLayout.SOUTH);

        btnHit.setEnabled(false);
        btnStay.setEnabled(false);
        btnNew.setEnabled(false);

        setWindowSettings();	
    }

    public JLabel[] getLblPlayerCards()
    {
        return lblPlayerCards;
    }

    public JLabel[] getLblDealerCards()
    {
        return lblDealerCards;
    }

    public void displayMessage(String message)
    {
        JOptionPane.showMessageDialog(null, message);
    }

    public void addButtonListeners(ActionListener listener)
    {
        btnDeal.addActionListener(listener);
        btnHit.addActionListener(listener);
        btnStay.addActionListener(listener);
        btnNew.addActionListener(listener);
    }

    public void enableButton(String buttonText, boolean enabledOrDisabled)
    {
        switch (buttonText)
        {
            case "Deal": 
                btnDeal.setEnabled(enabledOrDisabled);
                break;
            case "Hit":
                btnHit.setEnabled(enabledOrDisabled);
                break;
            case "Stay":
                btnStay.setEnabled(enabledOrDisabled);
                break;
            case "New":
                btnNew.setEnabled(enabledOrDisabled);
                break;
            default:
                System.err.printf("%s is an invalid option for method " + 
                    "enableButton", buttonText);
        }
    }

    public void updateScore(int playerOrDealer, int score)
    {
        String txtScore = Integer.toString(score);

        if (playerOrDealer == PLAYER) lblPlayerScoreNum.setText(txtScore);
        else lblDealerScoreNum.setText(txtScore);
    }

    private JLabel[] setupCards()
    {
        JLabel[] cards = new JLabel[7];

        for (int i = 0; i < cards.length; ++i)
        {
            cards[i] = new JLabel(new ImageIcon("cardImages/card.gif"));
        }

        return cards;
    }

    // Post: Contents of lblPlayerCards & lblDealerCards added to pnlCardIcons
    private void pnlCardIconsAddControls()
    {
        for (int i = 0; i < 7; ++i)
            pnlCardIcons.add(lblPlayerCards[i]);

        for (int i = 0; i < 7; ++i)
            pnlCardIcons.add(lblDealerCards[i]);
    }

    // Post: adds btnDeal, btnHit, btnStay, and btnNew to pnlButtons.
    private void pnlButtonsAddControls()
    {
        pnlButtons.add(btnDeal);
        pnlButtons.add(btnHit);
        pnlButtons.add(btnStay);
        pnlButtons.add(btnNew);
    }

    private void pnlPlayerScoreAddControls()
    {
        pnlPlayerScore.add(lblPlayerScoreDesc);
        pnlPlayerScore.add(lblPlayerScoreNum);
    }

    private void pnlDealerScoreAddControls()
    {
        pnlDealerScore.add(lblDealerScoreDesc);
        pnlDealerScore.add(lblDealerScoreNum);
    }

    private void pnlBottomSectionAddControls()
    {
        pnlBottomSection.add(pnlPlayerScore, BorderLayout.WEST);
        pnlBottomSection.add(pnlButtons, BorderLayout.CENTER);
        pnlBottomSection.add(pnlDealerScore, BorderLayout.EAST);
    }

    /* Post: Window title set to "BlackJack".
    **       Window size set to 600px wide, 300px tall,
    **       Window location set to null (pops up in middle of screen).
    **       Window default close operation set to exit.
    **       Window visiblity set to true.
    */
    private void setWindowSettings()
    {
        setTitle("BlackJack");			
        setSize(600, 300);						
        setLocationRelativeTo(null);					
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
        setVisible(true);
    }
}