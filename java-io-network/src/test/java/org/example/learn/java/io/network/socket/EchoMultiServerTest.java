package org.example.learn.java.io.network.socket;

import org.example.learn.java.io.network.socket.bio.BioEchoClient;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class EchoMultiServerTest {

    @Test
    public void givenClient1_whenServerResponds_thenCorrect() throws IOException {
        BioEchoClient client1 = new BioEchoClient();
        client1.startConnection("127.0.0.1", 5555);
        String msg1 = client1.sendMessage("hello");
        String msg2 = client1.sendMessage("world");
        String terminate = client1.sendMessage(".");
        client1.stopConnection();
        System.out.println("terminate = " + terminate);

        assertEquals(msg1, "hello");
        assertEquals(msg2, "world");
        assertEquals(terminate, "bye");
    }

    @Test
    public void givenClient2_whenServerResponds_thenCorrect() throws IOException {
        BioEchoClient client2 = new BioEchoClient();
        client2.startConnection("127.0.0.1", 5555);
        String msg1 = client2.sendMessage("hello");
        String msg2 = client2.sendMessage("world");
        String terminate = client2.sendMessage(".");
        client2.stopConnection();
        System.out.println("terminate = " + terminate);

        assertEquals(msg1, "hello");
        assertEquals(msg2, "world");
        assertEquals(terminate, "bye");
    }
}