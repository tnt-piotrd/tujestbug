package pl.tnt.tjb.serialization;

import java.io.*;

public class Serialization {
    public static void main(String[] args) {
        Book book = new Book("LOTR", "Tolkien");
        serializeObject(book, "test1");
        Book book2 = deserializeObject("test1");
        System.out.println(book.equals(book2));
    }

    public static File serializeObject(Book book, String fileName) {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(fileName))){
            objectOutputStream.writeObject(book);
            objectOutputStream.flush();
            return new File(fileName);
        }catch (IOException e){
            throw new RuntimeException("Not able to serialize object", e);
        }
    }

    public static Book deserializeObject(String fileName) {
        try(ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(fileName))){
            return (Book) objectInputStream.readObject();
        }catch (IOException | ClassNotFoundException e){
            throw new RuntimeException("Not able to deserialize object", e);
        }
    }
}
