// Eugene Triguba
// ytriguba17@ole.augie.edu
// TicTacToe.java
// 15-2 ColorAndImage

// A class with a single method, main, used to run the TicTacToe program by
// creating a model, view, and controller.
public class TicTacToe
{
    public static void main(String[] args) 
    {
        TicTacToeModel model = new TicTacToeModel();
        TicTacToeView view = new TicTacToeView();
        TicTacToeController controller = new TicTacToeController(model, view);
    }
}