/*
 * ************************************************************
 * 文件：_226_翻转二叉树.java
 * 模块：MyApplication.app
 * 项目：MyApplication
 * 当前修改时间：2021年01月15日 17:00:46
 * 上次修改时间：2021年01月15日 17:00:46
 * 作者：Havi
 * Copyright (c) 2021
 * ************************************************************
 *
 */

package com.example.leetcode.二叉树;

import java.util.LinkedList;
import java.util.Queue;

public class _226_翻转二叉树 {

    //层序遍历
    public TreeNode invertTree3(TreeNode root) {
        if (root == null) return root;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            TreeNode tmp = node.left;
            node.left = node.right;
            node.right = tmp;
            if (node.left != null) queue.offer(node.left);
            if (node.right != null) queue.offer(node.right);
        }

        return root;
    }

    //中序遍历
    public TreeNode invertTree2(TreeNode root) {
        if (root == null) return root;

        invertTree2(root.left);
        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;
        //这是个易错点
        invertTree2(root.left);
        return root;
    }

    //后序遍历
    public TreeNode invertTree1(TreeNode root) {
        if (root == null) return root;

        invertTree1(root.left);
        invertTree1(root.right);
        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;
        return root;
    }

    //前序遍历

    public TreeNode invertTree(TreeNode root) {
        if (root == null) return root;
        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;

        invertTree(root.left);
        invertTree(root.right);
        return root;
    }


}

