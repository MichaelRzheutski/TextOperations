package exceptions;

import java.util.InputMismatchException;

public class LackQueryWordsException extends InputMismatchException {
    public LackQueryWordsException(String message) {
        super(message);
    }
}
