package operations;

import exceptions.*;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RegExUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static helpers.ConsoleColors.*;

public class TextOperations {
    private final String PATH_TO_CONSOLE_FILE = "src/main/resources/texts/";
    private final String CONSOLE_FILE_NAME = "writtenFromConsole.txt";

    private final String PATH_TO_TEXT = "src/main/resources/texts/";
    private final String TEXT_FILE_NAME = "loremIpsum.txt";

    private final String OUTPUT_FILE_PATH = "src/main/resources/output/";
    private final String OUTPUT_FILE_NAME = "output.txt";

    static int iterationsCounter = 0;

    // Setup Logger log4j2
    static {
        System.setProperty("log4j.configurationFile", "src/test/resources/log4j2.xml");
    }

    private static final Logger LOGGER = LogManager.getLogger();

    public TextOperations() {
    }

    // Method reads text from console and writes to file
    public void readFromConsole(Scanner scanner, TextClass textClass) {
        while (true) {
            LOGGER.info(String.format("%sВведите текст: %s", ANSI_GREEN, ANSI_RESET));

            if (scanner.hasNextLine()) {
                scanner.nextLine();
                textClass.setEnteredText(scanner.nextLine());

                if (StringUtils.isNotBlank(textClass.getEnteredText())) {
                    textClass.setText(textClass.getEnteredText());

                    LOGGER.info(
                            ANSI_GREEN + "Текст обработан успешно: "
                                    + "\n" + ANSI_YELLOW + textClass.getEnteredText() + ANSI_RESET);
                    break;
                } else {
                    LOGGER.debug(
                            ANSI_RED + "Введена пустая строка, " +
                                    "введите какой-то текст!\n" + ANSI_RESET
                    );
                }

            } else {
                LOGGER.debug(
                        ANSI_RED + "%sНеверная операция, " +
                                "попробуйте ещё раз!\n" + ANSI_RESET
                );
            }
        }
    }

    // Method reads text from console and writes to file
    public void readFromConsoleAndSaveToFile(Scanner scanner, TextClass textClass) {
        try (FileWriter writer =
                     new FileWriter(PATH_TO_CONSOLE_FILE + CONSOLE_FILE_NAME, true)) {
            while (true) {
                LOGGER.info(String.format("%sВведите текст: %s", ANSI_GREEN, ANSI_RESET));

                if (scanner.hasNextLine()) {
                    scanner.nextLine();
                    textClass.setEnteredText(scanner.nextLine());

                    if (StringUtils.isNotBlank(textClass.getEnteredText())) {
                        writer.write(textClass.getEnteredText());
                        textClass.setText(textClass.getEnteredText());

                        LOGGER.info(
                                ANSI_GREEN + "В файл " + ANSI_YELLOW + CONSOLE_FILE_NAME + ANSI_GREEN
                                        + " был записан текст: "
                                        + "\n" + ANSI_YELLOW + textClass.getEnteredText() + ANSI_RESET);
                        break;
                    } else {
                        LOGGER.debug(
                                ANSI_RED + "Введена пустая строка, " +
                                        "введите какой-то текст!\n" + ANSI_RESET
                        );
                    }

                } else {
                    LOGGER.debug(
                            ANSI_RED + "%sНеверная операция, " +
                                    "попробуйте ещё раз!\n" + ANSI_RESET
                    );
                }
            }
        } catch (IOException e) {
            LOGGER.debug(ANSI_RED + "Ошибка в классе: " + ANSI_GREEN
                    + getClass().getName() + " "
                    + ANSI_RED + e.getMessage() + "\n" + ANSI_RESET);
        }
    }

    // Method reads a text from the file
    public void readFromTextFile(TextClass textClass) {
        try (BufferedReader br =
                     new BufferedReader(new FileReader(PATH_TO_TEXT + TEXT_FILE_NAME))) {
            textClass.setText(br.readLine());

            LOGGER.info(
                    ANSI_GREEN + "Файл " + ANSI_YELLOW + TEXT_FILE_NAME + ANSI_GREEN
                            + " имеет следующее содержание: \n" + ANSI_YELLOW + textClass.getText()
                            + "\n" + ANSI_RESET
            );

        } catch (IOException e) {
            LOGGER.debug(ANSI_RED + "Ошибка в классе: " + ANSI_GREEN
                    + getClass().getName() + " "
                    + ANSI_RED + e.getMessage() + "\n" + ANSI_RESET);
        }
    }

