package org.example.learn.java.io.network.socket.nio;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

public class NioClientTest {

    @Test
    public void test0() throws IOException, InterruptedException {
        InetSocketAddress socketAddress = new InetSocketAddress("localhost", 1111);
        System.out.println("Connecting to Server on port 1111 ....");
        SocketChannel socketClient = SocketChannel.open(socketAddress);

        List<String> messages = new ArrayList<>();
        messages.add("Message 1");
        messages.add("Message 2");
        messages.add("Message 3");

        for(String msg : messages) {

            byte[] message = new String(msg).getBytes();
            ByteBuffer buffer = ByteBuffer.wrap(message);
            System.out.println("sending: " + msg);
            socketClient.write(buffer);
            buffer.clear();

            ByteBuffer ackBuffer = ByteBuffer.allocate(256);
            socketClient.read(ackBuffer);
            String ackRes = new String(ackBuffer.array()).trim();

            System.out.println("Ack from Server :" + ackRes);
            Thread.sleep(2000);
        }

        socketClient.close();
    }
}
