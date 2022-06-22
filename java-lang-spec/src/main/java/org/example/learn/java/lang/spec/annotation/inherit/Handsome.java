package org.example.learn.java.lang.spec.annotation.inherit;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@NonInheritableTrait(name = "英俊指数")
public @interface Handsome {

    String level();
}
