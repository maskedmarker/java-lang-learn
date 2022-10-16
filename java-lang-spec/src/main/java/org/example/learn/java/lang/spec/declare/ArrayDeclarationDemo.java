package org.example.learn.java.lang.spec.declare;

/**
 * 数组类型的变量声明
 *
 * 数组声明的风格:
 * int[] intArray java推荐的
 * int intArray[] Java不推荐,C-style array declaration
 *
 */
public class ArrayDeclarationDemo {
    public static void main(String[] args) {
        // 一维数组
        int[] intArray = {1, 2, 3};
        char[] charArray = {'h', 'e'};
        String[] strArray = {"hello", "world"};

        // 二维数组===一维数组的一层嵌套
        int[][] intArrayArray = {{1}, {2}};
        char[][]  charArrayArray = {{'h'}, {'e'}};
        String[][] strArrayArray = {{"hello"}, {"world"}};


        // 多维数组===一维数组的多层嵌套
    }
}
