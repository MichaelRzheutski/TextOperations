package operations.finding;

import org.apache.commons.lang3.RegExUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

import static helpers.ConsoleColors.*;

public class UniqueWordsFinder {
    private int totalUniqueWordsQuantity;

    // Setup Logger log4j2
    static {
        System.setProperty("log4j.configurationFile", "src/test/resources/log4j2.xml");
    }

    private static final Logger LOGGER = LogManager.getLogger();

    public UniqueWordsFinder() {
    }

    // Method counts unique words quantity in the text
    public int countUniqueWords(String text) {
        List<String> uniqueWordsList = new ArrayList<>();

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

    public int getUniqueWordsQuantity() {
        return totalUniqueWordsQuantity;
    }

    public void setUniqueWordsQuantity(int totalUniqueWordsQuantity) {
        this.totalUniqueWordsQuantity = totalUniqueWordsQuantity;
    }
}
