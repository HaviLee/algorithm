/*
 * ************************************************************
 * 文件：SingleLinkedList2.java
 * 模块：MyApplication.app
 * 项目：MyApplication
 * 当前修改时间：2020年12月02日 22:33:36
 * 上次修改时间：2020年12月02日 22:31:58
 * 作者：Havi
 * Copyright (c) 2020
 * ************************************************************
 *
 */

package com.example.mj.LinkedList.single;

import com.example.mj.ArrayList.AbstractList;

/*
使用LinkedList实现List接口
使用了虚拟头结点
 */
public class SingleLinkedList2<E> extends AbstractList<E> {

    /*
    LinkedList不需要构造函数
    ArrayList需要使用者传递一个容量，因此需要一个构造函数
     */
    private Node<E> first;

    /*
    需要一个构造函数初始化一个虚拟头结点
     */
    public SingleLinkedList2() {
        first = new Node<>(null, null);
    }
    @Override
    public void clear() {
        size = 0;
        first = null;
        //
    }

    @Override
    public void add(int index, E element) {
        rangeCheckForAdd(index);
        Node<E> prev = index == 0 ? first : node(index-1);
        prev.next = new Node<E>(element, prev.next);

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
        Node<E> prev = index == 0 ? first : node(index-1);
        Node<E> node = prev.next;
        prev.next = node.next;
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
        Node<E> node = first.next;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("size=").append(size).append(",[");
        Node<E> node = first.next;
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

