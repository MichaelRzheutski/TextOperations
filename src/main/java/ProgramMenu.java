import exceptions.NegativeValueException;
import exceptions.NotNumberException;
import exceptions.OutOfMenuBoundsException;
import operations.ConsoleTextWriter;
import operations.TextFileReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

import static helpers.ConsoleColors.*;

public class ProgramMenu {
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

    ProgramMenu() {

    }

    // Main menu
    public void mainMenu() {
        boolean isExit = false;
        int option;

        try (Scanner scanner = new Scanner(System.in)) {
            while (!isExit) {
                LOGGER.info(
                        String.format("%sПожалуйста, выберите одну из предложенных операций: %s",
                                ANSI_GREEN, ANSI_RESET)
                );
                LOGGER.info("[1]. Ввести какой-то текст");
                LOGGER.info("[2]. Прочитать текстовый файл");
                LOGGER.info("[0]. Выйти из программы");

                while (true) {
                    if (scanner.hasNextInt()) {
                        option = scanner.nextInt();
                        break;
                    } else {
                        throw new NotNumberException("Вместо числа введена строка", scanner.next());
                    }
                }

                switch (option) {
                    case 0 -> isExit = true;
                    case 1 -> consoleTextWriter.typeToConsole();
                    case 2 -> textFileReader.readTextFromFile();
                    case 3 -> throw new OutOfMenuBoundsException(
                            "Введён пункт меню " + option + " свыше доступных", option - 1);
                    case -1 -> throw new NegativeValueException("Введено негативное число", option);
                    default -> LOGGER.info(
                            String.format("%sНеверная операция, попробуйте ещё раз!%s\n",
                                    ANSI_RED, ANSI_RESET)
                    );
                }
            }
        } catch (NotNumberException e) {
            LOGGER.debug(ANSI_RED +
                    "Ой, произошла ошибочка " + e + " " + ANSI_YELLOW + e.getValue()
                    + ANSI_RED + " в классе: " + ANSI_GREEN + getClass().getName() + ANSI_RESET);
        } catch (NegativeValueException e) {
            LOGGER.debug(ANSI_RED +
                    "Ой, произошла ошибочка " + e + " " + ANSI_YELLOW + e.getNumber()
                    + ANSI_RED + " в классе: " + ANSI_GREEN + getClass().getName() + ANSI_RESET);
        } catch (OutOfMenuBoundsException e) {
            LOGGER.debug(ANSI_RED +
                    "Ой, произошла ошибочка " + e + " " + ANSI_YELLOW + e.getNumber()
                    + ANSI_RED + " в классе: " + ANSI_GREEN + getClass().getName() + ANSI_RESET);
        }
    }
}
