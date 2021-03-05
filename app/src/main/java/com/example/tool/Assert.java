/*
 * ************************************************************
 * 文件：Assert.java
 * 模块：MyApplication.app
 * 项目：MyApplication
 * 当前修改时间：2020年12月02日 22:34:22
 * 上次修改时间：2020年11月30日 22:01:11
 * 作者：Havi
 * Copyright (c) 2020
 * ************************************************************
 *
 */

package com.example.tool;

public class Assert {
    public Assert() {
    }

    public static void test(boolean value) {
        try {
            if (!value) {
                throw new Exception("测试未通过");
            }
        } catch (Exception var2) {
            var2.printStackTrace();
        }

    }
}
