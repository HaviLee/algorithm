/*
 * ************************************************************
 * 文件：MainActivity.java
 * 模块：app
 * 项目：MyApplication
 * 当前修改时间：2020年12月01日 17:41:15
 * 上次修改时间：2020年12月01日 08:28:13
 * 作者：Havi
 * Copyright (c) 2020
 * ************************************************************
 *
 */

package com.example.Activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.example.mj.LinkedList.single.SingleLinkedList;
import com.example.mj.ArrayList.List;
import com.example.tool.TimeTools;

public class MainActivity extends AppCompatActivity {

    /*
    java中所有的类，最终都继承自java.lang.object
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TimeTools.check("list", new TimeTools.Task() {
            @Override
            public void execute() {

            }
        });
        /*
        List<Integer> list = new List();
        list.add(10);
        list.add(8);
        list.add(9);
        list.add(7);
        list.set(2,99);
        System.out.println(list.toString());

        Assert.test(list.get(0) == 10);
        Assert.test(list.size() == 4);
        Assert.test(list.get(2) == 99);

        List<Person> list1 = new List<>();
        list1.add(new Person(8, "test"));
        list1.add(new Person(15,"rose"));
        System.out.println(list1.toString());
        */
//        LinkedList linkedList = new LinkedList();
        List<Integer> list = new SingleLinkedList<>();
        list.add(20);
        list.add(0, 10);
        list.add(list.size(),30);
        System.out.println(list);


        setContentView(R.layout.activity_main);
    }



}