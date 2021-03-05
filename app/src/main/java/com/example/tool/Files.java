/*
 * ************************************************************
 * 文件：Files.java
 * 模块：MyApplication.app
 * 项目：MyApplication
 * 当前修改时间：2021年01月10日 16:29:58
 * 上次修改时间：2021年01月10日 16:29:58
 * 作者：Havi
 * Copyright (c) 2021
 * ************************************************************
 *
 */

package com.example.tool;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class Files {

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static void writeToFile(String filePath, Object data) {
        writeToFile(filePath, data, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static void writeToFile(String filePath, Object data, boolean append) {
        if (filePath == null || data == null) return;

        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }

            try (FileWriter writer = new FileWriter(file, append)) {
                try (BufferedWriter out = new BufferedWriter(writer)) {
                    out.write(data.toString());
                    out.flush();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
