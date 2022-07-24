package org.example.learn.java.io.network.url;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

/**
 * In general, creating a connection to a URL is a multistep process
 * HttpsUrlConnection的一般使用操作步骤
 * stackoverflow有个关于HttpsUrlConnection的非常好的问答,包含了多次请求中cookie的处理,建议参考学习
 * https://stackoverflow.com/questions/2793150/how-to-use-java-net-urlconnection-to-fire-and-handle-http-requests/2793153#2793153
 */
public class HttpsUrlConnectionBasicDemo {
    public static void main(String[] args) {
        String httpsUrl = "https://www.baidu.com";
        HttpsUrlConnectionBasicDemo demo = new HttpsUrlConnectionBasicDemo();
        demo.doRequest(httpsUrl, null);
        demo.doRequest(httpsUrl, "POST");
    }

    /**
     * HttpsURLConnection类默认使用http的get方法,post方法需要显式声明
     * @param httpsUrl https的url
     */
    public void doRequest(String httpsUrl, String method) {
        HttpsURLConnection httpsUrlConnection = null;
        try {
            URL url = new URL(httpsUrl);

            httpsUrlConnection = (HttpsURLConnection) url.openConnection(); // 只是创建URLConnection对象
            if (method != null) {
                httpsUrlConnection.setRequestMethod(method);
            }

            manipulateSetupParameters(httpsUrlConnection);
            manipulateRequestProperties(httpsUrlConnection);

            // connect()方法调用返回时,已经创建了与server的tcp连接,此时并没有将http协议的request发送给server
            httpsUrlConnection.connect();

            // 当getInputStream()方法调用返回时,才会将http request的header和body发送给server
            accessRemoteObject(httpsUrlConnection);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (httpsUrlConnection != null) {
                try {
                    httpsUrlConnection.getOutputStream().close();
                } catch (IOException e) {
                    // ignore
                }
            }
        }
    }

    // connection相关参数
    private void manipulateSetupParameters(HttpsURLConnection urlConnection) {
        urlConnection.setConnectTimeout(1000);
        urlConnection.setReadTimeout(2000);
        urlConnection.setDoInput(true);
        urlConnection.setDoOutput(true);

        urlConnection.setAllowUserInteraction(false); // 比如要用户输入密码,操作系统会提示
    }

    // request相关参数
    private void manipulateRequestProperties(HttpsURLConnection urlConnection) {
        urlConnection.setUseCaches(false);
        urlConnection.setIfModifiedSince(1000 * 60);
        urlConnection.addRequestProperty("Accept", "text/*");
    }

    private void accessRemoteObject(HttpsURLConnection httpsUrlConnection) throws IOException {
        // 当getInputStream()方法调用返回时,才会将http request的header和body发送给server,并等待和解析response.
        // 能够触发connection发送报文的方法不仅仅只有getInputStream
        InputStream inputStream = httpsUrlConnection.getInputStream();

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8)); //写死编码
        String readContent;
        while ((readContent = bufferedReader.readLine()) != null) {
            System.out.print(readContent);
        }

        System.out.println();
        String requestMethod = httpsUrlConnection.getRequestMethod(); // 默认使用的是GET方法
        System.out.println("requestMethod = " + requestMethod);
    }
}
