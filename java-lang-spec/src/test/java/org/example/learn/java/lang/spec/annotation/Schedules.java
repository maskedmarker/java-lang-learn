package org.example.learn.java.lang.spec.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Schedules {

    Schedule[] value();
}
