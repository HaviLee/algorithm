/*
 * ************************************************************
 * 文件：TextViewActivity.java
 * 模块：MyApplication.app
 * 项目：MyApplication
 * 当前修改时间：2020年12月10日 10:40:47
 * 上次修改时间：2020年12月10日 10:40:46
 * 作者：Havi
 * Copyright (c) 2020
 * ************************************************************
 *
 */

package com.example.Activity;

import android.os.Bundle;

import com.example.Activity.ActivityManager.BaseActivity;

public class TextViewActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_view);
    }
}