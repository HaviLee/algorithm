/*
 * ************************************************************
 * 文件：Strings.java
 * 模块：MyApplication.app
 * 项目：MyApplication
 * 当前修改时间：2021年01月10日 12:08:27
 * 上次修改时间：2021年01月10日 12:08:27
 * 作者：Havi
 * Copyright (c) 2021
 * ************************************************************
 *
 */

package com.example.mj.printer;

public class Strings {
    public static String repeat(String string, int count) {
        if (string == null) return null;

        StringBuilder builder = new StringBuilder();
        while (count-- > 0) {
            builder.append(string);
        }
        return builder.toString();
    }

    public static String blank(int length) {
        if (length < 0) return null;
        if (length == 0) return "";
        return String.format("%" + length + "s", "");
    }
}

