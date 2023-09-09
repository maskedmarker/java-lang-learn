package org.example.learn.java.io.network.socket;

import org.example.learn.java.io.network.socket.bio.BioEchoClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.InetAddress;

import static org.junit.Assert.assertEquals;

public class EchoServerTest {

    private BioEchoClient client;

    @Before
    public void setup() throws IOException {
        int serverPort = 4444;
        client = new BioEchoClient();
        client.startConnection(InetAddress.getLocalHost().getHostAddress(), serverPort);
    }

    @After
    public void tearDown() throws IOException {
        client.stopConnection();
    }

    @Test
    public void givenClient_whenServerEchosMessage_thenCorrect() throws IOException {
        String resp1 = client.sendMessage("hello");
        String resp2 = client.sendMessage("world");
        String resp3 = client.sendMessage("!");
        String resp4 = client.sendMessage(".");

        assertEquals("hello", resp1);
        assertEquals("world", resp2);
        assertEquals("!", resp3);
        assertEquals("good bye", resp4);
    }
}