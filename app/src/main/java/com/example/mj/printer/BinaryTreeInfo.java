/*
 * ************************************************************
 * 文件：BinaryTreeInfo.java
 * 模块：MyApplication.app
 * 项目：MyApplication
 * 当前修改时间：2021年01月10日 12:06:02
 * 上次修改时间：2021年01月10日 12:06:02
 * 作者：Havi
 * Copyright (c) 2021
 * ************************************************************
 *
 */

package com.example.mj.printer;

public interface BinaryTreeInfo {
    /**
     * who is the root node
     */
    Object root();
    /**
     * how to get the left child of the node
     */
    Object left(Object node);
    /**
     * how to get the right child of the node
     */
    Object right(Object node);
    /**
     * how to print the node
     */
    Object string(Object node);
}
