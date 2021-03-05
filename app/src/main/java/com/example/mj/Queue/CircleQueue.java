/*
 * ************************************************************
 * 文件：CircleQueue.java
 * 模块：MyApplication.app
 * 项目：MyApplication
 * 当前修改时间：2020年12月22日 09:47:30
 * 上次修改时间：2020年12月22日 09:47:30
 * 作者：Havi
 * Copyright (c) 2020
 * ************************************************************
 *
 */

package com.example.mj.Queue;

/*
循环队列：底层使用*动态数组*实现；
ArrayList优化：将各个接口优化到O(1)，也称为循环队列
 */

import java.util.Arrays;

public class CircleQueue<E> {
    private int front = 0;//存储当前的队头
    private int size;
    private E[] elements; //使用数组进行保存
    private static final int DEFAULT_CAPACITY = 10;

    public CircleQueue() {
        elements = (E[]) new Object[DEFAULT_CAPACITY];
    }

    /*
     获取队列的个数
    */
    public int size() {
        return size;
    }

    /*
    队列是否为空
     */
    public boolean isEmpty() {
        return size == 0;
    }

    public void enQueue(E element) {
        ensureCapacity(size+1);
        int index = index(size);
        elements[index] = element;
        size++;
    }

    public E deQueue() {
        E element = elements[front];
        elements[front] = null;
        front = index(1);
        size--;
        return element;
    }

    public E front() {
        return elements[front];
    }

    public void clear() {
        for (int i = 0; i < elements.length; i++) {
            elements[index(i)] = null;
        }
        size = 0;
        front = 0;
    }

    /*
    根据传入的索引，获得在数组中的真实索引位置
     */
    private int index(int index) {
        /*
        优化前：        return (front + index) % elements.length;

         */
        index += front;
        return index - (index >= elements.length ? elements.length : 0);
    }

    private void ensureCapacity(int capacity) {
        //旧的容量
        int oldCapacity = elements.length;
        //表示容量够用
        if (oldCapacity >= capacity) return;
        //来到下面说明容量不够用，需要扩容
        //首先申请新的容量/扩容为原来的3/2,即1.5倍
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        //申请新的空间
        E[] newElements = (E[]) new Object[newCapacity];
        //复制操作;这个时候应该从队头复制;(i+front)%elements.length获取真实索引
        for (int i = 0; i < size; i++) {
            newElements[i] = elements[index(i)];
        }
        elements = newElements;
        front = 0;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Array: size:").append(size);
        builder.append("[");
        for (int i = 0; i < elements.length; i++) {
            builder.append(elements[i]);
            builder.append(",");
        }
        builder.append("]");
        return builder.toString();
    }
}
