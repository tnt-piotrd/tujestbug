package utils.logger;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeStampLogger extends LoggerDecorator{
    public TimeStampLogger(Logger decoratedLogger) {
        super(decoratedLogger);
    }

    @Override
    public void log(String message) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss.SSS"));
        decoratedLogger.log("[%s] %s".formatted(timestamp, message));
    }
}
