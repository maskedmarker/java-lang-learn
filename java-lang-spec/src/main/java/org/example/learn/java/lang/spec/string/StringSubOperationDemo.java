package org.example.learn.java.lang.spec.string;

/**
 * @author cjh
 */
public class StringSubOperationDemo {

    public static void main(String[] args) {
        String rawString = "0123456789abcdef";
        StringSubOperationDemo demo = new StringSubOperationDemo();

        String subString = demo.zeroLen(rawString);
        System.out.printf("substring is [%s]%n", subString);
    }

    private String zeroLen(String rawString) {
        return rawString.substring(0, 0);
    }
}
