/*
 * ************************************************************
 * 文件：Person.java
 * 模块：MyApplication.app
 * 项目：MyApplication
 * 当前修改时间：2020年12月02日 22:35:02
 * 上次修改时间：2020年11月30日 22:01:11
 * 作者：Havi
 * Copyright (c) 2020
 * ************************************************************
 *
 */

package com.example.model;

import java.util.Objects;

public class Person {
    private int age;
    private String name;


    public Person(int age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age &&
                name.equals(person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(age, name);
    }
}
