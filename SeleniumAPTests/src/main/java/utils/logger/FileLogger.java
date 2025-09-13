package utils.logger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileLogger implements Logger{
    private final String filePath;

    public FileLogger(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void log(String message) {
        File file = new File(filePath);
        File parentDir = file.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath, true))) {
            writer.println(message);
        } catch (IOException e) {
            throw new RuntimeException("Failed to write to log file: " + e.getMessage());
        }
    }
}
