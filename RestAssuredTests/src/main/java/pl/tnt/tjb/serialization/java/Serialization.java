package pl.tnt.tjb.serialization.java;

import java.io.*;

public class Serialization {
    public static File serializeObject(Book book, String fileName) {
        try (ObjectOutputStream objectOutputStream =
                new ObjectOutputStream(new FileOutputStream(fileName))) {
            objectOutputStream.writeObject(book);
            objectOutputStream.flush();
            return new File(fileName);
        } catch (IOException e) {
            throw new RuntimeException("Not able to serialize object", e);
        }
    }

    public static Book deserializeObject(String fileName) {
        try (ObjectInputStream objectInputStream =
                new ObjectInputStream(new FileInputStream(fileName))) {
            return (Book) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Not able to deserialize object", e);
        }
    }
}
