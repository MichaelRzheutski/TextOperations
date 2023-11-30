package menus;

import exceptions.NotNumberException;
import operations.TextClass;
import operations.TextOperations;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

import static helpers.ConsoleColors.*;

public class MainMenu {
    private final TextClass textClass = new TextClass();
    private final TextOperations textOperations = new TextOperations();
    private final TextOperationsMenu textOperationsMenu = new TextOperationsMenu();
    private final Scanner scanner = new Scanner(System.in);

    // Setup Logger log4j2
    static {
        System.setProperty("log4j.configurationFile", "src/test/resources/log4j2.xml");
    }

    private static final Logger LOGGER = LogManager.getLogger();

    // Greeting
    static {
        LOGGER.info(
                String.format("%sПрограмма для работы с текстом%s", ANSI_GREEN, ANSI_RESET)
        );
    }

    public MainMenu() {
    }

    // Main menu
    public final void mainMenu() {
        int option;

        try (scanner) {
            while (true) {
                LOGGER.info(
                        ANSI_GREEN + "Пожалуйста, " +
                                "выберите одну из предложенных операций: " + ANSI_RESET
                );
                LOGGER.info("[1]. Прочитать текст из консоли без сохранения в файл");
                LOGGER.info("[2]. Прочитать текст из консоли с сохранением в файл");
                LOGGER.info("[3]. Прочитать текстовый файл с жёсткого диска");
                LOGGER.info("[4]. Очистить файл с записями операций");
                LOGGER.info("[0]. Выйти из программы");

                if (scanner.hasNextInt()) {
                    option = scanner.nextInt();

                    switch (option) {
                        case 0 -> System.exit(0);
                        case 1 -> {
                            textOperations.readFromConsole(scanner, textClass);
                            textOperationsMenu.showTextOperationsMenu(scanner, textClass);
                        }
                        case 2 -> {
                            textOperations.readFromConsoleAndSaveToFile(scanner, textClass);
                            textOperationsMenu.showTextOperationsMenu(scanner, textClass);
                        }
                        case 3 -> {
                            textOperations.readFromTextFile(textClass);
                            textOperationsMenu.showTextOperationsMenu(scanner, textClass);
                        }
                        case 4 -> {
                            LOGGER.info(ANSI_GREEN + "Вы уверены? [1]. Да | [2] Нет" + ANSI_RESET);
                            textOperations.askForFileClean(scanner);
                        }
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
        } catch (NotNumberException e) {
            LOGGER.debug(ANSI_RED + "Ошибка в классе: " + ANSI_GREEN
                    + getClass().getName() + " "
                    + ANSI_RED + e.getMessage() + ANSI_RESET);
        }
    }
}
