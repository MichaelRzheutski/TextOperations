import exceptions.NegativeValueException;
import exceptions.NotNumberException;
import exceptions.OutOfMenuBoundsException;
import operations.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Scanner;

import static helpers.ConsoleColors.*;

public class ProgramMenu {
    ConsoleTextWriter consoleTextWriter = new ConsoleTextWriter();
    TextFileReader textFileReader = new TextFileReader();
    TotalUniqueWordsFinder totalUniqueWordsFinder = new TotalUniqueWordsFinder();
    WordSplitter wordSplitter = new WordSplitter();
    WordFinder wordFinder = new WordFinder();

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
        int option;

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                LOGGER.info(
                        String.format("%sПожалуйста, выберите одну из предложенных операций: %s",
                                ANSI_GREEN, ANSI_RESET)
                );
                LOGGER.info("[1]. Ввести какой-то текст");
                LOGGER.info("[2]. Прочитать текстовый файл");
                LOGGER.info("[3]. Посчитать количество уникальных слов в тексте файла");
                LOGGER.info("[4]. Получить все символы текста в файле в верхнем регистре");
                LOGGER.info("[5]. Найти слово в тексте файла");
                LOGGER.info("[0]. Выйти из программы");

                if (scanner.hasNextInt()) {
                    option = scanner.nextInt();

                    switch (option) {
                        case 0 -> System.exit(0);
                        case 1 -> consoleTextWriter.typeToConsole();
                        case 2 -> textFileReader.readTextFromFile();
                        case 3 -> totalUniqueWordsFinder.countUniqueWords();
                        case 4 -> wordSplitter.getAllLetters();
                        case 5 -> wordFinder.findWord();
                        case 6 -> throw new OutOfMenuBoundsException(
                                "Введён пункт меню " + option + " свыше доступных", option - 1);
                        case -1 -> throw new NegativeValueException("Введено негативное число", option);
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
        } catch (NegativeValueException e) {
            LOGGER.debug(ANSI_RED +
                    "Ой, произошла ошибочка " + e + " " + ANSI_YELLOW + e.getNumber()
                    + ANSI_RED + " в классе: " + ANSI_GREEN + getClass().getName() + ANSI_RESET);
        } catch (OutOfMenuBoundsException e) {
            LOGGER.debug(ANSI_RED +
                    "Ой, произошла ошибочка " + e + " " + ANSI_YELLOW + e.getNumber()
                    + ANSI_RED + " в классе: " + ANSI_GREEN + getClass().getName() + ANSI_RESET);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
