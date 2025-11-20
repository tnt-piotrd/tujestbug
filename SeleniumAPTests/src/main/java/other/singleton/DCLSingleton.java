package other.singleton;

public class DCLSingleton {
    private static volatile DCLSingleton instance;
    private int counter = 0;

    private DCLSingleton() {
    }

    public static DCLSingleton getInstance() {
        if (instance == null) {
            synchronized (DCLSingleton.class) {
                if (instance == null) {
                    instance = new DCLSingleton();
                }
            }
        }
        return instance;
    }

    public int incrementAndGet() {
        return ++counter;
    }

    public int getCounter() {
        return counter;
    }
}
