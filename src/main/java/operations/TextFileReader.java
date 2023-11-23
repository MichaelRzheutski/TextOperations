package operations;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import static helpers.ConsoleColors.*;

public class TextFileReader {

    // Setup Logger log4j2
    static {
        System.setProperty("log4j.configurationFile", "src/test/resources/log4j2.xml");
    }

    private static final Logger LOGGER = LogManager.getLogger();

    public TextFileReader() {
    }

    // Method reads text from file
    public void readTextFromFile() {
        boolean isExit = false;
        String enteredText;
        String text = null;
        String filePath = "src/main/resources/";
        String fileName = "text.txt";


        try (BufferedReader br = new BufferedReader(new FileReader(filePath + fileName))) {
            Scanner scanner = new Scanner(System.in);

            while (!isExit) {
                LOGGER.info(String.format("%sВведите название файла для чтения: %s",
                        ANSI_GREEN, ANSI_RESET));

                while (true) {
                    if (scanner.hasNextLine()) {
                        enteredText = scanner.nextLine();

                        if (enteredText.equals(fileName)) {
                            text = br.readLine();
                        }

                        isExit = true;
                    } else {
                        LOGGER.info(
                                String.format("%sНеверная операция, попробуйте ещё раз!%s\n",
                                        ANSI_RED, ANSI_RESET)
                        );
                    }
                    break;
                }

                LOGGER.info(ANSI_GREEN + "Файл " + ANSI_YELLOW + fileName + ANSI_GREEN
                        + " имеет следующее содержание: \n" + ANSI_YELLOW + text + ANSI_RESET);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
