package org.example.learn.java.io.file.uri;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import java.net.URISyntaxException;

public class UriDemoTest{

    private UriDemo demo;

    @Before
    public void setup() throws URISyntaxException {
        String uri = "jar:file:/C:/Program%20Files/test.jar!/foo/bar?queryString=123#fragment";
        this.demo = new UriDemo(uri);
    }

    @Test
    public void testDemoScheme() {
        this.demo.demoScheme();
    }

    @Test
    public void testDemoSchemeSpecificPart() {
        this.demo.demoSchemeSpecificPart();
    }

    @Test
    public void demoPath() {
        this.demo.demoPath();
    }
}