/*
 * ************************************************************
 * 文件：LoginActivity.java
 * 模块：MyApplication.app
 * 项目：MyApplication
 * 当前修改时间：2020年12月28日 09:16:58
 * 上次修改时间：2020年12月28日 09:16:58
 * 作者：Havi
 * Copyright (c) 2020
 * ************************************************************
 *
 */

package com.example.Activity.强制下线;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.Activity.ActivityManager.BaseActivity;
import com.example.Activity.R;

public class LoginActivity extends BaseActivity {

    private EditText accountEdit;
    private EditText passEdit;
    private Button login;
    private Button logout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        accountEdit = findViewById(R.id.account);
        passEdit = findViewById(R.id.pass);

        login = findViewById(R.id.login_button);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (accountEdit.getText().toString().equals("admin") && passEdit.getText().toString().equals("123456")) {
                    Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                }
            }
        });

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.offline");
        ForceOffLineReceiver forceOffLineReceiver = new ForceOffLineReceiver();
        registerReceiver(forceOffLineReceiver, intentFilter);

        logout = findViewById(R.id.force_logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("android.intent.action.offline");
                intent.setComponent(new ComponentName(getPackageName(),"com.example.Activity.强制下线.ForceOffLineReceiver"));
                sendBroadcast(intent);
            }
        });
    }
}