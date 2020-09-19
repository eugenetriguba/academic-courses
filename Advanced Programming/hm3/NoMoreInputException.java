import java.lang.Exception;

public class NoMoreInputException extends Exception {
    public NoMoreInputException(String message) {
        super(message);
    }
}