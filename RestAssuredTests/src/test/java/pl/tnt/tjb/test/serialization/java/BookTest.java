package pl.tnt.tjb.test.serialization.java;

import static org.testng.Assert.assertEquals;

import java.io.File;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import pl.tnt.tjb.serialization.java.Book;
import pl.tnt.tjb.serialization.java.Serialization;

public class BookTest {
    private static final String FILE_NAME = "book";
    private static final Book LOTR_BOOK = new Book("Lord of The Rings", "J.R.R. Tolkien");

    @Test
    public void shouldBeAbleToSerializeBook() {
        Serialization.serializeObject(LOTR_BOOK, FILE_NAME);
    }

    @Test(dependsOnMethods = "shouldBeAbleToSerializeBook")
    public void deserializationShouldBeSuccessful() {
        Book deserializedBook = Serialization.deserializeObject(FILE_NAME);
        assertEquals(deserializedBook, LOTR_BOOK);
    }

    @AfterClass
    public void deleteFile() {
        File file = new File(FILE_NAME);
        if (!file.delete()) {
            throw new RuntimeException("Was unable to delete file!");
        }
    }
}
