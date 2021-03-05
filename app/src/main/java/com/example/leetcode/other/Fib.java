/*
 * ************************************************************
 * 文件：Fib.java
 * 模块：app
 * 项目：MyApplication
 * 当前修改时间：2020年12月03日 09:20:53
 * 上次修改时间：2020年12月01日 17:41:30
 * 作者：Havi
 * Copyright (c) 2020
 * ************************************************************
 *
 */

package com.example.leetcode.other;

public class Fib {
    public Fib(int i) {

    }

    /*
    递归调用实现斐波那契额数列
     */
    public static int fib1(int n) {
        if (n <= 1) return n;
        return fib1(n) + fib1(n-1);
    }

    /*
    暴力解决
    1，1，2，3，5，8，13。。。。
    其实跟n无关；只是标记需要执行几次
     */
    static int fib(int n) {
        if (n <= 1) return n;
        int first = 0;
        int second = 1;
        for (int i = 0; i < n-1; i++) {
            int sum = first + second;
            first = second;
            second = sum;
        }
        return second;
    }
}
