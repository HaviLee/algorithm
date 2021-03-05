/*
 * ************************************************************
 * 文件：SecondActivity.java
 * 模块：app
 * 项目：MyApplication
 * 当前修改时间：2020年12月02日 17:54:32
 * 上次修改时间：2020年12月02日 17:54:31
 * 作者：Havi
 * Copyright (c) 2020
 * ************************************************************
 *
 */

package com.example.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.Activity.ActivityManager.BaseActivity;

public class SecondActivity extends BaseActivity {

    //可以通过暴露一个启动activity的方法；解决参数问题

    public static void actionStart(Context context, String data1) {
        Intent intent = new Intent(context, SecondActivity.class);
        intent.putExtra("extra_data", data1);
        context.startActivity(intent);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        final Intent intent = this.getIntent();
        String data = intent.getStringExtra("extra_data");
        Log.d("Section Activity", data);
        Button button = findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent();
                intent1.putExtra("data_return","hello first");
                setResult(RESULT_OK,intent1);
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent();
        intent.putExtra("data_return","hello first");
        setResult(RESULT_OK, intent);
        finish();
    }
}