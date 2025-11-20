package utils.logger;

public abstract class LoggerDecorator implements Logger{
    protected Logger decoratedLogger;

    public LoggerDecorator(Logger decoratedLogger) {
        this.decoratedLogger = decoratedLogger;
    }
}
