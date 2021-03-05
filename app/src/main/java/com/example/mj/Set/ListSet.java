/*
 * ************************************************************
 * 文件：ListSet.java
 * 模块：MyApplication.app
 * 项目：MyApplication
 * 当前修改时间：2021年01月25日 10:38:39
 * 上次修改时间：2021年01月25日 10:38:39
 * 作者：Havi
 * Copyright (c) 2021
 * ************************************************************
 *
 */

package com.example.mj.Set;

import com.example.mj.ArrayList.List;
import com.example.mj.LinkedList.LinkedList;
import com.example.mj.二叉树.BinaryTree;

public class ListSet<E> implements Set<E> {

    private List<E> list = new LinkedList<>();

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public boolean contains(E element) {
        return list.contains(element);
    }

    @Override
    public void add(E element) {
        //对应已经存在的元素
        //1、可以替换
        int index = list.indexOf(element);
        if (index != List.ELEMENT_NOT_FOUND) {
            list.set(index, element);
        } else {
            list.add(element);
        }
//        if (list.contains(element)) return;
//        list.add(element);
    }

    @Override
    public void remove(E element) {
        int index = list.indexOf(element);
        if (index != List.ELEMENT_NOT_FOUND) {
            list.remove(element);
        }
    }

    @Override
    public void traversal(BinaryTree.Visitor<E> visitor) {
        if (visitor == null) return;
        int size = this.size();
        for (int i = 0; i < size; i++) {
            if (visitor.visit(list.get(i))) return;
        }
    }
}
