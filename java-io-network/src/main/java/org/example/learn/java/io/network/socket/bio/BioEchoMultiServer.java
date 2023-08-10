package org.example.learn.java.io.network.socket.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class BioEchoMultiServer {
    private ServerSocket serverSocket;

    public void start(int port) throws IOException {
        System.out.println("EchoMultiServer is starting");

        serverSocket = new ServerSocket(port);
        System.out.println("EchoMultiServer is listening on port " + port);

        while (true) {
            Socket clientSocket = serverSocket.accept();
            InetSocketAddress remoteSocketAddress = (InetSocketAddress) clientSocket.getRemoteSocketAddress();
            System.out.printf("EchoMultiServer accepted a socket [ip=%s port=%s]%n", remoteSocketAddress.getAddress().getHostAddress(), remoteSocketAddress.getPort());

            new EchoClientHandler(clientSocket).start();
        }
    }

    public void stop() throws IOException {
        serverSocket.close();
        System.out.println("EchoMultiServer stopped");
    }

    private static class EchoClientHandler extends Thread {
        private Socket clientSocket;
        private PrintWriter out;
        private BufferedReader in;

        public EchoClientHandler(Socket socket) {
            this.clientSocket = socket;
        }

        public void run() {
            System.out.println("EchoServer is running");

            try {
                out = new PrintWriter(clientSocket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    if (".".equals(inputLine)) {
                        out.println("bye");
                        break;
                    }
                    out.println(inputLine);
                }

                in.close();
                out.close();
                clientSocket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BioEchoMultiServer multiServer = new BioEchoMultiServer();
        multiServer.start(5555);
    }
}
