package operations.reading;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static helpers.ConsoleColors.*;

public class TextFileReader {
    private String readText;
    private final String FILE_PATH = "src/main/resources/texts/";
    private final String FILE_NAME = "LoremIpsum.txt";

    // Setup Logger log4j2
    static {
        System.setProperty("log4j.configurationFile", "src/test/resources/log4j2.xml");
    }

    private static final Logger LOGGER = LogManager.getLogger();

    public TextFileReader() {
    }

    // Method reads a text from the file
    public void readTextFromLoremIpsum() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH + FILE_NAME))) {
            readText = br.readLine();

            LOGGER.info(
                    ANSI_GREEN + "Файл " + ANSI_YELLOW + FILE_NAME + ANSI_GREEN
                            + " имеет следующее содержание: \n" + ANSI_YELLOW + readText
                            + "\n" + ANSI_RESET
            );

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public String getReadText() {
        return readText;
    }

    public void setReadText(String readText) {
        this.readText = readText;
    }

    public String getFILE_PATH() {
        return FILE_PATH;
    }

    public String getFILE_NAME() {
        return FILE_NAME;
    }
}
