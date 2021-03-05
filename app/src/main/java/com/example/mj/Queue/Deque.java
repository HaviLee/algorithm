/*
 * ************************************************************
 * 文件：Deque.java
 * 模块：MyApplication.app
 * 项目：MyApplication
 * 当前修改时间：2020年12月22日 08:49:20
 * 上次修改时间：2020年12月22日 08:49:20
 * 作者：Havi
 * Copyright (c) 2020
 * ************************************************************
 *
 */

/*
双端队列：Double Ended Queue;能在头尾两端进行删除和添加操作；
 */
package com.example.mj.Queue;

import java.util.LinkedList;
import java.util.List;

public class Deque<E> {

    private List<E> list = new LinkedList<>();

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public int size() {
        return list.size();
    }

    /*
    队尾入队
     */
    public void enQueueRear(E element) {
        list.add(element);
    }

    /*
    队头入队
     */
    public void enQueueFront(E element) {
        list.add(0, element);
    }

    /*
    队头出队
     */
    public E deQueueFront() {
        return list.remove(0);
    }

     /*
     队尾出队
      */
    public E deQueueRear() {
        return  list.remove(list.size()-1);
    }

    public E front() {
       return list.get(0);
    }

    public E rear() {
        return list.get(list.size()-1);
    }

}
