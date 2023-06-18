package org.example.learn.java.lang.spec.serialization;

import org.junit.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SerializationPerson2Test {

    @Test
    public void testSerialization() throws IOException {
        SerializationPerson2 person = new SerializationPerson2();
        person.setId(1024);
        person.setName("zhangsan");
        System.out.println("person = " + person);

        String objectFileName = SerializationPerson2.class.getSimpleName() + ".obj";
        ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(Paths.get(objectFileName)));
        oos.writeObject(person);
    }

    @Test
    public void testDeserialization() throws IOException, ClassNotFoundException {
        String objectFileName = SerializationPerson2.class.getSimpleName() + ".obj";
        ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(Paths.get(objectFileName)));
        SerializationPerson2 person = (SerializationPerson2) ois.readObject();
        System.out.println("person = " + person);
    }
}