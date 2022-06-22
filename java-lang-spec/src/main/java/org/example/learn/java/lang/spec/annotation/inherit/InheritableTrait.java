package org.example.learn.java.lang.spec.annotation.inherit;

import java.lang.annotation.*;

/**
 * 可以遗传的特征
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
public @interface InheritableTrait {

    String name();
}
