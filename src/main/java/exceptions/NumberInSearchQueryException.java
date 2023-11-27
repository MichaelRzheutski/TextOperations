package exceptions;

import java.util.InputMismatchException;

public class NumberInSearchQueryException extends InputMismatchException {
    public NumberInSearchQueryException(String message) {
        super(message);
    }
}
