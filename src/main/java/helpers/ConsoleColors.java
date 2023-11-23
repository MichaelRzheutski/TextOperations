package helpers;

public enum ConsoleColors {
    ANSI_RESET("\u001B[0m"),
    ANSI_GREEN("\u001B[32m"),
    ANSI_RED("\u001B[31m"),
    ANSI_YELLOW("\u001B[33m");

    private final String colorTitle;

    ConsoleColors(String colorTitle) {
        this.colorTitle = colorTitle;
    }

    public String getColorTitle() {
        return colorTitle;
    }

    @Override
    public String toString() {
        return colorTitle;
    }
}
