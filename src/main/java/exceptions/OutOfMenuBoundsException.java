package exceptions;

public class OutOfMenuBoundsException extends Exception {
    private final int number;

    public int getNumber() {
        return number;
    }

    public OutOfMenuBoundsException(String message, int number) {
        super(message);
        this.number = number;
    }
}
