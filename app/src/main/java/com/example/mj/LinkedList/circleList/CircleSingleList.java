/*
 * ************************************************************
 * 文件：CircleSingleList.java
 * 模块：MyApplication.app
 * 项目：MyApplication
 * 当前修改时间：2020年12月16日 14:23:28
 * 上次修改时间：2020年12月16日 14:23:28
 * 作者：Havi
 * Copyright (c) 2020
 * ************************************************************
 *
 */

package com.example.mj.LinkedList.circleList;
import com.example.mj.ArrayList.AbstractList;

public class CircleSingleList<E> extends AbstractList<E> {
    /*
    LinkedList不需要构造函数
    ArrayList需要使用者传递一个容量，因此需要一个构造函数
     */
    private Node<E> first;

    @Override
    public void clear() {
        size = 0;
        first = null;
        //
    }

    @Override
    public void add(int index, E element) {
        rangeCheckForAdd(index);
        //特殊处理0//循环链表特殊处理的地方
        if (index == 0) {
            //这个是创建一个新的节点//这里是先不改这个，否则会改变下面查找node结构
            Node newFirst = new Node<>(element, first);
            //拿到最后一个节点
            Node last = size == 0 ? newFirst : node(size-1);
            last.next = newFirst;
            first = newFirst;
        } else {
            Node<E> prev = node(index-1);
            prev.next = new Node<>(element, prev.next);
        }
        size++;

    }

    @Override
    public int indexOf(E element) {
        Node<E> node = first;
        if (element == null) {
            for (int i = 0; i < size; i++) {
                if (node.element == null) return i;
                node = node.next;
            }
        } else  {
            for (int i = 0; i < size; i++) {
                if (element.equals(node.element)) return i;
                node = node.next;
            }
        }
        return ELEMENT_NOT_FOUND;
    }

    @Override
    public E get(int index) {
        Node<E> node = node(index);
        return node.element;
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
        rangeCheck(index);
        Node<E> node = first;
        if (index == 0) {
            if (size == 1) {
                //只有一个节点的时候，就可以了
                first = null;
            } else  {//针对多个节点的时候
                //这里需要先去拿最后一个节点；如果放到下面一句之后就会报错；
                Node last = node(size-1);
                first = first.next;
                last.next = first;
            }

        } else {
            node = node(index);
            node(index-1).next = node.next;
        }
        size--;
        return node.element;


    }

    @Override
    public void remove(E element) {

    }


    /*
    获取index节点的对象
     */
    private Node<E> node(int index) {
        rangeCheck(index);
        Node<E> node = first;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("size=").append(size).append(",[");
        Node<E> node = first;
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                builder.append(",");
            }
            builder.append(node.element);
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
        //需要一个构造函数
        public Node(E element, Node<E> next) {
            this.element = element;
            this.next = next;
        }

    }

}
