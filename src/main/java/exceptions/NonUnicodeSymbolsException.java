package exceptions;

import java.util.InputMismatchException;

public class NonUnicodeSymbolsException extends InputMismatchException {
    public NonUnicodeSymbolsException(String message) {
        super(message);
    }
}
