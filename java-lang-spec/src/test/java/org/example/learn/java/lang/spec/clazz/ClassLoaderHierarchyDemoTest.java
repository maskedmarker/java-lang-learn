package org.example.learn.java.lang.spec.clazz;

import junit.framework.TestCase;

public class ClassLoaderHierarchyDemoTest extends TestCase {

    private ClassLoaderHierarchyDemo demo = new ClassLoaderHierarchyDemo();

    public void testDisplayBuiltInClassLoaders() {
        demo.displayBuiltInClassLoaders();
    }

    public void testSystemClassLoader() {
        demo.systemClassLoader();
    }
}