/*
 * ************************************************************
 * 文件：Msg.java
 * 模块：MyApplication.app
 * 项目：MyApplication
 * 当前修改时间：2020年12月21日 22:19:45
 * 上次修改时间：2020年12月21日 22:18:44
 * 作者：Havi
 * Copyright (c) 2020
 * ************************************************************
 *
 */

package com.example.Activity.Message;

import java.net.PortUnreachableException;

public class Msg {
    public static final int TYPE_RECEIVED = 0;
    public static final int TYPE_SEND = 1;
    private String content;

    private int type;

    /*
    构造函数
     */
    public Msg(String content, int type) {
        this.content = content;
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public int getType() {
        return type;
    }
}
