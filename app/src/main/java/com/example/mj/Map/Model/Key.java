/*
 * ************************************************************
 * 文件：Key.java
 * 模块：MyApplication.app
 * 项目：MyApplication
 * 当前修改时间：2021年01月28日 09:38:07
 * 上次修改时间：2021年01月28日 09:38:07
 * 作者：Havi
 * Copyright (c) 2021
 * ************************************************************
 *
 */

package com.example.mj.Map.Model;

import java.util.Objects;

public class Key {

    protected int value;

    public Key(int value) {
        this.value = value;
    }

    /*
    * 自定义对象可以作为key使用，必须满足如果两个对象equals为true,那么hashcode一定一样；
    * */
    @Override
    public boolean equals(Object o) {
        //比较是否为同一个对象
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Key key = (Key) o;
        return value == key.value;
    }

    /*
    * 这个就是自定义对象作为Hash 表的key的时候需要重写hashCode和equals方法
    * */
    @Override
    public int hashCode() {
        //制造了一个前9个key的hash冲突了；
        return value / 10;
    }

    @Override
    public String toString() {
        return "k" + value;
    }
}
