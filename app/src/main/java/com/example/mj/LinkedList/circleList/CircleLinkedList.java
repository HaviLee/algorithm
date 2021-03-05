/*
 * ************************************************************
 * 文件：CircleLinkList.java
 * 模块：MyApplication.app
 * 项目：MyApplication
 * 当前修改时间：2020年12月16日 21:37:42
 * 上次修改时间：2020年12月16日 21:37:42
 * 作者：Havi
 * Copyright (c) 2020
 * ************************************************************
 *
 */

package com.example.mj.LinkedList.circleList;

import com.example.mj.ArrayList.AbstractList;

public class CircleLinkedList<E> extends AbstractList<E> {
    private Node<E> first;
    private Node<E> last;
    /*
    约瑟夫问题：
    1、增加一个current指针；指向当前某个节点；
    2、增加reset方法；让current指向结点first；
    3、增加next方法；让current指向下一步；current = current.next
    4、增加remove方法：删除current指向的结点，并且current自动指向下一个
     */

    public void  reset() {

    }

    public E next() {
        if (current == null) return null;
        current = current.next;
        return current.element;
    }

    public E remove() {
        if (current == null) return null;
        Node next = current.next;
        E element = remove(current);
        if (size == 0) {//需要考略删完之后元素是不是为空了
            current = null;
        } else {
            current = next;
        }
        return element;
    }

    //新增一个删除结点的方法
    private E remove(Node<E> node) {
        if (size == 1) {
            first = null;
            last = null;
        } else {
            Node<E> next = node.next;
            Node<E> pre = node.pre;
            pre.next = next;
            next.pre = pre;
            if (node == first) { //等价于Index == 0 // node == first
                first = next;
            }

            if (node == last) { //等价于index == size - 1
                last = pre;
            }
        }
        size--;
        return node.element;
    }

    private Node<E> current; //用来指向某个节点的

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
            last = new Node<>(oldLast, element, first);//在最后添加节点的话，他的next要指向firtst
            if (oldLast == null) { //表示链表添加的第一个元素
                first = last;
                //如果是第一个元素，需要自己指向自己
                first.next = first;
                first.pre = first;
            } else {
                oldLast.next = last;
                //需要first的next指向last
                first.pre = last;
            }
        } else {//其他地方插入
            //分析可知，当前index所在的node就是newNode的nex,而当前节点的pre就是newNode的pre;
            Node<E> next = node(index);
            Node<E> prev = next.pre;
            Node<E> newNode = new Node<>(prev, element, next);
            next.pre = newNode;
            prev.next = newNode;

            if (index == 0) {//特殊处理index==0
                first = newNode;
            }
        }

        size++;

    }

    @Override
    public int indexOf(E element) {
        Node<E> node = first;
        for (int i = 0; i < size; i++) {
            if (element.equals(node.element)) return i;

            node = node.next;
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

        return remove(node(index));
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
            for (int i = size - 1; i > index; i--) {
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
