/*
 * ************************************************************
 * 文件：ThirdActivity.java
 * 模块：app
 * 项目：MyApplication
 * 当前修改时间：2020年12月02日 19:52:49
 * 上次修改时间：2020年12月02日 19:52:49
 * 作者：Havi
 * Copyright (c) 2020
 * ************************************************************
 *
 */

package com.example.Activity;

import android.os.Bundle;

import com.example.Activity.ActivityManager.BaseActivity;

public class ThirdActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
    }
}