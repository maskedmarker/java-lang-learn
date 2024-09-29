package org.example.learn.java.lang.spec.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ScheduleAnnotationContainer {

    Schedule[] value();
}
