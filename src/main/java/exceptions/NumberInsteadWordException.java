package exceptions;

import java.util.InputMismatchException;

public class NumberInsteadWordException extends InputMismatchException {
    public NumberInsteadWordException(String message) {
        super(message);
    }
}
