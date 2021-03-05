/*
 * ************************************************************
 * 文件：_141_环形链表.java
 * 模块：MyApplication.app
 * 项目：MyApplication
 * 当前修改时间：2020年12月01日 21:24:09
 * 上次修改时间：2020年12月01日 21:24:09
 * 作者：Havi
 * Copyright (c) 2020
 * ************************************************************
 *
 */

package com.example.leetcode.链表;

public class _141_环形链表 {

    /*
    利用快慢指针：
    1)如果快指针先指向null;则表示没有环
    2)如果有环，则快慢指针最后会指向同一个数据；
     */
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) return  false;
        ListNode fast = head.next;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) return true;

        }
        return false;
    }
}
