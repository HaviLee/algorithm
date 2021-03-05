/*
 * ************************************************************
 * 文件：ArrayList.java
 * 模块：MyApplication.app
 * 项目：MyApplication
 * 当前修改时间：2020年12月20日 22:34:02
 * 上次修改时间：2020年12月20日 22:31:53
 * 作者：Havi
 * Copyright (c) 2020
 * ************************************************************
 *
 */

package com.example.mj.ArrayList;

//泛型和Swift类似
public class ArrayList<E> extends AbstractList<E> {
    /*
    所有的元素
     */
    private E[] elements;

    private static final int DEFAULT_CAPACITY = 2;
//    private static final int

    /*
    具有参数的构造函数
     */
    public ArrayList(int capacity) {
        capacity = Math.max(capacity, DEFAULT_CAPACITY);
        elements = (E[]) new Object[capacity];
    }
    /*
    无参数的构造函数
     */
    public ArrayList() {
        //默认构造函数调用，有参数的
        this(DEFAULT_CAPACITY);
    }
    /*
    清楚所有的元素
     */
    public void clear() {
        //对与别人使用者来说，已经清空了
        //销毁内存和创建内存是消耗时间的
        //使用泛型之后，这个地方需要自己释放内存空间
        for (int i = 0; i < size; i++) {
            elements[i] = null;//释放了数组中存放的对象
        }
        size = 0;
    }

    /*
    插入元素到某个位置
    最好情况：O(1)
    最坏情况：O(n)
    平均复杂度：(1+2+3+4...+n)/n = O(n)
     */
    public void add(int index, E element) {

        //这个插入比较特殊
        rangeCheckForAdd(index);
        //至少保证容量比
        ensureCapacity(size+1);
        /*
        if (size - index >= 0) System.arraycopy(elements, index, elements, index + 1, size - index);

         */
        for (int i = size; i > index; i--) {
            elements[i] = elements[i-1];
        }
        elements[index] = element;
        size++;
    }

    /*
    返回元素所在的位置
     */
    public int indexOf(E element) {
        /*
        为了索引空数据
         */
        //方式一：
        for (int i = 0; i < size; i++) {
            if (valueEquals(element, elements[i])) return i;
        }
        //方式二：
        /*
        if (element == null) {
            for (int i = 0; i < size; i++) {
                if (elements[i] == null) return i;
            }
        } else {
            for (int i = 0; i < size; i++) {
            /*
            ==表示内存地址是否相对

                if (element.equals(elements[i])) return i;
            }
        }
        */
        return ELEMENT_NOT_FOUND;
    }

    private boolean valueEquals(E e1, E e2) {
        return e1 == null ? e2 == null : e1.equals(e2);
    }

    /*
    获取某个位置上的元素
    O(1)
     */
    public E get(int index) {
        rangeCheck(index);
        return elements[index];
    }


    /*
    设置index上的元素，并返回原来的元素
    O(1)
     */
    public E set(int index, E element) {
        rangeCheck(index);
        E old = elements[index];
        elements[index] = element;
        return old;
    }

    /*
    删除某个位置的元素
    并返回旧的元素
    最好情况：O(1)
    最坏情况：O(n)
    平均：O(n)
     */
    public E remove(int index) {
        rangeCheck(index);
        E old = elements[index];
        for (int i = index; i < size-1; i++) {
            elements[index] = elements[index+1];
        }
        size--;
        //需要size先减，再清空
        elements[size] = null;
        return old;
    }

    /*
    删除特定元素
     */
    public void remove(E element) {

    }

    /*
    对数组容量进行验证，扩容操作
    非线程安全
     */
    private void ensureCapacity(int capacity) {
        //旧的容量
        int oldCapacity = elements.length;
        //旧的容量大于新的容量
        if (oldCapacity >= capacity) {
            return;
        }
        //此时进行扩容操作；使用右移操作效率高
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        E[] newElements = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newElements[i] = elements[i];
        }
        elements = newElements;
        System.out.println(oldCapacity +"扩容操作：" + newCapacity);
    }

    @Override
    public String toString() {
        // [99,99]
        StringBuilder builder = new StringBuilder();
        builder.append("size = ").append(size).append(", [");
        for (int i = 0; i < size; i++) {
            if (i!=0) builder.append(", ");
            builder.append(elements[i]);
        }
        builder.append("]");
        return builder.toString();

    }
}
