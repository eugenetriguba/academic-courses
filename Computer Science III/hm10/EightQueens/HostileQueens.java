// Eugene Triguba
// ytriguba17@ole.augie.edu
// HostileQueens.java
// Homework 10: Backtracking & Generics

import java.util.Scanner;

public class HostileQueens
{
	public static void main (String[] args)
	{   
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Enter the size of the chess board: ");
        int size = keyboard.nextInt();
        System.out.print("Enter row for queen in column 0: ");
        int row = keyboard.nextInt();

        int[] queenPos = new int[size];
        ChessBoard board = new ChessBoard(size);
        
		if (EightQueens.isSolution(queenPos, row))
		{
			board.setQueens(queenPos);
			board.drawBoard();
		}
        else System.out.println("No solution");
    }
}

class ChessBoard
{
    private boolean[][] board;	
    
    // Post: board is initialized to a 2D 8 by 8 boolean array.
    //       Therefore all values in board are automatically initialized to
    //       false
	public ChessBoard()
	{
        board = new boolean[8][8];
    }

    // Post: board is initialized to a 2D size by size boolean array.
    //       So all values in board are automatically initialized to false
	public ChessBoard(int size)
	{
		board = new boolean[size][size];
    }
    
    // Post: all values in this ChessBoard's board reset to false
	private void clearBoard()
	{
		for (int row = 0; row < board.length; row++)
			for (int col = 0; col < board.length; col++)
				board[row][col] = false;
    }
    
    // Post: 8 queens set on board at cells (queenPos[0], 0), 
    //      (queenPos[1], 1), ... , (queenPos[7], 7)
	public void setQueens(int[] queenPos)
	{
		clearBoard();
        for (int col = 0; col < board.length; col++)
        {
            int row = queenPos[col];
            board[row][col] = true;
        }
    }
    
    // Output: Prints out the header 
    //         e.g. 0 1 2 3 and so on until board.length - 1
    //         Prints out each row of the board until board.length - 1
    //         e.g. rowNum - - - Q - - -
    // Note: each number or character has a width of 3
	public void drawBoard()
	{
        System.out.print("   ");
        for (int headerNum = 0; headerNum < board.length; headerNum++)
            System.out.printf("%3d", headerNum);
        System.out.println();
        
		for (int row = 0; row < board.length; row++)
		{
            System.out.printf("%3d", row); // row number
            
			for (int col = 0; col < board.length; col++)
				if (board[row][col]) System.out.printf("%3c", 'Q');
                else System.out.printf("%3c", '-');
                
			System.out.println();
		}
	}
}

// A class to solve the Eight Queens problem. It can see if the current position
// is a safe location, decide where to place each queen, and if there is a
// solution.
class EightQueens
{
	//  Return: true if (row,col) is safe, that is, queens set in
    //          queenPos[0]..queenPos[col-1] cannot attack queen at 
    //          (row,col); return false otherwise.
    //  Note: (j, i) and (row,col) in the same \ diagonal if i-j=col-row
    //	(j, i) and (row,col) in the same / diagonal if i+j=col+row
	public static boolean safeLocation(int row, int col, int[] queenPos)
	{
		for (int i = 0; i < col; i++)	
		{
			int j = queenPos[i];				// there is a queen at (j,i)
			if (j == row) return false;				// same row
			else if(i - j == col - row || i + j == col + row) return false; // same diagonal
		}
		return true;
    }
    
    // Pre:	row is the position of the first queen
    // Post: If there is a solution
    //       - queenPos[0] set to row
    //       - queenPos[1]..queenPos[queenPos.length - 1] set to safe positions
	// Return: true if there is a solution; false otherwise.
	public static boolean isSolution(int[] queenPos, int row)
	{
		queenPos[0] = row;
		if (placeQueens(queenPos, 1)) return true;	 
		else return false;				
    }
    
    // Pre:	queenPos[0]..queenPos[col - 1] have queens set safely
    //	    col is the next column to be set
    //	    col > 0
    // Post:    If there is a solution, queenPos[col]..queenPos[queenPos.length - 1] 
    //          set to safe positions]
	// Return:	True if there is a solution; false otherwise.
	private static boolean placeQueens(int[] queenPos, int col)
	{
		int row;
		boolean found;
		if (col == queenPos.length) found = true;
		else
		{
			found = false; 
			row = 0;
			while (row < queenPos.length && !found)
			{
				if (safeLocation(row, col, queenPos))
				{
					queenPos[col] = row;
					found = placeQueens(queenPos, col + 1); 
					if (!found) row++; 	
				}				
				else row++;			
			}	
		}
		return found;
	}
}
