package test;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class StringBuilderTest {
    @Test
    public void stringBuilderTest(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Hello");
        stringBuilder.append(" ");
        stringBuilder.append("World");
        String result = stringBuilder.toString();
        assertEquals(result, "Hello World");
    }
}
