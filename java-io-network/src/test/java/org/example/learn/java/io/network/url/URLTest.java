package org.example.learn.java.io.network.url;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class URLTest {

    /**
     *
     */
    @Test
    public void test() throws Exception {
        String urlString = "https://example.com";
        URL url = new URL(urlString);
        // Socket.connect()
        URLConnection connection = url.openConnection();
        InputStream inputStream = connection.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

    }

    /**
     * Content-Encoding指的是当前http消息体的内容的编码方式.消息体可能已经压缩过了,此时Content-Encoding就是压缩算法的名称列表;
     * Content-Type才是原始消息体的类型(肯能包含了编码方式,比如 content-type: text/html; charset=utf-8)
     * The original content of the message is described by the Content-Type header, whereas the Content-Encoding refers to the current state.
     * If the original resource is encoded, for example, pre-compressed, then this will not be reflected by the HTTP Content-Encoding header.
     */
    @Test
    public void testContentEncoding() throws Exception {
        String urlString = "https://www.baidu.com/";
        URL url = new URL(urlString);
        URLConnection connection = url.openConnection();
        InputStream inputStream = connection.getInputStream();
        // contentEncoding不是编码
        // The HTTP headers Content-encoding is used to compress the media type. Content-Encoding: gzip | compress | deflate | br| identity
        // The original content of the message is described by the Content-Type header, whereas the Content-Encoding refers to the current state.
//        String contentEncoding = connection.getContentEncoding();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

    }
}
