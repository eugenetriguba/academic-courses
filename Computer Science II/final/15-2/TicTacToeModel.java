
// A class to handle the logic behind a TicTacToe game.
public class TicTacToeModel
{
    private Board gameBoard;
    private GameState currentState;
    private CellState currentPlayer;

    // Post: gameBoard is set to a new Board.
    //       currentState is set to PLAYING.
    //       currentPlayer is set to CROSS.
    public TicTacToeModel()
    {
        gameBoard = new Board();
        currentState = GameState.PLAYING;
        currentPlayer = CellState.CROSS;
    }

    // Desc: Updates the currentState by finding out what it is given the passed
    //       CellState
    // Return: this TicTacToeModel's currentState
    public GameState whoWon(CellState player)
    {
        currentState = findCurrentState(player);
        return currentState;
    }

    // Return: this TicTacToeModel's currentPlayer.
    public CellState getCurrentPlayer()
    {
        return currentPlayer;
    }

    // Post: gameBoard's current row and col set to row and col.
    //       The cell at gameBoard's row and col is set to currentPlayer.
    public void updateGameBoard(int row, int col)
    {
        gameBoard.setCurrentRow(row);
        gameBoard.setCurrentCol(col);
        gameBoard.setCellState(row, col, currentPlayer);
    }

    // Post: This TicTacToeModel's currentPlayer set to the CellState CIRCLE if
    //       it is currently CROSS. Otherwise, currentPlayer set to the CellState
    //       CROSS.
    public void switchPlayers()
    {
        if (currentPlayer == CellState.CROSS) currentPlayer = CellState.CIRCLE;
        else currentPlayer = CellState.CROSS;
    }

    // Return: CROSS_WINS or CIRCLE_WINS if player has won.
    //         TIE if there has been a tie.
    //         Otherwise, PLAYING is returned.
    private GameState findCurrentState(CellState player)
    {
        if (gameBoard.hasWon(player))
        {
            if (player == CellState.CROSS) return GameState.CROSS_WINS;
            else return GameState.CIRCLE_WINS;
        } 
        else if (gameBoard.isTie())
        {
            return GameState.TIE;
        }
        else return GameState.PLAYING;
    }
}