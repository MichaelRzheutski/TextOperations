package exceptions;

public class NotNumberException extends Exception {
    private final String value;

    public NotNumberException(String message, String value) {
        super(message);
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
