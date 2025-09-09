package other.singleton;

import utils.logger.Logger;
import utils.logger.ConsoleLogger;
import utils.logger.TimeStampLogger;

public class LoggerSingleton {
    private static volatile LoggerSingleton instance;
    private final Logger logger;

    private LoggerSingleton() {
        this.logger = new TimeStampLogger(new ConsoleLogger());
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
