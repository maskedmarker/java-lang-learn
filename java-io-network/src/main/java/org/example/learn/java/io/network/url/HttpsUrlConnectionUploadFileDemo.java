package org.example.learn.java.io.network.url;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;

/**
 * HttpsUrlConnection上传文件的一般使用操作步骤
 *
 * stackoverflow有个关于HttpsUrlConnection的非常好的问答,包含了多次请求中cookie的处理,建议参考学习
 * https://stackoverflow.com/questions/2793150/how-to-use-java-net-urlconnection-to-fire-and-handle-http-requests/2793153#2793153
 *
 * TODO 先占个位置,后续优化代码
 */
public class HttpsUrlConnectionUploadFileDemo {
    public static void main(String[] args) {
        String uploadUrl = "https://www.baidu.com";
        HttpsUrlConnectionUploadFileDemo demo = new HttpsUrlConnectionUploadFileDemo();
        String formDataCharset = "UTF-8";
        try {
            demo.upload(uploadUrl, formDataCharset);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void upload(String url, String charset) throws IOException {
        String paramValue = "value";
        File textFile = new File("/path/to/file.txt");
        File binaryFile = new File("/path/to/file.bin");
        String boundary = Long.toHexString(System.currentTimeMillis()); // Just generate some unique random value.
        String CRLF = "\r\n"; // Line separator required by multipart/form-data.
        URLConnection connection = new URL(url).openConnection();
        connection.setDoOutput(true);
        connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);

        try (
                OutputStream output = connection.getOutputStream();
                PrintWriter writer = new PrintWriter(new OutputStreamWriter(output, charset), true);
        ) {
            // Send normal param.
            writer.append("--" + boundary).append(CRLF);
            writer.append("Content-Disposition: form-data; name=\"paramName\"").append(CRLF);
            writer.append("Content-Type: text/plain; charset=" + charset).append(CRLF);
            writer.append(CRLF).append(paramValue).append(CRLF).flush();

            // Send text file.
            writer.append("--" + boundary).append(CRLF);
            writer.append("Content-Disposition: form-data; name=\"textFile\"; filename=\"" + textFile.getName() + "\"").append(CRLF);
            writer.append("Content-Type: text/plain; charset=" + charset).append(CRLF); // Text file itself must be saved in this charset!
            writer.append(CRLF).flush();
            Files.copy(textFile.toPath(), output);
            output.flush(); // Important before continuing with writer!
            writer.append(CRLF).flush(); // CRLF is important! It indicates end of boundary.

            // Send binary file.
            writer.append("--" + boundary).append(CRLF);
            writer.append("Content-Disposition: form-data; name=\"binaryFile\"; filename=\"" + binaryFile.getName() + "\"").append(CRLF);
            writer.append("Content-Type: " + URLConnection.guessContentTypeFromName(binaryFile.getName())).append(CRLF);
            writer.append("Content-Transfer-Encoding: binary").append(CRLF);
            writer.append(CRLF).flush();
            Files.copy(binaryFile.toPath(), output);
            output.flush(); // Important before continuing with writer!
            writer.append(CRLF).flush(); // CRLF is important! It indicates end of boundary.

            // End of multipart/form-data.
            writer.append("--" + boundary + "--").append(CRLF).flush();
        }
    }
}
