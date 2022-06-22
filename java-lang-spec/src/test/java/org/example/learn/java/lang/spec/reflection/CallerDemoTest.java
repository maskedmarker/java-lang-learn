package org.example.learn.java.lang.spec.reflection;

import junit.framework.TestCase;

public class CallerDemoTest extends TestCase {

    public void testGetCallerWithoutCallerSensitive() {
        CallerDemo demo = new CallerDemo();
        demo.demoCallerWithoutCallerSensitive();
    }

    public void testGetCallerWithCallerSensitive() {
        CallerDemo demo = new CallerDemo();
        demo.demoCallerWithCallerSensitive();
    }
}