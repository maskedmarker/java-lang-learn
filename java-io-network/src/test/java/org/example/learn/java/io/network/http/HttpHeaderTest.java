package org.example.learn.java.io.network.http;

import org.junit.Test;

import javax.net.ssl.HttpsURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

public class HttpHeaderTest {

    /**
     * 只能获得部分的http header???
     * 获取到的是全部的header.因为用java调用时,server返回的header确实比较少.
     */
    @Test
    public void testHeaders() throws Exception {
        String urlString = "https://www.baidu.com/";
        URL url = new URL(urlString);
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        Map<String, List<String>> headerFields = connection.getHeaderFields();
        for (Map.Entry<String, List<String>> headerItem : headerFields.entrySet()) {
            String headerName = headerItem.getKey();
            List<String> headerValues = headerItem.getValue();
            System.out.println(String.format("%s = %s \r\n", headerName, headerValues));
        }
    }

    /**
     * http HEAD method
     */
    @Test
    public void testHead() throws Exception {
        String urlString = "https://www.baidu.com/";
        URL url = new URL(urlString);
        URLConnection connection = url.openConnection();
        Map<String, List<String>> headerFields = connection.getHeaderFields();
        for (Map.Entry<String, List<String>> headerItem : headerFields.entrySet()) {
            String headerName = headerItem.getKey();
            List<String> headerValues = headerItem.getValue();
            System.out.printf("%s=%s%n", headerName, headerValues);
        }
    }

    /**
     * Content-Encoding指的是当前http消息体的内容的编码方式.消息体可能已经压缩过了,此时Content-Encoding就是压缩算法的名称列表;
     * Content-Type才是原始消息体的类型(肯能包含了编码方式,比如 content-type: text/html; charset=utf-8)
     * The original content of the message is described by the Content-Type header, whereas the Content-Encoding refers to the current state.
     * If the original resource is encoded, for example, pre-compressed, then this will not be reflected by the HTTP Content-Encoding header.
     */
    @Test
    public void testContentXxx() throws Exception {
        String urlString = "https://www.baidu.com/";
        URL url = new URL(urlString);
        URLConnection connection = url.openConnection();
        String contentEncoding = connection.getContentEncoding(); //底层用的是 content-encoding
        System.out.println("contentEncoding = " + contentEncoding);
        contentEncoding = connection.getHeaderField("Content-Encoding");
        System.out.println("contentEncoding = " + contentEncoding);

        String contentType = connection.getContentType();
        System.out.println("contentType = " + contentType);
    }

}
