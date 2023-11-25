package operations;

import org.apache.commons.lang3.RegExUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static helpers.ConsoleColors.*;

public class WordSplitter {
    TextFileReader textFileReader = new TextFileReader();

    // Setup Logger log4j2
    static {
        System.setProperty("log4j.configurationFile", "src/test/resources/log4j2.xml");
    }

    private static final Logger LOGGER = LogManager.getLogger();

    public WordSplitter() {
    }

    public String getAllLetters() throws IOException {
        String text = textFileReader.readTextFromFile();
        List<Character> characterList = new ArrayList<>();

        String uppercasedText = StringUtils.upperCase(text);
        uppercasedText = RegExUtils.removePattern(uppercasedText, "[^А-ЯЁA-Z]");
        for (char letter : uppercasedText.toCharArray()) {
            characterList.add(letter);
        }
        uppercasedText = StringUtils.joinWith(" ", characterList.toArray());

        LOGGER.info(
                ANSI_GREEN + "Текст преобразован в верхний регистр: \n"
                        + ANSI_YELLOW + uppercasedText + ANSI_RESET
        );

        LOGGER.info(
                ANSI_GREEN + "Общее количество слов в тексте: "
                        + ANSI_YELLOW + totalWords(text) + "\n" + ANSI_RESET
        );

        return uppercasedText;
    }

    public int totalWords(String text) throws IOException  {
        String clearedText = RegExUtils.removePattern(text, "[^А-Яа-яЁёA-Za-z]");
        int totalLetters = clearedText.length();

        return totalLetters;
    }
}
