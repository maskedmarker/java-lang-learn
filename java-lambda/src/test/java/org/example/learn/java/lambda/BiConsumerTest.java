package org.example.learn.java.lambda;

import org.junit.Assert;
import org.junit.Test;

import java.util.function.BiConsumer;

public class BiConsumerTest {

    private static class User {
        private Integer id;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }
    }

    /**
     * 将实例方法(非方法)应用于BiConsumer时,实例方法的形参只能有一个
     */
    @Test
    public void test0() {
        // BiConsumer用于实例方法(非方法)时,编译器就认定你BiConsumer.accept的第一arg是实例对象,第二个arg是实例方法的唯一实参
        BiConsumer<User, Integer> idSetter = User::setId;
        User user = new User();
        idSetter.accept(user, 1);
        Assert.assertEquals(1, (int) user.getId());
    }
}
