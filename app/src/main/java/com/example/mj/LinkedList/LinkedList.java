/*
 * ************************************************************
 * 文件：LinkedList.java
 * 模块：MyApplication.app
 * 项目：MyApplication
 * 当前修改时间：2020年12月02日 22:40:27
 * 上次修改时间：2020年12月02日 22:40:27
 * 作者：Havi
 * Copyright (c) 2020
 * ************************************************************
 *
 */

package com.example.mj.LinkedList;
import com.example.mj.ArrayList.AbstractList;
import com.example.mj.LinkedList.single.SingleLinkedList2;

public class LinkedList<E> extends AbstractList<E> {

    private Node<E> first;
    private Node<E> last;

    @Override
    public void clear() {
        /*
        Java中有GC Root对象；一个对象如果不是被GC root对象指向的对象
        比如栈指针（局部对象  ）指向的对象称为GC Root对象；
         */
        size = 0;
        first = null;
        last = null;
    }

    @Override
    public void add(int index, E element) {
        if (index == size) { //这个是链表已经有元素；size>1
            Node<E> oldLast = last;
            last = new Node<>(oldLast, element, null);
            if (oldLast == null) { //表示链表添加的第一个元素
                first = last;
            } else {
                oldLast.next = last;
            }
        } else {
            //分析可知，当前index所在的node就是newNode的nex,而当前节点的pre就是newNode的pre;
            Node<E> next = node(index);
            Node<E> prev = next.pre;
            Node<E> newNode = new Node<>(prev, element, next);
            next.pre = newNode;

            if (prev == null) {//特殊处理index==0
                first = newNode;
            } else {
                prev.next = newNode;
            }
        }

        size++;

    }

    @Override
    public int indexOf(E element) {
        for (int i = 0; i < size; i++) {
            Node<E> node = node(i);
            if (element == node.element) return i;
        }
        return ELEMENT_NOT_FOUND;
    }

    @Override
    public E get(int index) {
        return node(index).element;
    }

    @Override
    public E set(int index, E element) {
        Node<E> node = node(index);
        E old = node.element;
        node.element = element;
        return old;
    }

    @Override
    public E remove(int index) {
        Node<E> node = node(index);
        Node<E> next = node.next;
        Node<E> pre = node.pre;
        if (pre == null) { //等价于Index == 0
            first = next;
        } else {
            pre.next = next;
        }

        if (next == null) { //等价于index == size - 1
            last = pre;
        } else  {
            next.pre = pre;
        }
        size--;
        return node.element;
    }

    @Override
    public void remove(E element) {

    }

    /*
    查找node的方法
     */
    private Node<E> node(int index) {
        rangeCheck(index);
        //index小于size一般从first开始查找
        Node<E> node;
        if (index < (size >> 1)) {
            node = first;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
        } else {
            node = last;
            for (int i = size-1; i > index; i--) {
                node = node.pre;
            }
        }
        return node;
    }
    /*
    打印方法
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("size=").append(size).append(",[");
        Node<E> node = first;
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                builder.append(",");
            }
            builder.append(node.toString());
            node = node.next;
        }
        builder.append("]");
        return builder.toString();
    }

    /*
        需要定义一个内部类;在某个类内部使用
         */
    private static class Node<E> {
        E element;
        Node<E> next;
        Node<E> pre;
        //需要一个构造函数
        public Node(Node<E> pre, E element, Node<E> next) {
            this.element = element;
            this.pre = pre;
            this.next = next;
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            if (pre != null) builder.append(pre.element);
            builder.append("_").append(element).append("_");
            if (next != null) builder.append(next.element);
            return builder.toString();
        }
    }
}
