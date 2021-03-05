/*
 * ************************************************************
 * 文件：Person.java
 * 模块：MyApplication.app
 * 项目：MyApplication
 * 当前修改时间：2021年01月08日 11:13:04
 * 上次修改时间：2021年01月08日 11:13:04
 * 作者：Havi
 * Copyright (c) 2021
 * ************************************************************
 *
 */

package com.example.mj.BinarySearchTree;

import java.util.Objects;

public class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    @Override
    public int hashCode() {
        return 1;
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                '}';
    }
}
