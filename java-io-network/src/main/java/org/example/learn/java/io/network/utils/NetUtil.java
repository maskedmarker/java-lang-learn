package org.example.learn.java.io.network.utils;

import java.net.UnknownHostException;
import java.util.Arrays;

public class NetUtil {

    public static String toIpString(byte[] ipBytes) {
        String[] ipInts = new String[ipBytes.length];
        for (int i = 0; i < ipBytes.length; i++) {
            ipInts[i] = Integer.toString(0x000000FF & ipBytes[i]);
        }

        return String.join(".", Arrays.asList(ipInts));
    }
}
