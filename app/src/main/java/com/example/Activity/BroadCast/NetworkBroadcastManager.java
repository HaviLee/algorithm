/*
 * ************************************************************
 * 文件：NetworkBroadcastManager.java
 * 模块：MyApplication.app
 * 项目：MyApplication
 * 当前修改时间：2020年12月22日 19:50:32
 * 上次修改时间：2020年12月22日 19:50:32
 * 作者：Havi
 * Copyright (c) 2020
 * ************************************************************
 *
 */

package com.example.Activity.BroadCast;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.Activity.R;

/*
动态注册广播接收器
 */

public class NetworkBroadcastManager extends AppCompatActivity {

    private IntentFilter intentFilter;

    private LoalBroadcastReceiver loalBroadcastReceiver = new LoalBroadcastReceiver();


    //网络监听
    private NetworkChangeReceiver networkChangeReceiver;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.network_layout);
        /*
        动态注入广播
         */
        intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        networkChangeReceiver = new NetworkChangeReceiver();
        registerReceiver(networkChangeReceiver, intentFilter);

        /*
        动态注入自定义广播
         */

        IntentFilter intentFilter1 = new IntentFilter();
        intentFilter1.addAction("android.com.example.Activity.BroadCast.MY_BROADCAST");
//        MyBroadcastReceiver myBroadcastReceiver = new MyBroadcastReceiver();
//        registerReceiver(myBroadcastReceiver, intentFilter1);
        //

        MyOrderBroadcastReceiver myOrderBroadcastReceiver = new MyOrderBroadcastReceiver();
        registerReceiver(myOrderBroadcastReceiver, intentFilter1);

        Button button = findViewById(R.id.send_my_broadcast);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                静态广播有问题，尽量使用动态注入广播
                 */
                Intent intent = new Intent("android.com.example.Activity.BroadCast.MY_BROADCAST");
//                intent.setComponent(new ComponentName(getPackageName(),"com.example.Activity.BroadCast.MyBroadcastReceiver"));
                intent.setComponent(new ComponentName(getPackageName(),"com.example.Activity.BroadCast.MyOrderBroadcastReceiver"));

//                sendBroadcast(intent); 无序广播
                sendOrderedBroadcast(intent, null);

            }
        });
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

    }

    //内部类

    class NetworkChangeReceiver extends BroadcastReceiver {

        /*
        只要定义一个类，继承自BroadcastReceiver就可以接受消息
         */
        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isAvailable()) {
                Toast.makeText(context, "network is available", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "network is unavailable", Toast.LENGTH_SHORT).show();

            }
        }
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(networkChangeReceiver);
        super.onDestroy();
    }
}


