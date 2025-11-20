package utils.logger;

public class LevelBasedColorLogger extends LoggerDecorator{
    private String DEFAULT_COLOR = "\u001B[37m"; // Grey
    private String ERROR_COLOR = "\u001B[31m"; // Red
    private String WARN_COLOR = "\u001B[33m"; // Yellow
    private String PASS_COLOR = "\u001B[32m"; // Green
    private String RESET_COLOR = "\u001B[0m"; // Reset color

    public LevelBasedColorLogger(Logger decoratedLogger) {
        super(decoratedLogger);
    }

    @Override
    public void log(String message) {
        String color;
        if (message.contains("ERROR")){
            color = ERROR_COLOR;
        } else if (message.contains("WARN")) {
            color = WARN_COLOR;
        } else if (message.toLowerCase().contains("pass")
                || message.toLowerCase().contains("success")) {
            color = PASS_COLOR;
        } else {
            color = DEFAULT_COLOR;
        }
        decoratedLogger.log(color + message + RESET_COLOR);
    }
}