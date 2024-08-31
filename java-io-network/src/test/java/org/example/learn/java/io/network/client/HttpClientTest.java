package org.example.learn.java.io.network.client;

import org.junit.Test;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 * jdk8中的httpClient实现是sun.net.www.http.HttpClient
 * sun.net.www.http.HttpClient在创建时,会在其构造函数中创建Socket,并试图与url中的目标ip+port建立连接,
 * 如果建立连接失败,则抛出异常,同时sun.net.www.http.HttpClient创建失败
 * 连接建立后,HttpClient的serverSocket/serverOutput属性就已经完全可用了;属性需要在必要时HttpClient才会为其赋值(场景只有在解析http的header时)
 * 先解析http响应(parseHTTP),基于http头信息再做后续操作
 *
 *
 *
 *
 * sun.net.www.protocol.http.HttpURLConnection#writeRequests()
 *  先为HttpURLConnection的header属性写入header的key-value,然后在将header写入到socket中,即调用
 *  sun.net.www.http.HttpClient#writeRequests(sun.net.www.MessageHeader, sun.net.www.http.PosterOutputStream, boolean)
 *
 *  sun.net.www.http.HttpClient#parseHTTP(sun.net.www.MessageHeader, sun.net.ProgressSource, sun.net.www.protocol.http.HttpURLConnection)
 */
public class HttpClientTest {

    /**
     * 不推荐直接创建httpClient, 而是通过HttpURLConnection间接使用httpClient;
     * 或者使用apache httpClient
     */
    @Test
    public void test0() throws Exception {
/*        String urlString = "http://localhost:9081/myapp/nonExist";
        URL url = new URL(urlString);
        sun.net.www.protocol.http.HttpURLConnection  connection = (sun.net.www.protocol.http.HttpURLConnection ) url.openConnection();

        int connectTimeout = 2000;
        HttpClient httpClient = HttpClient.New(url, Proxy.NO_PROXY, connectTimeout, connection);

        // 原生的httpClient需要写入http请求,然后解析http响应
       MessageHeader messageHeader = new MessageHeader();
        messageHeader.prepend("GET" + " " + "/myapp/hello/echo?msg=zhangSan" + " " + "HTTP/1.1", (String)null);
        httpClient.writeRequests(null, null);
        httpClient.parseHTTP(null, null, connection);*/
    }


    /**
     * jdk中,在HttpURLConnection触发连接时创建sun.net.www.http.HttpClient
     *
     */
    @Test
    public void testHeaders() throws Exception {
        String urlString = "http://localhost:9080/myapp/hello/echo?msg=zhangSan";
        URL url = new URL(urlString);
        // HttpURLConnection实例是初始状态,并没有与网络建立连接
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        // 因为要获取响应的header,肯定要先发送请求,要发送请求必须要建立网络连接
        // 如果要获取http报文header,就要先建立tcp连接,向tcp写入http请求报文,从socket中读取server的inputStream,然后解析inputStream
        Map<String, List<String>> headerFields = connection.getHeaderFields();
        for (Map.Entry<String, List<String>> headerItem : headerFields.entrySet()) {
            String headerName = headerItem.getKey();
            List<String> headerValues = headerItem.getValue();
            System.out.println(String.format("%s = %s \r\n", headerName, headerValues));
        }
    }

    @Test
    public void testHeaders2() throws Exception {
        String urlString = "http://localhost:9080/myapp/nonExist";
        URL url = new URL(urlString);
        // HttpURLConnection实例是初始状态,并没有与网络建立连接
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        // 因为要获取响应的header,肯定要先发送请求,要发送请求必须要建立网络连接
        // 如果要获取http报文header,就要先建立tcp连接,向tcp写入http请求报文,从socket中读取server的inputStream,然后解析inputStream
        Map<String, List<String>> headerFields = connection.getHeaderFields();
        for (Map.Entry<String, List<String>> headerItem : headerFields.entrySet()) {
            String headerName = headerItem.getKey();
            List<String> headerValues = headerItem.getValue();
            System.out.println(String.format("%s = %s \r\n", headerName, headerValues));
        }
    }

    @Test
    public void testHeaders3() throws Exception {
        String urlString = "http://localhost:9081/myapp/nonExist";
        URL url = new URL(urlString);
        // HttpURLConnection实例是初始状态,并没有与网络建立连接
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        // 此时触发创建httpClient,并试图与server建立tcp连接,并发送http请求报文
        connection.connect();
        // 如果是跳过调用connect,直接getHeaderFields是不会抛异常(因为IOException异常被吞了),返回的是一个空map
        Map<String, List<String>> headerFields = connection.getHeaderFields();
        for (Map.Entry<String, List<String>> headerItem : headerFields.entrySet()) {
            String headerName = headerItem.getKey();
            List<String> headerValues = headerItem.getValue();
            System.out.println(String.format("%s = %s \r\n", headerName, headerValues));
        }
    }
}
