package org.example.learn.java.lang.spec.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionUtil {

    public static Object invoke(Object instance, String methodName) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = instance.getClass().getDeclaredMethod(methodName);
        method.setAccessible(true);
        return method.invoke(instance);
    }

    public static Object getField(Object instance, String fieldName) throws IllegalAccessException, NoSuchFieldException {
        Field field = instance.getClass().getField(fieldName);
        field.setAccessible(true);
        return field.get(instance);
    }
}
