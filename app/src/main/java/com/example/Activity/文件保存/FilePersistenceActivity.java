/*
 * ************************************************************
 * 文件：FilePersistenceActivity.java
 * 模块：MyApplication.app
 * 项目：MyApplication
 * 当前修改时间：2020年12月28日 16:22:51
 * 上次修改时间：2020年12月28日 16:22:51
 * 作者：Havi
 * Copyright (c) 2020
 * ************************************************************
 *
 */

package com.example.Activity.文件保存;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.Activity.R;

import org.w3c.dom.Text;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class FilePersistenceActivity extends AppCompatActivity {

    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_persistence);
        editText = findViewById(R.id.input_file_view);

        TextView showText = findViewById(R.id.show_data_label);

        Button loadData = findViewById(R.id.load_data);
        loadData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    /*
    销毁的时候处理一些东西
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        save(editText.getText().toString());
    }

//    private String load() {
//        Fil
//    }

    private void save(String inputText) {
        FileOutputStream outputStream = null;
        BufferedWriter writer = null;
        try {
            outputStream = openFileOutput("data", Context.MODE_PRIVATE);
            writer = new BufferedWriter(new OutputStreamWriter(outputStream));
            writer.write(inputText);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}