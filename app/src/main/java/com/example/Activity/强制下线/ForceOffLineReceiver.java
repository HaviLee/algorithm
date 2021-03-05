/*
 * ************************************************************
 * 文件：ForceOffLineReceiver.java
 * 模块：MyApplication.app
 * 项目：MyApplication
 * 当前修改时间：2020年12月28日 16:00:47
 * 上次修改时间：2020年12月28日 16:00:47
 * 作者：Havi
 * Copyright (c) 2020
 * ************************************************************
 *
 */

package com.example.Activity.强制下线;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class ForceOffLineReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        Toast.makeText(context, "退出登录", Toast.LENGTH_SHORT).show();
    }
}