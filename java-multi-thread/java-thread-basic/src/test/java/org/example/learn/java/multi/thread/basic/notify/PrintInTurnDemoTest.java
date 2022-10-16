package org.example.learn.java.multi.thread.basic.notify;

import junit.framework.TestCase;

public class PrintInTurnDemoTest extends TestCase {

    public void testPrintInTurn() {
        PrintInTurnDemo demo = new PrintInTurnDemo(100);
        demo.printInTurn();
    }
}