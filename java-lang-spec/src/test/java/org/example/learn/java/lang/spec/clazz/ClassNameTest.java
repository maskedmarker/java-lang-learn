package org.example.learn.java.lang.spec.clazz;

import org.junit.Test;

import java.util.Observable;
import java.util.Observer;

public class ClassNameTest {

    private static Observer anonymousInnerClassInstance = new Observer() {
        @Override
        public void update(Observable o, Object arg) {
            // noop
        }
    };

    /**
     * ordinary class
     */
    @Test
    public void test0() {
        Class<Object> classInstance = Object.class;
        String name = classInstance.getName();
        System.out.println("name = " + name);

        String simpleName = classInstance.getSimpleName();
        System.out.println("simpleName = " + simpleName);

        String canonicalName = classInstance.getCanonicalName();
        System.out.println("canonicalName = " + canonicalName);

        String typeName = classInstance.getTypeName();
        System.out.println("typeName = " + typeName);
    }

    /**
     * inner class
     */
    @Test
    public void test1() {
        Class<Thread.State> classInstance = Thread.State.class;
        String name = classInstance.getName();
        System.out.println("name = " + name);

        String simpleName = classInstance.getSimpleName();
        System.out.println("simpleName = " + simpleName);

        String canonicalName = classInstance.getCanonicalName();
        System.out.println("canonicalName = " + canonicalName);

        String typeName = classInstance.getTypeName();
        System.out.println("typeName = " + typeName);
    }

    /**
     * anonymous inner class
     */
    @Test
    public void test2() {
        Class<? extends Observer> classInstance = ClassNameTest.anonymousInnerClassInstance.getClass();
        String name = classInstance.getName();
        System.out.println("name = " + name);

        String simpleName = classInstance.getSimpleName();
        System.out.println("simpleName = " + simpleName);

        String canonicalName = classInstance.getCanonicalName();
        System.out.println("canonicalName = " + canonicalName);

        String typeName = classInstance.getTypeName();
        System.out.println("typeName = " + typeName);
    }
}
