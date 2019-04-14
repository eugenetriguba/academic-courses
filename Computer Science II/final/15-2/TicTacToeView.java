import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

// A class to create and handle the changes to the GUI.
public class TicTacToeView extends JFrame
{
    private JLabel gameMessage = new JLabel("Welcome to TicTacToe!", JLabel.CENTER);
    
    private JButton[][] btnCellStates = new JButton[3][3];
    private JPanel pnlGameBoard = new JPanel(new GridLayout(3, 3, 10, 10));

    private Icon crossIcon = new ImageIcon("images/cross.jpg");
    private Icon circleIcon = new ImageIcon("images/circle.jpg");
    private Icon emptyIcon = new ImageIcon("images/empty.jpg");

    // Post: btnCellStates is filled with new JButtons.
    //       Each cell in btnCellStates has it's client property row and col set.
    //       Each cell in btnCellStates has it's icon set to emptyIcon.
    //       gameMessage's size is set to 400px wide, 50px tall.
    //       pnlGameBoard's background color is set to black.
    //       pnlGameBoard has each cell in btnCellStates added to it.
    //       gameMessage is placed in the north section of the border layout.
    //       pnlGameBoard is placed in the center section of the border layout.
    //       Window settings are set.
    public TicTacToeView()
    {
        initBtnCellStates();
        gameMessage.setPreferredSize(new Dimension(400, 50));
        pnlGameBoard.setBackground(Color.BLACK);

        addPnlGameBoardControls();
        add(gameMessage, BorderLayout.NORTH);
        add(pnlGameBoard, BorderLayout.CENTER);
        setWindowSettings();
    }

    // Return: this TicTacToeView's empty icon.
    public Icon getEmptyIcon()
    {
        return emptyIcon;
    }

    // Return: this TicTacToeView's cross icon.
    public Icon getCrossIcon()
    {
        return crossIcon;
    }

    // Return: this TicTacToeView's circle icon.
    public Icon getCircleIcon()
    {
        return circleIcon;
    }

    // Post: this TicTacToeView's gameMessage has it's text set to message.
    public void setGameMessage(String message)
    {
        gameMessage.setText(message);
    }

    // Post: Each cell in btnCellStates has listener added to it as an
    //       ActionListener.
    public void addActionListeners(ActionListener listener)
    {
        for (int row = 0; row < 3; row++)
            for (int col = 0; col < 3; col++)
            btnCellStates[row][col].addActionListener(listener);
    }

    // Post: Each cell in btnCellStates is disabled.
    public void disableCells()
    {
        for (JButton[] cellArr : btnCellStates)
            for (JButton cell : cellArr)
                cell.setEnabled(false);
    }

    // Post: btnCellStates is filled with new JButtons.
    //       Each cell in btnCellStates has it's client property row and col set.
    //       Each cell in btnCellStates has it's icon set to emptyIcon.
    private void initBtnCellStates()
    {
        for (int row = 0; row < 3; row++)
            for (int col = 0; col < 3; col++)
            {
                btnCellStates[row][col] = new JButton();
                btnCellStates[row][col].putClientProperty("row", row);
                btnCellStates[row][col].putClientProperty("col", col);
                btnCellStates[row][col].setIcon(emptyIcon);
            }
    }

    // Post: pnlGameBoard has each cell in btnCellStates added to it.
    private void addPnlGameBoardControls()
    {
        for (JButton[] cellArr : btnCellStates)
            for (JButton cell : cellArr)
                pnlGameBoard.add(cell);
    }

    // Post: Title of the window set to "TicTacToe"
    //       Window size set to 400px wide, 500px tall.
    //       Window is set to pop up in the middle of the screen.
    //       Default close operation set to exit.
    //       Makes the window visible and non-resizable.
    private void setWindowSettings()
    {
        setTitle("TicTacToe");
        setSize(400, 500);
        setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
        setVisible(true);
        setResizable(false);
    }
}