package operations;

public class TextClass {
    private String text;
    private String enteredText;
    private int totalUniqueWords;
    private String uppercasedText;
    private int totalFoundWords;
    private String wordToSearch;

    public TextClass() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getTotalUniqueWords() {
        return totalUniqueWords;
    }

    public void setTotalUniqueWords(int totalUniqueWords) {
        this.totalUniqueWords = totalUniqueWords;
    }

    public String getUppercasedText() {
        return uppercasedText;
    }

    public void setUppercasedText(String uppercasedText) {
        this.uppercasedText = uppercasedText;
    }

    public int getTotalFoundWords() {
        return totalFoundWords;
    }

    public void setTotalFoundWords(int totalFoundWords) {
        this.totalFoundWords = totalFoundWords;
    }

    public String getWordToSearch() {
        return wordToSearch;
    }

    public void setWordToSearch(String wordToSearch) {
        this.wordToSearch = wordToSearch;
    }

    public String getEnteredText() {
        return enteredText;
    }

    public void setEnteredText(String enteredText) {
        this.enteredText = enteredText;
    }
}
