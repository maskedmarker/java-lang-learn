package org.example.learn.java.io.network.socket.bio;

import org.junit.Test;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * java.net.ServerSocket 该方法也是同步阻塞绑定端口,如果绑定失败,则抛出异常
 */
public class BioServerTest {

    //static ServerSocket variable
    private static ServerSocket server;
    //socket server port on which it will listen
    private static int port = 9876;

    @Test
    public void test0() throws IOException, ClassNotFoundException {
        //create the socket server object
        server = new ServerSocket(port);

        //keep listens indefinitely until receives 'exit' call or program terminates
        while (true) {
            System.out.println("Waiting for the client request");
            //creating socket and waiting for client connection
            Socket socket = server.accept();

            //read from socket to ObjectInputStream object
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            //convert ObjectInputStream object to String
            String message = (String) ois.readObject();
            System.out.println("Message Received: " + message);
            //create ObjectOutputStream object
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            //write object to Socket
            oos.writeObject("Hi Client " + message);
            //close resources
            ois.close();
            oos.close();
            socket.close();

            //terminate the server if client sends exit request
            if (message.equalsIgnoreCase("exit")) {
                break;
            }
        }

        System.out.println("Shutting down Socket server!!");
        //close the ServerSocket object
        server.close();
    }
}
