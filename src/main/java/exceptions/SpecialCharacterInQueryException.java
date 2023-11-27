package exceptions;

import java.util.InputMismatchException;

public class SpecialCharacterInQueryException extends InputMismatchException {
    private final String value;

    public String getValue() {
        return value;
    }

    public SpecialCharacterInQueryException(String message, String value) {
        super(message);
        this.value = value;
    }
}
