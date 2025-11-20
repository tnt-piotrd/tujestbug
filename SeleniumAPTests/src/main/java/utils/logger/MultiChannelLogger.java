package utils.logger;

import java.util.List;

public class MultiChannelLogger implements Logger{
    private final List<Logger> loggers;

    public MultiChannelLogger(List<Logger> loggers) {
        this.loggers = loggers;
    }

    @Override
    public void log(String message) {
        for (Logger logger:loggers){
            logger.log(message);
        }
    }
}
