package org.example.learn.java.io.network.socket.tcp;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.CountDownLatch;

/**
 *sever和client建立tcp连接后,serverSocket却不调用accept
 * 此时tcp client是可以发送tcp数据包的,并且tcp server也会给与正常的ack回应(操作系统的网络协议栈自动处理的)
 * tcp server会将这些接收到的数据缓存起来(放在哪里???)
 * 随着越来越多的tcp client数据被tcp server接收, tcp server回复的ack报文中的可接收窗口越来越小,
 * 直到tcp client发送的报文被tcp server收到后,tcp server回复的ack报文中win=0
 * tcp client再发数据报文,tcp server的ack报文中win=0
 *
 * 请注意:
 * 整个过程中tcp server的ack报文中的seq都是1,只是ack在增加.
 */
public class ServerNotAccept2Test {

    private static int SERVER_PORT = 9876;

    CountDownLatch countDownLatch = new CountDownLatch(1);

    @Before
    public void server() throws IOException {
        new Thread(() -> {
            // create a listening server socket
            try {
                ServerSocket serverSocket = new ServerSocket(SERVER_PORT);
                // serverSocket DO NOT accept connection

                countDownLatch.countDown();

                // block main thread to prevent process from termination
                System.in.read();
                System.out.println("server is shutdown");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }

    @Test
    public void client() throws IOException, InterruptedException {
        // wait server is ready
        countDownLatch.await();

        InetAddress host = InetAddress.getLocalHost();
        // create tcp connection
        Socket socket = new Socket(host.getHostName(), SERVER_PORT);
        OutputStream os = socket.getOutputStream();

        String longText = "abcdefghijklmnopqrstuvwxyz|abcdefghijklmnopqrstuvwxyz|abcdefghijklmnopqrstuvwxyz|abcdefghijklmnopqrstuvwxyz|";
        for (int i = 0; i < Integer.MAX_VALUE - 10; i++) {
            System.out.println("Sending request to Socket Server");
            os.write(i);
            for (int j = 0; j < 10000; j++) {
                os.write(longText.getBytes("ASCII"));
            }

            System.out.println("sleep 1s");
            Thread.sleep(100);
        }

        // block main thread to prevent process from termination
        System.in.read();
        os.close();
        System.out.println("client is shutdown");
    }
}
