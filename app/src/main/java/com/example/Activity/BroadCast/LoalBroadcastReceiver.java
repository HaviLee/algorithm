/*
 * ************************************************************
 * 文件：LoalBroadcastReceiver.java
 * 模块：MyApplication.app
 * 项目：MyApplication
 * 当前修改时间：2020年12月28日 09:09:50
 * 上次修改时间：2020年12月28日 09:09:49
 * 作者：Havi
 * Copyright (c) 2020
 * ************************************************************
 *
 */

//已经启用的API

package com.example.Activity.BroadCast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class LoalBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        Toast.makeText(context, "这是local broadcast", Toast.LENGTH_SHORT).show();
    }
}