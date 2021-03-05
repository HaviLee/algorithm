/*
 * ************************************************************
 * 文件：Map.java
 * 模块：MyApplication.app
 * 项目：MyApplication
 * 当前修改时间：2021年01月25日 12:39:03
 * 上次修改时间：2021年01月25日 12:39:03
 * 作者：Havi
 * Copyright (c) 2021
 * ************************************************************
 *
 */

package com.example.mj.Map;

public interface Map<K, V> {

    /*
    * 映射表数据大小
    * */
    int size();

    /*
    * 映射表是否为空
    * */
    boolean isEmpty();

    /*
    * 清空映射表
    * */
    void clear();

    /*
    * 往映射表里添加元素
    * */
    V put(K key, V value);

    /*
    * 根据key读取vlaue
    * */
    V get(K key);

    /*
    * 移除元素
    * */
    V remove(K key);

    /*
    * 映射表是否包含某个key
    * */
    boolean containsKey(K key);

    /*
    * 映射表是否包含某个value
    * */
    boolean containsValue(V value);

    /*
    * 映射表的遍历
    * */
    void traversal(Visitor<K, V> visitor);

    public static abstract class Visitor<K, V> {
        boolean stop;
        protected abstract boolean visit(K key, V value);
    }
}
