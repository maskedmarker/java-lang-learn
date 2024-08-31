package org.example.learn.java.io.network.url;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.stream.Stream;

/**
 * 注意看javadoc中关于URLConnection的解释说明
 * URLConnection objects go through two phases: first they are created, then they are connected.
 * After being created, and before being connected, various options can be specified (e.g., doInput and UseCaches).
 * After connecting, it is an error to try to set them.
 * Operations that depend on being connected,like getContentLength, will implicitly perform the connection, if necessary.
 *
 */
public class URLConnectionTest {

    /**
     * URL对象的重要属性是URLStreamHandler,URLStreamHandler用来处理具体的通讯.
     * 每个URL对象在实例化的时候,就会同步生成一个URLStreamHandler对象.
     *
     * URL#openConnection()的调用并不会触发建立通讯层的联接,只有调用URLConnection.connect()才会.如下是javadoc中的描述
     * It should be noted that a URLConnection instance does not establish the actual network connection on creation.This will happen only when calling URLConnection.connect().
     *
     * java.net.URLConnection#getInputStream()/java.net.URLConnection#getOutputStream()会间接触发URLConnection.connect()
     */
    @Test
    public void test() throws Exception {
        String urlString = "https://example.com";
        URL url = new URL(urlString);
        // 并不会立即建立通讯联接
        URLConnection connection = url.openConnection();
        // 因为要获取响应的server的inputStream,肯定要先建立网络连接,然后在发送http请求,才能拿到server输出的内容
        // 会间接触发URLConnection.connect()
        // 如果是http请求,该方法在返回InputStream前,已经将stream中的报文头读取消费掉了,只剩下报文体
        // header相关的byte本来就与url对应的资源无关,仅仅是通讯中协议附加的,返回给用户的stream就应该屏蔽掉header相关的byte,仅仅包含资源本身
        InputStream inputStream = connection.getInputStream();

        // 提前知道网站的编码是utf-8
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
        Stream<String> lines = bufferedReader.lines();
        lines.forEach(System.out::println);
    }

    @Test
    public void test1() throws Exception {
        String urlString = "https://example.com";
        URL url = new URL(urlString);
        // 从url可以看出是http协议,所以可以放心cast
        // 默认HttpURLConnection的method是GET
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        // getInputStream还会触发将请求写入到socket中
        InputStream inputStream = connection.getInputStream();

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
        Stream<String> lines = bufferedReader.lines();
        lines.forEach(System.out::println);
    }

    /**
     * HttpURLConnection默认的method是GET,如果要使用POST方法,需要手动设置
     */
    @Test
    public void test2() throws Exception {
        String urlString = "https://passport.baidu.com/v2/api/?login";
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        // 因为要在http请求体中携带数据,所以需要output
        connection.setDoOutput(true);
        // TODO 设置form表单
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        connection.setRequestProperty("Accept", "*");
        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = "username=1111111&password=222222".getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
            Stream<String> lines = bufferedReader.lines();
            lines.forEach(System.out::println);
        } else {
            System.out.println("responseCode = " + responseCode);
        }
    }
}
