/*
 * ************************************************************
 * 文件：TreeSet1.java
 * 模块：MyApplication.app
 * 项目：MyApplication
 * 当前修改时间：2021年01月25日 10:53:58
 * 上次修改时间：2021年01月25日 10:53:58
 * 作者：Havi
 * Copyright (c) 2021
 * ************************************************************
 *
 */

package com.example.mj.Set;

import com.example.mj.二叉树.BinaryTree;
import com.example.mj.二叉树.RBTree;

public class TreeSet1<E> implements Set<E> {

    /*
    * 对于Tree Set可以使用Tree Map实现；
    * */
    private RBTree<E> binaryTree = new RBTree<>();

    @Override
    public int size() {
        return binaryTree.size();
    }

    @Override
    public boolean isEmpty() {
        return binaryTree.isEmpty();
    }

    @Override
    public void clear() {
        binaryTree.clear();
    }

    @Override
    public boolean contains(E element) {
        return binaryTree.contains(element);
    }

    @Override
    public void add(E element) {
        //binary tree 默认支持去重
        binaryTree.add(element);
    }

    @Override
    public void remove(E element) {
        binaryTree.remove(element);
    }

    @Override
    public void traversal(BinaryTree.Visitor<E> visitor) {
        binaryTree.inorderTraversal(visitor);
    }
}
