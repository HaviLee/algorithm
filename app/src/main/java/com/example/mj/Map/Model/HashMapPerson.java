/*
 * ************************************************************
 * 文件：HashMapPerson.java
 * 模块：MyApplication.app
 * 项目：MyApplication
 * 当前修改时间：2021年01月30日 09:22:46
 * 上次修改时间：2021年01月30日 09:22:46
 * 作者：Havi
 * Copyright (c) 2021
 * ************************************************************
 *
 */

package com.example.mj.Map.Model;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.Objects;
@RequiresApi(api = Build.VERSION_CODES.KITKAT)

public class HashMapPerson implements Comparable {
    private int age;
    private float height;
    private String name;

    public HashMapPerson(int age, float height, String name) {
        this.age = age;
        this.height = height;
        this.name = name;
    }

    /*
    * equals:是比较两个对象是否相等；
    * */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HashMapPerson that = (HashMapPerson) o;
        return age == that.age &&
                Float.compare(that.height, height) == 0 &&
                Objects.equals(name, that.name);
    }

    /*
    * 是为了获取对象的hash code;
    * */
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public int hashCode() {
        //计算方法就是将每个属性的hash值都参与计算公式：x*n^2 + y*n^0 + z*n^
        int hashCode = Integer.hashCode(age);
        hashCode = hashCode*31+Float.hashCode(height);
        hashCode = hashCode*31+ (name != null ? name.hashCode():0);
        return hashCode;
    }

    /*
    * compareTo方法是用来比较大小的：
    * 所以在二叉搜索树中，使用了compareTo就可以了
    * */
    @Override
    public int compareTo(Object o) {
        return this.age - ((HashMapPerson)o).age;
    }
}
