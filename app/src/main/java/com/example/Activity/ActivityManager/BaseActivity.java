/*
 * ************************************************************
 * 文件：BaseActivity.java
 * 模块：MyApplication.app
 * 项目：MyApplication
 * 当前修改时间：2020年12月21日 22:26:31
 * 上次修改时间：2020年12月15日 21:19:24
 * 作者：Havi
 * Copyright (c) 2020
 * ************************************************************
 *
 */

package com.example.Activity.ActivityManager;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {

    //在 activity创建的时候入栈

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("BaseActivity", getClass().getSimpleName());
        ActivityController.addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityController.removeActivity(this);
    }
}
