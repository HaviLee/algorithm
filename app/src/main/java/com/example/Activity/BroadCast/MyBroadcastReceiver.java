/*
 * ************************************************************
 * 文件：MyBroadcastReceiver.java
 * 模块：MyApplication.app
 * 项目：MyApplication
 * 当前修改时间：2020年12月24日 13:33:04
 * 上次修改时间：2020年12月24日 13:33:03
 * 作者：Havi
 * Copyright (c) 2020
 * ************************************************************
 *
 */

package com.example.Activity.BroadCast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        Toast.makeText(context, "自定义广播", Toast.LENGTH_SHORT).show();
    }
}