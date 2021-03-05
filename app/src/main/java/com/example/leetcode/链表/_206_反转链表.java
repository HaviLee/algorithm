/*
 * ************************************************************
 * 文件：_206_反转链表.java
 * 模块：app
 * 项目：MyApplication
 * 当前修改时间：2020年12月01日 12:39:10
 * 上次修改时间：2020年12月01日 12:39:10
 * 作者：Havi
 * Copyright (c) 2020
 * ************************************************************
 *
 */

package com.example.leetcode.链表;

public class _206_反转链表 {

    class Solution {
        /*
        递归实现
        传给什么返回的是已完成的
         */
        public ListNode reverseList(ListNode head) {
            if (head == null || head.next == null) return head;
            ListNode newHead = reverseList(head.next);
            head.next.next = head;
            head.next = null;
            return newHead;
        }

        /*
        非递归方式
         */
        public ListNode reverseList1(ListNode head) {
            if (head == null || head.next == null) return head;
            ListNode newHead = null;
            while (head != null) {
                ListNode tmp = head.next;
                head.next = newHead;
                newHead = head;
                head = tmp;
            }
            return  newHead;
        }
    }
}




















