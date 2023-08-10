package org.example.learn.java.io.network.address;

import org.junit.Test;

import java.net.InetAddress;
import java.net.InetSocketAddress;

/**
 * 传输层的地址由InetSocketAddress表示,即ip+port
 */
public class InetSocketAddressTest {

    @Test
    public void test0() {
        String hostname = "www.baidu.com";
        int port = 80;
        InetSocketAddress inetSocketAddress = new InetSocketAddress(hostname, port);
        InetAddress inetAddress = inetSocketAddress.getAddress();
        int port1 = inetSocketAddress.getPort();
        String hostName = inetSocketAddress.getHostName();
        System.out.println("inetAddress = " + inetAddress);
        System.out.println("hostName = " + hostName);
        System.out.println("port1 = " + port1);
    }

}
