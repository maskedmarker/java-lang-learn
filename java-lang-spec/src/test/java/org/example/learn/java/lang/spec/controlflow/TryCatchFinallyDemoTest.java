package org.example.learn.java.lang.spec.controlflow;

import org.junit.Before;
import org.junit.Test;

public class TryCatchFinallyDemoTest {

    private TryCatchFinallyDemo demo;

    @Before
    public void setUp() {
        demo = new TryCatchFinallyDemo();
    }

    @Test
    public void testNoException() {
        String result = demo.execOrder(1);
        System.out.println("result = " + result);
    }

    @Test
    public void testException() {
        String result = demo.execOrder(0);
        System.out.println("result = " + result);
    }
}