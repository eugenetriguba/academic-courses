import java.awt.event.*;
import javax.swing.*;

// A class to handle the interaction between the TicTacToeModel and
// TicTacToeView. It handles action events, handles the turns of players,
// and determines who wins.
public class TicTacToeController
{
    private TicTacToeModel model;
    private TicTacToeView view;

    // Post: model is set to a new TicTacToeModel.
    //       view is set to a new TicTacToeView.
    //       Registers listeners for the view set to ActionHandler
    public TicTacToeController()
    {
        this(new TicTacToeModel(), new TicTacToeView());
    }

    // Post: this TicTacToeController's model is set to model.
    //       this TicTacToeController's view is set to view.
    //       the view set it's action listeners to listener.
    public TicTacToeController(TicTacToeModel model, TicTacToeView view)
    {
        this.model = model;
        this.view = view;
        registerListeners(new ActionHandler());
    }

    // Post: the view set it's action listeners to listener.
    public void registerListeners(ActionListener listener)
    {
        view.addActionListeners(listener);
    }

    // Desc: Handles taking one turn in a TicTacToe game. 
    // Post: sets the icon of cell to the current player
    //       updates the game board's current row, col, and cell state.
    //       Switches the current player to the other one.
    //       Determines if there has been a winner or tie.
    private void takeTurn(JButton cell)
    {
        int row = Integer.parseInt(cell.getClientProperty("row").toString());
        int col = Integer.parseInt(cell.getClientProperty("col").toString());
        CellState player = model.getCurrentPlayer();

        if (player == CellState.CIRCLE) cell.setIcon(view.getCircleIcon());
        else cell.setIcon(view.getCrossIcon());
        model.updateGameBoard(row, col);
        model.switchPlayers();

        determineResult(player);
    }

    // Desc: Determines what the result of the game is [circle win, cross win,
    //       or tie].
    // Post: All cells of the game are disabled if there is a result.
    //       The game message of the view is set to the outcome.
    //       If there is not a circle win, cross win, or tie, nothing happens.
    private void determineResult(CellState player)
    {
        if (model.whoWon(player) == GameState.CIRCLE_WINS)
        {
            view.setGameMessage("Game Over! O wins!");
            view.disableCells();
        }
        else if (model.whoWon(player) == GameState.CROSS_WINS)
        {
            view.setGameMessage("Game Over! X wins!");
            view.disableCells();
        }
        else if (model.whoWon(player) == GameState.TIE)
        {
            view.setGameMessage("Game Over! Tie!");
            view.disableCells();
        }
    }

    // A class to handle action events on the cells
    private class ActionHandler implements ActionListener
    {
        // Post: take's a turn on the cell if it's icon is emptyIcon.
        public void actionPerformed(ActionEvent e) 
        {
            JButton cell = (JButton) e.getSource();
            Icon emptyIcon = view.getEmptyIcon();

            if (cell.getIcon().equals(emptyIcon))
            {
                takeTurn(cell);
            }
        }
    }
}