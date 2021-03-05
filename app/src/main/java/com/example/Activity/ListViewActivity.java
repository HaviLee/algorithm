/*
 * ************************************************************
 * 文件：ListViewActivity.java
 * 模块：MyApplication.app
 * 项目：MyApplication
 * 当前修改时间：2020年12月10日 15:55:19
 * 上次修改时间：2020年12月10日 15:55:19
 * 作者：Havi
 * Copyright (c) 2020
 * ************************************************************
 *
 */

package com.example.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class ListViewActivity extends AppCompatActivity {

    private String[] data = {"apple", "banana", "Orange", "apple", "banana", "Orange", "apple", "banana", "Orange"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);


    }
}