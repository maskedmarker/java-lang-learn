package org.example.learn.java.io.network.socket.nio;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.EnumSet;

public class NioClientTest {

    @Test
    public void test0() throws IOException, InterruptedException, ClassNotFoundException {
        ServerSocketChannel serverSocketChannel = null;
        SocketChannel client = null;

        // The new channel's socket is initially unbound
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(9000));
        // If this channel is in non-blocking mode then this method will immediately return null if there are no pending connections
        client = serverSocketChannel.accept();
        System.out.println("Connection Set:  " + client.getRemoteAddress());
        Path path = Paths.get(null);
        FileChannel fileChannel = FileChannel.open(path, EnumSet.of(StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE));

        ByteBuffer buffer = ByteBuffer.allocate(1024);
        while (client.read(buffer) > 0) {
            buffer.flip();
            fileChannel.write(buffer);
            buffer.clear();
        }
        fileChannel.close();
        System.out.println("File Received");
        client.close();
    }


}
