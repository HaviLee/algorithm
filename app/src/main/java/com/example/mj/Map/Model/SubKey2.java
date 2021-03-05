/*
 * ************************************************************
 * 文件：SubKey2.java
 * 模块：MyApplication.app
 * 项目：MyApplication
 * 当前修改时间：2021年01月30日 10:20:56
 * 上次修改时间：2021年01月30日 10:20:56
 * 作者：Havi
 * Copyright (c) 2021
 * ************************************************************
 *
 */

package com.example.mj.Map.Model;

public class SubKey2 extends Key {

    public SubKey2(int value) {
        super(value);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (o == null || (o.getClass() != SubKey1.class && o.getClass() != SubKey2.class)) return  false;
        return ((Key)o).value == value;
    }
}
