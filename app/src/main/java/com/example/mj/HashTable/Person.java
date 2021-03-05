/*
 * ************************************************************
 * 文件：Person.java
 * 模块：MyApplication.app
 * 项目：MyApplication
 * 当前修改时间：2021年01月26日 18:29:41
 * 上次修改时间：2021年01月26日 18:29:41
 * 作者：Havi
 * Copyright (c) 2021
 * ************************************************************
 *
 */

package com.example.mj.HashTable;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.Objects;

public class Person {
    private int age;
    private float height;
    private String name;

    public Person(int age,float height, String name) {
        this.age = age;
        this.height = height;
        this.name = name;
    }

    /*
    * 重写equals方法是为了在出现hash 冲突的时候，去比较链表里面的key是不是一样的；
    * */
    @Override
    public boolean equals(Object o) {
        //== 比较的时内存地址，这两个的内存地址都一样，说明是同一个对象；
        if (this == o) return true;
        //如果传入对象为空，或者两个对象类型不一样，肯定不是同一个对象；
        if (o == null || getClass() != o.getClass()) return false;
        //下面就是你自己定义自定义对象相等的标准；我们比较成员变量是不是相等；
        Person person = (Person) o;
        return age == person.age && height == person.height &&
                (name == null) ? person.name == null : name.equals(person.name);
    }

    /*
    * 自定义对象，重写HashCode是为了进行计算hash表中的索引
    * */
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public int hashCode() {
        int hashCode = Integer.hashCode(age);
        hashCode = hashCode * 31 + Float.hashCode(height);
        hashCode = hashCode * 31 + (name != null ? name.hashCode() : 0);
        return hashCode;

    }
}
