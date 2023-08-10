package org.example.learn.java.io.network.socket.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class BioEchoServer {

    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;



    public void start(int port) throws IOException {
        System.out.println("EchoServer is starting");

        serverSocket = new ServerSocket(port);
        System.out.println("EchoServer is listening on port " + port);

        clientSocket = serverSocket.accept();
        InetSocketAddress remoteSocketAddress = (InetSocketAddress) clientSocket.getRemoteSocketAddress();
        System.out.printf("EchoServer accepted a socket [ip=%s port=%s]%n", remoteSocketAddress.getAddress().getHostAddress(), remoteSocketAddress.getPort());

        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            System.out.println("EchoServer is running");

            if (".".equals(inputLine)) {
                out.println("good bye");
                break;
            }
            out.println(inputLine);
        }

        System.out.println("EchoServer stopped");
    }

    public static void main(String[] args) throws IOException {
        BioEchoServer echoServer = new BioEchoServer();
        echoServer.start(4444);
    }
}

