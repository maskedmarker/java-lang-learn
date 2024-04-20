package org.example.learn.java.lang.spec.polymorphism;

import org.junit.Test;

/**
 *
 * 理解的关键点在于:java的多态
 * 在 Java 中，多态性是在字节码层面通过虚方法表（Virtual Method Table）来实现的.
 * 虚方法表是 Java 虚拟机在加载类的时候创建的一张表，用于存储类的方法信息，包括方法的地址等。
 *
 */
public class Ch01PolyTest {

    private static class ClassA{
        public void method1() {
            System.out.println(this.getClass() + " method1 in ClassA");
        }

        public void method2() {
            System.out.println(this.getClass() + " method2 in ClassA");
        }

        public void method3() {
            System.out.println(this.getClass() + " method3 in ClassA");
            method1();
        }

        public void method4() {
            System.out.println(this.getClass() + " method4 in ClassA");
            method2();
        }
    }

    private static class ClassB extends ClassA {
        public void method1() {
            System.out.println(this.getClass() + " method1 in ClassB");
        }

        public void method3() {
            System.out.println(this.getClass() + " method3 in ClassB");
            method1(); // 法引用指向的是 com.example.learn.bytecode.cglib.Ch04SelfInvocationTest.ClassB.method1
        }

        public void method4() {
            System.out.println(this.getClass() + " method4 in ClassB");
            method2(); // 方法引用指向的是 com.example.learn.bytecode.cglib.Ch04SelfInvocationTest.ClassA.method2
        }

        public void method5() {
            System.out.println(this.getClass() + " method5 in ClassB");
            super.method1(); // 方法引用指向的是 com.example.learn.bytecode.cglib.Ch04SelfInvocationTest.ClassA.method1
        }
    }

    private static class ClassC extends ClassA {
        public void method1() {
            System.out.println(this.getClass() + " method1 in ClassC");
        }

        public void method3() {
            System.out.println(this.getClass() + " method3 in ClassC");
            super.method3();
        }

        public void method4() {
            System.out.println(this.getClass() + " method4 in ClassC");
            super.method4();
        }
    }

    @Test
    public void test0() {
        ClassA a = new ClassA();
        a.method3();
        a.method4();
        System.out.println("----------------------------------------------");

        ClassB b = new ClassB();
        b.method3();
        b.method4();
        System.out.println("----------------------------------------------");

        ClassC c = new ClassC();
        c.method3(); // method3 in ClassC  -> method3 in ClassA -> method1 in ClassC
        c.method4(); // method4 in ClassC -> method4 in ClassA -> method2 in ClassA
    }
}
