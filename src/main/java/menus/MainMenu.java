package menus;

import exceptions.NotNumberException;
import operations.reading.TextFileReader;
import operations.writing.ConsoleTextWriter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

import static helpers.ConsoleColors.*;

public class MainMenu {
    TextOperationsMenu textOperationsMenu = new TextOperationsMenu();
    ConsoleTextWriter consoleTextWriter = new ConsoleTextWriter();
    TextFileReader textFileReader = new TextFileReader();

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

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                LOGGER.info(
                        String.format("%sПожалуйста, выберите одну из предложенных операций: %s",
                                ANSI_GREEN, ANSI_RESET)
                );
                LOGGER.info("[1]. Ввести какой-то текст");
                LOGGER.info("[2]. Прочитать текстовый файл \"LoremIpsum\"");
                LOGGER.info("[0]. Выйти из программы");

                if (scanner.hasNextInt()) {
                    option = scanner.nextInt();

                    switch (option) {
                        case 0 -> System.exit(0);
                        case 1 -> {
                            consoleTextWriter.typeToConsole();
                            textOperationsMenu.showTextOperationsMenu(
                                    scanner, consoleTextWriter.getEnteredText()
                            );
                        }
                        case 2 -> {
                            textFileReader.readTextFromLoremIpsum();
                            textOperationsMenu.showTextOperationsMenu(
                                    scanner, textFileReader.getReadText()
                            );
                        }
                        default -> LOGGER.debug(
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
        }
    }
}
