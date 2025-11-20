package test.other.singleton;

import org.testng.annotations.Test;
import other.singleton.SimpleSingleton;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertSame;

public class SimpleSingletonTest {
    @Test
    public void shouldReturnSameInstance() {
        SimpleSingleton first = SimpleSingleton.getInstance();
        SimpleSingleton second = SimpleSingleton.getInstance();

        assertSame(first, second, "Instances are not the same!");
    }

    @Test
    public void shouldIncrementCounterGlobally(){
        SimpleSingleton singleton = SimpleSingleton.getInstance();

        int value1 = singleton.incrementAndGet();
        int value2 = singleton.incrementAndGet();

        assertEquals(2, value2, "Counter should be incremented globally!");
    }

    @Test
    public void singletonShouldMaintainStateAcrossTests(){
        SimpleSingleton singleton = SimpleSingleton.getInstance();
        int currentCounter = singleton.getCounter();
        assertThat("Singleton should keep state across tests executions", currentCounter, greaterThan(0));
    }
}
