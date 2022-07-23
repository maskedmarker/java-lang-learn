package org.example.learn.java.io.network.url;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

/**
 * 演示server返回http错误响应
 */
public class HttpsUrlConnectionErrorResponseDemo {
    public static void main(String[] args) {
        String notFoundHttpsUrl = "https://www.baidu.com//sfasdfa";
        HttpsUrlConnectionErrorResponseDemo demo = new HttpsUrlConnectionErrorResponseDemo();
        demo.doRequest(notFoundHttpsUrl, null);
    }

    /**
     * HttpsURLConnection类默认使用http的get方法,post方法需要显式声明
     * @param httpsUrl https的url
     */
    public void doRequest(String httpsUrl, String method) {
        URLConnection urlConnection = null;
        try {
            URL url = new URL(httpsUrl);

            urlConnection = url.openConnection(); // 只是创建URLConnection对象
            HttpsURLConnection httpsUrlConnection = (HttpsURLConnection) urlConnection;
            if (method != null) {
                httpsUrlConnection.setRequestMethod(method);
            }

            manipulateSetupParameters(httpsUrlConnection);
            manipulateRequestProperties(httpsUrlConnection);

            // connect()方法调用返回时,已经创建了与server的tcp连接,此时并没有将http协议的request发送给server
            urlConnection.connect();

            accessRemoteObject(httpsUrlConnection);
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
    }

    // request相关参数
    private void manipulateRequestProperties(URLConnection urlConnection) {
        urlConnection.setUseCaches(false);
        urlConnection.setIfModifiedSince(1000 * 60);
        urlConnection.addRequestProperty("Accept", "text/*");
    }

    /**
     * 当server的http响应码是异常码时,java.net.URLConnection#getInputStream()方法将会抛异常(java.io.FileNotFoundException)
     * 必须使用java.net.HttpURLConnection#getErrorStream()方法
     */
    private void accessRemoteObject(HttpsURLConnection urlConnection) throws IOException {
        // 当getResponseCode()方法调用返回时,才会将http request的header和body发送给server,并等待和解析response.
        // 对于response code=404/410的,会new一个FileNotFoundException,等待调用getInputStream()方法时抛出
        int responseCode = urlConnection.getResponseCode();
        InputStream inputStream;
        if (HttpURLConnection.HTTP_OK == responseCode) {
            inputStream = urlConnection.getInputStream(); // 正常情况下,对应NetworkClient.serverInput
        } else {
            inputStream = urlConnection.getErrorStream(); // 底层也是NetworkClient.serverInput
        }
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
        String readContent;
        while ((readContent = bufferedReader.readLine()) != null) {
            System.out.print(readContent);
        }
    }
}
