package exceptions;

public class NegativeValueException extends Exception {
    private final int number;

    public int getNumber() {
        return number;
    }

    public NegativeValueException(String message, int number) {
        super(message);
        this.number = number;
    }
}
