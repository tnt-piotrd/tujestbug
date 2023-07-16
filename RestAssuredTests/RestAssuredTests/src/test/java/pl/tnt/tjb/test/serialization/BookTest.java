package pl.tnt.tjb.test.serialization;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import pl.tnt.tjb.serialization.Book;
import pl.tnt.tjb.serialization.Serialization;

import java.io.File;

import static org.testng.Assert.assertEquals;

public class BookTest {
    private final static String FILE_NAME = "book";
    private final static Book LOTR_BOOK = new Book("Lord of The Rings", "J.R.R. Tolkien");

    @Test
    public void shouldBeAbleToSerializeBook(){
        Serialization.serializeObject(LOTR_BOOK, FILE_NAME);
    }

    @Test(dependsOnMethods = "shouldBeAbleToSerializeBook")
    public void deserializationShouldBeSuccessful(){
        Book deserializedBook = Serialization.deserializeObject(FILE_NAME);
        assertEquals(deserializedBook, LOTR_BOOK);
    }

    @AfterClass
    public void deleteFile(){
        File file = new File(FILE_NAME);
        if (!file.delete()){
            throw new RuntimeException("Was unable to delete file!");
        }
    }
}
