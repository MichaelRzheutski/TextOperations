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
    public void showTextOperationsMenu(String textToOperate) {
        int option;

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                LOGGER.info(
                        String.format("%sПожалуйста, выберите одну из предложенных операций: %s",
                                ANSI_GREEN, ANSI_RESET)
                );
                LOGGER.info("[1]. Посчитать количество уникальных слов в тексте файла");
                LOGGER.info("[2]. Получить все символы текста в файле в верхнем регистре");
                LOGGER.info("[3]. Посчитать количество вхождений слова в тексте");
                LOGGER.info("[4]. Записать результат работы программы в файл");
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
                        default -> LOGGER.info(
                                String.format("%sНеверная операция, попробуйте ещё раз!%s\n",
                                        ANSI_RED, ANSI_RESET)
                        );
                    }
                } else {
                    throw new NotNumberException("Вместо числа введена строка", scanner.next());
                }
            }
        } catch (NotNumberException e) {
            LOGGER.debug(ANSI_RED +
                    "Ой, произошла ошибочка " + e + " " + ANSI_YELLOW + e.getValue()
                    + ANSI_RED + " в классе: " + ANSI_GREEN + getClass().getName() + ANSI_RESET);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
