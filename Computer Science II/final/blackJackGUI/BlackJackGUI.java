import java.awt.*;
import javax.swing.*;

public class BlackJack
{
    // Output: Displays a GUI for a Black Jack game.
    public static void main(String[] args) 
    {
        MyJFrame frame = new MyJFrame();
    }
}

// MyJFrame creates a GUI for a Black Jack game.
class MyJFrame extends JFrame
{
    private JLabel[] lblPCards;
    private JLabel[] lblDCards;
    private JPanel pnlLabels;

    private JButton btnDeal;
    private JButton btnPlayer;
    private JButton btnDealer;
    private JButton btnNew;
    private JPanel pnlButtons;

    /* Post: 
    **
    **
    **
    */
    public MyJFrame()
    {
        super();
        initDataStructures();

        pnlLabelsAddControls();
        pnlButtonsAddControls();
        add(pnlLabels, BorderLayout.CENTER);
        add(pnlButtons, BorderLayout.SOUTH);

        setWindowSettings();	
    }

    /* Post: 
    **
    **
    **
    */
    private void initDataStructures()
    {
        lblPCards = new JLabel[7];
        lblDCards = new JLabel[7];
        pnlLabels = new JPanel(new GridLayout(2, 7));

        btnDeal = new JButton("Deal");
        btnPlayer = new JButton("Player");
        btnDealer = new JButton("Dealer");
        btnNew = new JButton("New");
        pnlButtons = new JPanel(new FlowLayout());
    }

    /* Post: lblPCards is filled with JLabels captioned "Player".
    **       lblDCards is filled with JLabels captioned "Dealer".
    **       Contents of lblPCards & lblDCards added to pnlLabels
    */
    private void pnlLabelsAddControls()
    {
        for (int i = 0; i < 7; ++i)
        {
            lblPCards[i] = new JLabel("Player");
            lblDCards[i] = new JLabel("Dealer");
        }

        for (int i = 0; i < 7; ++i)
            pnlLabels.add(lblPCards[i]);

        for (int i = 0; i < 7; ++i)
            pnlLabels.add(lblDCards[i]);
    }

    // Post: adds btnDeal, btnPlayer, btnDealer, and btnNew to pnlButtons.
    private void pnlButtonsAddControls()
    {
        pnlButtons.add(btnDeal);
        pnlButtons.add(btnPlayer);
        pnlButtons.add(btnDealer);
        pnlButtons.add(btnNew);
    }

    /* Post: Window title set to "BlackJack".
    **       Window size set to 500px wide, 250px tall,
    **       Window location set to null (pops up in middle of screen).
    **       Window default close operation set to exit.
    **       Window visiblity set to true.
    */
    private void setWindowSettings()
    {
        setTitle("BlackJack");			
        setSize(500, 250);						
        setLocationRelativeTo(null);					
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
        setVisible(true);
    }
}