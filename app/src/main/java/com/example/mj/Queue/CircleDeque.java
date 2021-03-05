/*
 * ************************************************************
 * 文件：CircleDeque.java
 * 模块：MyApplication.app
 * 项目：MyApplication
 * 当前修改时间：2020年12月22日 22:21:29
 * 上次修改时间：2020年12月22日 22:21:29
 * 作者：Havi
 * Copyright (c) 2020
 * ************************************************************
 *
 */

package com.example.mj.Queue;

public class CircleDeque<E> {

    private E[] elements;
    private int front;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;

    public CircleDeque() {
        elements = (E[]) new Object[DEFAULT_CAPACITY];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;

    }

    /*
    尾部入队
     */
    public void enQueueRear(E element) {
        //尾部入队和之前的一样
        ensureCapacity(size+1);
        elements[index(size)] = element;
        size++;
    }

    /*
    头部入队
     */
    public void enQueueFront(E element) {
        ensureCapacity(size+1);
        //elements[front-1] = element;
        //套用索引公式
        front = index(-1);//首先变换找出front真实位置
        elements[front] = element;
        size++;
    }

    /*
    尾部出队
     */
    public E deQueueRear() {
        //首先查找尾部索引
        int rearIndex = index(size-1);
        E rearElement = elements[rearIndex];
        elements[rearIndex] = null;
        size--;
        return rearElement;
    }

    /*
    头部出队
     */
    public E deQueueFront() {
        E frontElement = elements[front];
        elements[front] = null;//置空
        front = index(1);
        size--;
        return frontElement;
    }

    public E rear() {
        return elements[index(size-1)];
    }

    public E front() {
        return elements[front];
    }

    public void clear() {
        /*
        注意要先清空数据，再重置front和size
         */
        for (int i = 0; i < elements.length; i++) {
            elements[index(i)] = null;
        }
        size = 0;
        front = 0;
    }
    /*
    根据传入的索引，获得在数组中的真实索引位置
     */
    /*
    优化：
    编码中尽量避免使用乘除取模，浮点运算，CPU处理这些比较耗时
     */
    private int index(int index) {
        /*优化后
        主要是index+front不会超过elements个数的两倍
         */
        index += front;
        if (index < 0) {
            //小于0的情况是在front等于0的时候出现的；为了
            return index + elements.length;
        }
        return index - ((index >= elements.length) ? elements.length : 0);
        /*优化前
        index += front;
        if (index < 0) {
            //小于0的情况是在front等于0的时候出现的；为了
            return index + elements.length;
        }
        return index % elements.length;
         */
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
