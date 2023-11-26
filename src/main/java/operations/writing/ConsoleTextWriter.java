package operations.writing;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import static helpers.ConsoleColors.*;

public class ConsoleTextWriter {
    private String enteredText;
    private String filePath = "src/main/resources/output/";
    private String fileName = "output.txt";

    // Setup Logger log4j2
    static {
        System.setProperty("log4j.configurationFile", "src/test/resources/log4j2.xml");
    }

    private static final Logger LOGGER = LogManager.getLogger();

    public ConsoleTextWriter() {
    }

    // Method types text to console
    public void typeToConsole() {
        try (FileWriter writer = new FileWriter(filePath + fileName, false)) {
            Scanner scanner = new Scanner(System.in);

            while (true) {
                LOGGER.info(String.format("%sВведите текст: %s", ANSI_GREEN, ANSI_RESET));

                if (scanner.hasNextLine()) {
                    enteredText = scanner.nextLine();

                    if (StringUtils.isNotBlank(enteredText)) {
                        writer.write(enteredText);

                        LOGGER.info(ANSI_GREEN + "В файл " + ANSI_YELLOW + fileName + ANSI_GREEN
                                + " был записан текст: " + "\n" + ANSI_YELLOW + enteredText + ANSI_RESET);

                        break;
                    } else {
                        LOGGER.info(
                                String.format("%sВведена пустая строка, введите какой-то текст!%s\n",
                                        ANSI_RED, ANSI_RESET)
                        );
                    }

                } else {
                    LOGGER.info(
                            String.format("%sНеверная операция, попробуйте ещё раз!%s\n",
                                    ANSI_RED, ANSI_RESET)
                    );
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public String getEnteredText() {
        return enteredText;
    }

    public void setEnteredText(String enteredText) {
        this.enteredText = enteredText;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
