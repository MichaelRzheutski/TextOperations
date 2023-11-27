package exceptions;

import java.util.InputMismatchException;

public class SpecialCharacterInQueryException extends InputMismatchException {
    public SpecialCharacterInQueryException(String message) {
        super(message);
    }
}
