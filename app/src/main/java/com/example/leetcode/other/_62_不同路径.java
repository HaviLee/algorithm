/*
 * ************************************************************
 * 文件：_62_不同路径.java
 * 模块：MyApplication.app
 * 项目：MyApplication
 * 当前修改时间：2020年12月09日 09:02:36
 * 上次修改时间：2020年12月09日 09:02:36
 * 作者：Havi
 * Copyright (c) 2020
 * ************************************************************
 *
 */

package com.example.leetcode.other;

public class _62_不同路径 {
    /*
    递归方法；超时
     */
    class Solution {
        public int uniquePaths(int m, int n) {
            return uniquePathsHelper(1, 1, m, n);
        }

        //第i行第j列到第m行第n列共有多少种路径
        public int uniquePathsHelper(int i, int j, int m, int n) {
            //边界条件的判断
            if (i > m || j > n)
                return 0;
            if ((i == m && j == n))
                return 1;
            //从右边走有多少条路径
            int right = uniquePathsHelper(i + 1, j, m, n);
            //从下边走有多少条路径
            int down = uniquePathsHelper(i, j + 1, m, n);
            //返回总的路径
            return right + down;
        }

    }
}
