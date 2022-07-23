package org.example.learn.java.io.network.url;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

/**
 * In general, creating a connection to a URL is a multistep process
 * UrlConnection的一般使用操作步骤
 *
 * UrlConnection的子类很多,不仅包含http/https协议的,还包含jar协议的
 *
 * stackoverflow有个非常好的问答,建议参考学习
 * https://stackoverflow.com/questions/2793150/how-to-use-java-net-urlconnection-to-fire-and-handle-http-requests/2793153#2793153
 */
public class UrlConnectionDemo {
    public static void main(String[] args) {
        String urlString = "https://www.baidu.com";
        UrlConnectionDemo demo = new UrlConnectionDemo();
        demo.doFoo(urlString);
    }

    private void doFoo(String httpsUrlString) {
        URLConnection urlConnection = null;
        try {
            URL url = new URL(httpsUrlString);

            // The connection object is created by invoking the openConnection method on a URL.
            // It should be noted that a URLConnection instance does not establish the actual network connection on creation
            urlConnection = url.openConnection(); // 只是创建URLConnection对象
            System.out.println("urlConnectionClass = " + urlConnection.getClass());

            //The setup parameters and general request properties are manipulated.
            manipulateSetupParameters(urlConnection);
            manipulateRequestProperties(urlConnection);

            //The actual connection to the remote object is made, using the connect method.
            urlConnection.connect();

            //The remote object becomes available. The header fields and the contents of the remote object can be accessed.
            accessRemoteObject(urlConnection);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                try {
                    urlConnection.getOutputStream().close();
                } catch (IOException e) {
                    // ignore
                }
            }
        }
    }

    // connection相关参数
    private void manipulateSetupParameters(URLConnection urlConnection) {
        urlConnection.setConnectTimeout(1000);
        urlConnection.setReadTimeout(2000);
        urlConnection.setDoInput(true);
        urlConnection.setDoOutput(true);

        // for example: if used in an applet and a URL request is made that requires a username/password this signifies that the system GUI to ask the user for input can be called
        urlConnection.setAllowUserInteraction(false); // 比如要用户输入密码,操作系统会提示
    }

    // request相关参数
    private void manipulateRequestProperties(URLConnection urlConnection) {
        urlConnection.setUseCaches(false);
        urlConnection.setIfModifiedSince(1000 * 60);
        urlConnection.addRequestProperty("Accept", "text/*");
    }

    private void accessRemoteObject(URLConnection urlConnection) throws IOException {
        BufferedReader bufferedReader;
        InputStream inputStream = urlConnection.getInputStream();
        bufferedReader = new BufferedReader(new InputStreamReader(new BufferedInputStream(inputStream), StandardCharsets.UTF_8));
        String readContent;
        while ((readContent = bufferedReader.readLine()) != null) {
            System.out.print(readContent);
        }

        System.out.println();
        long ifModifiedSince = urlConnection.getIfModifiedSince();
        System.out.println("ifModifiedSince = " + ifModifiedSince);
        long lastModified = urlConnection.getLastModified();
        System.out.println("lastModified = " + lastModified);
    }
}
