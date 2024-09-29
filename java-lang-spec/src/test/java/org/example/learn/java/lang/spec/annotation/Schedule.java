package org.example.learn.java.lang.spec.annotation;

import java.lang.annotation.*;

/**
 * 对于repeatable annotation,需要同时为其定义一个annotation container,不然class中关于
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Repeatable(ScheduleAnnotationContainer.class)
public @interface Schedule {

    String jobName();

    String cron();
}
