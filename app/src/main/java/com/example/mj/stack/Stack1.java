/*
 * ************************************************************
 * 文件：Stack1.java
 * 模块：MyApplication.app
 * 项目：MyApplication
 * 当前修改时间：2020年12月20日 22:32:37
 * 上次修改时间：2020年12月20日 22:31:53
 * 作者：Havi
 * Copyright (c) 2020
 * ************************************************************
 *
 */

package com.example.mj.stack;

import com.example.mj.ArrayList.ArrayList;

/*
我们可以直接利用继承的方式来完成栈，但是这样的话栈就拥有的ArrayList所有的方法；从接口角度来说不合适
 */

public class Stack1<E> extends ArrayList {

    /*
    获取栈的元素的个数
    直接用父类的就可以
     */
//    public int size() {
//        return size;
//    }

    /*
    判断栈是不是空的
    直接利用父类就可以
     */
//    public boolean isEmpty() {
//        return size == 0;
//    }

    /*
    入栈操作
     */
    public void push(E element) {
        add(element);
    }

    /*
    出栈操作
     */
    public E pop() {
        return (E) remove(size-1);
    }

    /*
    获取栈顶元素
     */
    public E top() {
        return (E) get(size-1);
    }
}
