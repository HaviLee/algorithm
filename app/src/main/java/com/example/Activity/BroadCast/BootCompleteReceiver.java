/*
 * ************************************************************
 * 文件：BootCompleteReceiver.java
 * 模块：MyApplication.app
 * 项目：MyApplication
 * 当前修改时间：2020年12月22日 20:23:41
 * 上次修改时间：2020年12月22日 20:23:40
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

/*
动态注册我们的广播接收器
 */

public class BootCompleteReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        Toast.makeText(context, "Boot complete", Toast.LENGTH_SHORT).show();
    }
}