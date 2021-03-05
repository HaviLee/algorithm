/*
 * ************************************************************
 * 文件：MyOrderBroadcastReceiver.java
 * 模块：MyApplication.app
 * 项目：MyApplication
 * 当前修改时间：2020年12月25日 10:15:28
 * 上次修改时间：2020年12月25日 10:15:28
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

public class MyOrderBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        Toast.makeText(context, "这是有序光爆", Toast.LENGTH_SHORT).show();
    }
}