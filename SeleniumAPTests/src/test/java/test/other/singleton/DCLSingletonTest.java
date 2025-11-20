package test.other.singleton;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import other.singleton.DCLSingleton;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertSame;

public class DCLSingletonTest {
    @Test
    public void shouldReturnSameInstanceOnEachCall() {
        DCLSingleton instance1 = DCLSingleton.getInstance();
        DCLSingleton instance2 = DCLSingleton.getInstance();

        assertSame(instance1, instance2, "Instances are not the same!");
    }

    @Test
    public void shouldIncrementCounterGlobally(){
        DCLSingleton singleton = DCLSingleton.getInstance();

        int value1 = singleton.incrementAndGet();
        int value2 = singleton.incrementAndGet();

        assertEquals(2, value2, "Counter should be incremented globally!");
    }

    @Test(dataProvider = "threads")
    public void shouldReturnSameInstanceAcrossThreads() {
        DCLSingleton instance1 = DCLSingleton.getInstance();
        DCLSingleton instance2 = DCLSingleton.getInstance();

        assertSame(instance1, instance2, "Instance should be consistent in the same thread");

        System.out.println("Thread: " + Thread.currentThread().getName() + ", Instance: " + instance1);
    }

    @DataProvider(name = "threads", parallel = true)
    public Object[][] threadData() {
        return new Object[10][0]; // 10 threads, no parameters needed
    }
}
