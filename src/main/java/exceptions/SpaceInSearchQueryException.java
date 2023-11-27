package exceptions;

import java.util.InputMismatchException;

public class SpaceInSearchQueryException extends InputMismatchException {
    public SpaceInSearchQueryException(String message) {
        super(message);
    }
}
