/*
 * ************************************************************
 * 文件：TitleLayout.java
 * 模块：MyApplication.app
 * 项目：MyApplication
 * 当前修改时间：2020年12月10日 15:34:48
 * 上次修改时间：2020年12月10日 15:34:47
 * 作者：Havi
 * Copyright (c) 2020
 * ************************************************************
 *
 */

package com.example.Activity;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

public class TitleLayout extends LinearLayout {

    public TitleLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.title, this);
    }
}
