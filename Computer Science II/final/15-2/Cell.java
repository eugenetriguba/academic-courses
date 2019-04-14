
// A Cell models a cell in a TicTacToe game. It knows it's state and position in
// the form of the row and column. A Cell can get and set it's attributes as well
// as clear it's state.
public class Cell
{
    private CellState state;
    private int row;
    private int col;

    // Post: state is set to EMPTY
    //       row and col set to -1
    public Cell()
    {
        this(CellState.EMPTY, -1, -1);
    }

    // Post: state is set to EMPTY
    //       This Cell's row and col set to row and col
    public Cell(int row, int col)
    {
        this(CellState.EMPTY, row, col);
    }

    // Post: This Cell's state, row, and col set to the state, row, and col
    //       passed by the caller
    public Cell(CellState state, int row, int col)
    {
        this.state = state;
        this.row = row;
        this.col = col;
    }

    // Return: this Cell's state
    public CellState getState()
    {
        return state;
    }

    // Post: this Cell's state set to state
    public void setState(CellState state)
    {
        this.state = state;
    }

    // Return: this Cell's row
    public int getRow()
    {
        return row;
    }

    // Post: this Cell's row set to row
    public void setRow(int row)
    {
        this.row = row;
    }

    // Return: this Cell's col
    public int getCol()
    {
        return col;
    }

    // Post: this Cell's col set to col
    public void setCol(int col)
    {
        this.col = col;
    }

    // Post: state set to EMPTY
    public void clear()
    {
        this.state = CellState.EMPTY;
    }
}