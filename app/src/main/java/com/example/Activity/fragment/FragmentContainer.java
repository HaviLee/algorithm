/*
 * ************************************************************
 * 文件：FragmentContainer.java
 * 模块：MyApplication.app
 * 项目：MyApplication
 * 当前修改时间：2020年12月21日 22:25:00
 * 上次修改时间：2020年12月21日 21:32:44
 * 作者：Havi
 * Copyright (c) 2020
 * ************************************************************
 *
 */

package com.example.Activity.fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.Activity.R;
import com.example.Activity.fragment.AnotherRightFragment;
import com.example.Activity.fragment.RightFragment;

public class FragmentContainer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_container);
        replaceFragment(new RightFragment());
        Button button = findViewById(R.id.fragment_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.fragment_button:
                        replaceFragment(new AnotherRightFragment());
                        break;
                }
            }
        });

    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.right_fragment_view, fragment);
        //此句代码代表是模拟栈的情况
        transaction.addToBackStack(null);
        transaction.commit();
    }
}