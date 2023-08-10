package org.example.learn.java.io.network.socket.nio;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NioServerTest {

    @Test
    public void test0() throws IOException {
        //define an assign a selector
        Selector selector = Selector.open();
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //specify the port and host to connect to
        InetSocketAddress serverSocketAddr = new InetSocketAddress("localhost", 1111);
        serverSocketChannel.bind(serverSocketAddr);
        //to set our server as non-blocking.
        serverSocketChannel.configureBlocking(false);

        while (true) {
            System.out.println("I'm a server and I'm waiting for new connection and buffer select...");
            selector.select();

            //define a set of selectable keys
            Set<SelectionKey> selKeys = selector.selectedKeys();
            Iterator<SelectionKey> keyIterator = selKeys.iterator();

            //iterate over the selected keys
            while (keyIterator.hasNext()) {
                SelectionKey myKey = keyIterator.next();

                /*if both the server and the client have binded to a port and
                both are ready to share data with one another isAcceptable()
                will return true */

                if (myKey.isAcceptable()) {
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ);
                    System.out.println("Connection Accepted: " + socketChannel.getLocalAddress() + "\n");
                } else if (myKey.isReadable()) {
                    SocketChannel socketChannel = (SocketChannel) myKey.channel();
                    ByteBuffer rcvBuffer = ByteBuffer.allocate(1024);
                    socketChannel.read(rcvBuffer);
                    InputStream in = new ByteArrayInputStream(rcvBuffer.array());
                    //do something with your data
                    //send ACK

                    byte[] ackByte = "bye bye".getBytes();
                    ByteBuffer sendBuffer = ByteBuffer.wrap(ackByte);
                    socketChannel.write(sendBuffer);

                }
            }

            //once read, each key is removed from the operation.
            keyIterator.remove();
        }
    }
}
