package org.example.learn.java.io.network.address;

import org.example.learn.java.io.network.utils.NetUtil;
import org.junit.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * java.net.InetAddress#getByName(java.lang.String) 该方法会同步发生dns解析,所以此处有可能发生UnknownHostException异常
 */
public class HostnameTest {

    @Test
    public void test0() throws UnknownHostException {
//        String hostname = "http://www.baidu.com";
        String hostname = "www.baidu.com";
        // 发生了dns解析
        InetAddress inetAddress = InetAddress.getByName(hostname);
        byte[] ipBytes = inetAddress.getAddress();
        String ipString = NetUtil.toIpString(ipBytes);
        System.out.println("ipString = " + ipString);
    }
}
