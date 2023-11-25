package operations;

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
    TextFileReader textFileReader = new TextFileReader();

    // Setup Logger log4j2
    static {
        System.setProperty("log4j.configurationFile", "src/test/resources/log4j2.xml");
    }

    private static final Logger LOGGER = LogManager.getLogger();

    public WordFinder() {
    }

    public int findWord() {
        String text = textFileReader.readTextFromFile();
        int totalFoundWords = 0;
        String enteredWord = "";

        String lowercasedText = StringUtils.lowerCase(text);
        lowercasedText = RegExUtils.removePattern(lowercasedText, "[^а-яёa-z\\s]");
        List<String> wordList = new ArrayList<>(Arrays.asList(StringUtils.split(lowercasedText)));

        Scanner scanner = new Scanner(System.in);

        LOGGER.info(ANSI_GREEN + "Введите слово для поиска:" + ANSI_RESET);

        if (scanner.hasNextLine()) {
            enteredWord = scanner.nextLine();
        }

        for (String word : wordList) {
            if (StringUtils.equals(word, enteredWord)) {
                totalFoundWords++;
            }
        }

        LOGGER.info(
                ANSI_GREEN + "Поиск по слову: \n"
                        + ANSI_YELLOW + enteredWord + ANSI_RESET
        );

        LOGGER.info(
                ANSI_GREEN + "Всего нашлось совпадений в тексте: \n"
                        + ANSI_YELLOW + totalFoundWords + ANSI_RESET
        );

        return totalFoundWords;
    }
}