    // Method counts unique words quantity in the text
    public int countUniqueWords(TextClass textClass) {
        List<String> uniqueWordsList = new ArrayList<>();
        String lowercasedText = StringUtils.lowerCase(textClass.getText());
        String clearedText = RegExUtils.removePattern(lowercasedText, "[^а-яёa-z\\s]");

        for (String word : StringUtils.split(clearedText)) {
            if (!uniqueWordsList.contains(word)) {
                uniqueWordsList.add(word);
            }
        }

        textClass.setTotalUniqueWords(uniqueWordsList.size());

        LOGGER.info(
                ANSI_GREEN + "Количество уникальных слов в тексте: "
                        + ANSI_YELLOW + textClass.getTotalUniqueWords() + "\n" + ANSI_RESET
        );

        return textClass.getTotalUniqueWords();
    }

    // Method uppercases all symbols in the text
    public String uppercaseAllLetters(TextClass textClass) throws IOException {
        List<Character> characterList = new ArrayList<>();
        textClass.setUppercasedText(StringUtils.upperCase(textClass.getText()));
        textClass.setUppercasedText(
                RegExUtils.removePattern(textClass.getUppercasedText(), "[^А-ЯЁA-Z]")
        );

        for (char letter : textClass.getUppercasedText().toCharArray()) {
            characterList.add(letter);
        }

        textClass.setUppercasedText(StringUtils.joinWith(" ", characterList.toArray()));

        LOGGER.info(
                ANSI_GREEN + "Текст преобразован в верхний регистр: \n"
                        + ANSI_YELLOW + textClass.getUppercasedText() + ANSI_RESET
        );

        LOGGER.info(
                ANSI_GREEN + "Общее количество букв в тексте: "
                        + ANSI_YELLOW + getTotalLettersNumber(textClass) + "\n" + ANSI_RESET
        );

        return textClass.getUppercasedText();
    }

    // Method returns total number of letters in the text
    public int getTotalLettersNumber(TextClass textClass) {
        String clearedText = RegExUtils.removePattern(textClass.getText(), "[^А-Яа-яЁёA-Za-z]");

        return clearedText.length();
    }

