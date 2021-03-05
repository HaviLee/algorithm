/*
 * ************************************************************
 * 文件：SubKey1.java
 * 模块：MyApplication.app
 * 项目：MyApplication
 * 当前修改时间：2021年01月30日 10:16:24
 * 上次修改时间：2021年01月30日 10:08:00
 * 作者：Havi
 * Copyright (c) 2021
 * ************************************************************
 *
 */

package com.example.mj.Map.Model;

public class SubKey1 extends Key {

    public SubKey1(int value) {
        super(value);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (o == null || (o.getClass() != SubKey1.class && o.getClass() != SubKey2.class)) {
            return false;
        }
        return ((Key)o).value == value;
    }
}
