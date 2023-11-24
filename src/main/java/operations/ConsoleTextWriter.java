package operations;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import static helpers.ConsoleColors.*;

public class ConsoleTextWriter {

    // Setup Logger log4j2
    static {
        System.setProperty("log4j.configurationFile", "src/test/resources/log4j2.xml");
    }

    private static final Logger LOGGER = LogManager.getLogger();

    public ConsoleTextWriter() {
    }

    // Method types text to console
    public void typeToConsole() {
        String enteredText = "";
        String filePath = "src/main/resources/";
        String fileName = "text.txt";

        try (FileWriter writer = new FileWriter(filePath + fileName, false)) {
            Scanner scanner = new Scanner(System.in);

            while (true) {
                LOGGER.info(String.format("%sВведите текст: %s", ANSI_GREEN, ANSI_RESET));

                if (scanner.hasNextLine()) {
                    enteredText = scanner.nextLine();

                    assert enteredText != null;
                    writer.write(enteredText);

                    break;
                } else {
                    LOGGER.info(
                            String.format("%sНеверная операция, попробуйте ещё раз!%s\n",
                                    ANSI_RED, ANSI_RESET)
                    );
                }
            }

            LOGGER.info(ANSI_GREEN + "В файл " + ANSI_YELLOW + fileName + ANSI_GREEN
                    + " был записан текст: " + "\n" + ANSI_YELLOW + enteredText + ANSI_RESET);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
