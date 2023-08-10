package org.example.learn.java.io.network.socket.bio;

import org.junit.Test;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

/**
 * java.net.Socket() 该方法会同步阻塞建立tcp连接,建立连接失败的话,会抛出异常
 */
public class BioClientTest {

    @Test
    public void test0() throws IOException, InterruptedException, ClassNotFoundException {
        //get the localhost IP address, if server is running on some other IP, you need to use that
        InetAddress host = InetAddress.getLocalHost();
        Socket socket = null;
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        for (int i = 0; i < 5; i++) {
            //establish socket connection to server
            socket = new Socket(host.getHostName(), 9876);
            //write to socket using ObjectOutputStream
            oos = new ObjectOutputStream(socket.getOutputStream());
            System.out.println("Sending request to Socket Server");
            if (i == 4) {
                oos.writeObject("exit");
            } else {
                oos.writeObject("" + i);
            }
            //read the server response message
            ois = new ObjectInputStream(socket.getInputStream());
            String message = (String) ois.readObject();
            System.out.println("Message: " + message);
            //close resources
            ois.close();
            oos.close();
            Thread.sleep(1000);
        }
    }
}
