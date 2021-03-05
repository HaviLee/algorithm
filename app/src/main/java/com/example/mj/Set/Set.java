/*
 * ************************************************************
 * 文件：Set.java
 * 模块：MyApplication.app
 * 项目：MyApplication
 * 当前修改时间：2021年01月25日 09:54:30
 * 上次修改时间：2021年01月25日 09:54:30
 * 作者：Havi
 * Copyright (c) 2021
 * ************************************************************
 *
 */

package com.example.mj.Set;

import com.example.mj.二叉树.BinaryTree;

public interface Set<E> {
    /*
    * 集合中元素个数
    * */
    int size();

    /*
    * 集合是否为空
    * */
    boolean isEmpty();

    /*
    * 集合清空操作
    * */
    void clear();

    /*
    * 集合是否包含某个元素
    * */
    boolean contains(E element);

    /*
    * 集合中添加元素
    * */
    void add(E element);

    /*
    * 集合中移除某个元素
    * */
    void remove(E element);

    /*
    * 定义遍历器，集合需要遍历接口
    * */
    void traversal(BinaryTree.Visitor<E> visitor);

}
