package org.example.learn.java.lang.spec.serialization;

import org.junit.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SerializationPerson3Test {

    private static final String FILE_NAME_SUFFIX = "ser";

    @Test
    public void testSerialization() throws IOException {
        SerializationPerson3 person = new SerializationPerson3();
        person.setId(1024);
        person.setName("zhangsan");
        System.out.println("person = " + person);

        String objectFileName = SerializationPerson3.class.getSimpleName() + FILE_NAME_SUFFIX;
        ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(Paths.get(objectFileName)));
        oos.writeObject(person);
    }

    @Test
    public void testDeserialization() throws IOException, ClassNotFoundException {
        String objectFileName = SerializationPerson3.class.getSimpleName() + FILE_NAME_SUFFIX;
        ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(Paths.get(objectFileName)));
        SerializationPerson3 person = (SerializationPerson3) ois.readObject();
        System.out.println("person = " + person);
    }
}