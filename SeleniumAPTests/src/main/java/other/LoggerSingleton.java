package other;

import utils.Logger;
import utils.SimpleLogger;

public class LoggerSingleton {
    private static volatile LoggerSingleton instance;
    private final Logger logger;

    private LoggerSingleton() {
        this.logger = new SimpleLogger();
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
