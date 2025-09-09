package utils.logger;

public class ClassNameLogger extends LoggerDecorator{
    public ClassNameLogger(Logger decoratedLogger) {
        super(decoratedLogger);
    }

    @Override
    public void log(String message) {
        String className = getCallerClassName();
        decoratedLogger.log("[%s] %s".formatted(className, message));
    }

    private String getCallerClassName() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        boolean foundLogger = false;
        for (StackTraceElement element : stackTrace) {
            String className = element.getClassName();
            // Skipping classes that are part of the logging framework or thread management
            if (foundLogger && !className.contains("Logger") && !className.startsWith("Thread")) {
                return className.substring(className.lastIndexOf(".") + 1);
            }
            if (className.equals(this.getClass().getName())) {
                foundLogger = true;
            }
        }
        return "UnknownClass";
    }
}
