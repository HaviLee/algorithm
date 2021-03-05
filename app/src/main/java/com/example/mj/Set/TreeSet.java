/*
 * ************************************************************
 * 文件：TreeSet.java
 * 模块：MyApplication.app
 * 项目：MyApplication
 * 当前修改时间：2021年01月25日 18:45:24
 * 上次修改时间：2021年01月25日 18:45:23
 * 作者：Havi
 * Copyright (c) 2021
 * ************************************************************
 *
 */

package com.example.mj.Set;

import com.example.mj.Map.Map;
import com.example.mj.Map.TreeMap;
import com.example.mj.二叉树.BinaryTree;

/*
* 利用TreeMap实现TreeSet
* */

public class TreeSet<E> implements Set<E> {

    Map<E, Object> map = new TreeMap<>();

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public boolean contains(E element) {
        return map.containsKey(element);
    }

    @Override
    public void add(E element) {
        map.put(element, null);
    }

    @Override
    public void remove(E element) {
        map.remove(element);
    }

    @Override
    public void traversal(final BinaryTree.Visitor<E> visitor) {
        map.traversal(new Map.Visitor<E, Object>() {
            @Override
            protected boolean visit(E key, Object value) {
                return visitor.visit(key);
            }
        });
    }
}
