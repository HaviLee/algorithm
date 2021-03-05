/*
 * ************************************************************
 * 文件：TimeTools.java
 * 模块：MyApplication.app
 * 项目：MyApplication
 * 当前修改时间：2020年12月02日 22:34:15
 * 上次修改时间：2020年11月30日 22:01:11
 * 作者：Havi
 * Copyright (c) 2020
 * ************************************************************
 *
 */

package com.example.tool;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeTools {

    /*
    定义一个时间格式化的常量；
     */
    private static final SimpleDateFormat fmt = new SimpleDateFormat("HH:mm:ss.SSS");

    /*
    定义了一个接口；协议
     */
    public interface Task {
        void execute();
    }

    /*
    定义的任务需要实现这样的接口
     */
    public static void check(String title, Task task) {
        if (task == null) return;
        title = title == null ? "" : ("【" + title + "】");
        System.out.println(title);
        System.out.println("开始" + fmt.format(new Date()));
        long begin = System.currentTimeMillis();
        task.execute();
        long end = System.currentTimeMillis();
        System.out.println("结束：" + fmt.format(new Date()));
        double delta = (end - begin)/1000.0;
        System.out.println("耗时："+delta+"秒");
        System.out.println("--------------------------------------------");
    }

}
