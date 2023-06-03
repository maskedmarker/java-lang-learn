package org.example.learn.java.lang.spec.clazz;

import org.junit.Test;

public class ClassLoaderDelegationTest {

    @Test
    public void testDelegate() {
        try {
            Class.forName("NotExistingClassName");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
