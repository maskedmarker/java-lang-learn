package org.example.learn.java.io.network.address;

import org.example.learn.java.io.network.utils.NetUtil;
import org.junit.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * InetAddress可以返回不同的格式ip信息
 *
 * InetAddress represents an Internet Protocol (IP) address.
 * InetAddress是IP层的地址,所以没有端口的概念.传输层的地址由InetSocketAddress表示
 *
 */
public class InetAddressTest {

    @Test
    public void test0() throws UnknownHostException {
        String hostname = "www.baidu.com";
        InetAddress inetAddress = InetAddress.getByName(hostname);

        // 以字节数组的形式返回
        byte[] address = inetAddress.getAddress();
        String ipString = NetUtil.toIpString(address);
        System.out.println("ipString = " + ipString);
        // 以转换后的字符串返回
        String hostAddress = inetAddress.getHostAddress();
        System.out.println("hostAddress = " + hostAddress);

        String hostName = inetAddress.getHostName();
        System.out.println("hostName = " + hostName);
        String canonicalHostName = inetAddress.getCanonicalHostName();
        System.out.println("canonicalHostName = " + canonicalHostName);
    }

    @Test
    public void test1() throws UnknownHostException {
        InetAddress localHost = InetAddress.getLocalHost();
        System.out.println("localHost = " + localHost);
        System.out.println("localHost.getHostAddress() = " + localHost.getHostAddress());
    }
}
