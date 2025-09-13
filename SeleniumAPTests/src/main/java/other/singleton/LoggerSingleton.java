package other.singleton;

import utils.logger.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class LoggerSingleton {
    private static volatile LoggerSingleton instance;
    private final Logger logger;

    private LoggerSingleton() {
        Logger consoleLogger = new ClassNameLogger(new TimeStampLogger(new LevelBasedColorLogger(new ConsoleLogger())));
        Logger fileLogger = new ClassNameLogger(new TimeStampLogger(
                new FileLogger("target/logs/%s.log"
                        .formatted(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd-HHmm"))))));
        this.logger = new MultiChannelLogger(List.of(consoleLogger, fileLogger));
    }

    public static LoggerSingleton getInstance() {
        if (instance == null) {
            synchronized (LoggerSingleton.class) {
                if (instance == null) {
                    instance = new LoggerSingleton();
                }
            }
        }
        return instance;
    }

    public void log(String message){
        logger.log(message);
    }
}
