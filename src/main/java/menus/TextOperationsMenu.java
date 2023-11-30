package menus;

import exceptions.NotNumberException;
import operations.TextClass;
import operations.TextOperations;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Scanner;

import static helpers.ConsoleColors.*;

public class TextOperationsMenu {
    TextOperations textOperations = new TextOperations();

    // Setup Logger log4j2
    static {
        System.setProperty("log4j.configurationFile", "src/test/resources/log4j2.xml");
    }

    private static final Logger LOGGER = LogManager.getLogger();

    public TextOperationsMenu() {
    }

    // Text operations menu
    public final void showTextOperationsMenu(Scanner scanner, TextClass textClass) {
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
                LOGGER.info("[5]. Очистить файл с записями операций");
                LOGGER.info("[6]. Выйти в предыдущее меню");
                LOGGER.info("[0]. Выйти из программы");

                if (scanner.hasNextInt()) {
                    option = scanner.nextInt();

                    switch (option) {
                        case 0 -> System.exit(0);
                        case 1 -> textClass.setTotalUniqueWords(
                                textOperations.countUniqueWords(textClass)
                        );

                        case 2 -> textClass.setUppercasedText(
                                textOperations.uppercaseAllLetters(textClass)
                        );
                        case 3 -> textClass.setTotalFoundWords(
                                textOperations.findWord(scanner, textClass)
                        );
                        case 4 -> textOperations.writeOperationsResultToFile(textClass);
                        case 5 -> {
                            LOGGER.info(ANSI_GREEN + "Вы уверены? [1]. Да | [2]. Нет" + ANSI_RESET);
                            textOperations.askForFileClean(scanner);
                        }
                        case 6 -> isExit = true;
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
