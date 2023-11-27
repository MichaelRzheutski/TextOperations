package menus;

import exceptions.NotNumberException;
import operations.WordSplitter;
import operations.finding.UniqueWordsFinder;
import operations.finding.WordFinder;
import operations.writing.ResultWriter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Scanner;

import static helpers.ConsoleColors.*;

public class TextOperationsMenu {
    UniqueWordsFinder uniqueWordsFinder = new UniqueWordsFinder();
    WordSplitter wordSplitter = new WordSplitter();
    WordFinder wordFinder = new WordFinder();
    ResultWriter resultWriter = new ResultWriter();

    // Setup Logger log4j2
    static {
        System.setProperty("log4j.configurationFile", "src/test/resources/log4j2.xml");
    }

    private static final Logger LOGGER = LogManager.getLogger();

    public TextOperationsMenu() {
    }

    // Text operations menu
    public final void showTextOperationsMenu(Scanner scanner, String textToOperate) {
        int option;
        boolean isExit = false;

        try {
            while (!isExit) {
                LOGGER.info(
                        ANSI_GREEN + "Пожалуйста, " +
                                "выберите одну из предложенных операций: " + ANSI_RESET
                );
                LOGGER.info("[1]. Посчитать количество уникальных слов в тексте файла");
                LOGGER.info("[2]. Получить все символы текста в верхнем регистре");
                LOGGER.info("[3]. Посчитать количество вхождений слова в тексте");
                LOGGER.info("[4]. Записать результат работы программы в файл");
                LOGGER.info("[5]. Выйти в предыдущее меню");
                LOGGER.info("[0]. Выйти из программы");

                if (scanner.hasNextInt()) {
                    option = scanner.nextInt();

                    switch (option) {
                        case 0 -> System.exit(0);
                        case 1 -> uniqueWordsFinder.setUniqueWordsQuantity(
                                uniqueWordsFinder.countUniqueWords(textToOperate)
                        );
                        case 2 -> wordSplitter.setUppercasedText(
                                wordSplitter.getAllLetters(textToOperate)
                        );
                        case 3 -> wordFinder.setTotalFoundWords(
                                wordFinder.findWord(textToOperate)
                        );
                        case 4 -> resultWriter.writeResultToFile(
                                textToOperate,
                                uniqueWordsFinder.getUniqueWordsQuantity(),
                                wordSplitter.getUppercasedText(),
                                wordFinder.getTotalFoundWords(),
                                wordFinder.getWordToSearch());
                        case 5 -> isExit = true;
                        default -> LOGGER.debug(
                                ANSI_RED + "Неверная операция, " +
                                        "попробуйте ещё раз!\n" + ANSI_RESET
                        );
                    }
                } else {
                    throw new NotNumberException(
                            "вместо числа введена строка "
                                    + ANSI_YELLOW + scanner.next() + ANSI_RESET);
                }
            }
        } catch (NotNumberException | IOException e) {
            LOGGER.debug(ANSI_RED + "Ошибка в классе: " + ANSI_GREEN
                    + getClass().getName() + " "
                    + ANSI_RED + e.getMessage() + "\n" + ANSI_RESET);
        }
    }
}
