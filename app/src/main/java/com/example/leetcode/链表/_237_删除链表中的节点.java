/*
 * ************************************************************
 * 文件：_237_删除链表中的节点.java
 * 模块：app
 * 项目：MyApplication
 * 当前修改时间：2020年12月01日 09:27:12
 * 上次修改时间：2020年12月01日 09:27:12
 * 作者：Havi
 * Copyright (c) 2020
 * ************************************************************
 * https://leetcode-cn.com/problems/delete-node-in-a-linked-list/
 */

package com.example.leetcode.链表;



public class _237_删除链表中的节点 {

    public void deleteNode(ListNode node) {
        ListNode nextNode = node.next;
        node.val = nextNode.val;
        node.next = nextNode.next;
    }

}


