package org.example.learn.java.io.network.socket.tcp;

import org.junit.Test;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerNotAcceptTest {

    private static int SERVER_PORT = 9876;

    @Test
    public void server() throws IOException {
        // create a listening server socket
        ServerSocket serverSocket = new ServerSocket(SERVER_PORT);
        // serverSocket DO NOT accept connection

        // block main thread to prevent process from termination
        System.in.read();
    }

    @Test
    public void client() throws IOException, InterruptedException {
        InetAddress host = InetAddress.getLocalHost();
        Socket socket = null;
        OutputStream os = null;

        for (int i = 0; i < 3; i++) {
            // create tcp connection
            socket = new Socket(host.getHostName(), SERVER_PORT);

            os = socket.getOutputStream();

            System.out.println("Sending request to Socket Server");
            os.write(i);

            System.out.println("sleep 1s");
            Thread.sleep(1000);
        }

        // block main thread to prevent process from termination
        System.in.read();
        os.close();
    }
}
