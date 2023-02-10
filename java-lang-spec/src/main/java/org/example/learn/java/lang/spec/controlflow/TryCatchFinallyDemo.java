package org.example.learn.java.lang.spec.controlflow;

public class TryCatchFinallyDemo {

    /**
     * 测试控制流
     * @param i 当i为0时,发生异常
     * @return 执行结果
     */
    public String execOrder(int i) {
        String result;
        try {
            int r = 1 / i;
            result = "set by try";
            return result;
        } catch (Exception e) {
            result = "set by exception";
            return result;
        } finally {
            result = "set by finally";
            return result;
        }
    }
}


/*

return result对应2个字节码(aload_2/areturn),即aload_n将要放回的值放到方法栈顶,areturn将栈顶值返回给调用方
javac编译后的字节码文件中没有java文件的第15行,3个return公用一个areturn,该areturn在字节码最后

    Code:
      stack=2, locals=6, args_size=2
         0: aconst_null
         1: astore_2
         2: iconst_1
         3: iload_1
         4: idiv
         5: istore_3
         6: ldc           #2                  // String set by try
         8: astore_2
         9: aload_2
        10: astore        4
        12: ldc           #3                  // String set by finally
        14: astore_2
        15: aload_2
        16: areturn
        17: astore_3
        18: ldc           #5                  // String set by exception
        20: astore_2
        21: aload_2
        22: astore        4
        24: ldc           #3                  // String set by finally
        26: astore_2
        27: aload_2
        28: areturn
        29: astore        5
        31: ldc           #3                  // String set by finally
        33: astore_2
        34: aload_2
        35: areturn
      Exception table:
         from    to  target type
             2    12    17   Class java/lang/Exception
             2    12    29   any
            17    24    29   any
            29    31    29   any
      LineNumberTable:
        line 11: 0
        line 13: 2
        line 14: 6
        line 15: 9
        line 20: 12
        line 21: 15
        line 16: 17
        line 17: 18
        line 18: 21
        line 20: 24
        line 21: 27
        line 20: 29
        line 21: 34
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            6      11     3     r   I
           18      11     3     e   Ljava/lang/Exception;
            0      36     0  this   Lorg/example/learn/java/lang/spec/controlflow/TryCatchFinallyDemo;
            0      36     1     i   I
            2      34     2 result   Ljava/lang/String;
* */
