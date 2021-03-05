/*
 * ************************************************************
 * 文件：AbstractHeap.java
 * 模块：MyApplication.app
 * 项目：MyApplication
 * 当前修改时间：2021年02月28日 15:55:27
 * 上次修改时间：2021年02月28日 15:55:27
 * 作者：Havi
 * Copyright (c) 2021
 * ************************************************************
 *
 */

package com.example.mj.二叉堆;

import java.util.Comparator;

/*
抽象类
 */
public abstract class AbstractHeap<E> implements Heap<E> {

    protected int size;
    protected Comparator<E> comparator;

    public AbstractHeap(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    public AbstractHeap() {

    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /*
     * 二叉堆具有比较性，因此需要定义比较操作
     * */
    protected int compare(E e1, E e2) {
        return comparator != null ? comparator.compare(e1, e2) : ((Comparable)e1).compareTo(e2);
    }
}
