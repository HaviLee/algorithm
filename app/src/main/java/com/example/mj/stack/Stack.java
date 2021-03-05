/*
 * ************************************************************
 * 文件：Stack.java
 * 模块：MyApplication.app
 * 项目：MyApplication
 * 当前修改时间：2020年12月18日 09:39:05
 * 上次修改时间：2020年12月18日 09:39:05
 * 作者：Havi
 * Copyright (c) 2020
 * ************************************************************
 *
 */

package com.example.mj.stack;

import com.example.mj.ArrayList.ArrayList;
import com.example.mj.ArrayList.List;

/**
 * 有两种解决思路：
 * 1、继承之前写的线性表；会破坏接口封装性
 * 2、使用组合方式，在栈的内部初始化一个ArrayList对象
 * @param <E>
 */
public class Stack<E> {

    private List<E> list = new ArrayList<E>();
    /*
    获取栈中元素的个数
     */
    public int size() {
        return list.size();
    }

    /*
    判断栈的元素是不是空的
     */
    public boolean isEmpty() {
        return list.isEmpty();
    }

    /*
    入栈操作
     */
    public void push(E element) {
        list.add(element);
    }

    /*
    出栈操作
     */
    public E pop() {
        return list.remove(size()-1);
    }

    /*
    获取栈顶元素
     */
    public E top() {
        return list.get(size()-1);
    }

    /*
    清空站里面的元素
     */
    public void clear() {
        list.clear();
    }
}
