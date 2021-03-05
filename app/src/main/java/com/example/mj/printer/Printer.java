/*
 * ************************************************************
 * 文件：Printer.java
 * 模块：MyApplication.app
 * 项目：MyApplication
 * 当前修改时间：2021年01月10日 12:07:15
 * 上次修改时间：2021年01月10日 12:07:15
 * 作者：Havi
 * Copyright (c) 2021
 * ************************************************************
 *
 */

package com.example.mj.printer;

public abstract class Printer {
    /**
     * 二叉树的基本信息
     */
    protected BinaryTreeInfo tree;

    public Printer(BinaryTreeInfo tree) {
        this.tree = tree;
    }

    /**
     * 生成打印的字符串
     */
    public abstract String printString();

    /**
     * 打印后换行
     */
    public void println() {
        print();
        System.out.println();
    }

    /**
     * 打印
     */
    public void print() {
        System.out.print(printString());
    }
}

