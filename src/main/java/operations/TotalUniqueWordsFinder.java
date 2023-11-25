package operations;

import org.apache.commons.lang3.RegExUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static helpers.ConsoleColors.*;

public class TotalUniqueWordsFinder {
    TextFileReader textFileReader = new TextFileReader();

    // Setup Logger log4j2
    static {
        System.setProperty("log4j.configurationFile", "src/test/resources/log4j2.xml");
    }

    private static final Logger LOGGER = LogManager.getLogger();

    public TotalUniqueWordsFinder() {
    }

    // Method counts unique words quantity in the text
    public int countUniqueWords() throws IOException {
        String text = textFileReader.readTextFromFile();
        List<String> uniqueWordsList = new ArrayList<>();

        int totalUniqueWordsQuantity;

        String lowercasedText = StringUtils.lowerCase(text);
        String clearedText = RegExUtils.removePattern(lowercasedText, "[^а-яёa-z\\s]");
        for (String word : StringUtils.split(clearedText)) {
            if (!uniqueWordsList.contains(word)) {
                uniqueWordsList.add(word);
            }
        }

        totalUniqueWordsQuantity = uniqueWordsList.size();

        LOGGER.info(
                ANSI_GREEN + "Количество уникальных слов в тексте: "
                        + ANSI_YELLOW + totalUniqueWordsQuantity + "\n" + ANSI_RESET
        );

        return totalUniqueWordsQuantity;
    }
}
