
// An object that models a TicTacToe board. This Board can get and set it's
// properties and see who has won.
public class Board
{
    private static final int ROWS = 3;
    private static final int COLS = 3;

    private int currentRow;
    private int currentCol;
    private Cell[][] cells;

    // Post: This Board's cells initialized to a new cell array set to ROWS and COLS.
    //       All cell's CellState in this Board's cells set to EMPTY.
    //       currentRow and currentCol set to 0.
    public Board()
    {
        currentRow = 0;
        currentCol = 0;
        cells = new Cell[ROWS][COLS];
        
        for (int row = 0; row < ROWS; row++)
            for (int col = 0; col < COLS; col++)
                cells[row][col] = new Cell(CellState.EMPTY, row, col);
    }

    // Return: this Board's currentRow
    public int getCurrentRow()
    {
        return currentRow;
    }

    // Post: this Board's currentRow set to currentRow
    public void setCurrentRow(int currentRow)
    {
        this.currentRow = currentRow;
    }

    // Return: this Board's currentCol
    public int getCurrentCol()
    {
        return currentCol;
    }

    // Post: this Board's currentCol set to currentCol
    public void setCurrentCol(int currentCol)
    {
        this.currentCol = currentCol;
    }

    // Return: The CellState of the inputted row and col of this Board's cells.
    public CellState getCellState(int row, int col)
    {
        return cells[row][col].getState();
    }

    // Post: The CellState of the inputted row and col of this Board's cells set
    //       to state.
    public void setCellState(int row, int col, CellState state)
    {
        cells[row][col].setState(state);
    }

    // Return: false if there is a cell with a CellState of EMPTY in this
    //         Board's cells. true otherwise.
    public boolean isTie()
    {
        boolean tie = true;

        loop: for (int row = 0; row < ROWS; row++)
            for (int col = 0; col < COLS; col++)
                if (cells[row][col].getState() == CellState.EMPTY)
                {
                    tie = false;
                    break loop;
                }

        return tie;
    }

    // Desc: Checks this board's cells to see if there has been a row, column,
    //       diagonal, and opposite diagonal win. That is based off the inputted
    //       state, the current row, and the current column properties of this object.
    // Return: true if state matches a row, column, diagonal, or opposite diagonal win.
    //         false otherwise.
    public boolean hasWon(CellState state)
    {
        boolean rowWin = 
               cells[currentRow][0].getState() == state 
            && cells[currentRow][1].getState() == state 
            && cells[currentRow][2].getState() == state;

        boolean colWin = 
               cells[0][currentCol].getState() == state
            && cells[1][currentCol].getState() == state
            && cells[2][currentCol].getState() == state;
        
        boolean diagonalWin =
               cells[0][0].getState() == state
            && cells[1][1].getState() == state
            && cells[2][2].getState() == state
            && currentRow == currentCol;
        
        boolean oppositeDiagonalWin =
               cells[0][0].getState() == state
            && cells[1][1].getState() == state
            && cells[2][2].getState() == state
            && currentRow + currentCol == 2;

        return (rowWin || colWin || diagonalWin || oppositeDiagonalWin);
    }
}