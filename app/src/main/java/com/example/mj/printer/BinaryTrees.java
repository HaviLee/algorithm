/*
 * ************************************************************
 * 文件：BinaryTrees.java
 * 模块：MyApplication.app
 * 项目：MyApplication
 * 当前修改时间：2021年01月10日 12:06:39
 * 上次修改时间：2021年01月10日 12:06:39
 * 作者：Havi
 * Copyright (c) 2021
 * ************************************************************
 *
 */

package com.example.mj.printer;

public final class BinaryTrees {

    private BinaryTrees() {
    }

    public static void print(BinaryTreeInfo tree) {
        print(tree, null);
    }

    public static void println(BinaryTreeInfo tree) {
        println(tree, null);
    }

    public static void print(BinaryTreeInfo tree, PrintStyle style) {
        if (tree == null || tree.root() == null) return;
        printer(tree, style).print();
    }

    public static void println(BinaryTreeInfo tree, PrintStyle style) {
        if (tree == null || tree.root() == null) return;
        printer(tree, style).println();
    }

    public static String printString(BinaryTreeInfo tree) {
        return printString(tree, null);
    }

    public static String printString(BinaryTreeInfo tree, PrintStyle style) {
        if (tree == null || tree.root() == null) return null;
        return printer(tree, style).printString();
    }

    private static Printer printer(BinaryTreeInfo tree, PrintStyle style) {
        if (style == PrintStyle.INORDER) return new InorderPrinter(tree);
        return new LevelOrderPrinter(tree);
    }

    public enum PrintStyle {
        LEVEL_ORDER, INORDER
    }
}
