/*
 * ************************************************************
 * 文件：PriorityQueue.java
 * 模块：MyApplication.app
 * 项目：MyApplication
 * 当前修改时间：2021年03月04日 22:12:26
 * 上次修改时间：2021年03月04日 22:12:26
 * 作者：Havi
 * Copyright (c) 2021
 * ************************************************************
 *
 */

package com.example.mj.Queue;

/*
* 普工队列：先进先出
* 优先级队列：按照优先级进行出队，将优先级高的元素作为队头出队；
* 应用：
* 这里使用堆实现
* */

import com.example.mj.二叉堆.BinaryHeap;

import java.util.Comparator;

public class PriorityQueue<E> {

    private BinaryHeap<E> heap;

    public PriorityQueue(Comparator<E> comparator) {
        this.heap = new BinaryHeap<>(comparator);
    }

    public int size() {
        return heap.size();
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    public void enQueue(E element) {
        heap.add(element);
    }

    public E deQueue() {
        //去除堆顶元素；即优先级最高的
        return heap.remove();
    }

    public E front() {
        return heap.get();
    }

    public void clear() {
        heap.clear();
    }
}
