package org.example.learn.java.lang.spec.string;

public class StringOperationDemo {
    public static void main(String[] args) {
        String mask = "**********";
        String content = "中华人民共和国";
        char c = content.charAt(0);
        String result = c + mask.substring(0, content.length() - 1);
        System.out.println("result = " + result);
    }
}
