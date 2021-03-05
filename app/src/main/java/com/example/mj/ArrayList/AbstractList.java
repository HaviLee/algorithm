/*
 * ************************************************************
 * 文件：AbstractList.java
 * 模块：MyApplication.app
 * 项目：MyApplication
 * 当前修改时间：2020年12月20日 22:34:02
 * 上次修改时间：2020年12月20日 22:33:24
 * 作者：Havi
 * Copyright (c) 2020
 * ************************************************************
 *
 */

package com.example.mj.ArrayList;

/*
abstract 关键字可以保证接口文件的方法不必要实现
对外界是不可见，并且是无法new的
 */
public abstract class AbstractList<E> implements List<E> {

    /*
    元素的数量
     */
    protected int size;
    /*
    元素的数量
     */
    public int size() {
        return size;
    }

    /*
    是否为空
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /*
    是否包含某个元素
     */
    public boolean contains(E element) {
        return indexOf(element) != ELEMENT_NOT_FOUND;
    }

    /*
    添加元素到最后位置
    最好：O(1)
    最坏：O(n),扩容的情况下；刚好容量不够的情况下
    均摊复杂度：某一次的时间复杂度是由其他导致的；此时将此次的时间复杂度均摊到其他位置；一般均摊复杂度和最好是一致的；
    一般在连续比较低的复杂度之后突然出现比较高的时间复杂度；
     */
    public void add(E element) {
        add(size, element);
    }

    /*
    抛出异常函数
     */
    protected void outOfBounds(int index) {
        throw new IndexOutOfBoundsException();
    }

    /*
    index检测
     */
    protected void rangeCheck(int index) {
        if (index < 0 || index >= size) {
            outOfBounds(index);
        }
    }

    /*
    检测添加到指定index位置
     */
    protected void rangeCheckForAdd(int index) {
        if (index < 0 || index > size) {
            outOfBounds(index);
        }
    }
}
