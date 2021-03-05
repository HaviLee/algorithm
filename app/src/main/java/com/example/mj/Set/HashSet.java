/*
 * ************************************************************
 * 文件：HashSet.java
 * 模块：MyApplication.app
 * 项目：MyApplication
 * 当前修改时间：2021年01月31日 19:34:46
 * 上次修改时间：2021年01月31日 19:34:46
 * 作者：Havi
 * Copyright (c) 2021
 * ************************************************************
 *
 */

package com.example.mj.Set;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.mj.Map.HashMap;
import com.example.mj.Map.Map;
import com.example.mj.二叉树.BinaryTree;

/*
* 利用Hash 表实现set
*
* */

@RequiresApi(api = Build.VERSION_CODES.KITKAT)
public class HashSet<E> implements Set<E>{

    private HashMap<E, Object> map = new HashMap<>();

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
