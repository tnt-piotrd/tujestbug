package other;

public class SimpleSingleton {
    private static SimpleSingleton instance;
    private int counter;

    private SimpleSingleton() {
        counter = 0;
    }

    public static SimpleSingleton getInstance() {
        if (instance == null) {
            instance = new SimpleSingleton();
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
