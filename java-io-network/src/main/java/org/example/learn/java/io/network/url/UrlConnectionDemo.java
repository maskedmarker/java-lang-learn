package org.example.learn.java.io.network.url;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * In general, creating a connection to a URL is a multistep process
 */
public class UrlConnectionDemo {
    public static void main(String[] args) {
        String urlString = "https://www.baidu.com";
        URLConnection urlConnection;
        BufferedReader bufferedReader = null;
        try {
            URL url = new URL(urlString);

            // The connection object is created by invoking the openConnection method on a URL.
            // It should be noted that a URLConnection instance does not establish the actual network connection on creation
            urlConnection = url.openConnection(); // 只是创建URLConnection对象
            Class<? extends URLConnection> urlConnectionClass = urlConnection.getClass();
            System.out.println("urlConnectionClass = " + urlConnectionClass);

            //The setup parameters and general request properties are manipulated.
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);
            urlConnection.setUseCaches(false);
            urlConnection.setIfModifiedSince(1000 * 60);
            // for example: if used in an applet and a URL request is made that requires a username/password this signifies that the system GUI to ask the user for input can be called
            urlConnection.setAllowUserInteraction(false); // 比如要用户输入密码,操作系统会提示

            //The actual connection to the remote object is made, using the connect method.
            urlConnection.connect();

            //The remote object becomes available. The header fields and the contents of the remote object can be accessed.
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
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    // ignore
                }
            }
        }
    }

}
