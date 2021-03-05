/*
 * ************************************************************
 * 文件：_232_使用栈实现队列.java
 * 模块：MyApplication.app
 * 项目：MyApplication
 * 当前修改时间：2020年12月21日 22:46:26
 * 上次修改时间：2020年12月21日 22:46:25
 * 作者：Havi
 * Copyright (c) 2020
 * ************************************************************
 *
 */

package com.example.leetcode.栈;

import java.util.Stack;
import java.util.Queue;

public class _232_使用栈实现队列 {

    /*
    使用两个栈：inStack / outStack
    1/入队：push到 inStack中
    2/出队：
        1)如果outStack为空，则将inStack所有的元素逐一弹出，push到
        outStack, outStack弹出栈顶元素
        2）如果outStack不为空，outStack弹出栈顶元素
     */

    private Stack<Integer> inStack = new Stack<>();
    private Stack<Integer> outStack = new Stack<>();

    public _232_使用栈实现队列() {
        /*
        也可以在这里初始化
         */
    }

    public void push(int x) {
        inStack.push(x);
    }

    public int pop() {
        checkOutStack();
        return outStack.pop();
    }

    public  int peek() {
        checkOutStack();
        return outStack.peek();
    }

    public boolean empty() {
        return inStack.isEmpty() && outStack.isEmpty();
    }

    private void checkOutStack() {
        if (outStack.isEmpty()) {
            while (!inStack.isEmpty()) {
                outStack.push(inStack.pop());
            }
        }
    }
}
