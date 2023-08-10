package org.example.learn.java.io.network.socket.nio;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class NioEchoClient {

    private SocketChannel socketChannel;
    private ByteBuffer buffer;

    public void start(int port) throws IOException {
        buffer = ByteBuffer.allocate(256);
        socketChannel = SocketChannel.open(new InetSocketAddress(InetAddress.getLocalHost().getHostAddress(), port));
    }

    public void stop() throws IOException {
        socketChannel.close();
        buffer = null;
    }

    public String sendMessage(String msg) throws IOException {
        buffer = ByteBuffer.wrap(msg.getBytes());
        String response = null;
        socketChannel.write(buffer);

        buffer.clear();
        socketChannel.read(buffer);
        response = new String(buffer.array()).trim();
        buffer.clear();
        System.out.println("response=" + response);

        return response;
    }
}
