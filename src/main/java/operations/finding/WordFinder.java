package operations.finding;

import exceptions.*;
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
        try {
            LOGGER.info(ANSI_GREEN + "Введите слово для поиска:" + ANSI_RESET);

            if (scanner.hasNextLine()) {
                wordToSearch = scanner.nextLine().toLowerCase();
            }

            String[] numbers = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
            String[] specSymbols = {",", ".", "&", "?", "!", "@", "#", "$", "%",
                    "^", "*", "(", ")", "+", "-", "/", "|", "<", ">", "\n", "\t"};

            for (String word : wordList) {
                if (!StringUtils.isAlpha(word)) {
                    throw new NonUnicodeSymbolsException(
                            "в поисковом запросе "
                                    + ANSI_YELLOW + wordToSearch + ANSI_RED
                                    + " содержатся символы кроме Юникода, уберите лишние символы!"
                    );
                }
                if (StringUtils.length(wordToSearch) <= 2) {
                    throw new LackQueryWordsException(
                            "в поисковом запросе "
                                    + ANSI_YELLOW + wordToSearch + ANSI_RED
                                    + " 2 или менее символов, введите слово целиком!"
                    );
                }
                if (StringUtils.isNumeric(wordToSearch)) {
                    throw new NumberInsteadWordException(
                            "вы ввели число "
                                    + ANSI_YELLOW + wordToSearch + ANSI_RED
                                    + " вместо слова, введите слово!"
                    );
                }
                if (StringUtils.containsAny(wordToSearch, numbers)) {
                    throw new NumberInSearchQueryException(
                            "в поисковом запросе " + ANSI_YELLOW + wordToSearch + ANSI_RED
                                    + " присутствует цифра, " + "введите слово без цифры!"
                    );
                }
                if (StringUtils.containsAny(wordToSearch, " ")) {
                    throw new SpaceInSearchQueryException(
                            "в поисковом запросе " + ANSI_YELLOW + wordToSearch + ANSI_RED
                                    + " присутствует пробел, " + "введите одно слово!"
                    );
                }
                if (StringUtils.containsAny(wordToSearch, specSymbols)) {
                    throw new SpecialCharacterInQueryException(
                            "в поисковом запросе " + ANSI_YELLOW + wordToSearch + ANSI_RED
                                    + " присутствует спецсимвол(ы), "
                                    + "введите слово без спецсимволов!"
                    );
                }
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
        } catch (NonUnicodeSymbolsException
                 | LackQueryWordsException
                 | NumberInsteadWordException
                 | NumberInSearchQueryException
                 | SpaceInSearchQueryException
                 | SpecialCharacterInQueryException e) {

            LOGGER.debug(ANSI_RED + "Ошибка в классе: " + ANSI_GREEN
                    + getClass().getName() + " " + ANSI_RED + e.getMessage()
                    + ANSI_RED + "\n" + ANSI_RESET);
        }

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
