package org.example.learn.java.io.file.charset;

import java.nio.charset.Charset;

/**
 * jvm的默认字符编码和系统参数file.encoding等值
 *
 * 添加VM option来演示 -Dfile.encoding=gbk/utf-8
 */
public class DefaultCharsetDemo {

    public static final String SYSTEM_PROPERTY_KEY_FILE_ENCODING = "file.encoding";

    public static void main(String[] args) {
        Charset defaultCharset = Charset.defaultCharset();
        System.out.println("defaultCharset = " + defaultCharset);
        String fileEncoding = System.getProperty(SYSTEM_PROPERTY_KEY_FILE_ENCODING);
        System.out.println("fileEncoding = " + fileEncoding);
    }
}
