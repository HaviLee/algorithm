/*
 * ************************************************************
 * 文件：SingleTapActivity.java
 * 模块：MyApplication.app
 * 项目：MyApplication
 * 当前修改时间：2020年12月09日 17:56:55
 * 上次修改时间：2020年12月09日 17:56:54
 * 作者：Havi
 * Copyright (c) 2020
 * ************************************************************
 *
 */

package com.example.Activity;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;

import com.example.Activity.ActivityManager.BaseActivity;

public class SingleTapActivity extends BaseActivity {

    /*
      取出栈里面的元素分为四种：
      默认，一直压栈
      singleTop:保证栈顶活动不会重复
      singleTask:保证整个栈里面活动不会重复
      singleInstance:使用这个标记的活动会被一个单独的返回栈进行管理；
     */

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_tap);
//        Button button = findViewById(R.id.singleTap);
//        button.setOnClickListener(new View.OnClickListener() {
//            /**
//             * Called when a view has been clicked.
//             *
//             * @param v The view that was clicked.
//             */
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(SingleTapActivity.this, SingleTapActivity.class);
//                startActivity(intent);
//            }
//        });

        //隐藏系统导航栏
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }
}