    // Method searches word in the text
    public int findWord(Scanner scanner, TextClass textClass) {
        String lowercasedText = StringUtils.lowerCase(textClass.getText());
        lowercasedText = RegExUtils.removePattern(lowercasedText, "[^а-яёa-z\\s]");
        List<String> wordList = new ArrayList<>(Arrays.asList(StringUtils.split(lowercasedText)));

        try {
            LOGGER.info(ANSI_GREEN + "Введите слово для поиска:" + ANSI_RESET);

            if (scanner.hasNextLine()) {
                scanner.nextLine();
                textClass.setWordToSearch(scanner.nextLine().toLowerCase());
            }

            String[] numbers = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
            String[] specSymbols = {",", ".", "&", "?", "!", "@", "#", "$", "%",
                    "^", "*", "(", ")", "+", "-", "/", "|", "<", ">", "\n", "\t"};

            textClass.setTotalFoundWords(0);

            for (String word : wordList) {
                if (!StringUtils.isAlpha(word)) {
                    throw new NonUnicodeSymbolsException(
                            "в поисковом запросе "
                                    + ANSI_YELLOW + textClass.getWordToSearch() + ANSI_RED
                                    + " содержатся символы кроме Юникода, уберите лишние символы!"
                    );
                }
                if (StringUtils.length(textClass.getWordToSearch()) <= 2) {
                    throw new LackQueryWordsException(
                            "в поисковом запросе "
                                    + ANSI_YELLOW + textClass.getWordToSearch() + ANSI_RED
                                    + " 2 или менее символов, введите слово целиком!"
                    );
                }
                if (StringUtils.isNumeric(textClass.getWordToSearch())) {
                    throw new NumberInsteadWordException(
                            "вы ввели число "
                                    + ANSI_YELLOW + textClass.getWordToSearch() + ANSI_RED
                                    + " вместо слова, введите слово!"
                    );
                }
                if (StringUtils.containsAny(textClass.getWordToSearch(), numbers)) {
                    throw new NumberInSearchQueryException(
                            "в поисковом запросе " + ANSI_YELLOW + textClass.getWordToSearch() + ANSI_RED
                                    + " присутствует цифра, " + "введите слово без цифры!"
                    );
                }
                if (StringUtils.containsAny(textClass.getWordToSearch(), " ")) {
                    throw new SpaceInSearchQueryException(
                            "в поисковом запросе " + ANSI_YELLOW + textClass.getWordToSearch() + ANSI_RED
                                    + " присутствует пробел, " + "введите одно слово!"
                    );
                }
                if (StringUtils.containsAny(textClass.getWordToSearch(), specSymbols)) {
                    throw new SpecialCharacterInQueryException(
                            "в поисковом запросе " + ANSI_YELLOW + textClass.getWordToSearch() + ANSI_RED
                                    + " присутствует спецсимвол(ы), "
                                    + "введите слово без спецсимволов!"
                    );
                }
                if (StringUtils.equals(word, textClass.getWordToSearch())) {
                    textClass.setTotalFoundWords(textClass.getTotalFoundWords() + 1);
                }
            }

            LOGGER.info(
                    ANSI_GREEN + "Поиск осуществлён по слову: "
                            + ANSI_YELLOW + textClass.getWordToSearch() + ANSI_RESET
            );

            LOGGER.info(
                    ANSI_GREEN + "Всего нашлось совпадений в тексте: "
                            + ANSI_YELLOW + textClass.getTotalFoundWords() + "\n" + ANSI_RESET
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

        return textClass.getTotalFoundWords();
    }

    // Method writes result of all text operations to file
    public void writeOperationsResultToFile(TextClass textClass) {
        try (FileWriter writer =
                     new FileWriter(OUTPUT_FILE_PATH + OUTPUT_FILE_NAME, true)) {

            StringBuilder newString = new StringBuilder();

            iterationsCounter++;

            newString.append("\n[*** ПРОХОД ").append(iterationsCounter)
                    .append(". НАЧАЛО ЗАПИСИ ***]").append("\n");
            newString.append("____Уникальные слова____");
            newString.append("\n");
            newString.append("Базовый текст: ").append(textClass.getText()).append("\n");
            if (textClass.getTotalUniqueWords() > 0) {
                newString.append("Количество уникальных слов: ")
                        .append(textClass.getTotalUniqueWords()).append("\n");
            } else {
                newString.append("Количество уникальных слов: слова не подсчитаны, " +
                        "сперва посчитайте слова").append("\n");
            }

            newString.append("\n____Символы преобразованные к верхнему регистру и их общее количество____");
            newString.append("\n");
            newString.append("Базовый текст: ").append(textClass.getText()).append("\n");
            if (textClass.getUppercasedText() != null) {
                newString.append("Преобразование к верхнему регистру: ")
                        .append(textClass.getUppercasedText()).append("\n");
            } else {
                newString.append("Преобразование к верхнему регистру: слова не преобразованы, " +
                        "сначала преобразуйте слова").append("\n");
            }

            newString.append("\n____Поиск по слову____");
            newString.append("\n");
            newString.append("Базовый текст: ").append(textClass.getText()).append("\n");
            if (textClass.getTotalFoundWords() > 0) {
                newString.append("Слово по которому проводился поиск: ")
                        .append(textClass.getWordToSearch()).append("\n");
                newString.append("Количество найденных слов: ")
                        .append(textClass.getTotalFoundWords()).append("\n");
            } else {
                newString.append("Количество найденных слов: слова не найдены, " +
                        "сначала найдите слова").append("\n");
            }

            newString.append("[*** ПРОХОД ").append(iterationsCounter)
                    .append(". ЗАПИСЬ ВЫПОЛНЕНА ***]").append("\n");

            writer.write(String.valueOf(newString));


        } catch (IOException e) {
            LOGGER.debug(ANSI_RED + "Ошибка в классе: " + ANSI_GREEN
                    + getClass().getName() + " "
                    + ANSI_RED + e.getMessage() + "\n" + ANSI_RESET);
        }

        LOGGER.info(
                ANSI_GREEN + "Результат работы программы записан в файл: \n"
                        + ANSI_YELLOW + OUTPUT_FILE_NAME + "\n" + ANSI_RESET
        );
    }

    // Method cleans the file
    public void clearFile() {
        try {
            FileUtils.write(new File(OUTPUT_FILE_PATH + OUTPUT_FILE_NAME),
                    "", Charset.defaultCharset()
            );
        } catch (IOException e) {
            LOGGER.debug(ANSI_RED + "Ошибка в классе: " + ANSI_GREEN
                    + getClass().getName() + " "
                    + ANSI_RED + e.getMessage() + "\n" + ANSI_RESET);
        }

        LOGGER.info(
                ANSI_GREEN + "Файл: " + ANSI_YELLOW + OUTPUT_FILE_NAME
                        + ANSI_GREEN + " был очищен!\n" + ANSI_RESET
        );
    }

    // Method additionally asks user should the file to be cleaned
    public void askForFileClean(Scanner scanner) {
        int enteredOption;

        if (scanner.hasNextInt()) {
            enteredOption = scanner.nextInt();
            if (enteredOption == 1) {
                clearFile();
            }
            if (enteredOption == 2) {
                LOGGER.info(ANSI_GREEN + "Вы передумали!\n" + ANSI_RESET);
            }
            if (enteredOption > 2) {
                LOGGER.debug(ANSI_RED + "Неверная операция, "
                        + "попробуйте ещё раз!\n" + ANSI_RESET);
            }
        }
    }
}
