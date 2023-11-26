package operations.finding;

import org.apache.commons.lang3.RegExUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static helpers.ConsoleColors.*;

public class WordFinder {
    private String wordToSearch;
    private int totalFoundWords;

    // Setup Logger log4j2
    static {
        System.setProperty("log4j.configurationFile", "src/test/resources/log4j2.xml");
    }

    private static final Logger LOGGER = LogManager.getLogger();

    public WordFinder() {
    }

    // Method searches word in the text
    public int findWord(String text) {
        String lowercasedText = StringUtils.lowerCase(text);
        lowercasedText = RegExUtils.removePattern(lowercasedText, "[^а-яёa-z\\s]");
        List<String> wordList = new ArrayList<>(Arrays.asList(StringUtils.split(lowercasedText)));

        Scanner scanner = new Scanner(System.in);
        LOGGER.info(ANSI_GREEN + "Введите слово для поиска:" + ANSI_RESET);

        if (scanner.hasNextLine()) {
            wordToSearch = scanner.nextLine();
        }

        for (String word : wordList) {
            if (StringUtils.equals(word, wordToSearch)) {
                totalFoundWords++;
            }
        }

        LOGGER.info(
                ANSI_GREEN + "Поиск осуществлён по слову: "
                        + ANSI_YELLOW + wordToSearch + ANSI_RESET
        );

        LOGGER.info(
                ANSI_GREEN + "Всего нашлось совпадений в тексте: "
                        + ANSI_YELLOW + totalFoundWords + "\n" + ANSI_RESET
        );

        return totalFoundWords;
    }

    public String getWordToSearch() {
        return wordToSearch;
    }

    public void setWordToSearch(String wordToSearch) {
        this.wordToSearch = wordToSearch;
    }

    public int getTotalFoundWords() {
        return totalFoundWords;
    }

    public void setTotalFoundWords(int totalFoundWords) {
        this.totalFoundWords = totalFoundWords;
    }
}
