package operations.writing;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileWriter;
import java.io.IOException;

import static helpers.ConsoleColors.*;

public class ResultWriter {
    private String filePath = "src/main/resources/output/";
    private String fileName = "output.txt";

    // Setup Logger log4j2
    static {
        System.setProperty("log4j.configurationFile", "src/test/resources/log4j2.xml");
    }

    private static final Logger LOGGER = LogManager.getLogger();

    public ResultWriter() {

    }

    public void writeResultToFile(
            String textToOperate, int uniqueWordsNumber,
            String splittedWords, int numberfoundWords, String wordToSearch
    ) {
        try (FileWriter writer = new FileWriter(filePath + fileName, false)) {

            StringBuilder newString = new StringBuilder();
            newString.append("____Уникальные слова____");
            newString.append("\n");
            newString.append("Базовый текст: ").append(textToOperate).append("\n");
            if (uniqueWordsNumber > 0) {
                newString.append("Количество уникальных слов: ").append(uniqueWordsNumber).append("\n");
            } else {
                newString.append("Количество уникальных слов: слова не подсчитаны, " +
                        "сперва посчитайте слова").append("\n");
            }

            newString.append("\n____Символы преобразованные к верхнему регистру и их общее количество____");
            newString.append("\n");
            newString.append("Базовый текст: ").append(textToOperate).append("\n");
            if (splittedWords != null) {
                newString.append("Преобразование к верхнему регистру: ").append(splittedWords).append("\n");
            } else {
                newString.append("Преобразование к верхнему регистру: слова не преобразованы, " +
                        "сначала преобразуйте слова").append("\n");
            }

            newString.append("\n____Поиск по слову____");
            newString.append("\n");
            newString.append("Базовый текст: ").append(textToOperate).append("\n");
            if (numberfoundWords > 0) {
                newString.append("Слово по которому проводился поиск: ").append(wordToSearch).append("\n");
                newString.append("Количество найденных слов: ").append(numberfoundWords).append("\n");
            } else {
                newString.append("Количество найденных слов: слова не найдены, " +
                        "сначала найдите слова").append("\n");
            }

            writer.write(String.valueOf(newString));


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        LOGGER.info(
                ANSI_GREEN + "Результат работы программы записан в файл: \n"
                        + ANSI_YELLOW + fileName + "\n" + ANSI_RESET
        );

    }
}