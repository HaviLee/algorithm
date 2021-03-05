/*
 * ************************************************************
 * 文件：List.java
 * 模块：MyApplication.app
 * 项目：MyApplication
 * 当前修改时间：2020年12月20日 22:34:02
 * 上次修改时间：2020年12月20日 22:33:24
 * 作者：Havi
 * Copyright (c) 2020
 * ************************************************************
 *
 */

package com.example.mj.ArrayList;

/*
java接口类似ios协议；只定义提供给外界的接口，不具体实现；
 */
public interface List<E> {
    static final int ELEMENT_NOT_FOUND = -1;
    /*
    清除所有的元素
     */
    void clear();

    /*
    元素的数量
     */
    int size();

    /*
    是否为空
     */
    boolean isEmpty();

    /*
    是否包含某个元素
     */
    boolean contains(E element);

    /*
    添加某个元素到最后位置
     */
    void add(E element);

    /*
    插入某个元素到某个位置
     */
    void add(int index, E element);

    /*
    返回元素所在的位置
     */
    int indexOf(E element);

    /*
    获取某个位置上的元素
     */
    E get(int index);

    /*
    替换某个位置元素
     */
    E set(int index, E element);

    /*
    删除某个位置元素
     */
    E remove(int index);

    /*
    删除某个元素
     */
    void remove(E element);
}
