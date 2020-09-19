import java.io.InputStream;
import java.util.Scanner;

public class Input {
    private Scanner scanIn;

    public Input(InputStream in) {
        scanIn = new Scanner(in);

        // Break on every word boundary
        scanIn.useDelimiter("(\\b|\\B)");
    }

    public char readChar() throws NoMoreInputException, EmptyLineException {
        String nextToken = readToken();
        char nextChar = nextToken.toCharArray()[0];
        scanIn.nextLine();

        if (nextToken.isEmpty() || isNewline(nextToken)) {
            throw new EmptyLineException("error: read in an empty line");
        }

        return nextChar;
    }

    public int readInt() throws NoMoreInputException {
        String token = readLine();
        return Integer.parseInt(token);
    }

    public boolean hasNext() {
        return scanIn.hasNext();
    }

    private String readToken() throws NoMoreInputException {
        if (scanIn.hasNext()) {
            return scanIn.next();
        } else {
            throw new NoMoreInputException("error: no input to read in");
        }
    }

    private String readLine() throws NoMoreInputException {
        if (scanIn.hasNext()) {
            return scanIn.nextLine();
        } else {
            throw new NoMoreInputException("error: no input to read in");
        }
    }

    private boolean isNewline(String token) {
        return !String.valueOf(token).matches(".");
    }
}
