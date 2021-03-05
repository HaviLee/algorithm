/*
 * ************************************************************
 * 文件：LinkedHashMap.java
 * 模块：MyApplication.app
 * 项目：MyApplication
 * 当前修改时间：2021年01月31日 16:55:00
 * 上次修改时间：2021年01月31日 16:55:00
 * 作者：Havi
 * Copyright (c) 2021
 * ************************************************************
 *
 */

package com.example.mj.Map;

/*
* LinkedHashMap:为了维持一个添加顺序
*
* */

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.Objects;

@RequiresApi(api = Build.VERSION_CODES.KITKAT)

public class LinkedHashMap<K,V> extends HashMap<K,V> {

    //需要记录最开始的头
    private LinkedNode<K,V> first;
    private LinkedNode<K,V> last;

    /*
    * 需要让子类实现这个node方法；
    * */
    @Override
    protected Node<K, V> createNode(K key, V value, Node<K, V> parent) {
        /*
        * 因为新加的节点肯定要调用这个；
        * */
        LinkedNode<K, V> node = new LinkedNode<>(key, value, parent);
        //表示创建的时第一个节点；
        if (first == null) {
            first = last = node;
        } else {
            //添加的不是第一个节点；只需要往节点最后添加即可；
            last.next = node;
            node.prev = last;
            last = node;

        }
        return node;
    }

    /*
    * 做删除链表中的元素；
    * */
    @Override
    protected void afterRemove(Node<K,V> willNode, Node<K, V> removeNode) {
        LinkedNode<K,V> node1 = ((LinkedNode) willNode);
        LinkedNode<K,V> node2 = ((LinkedNode) removeNode);

        if (willNode != removeNode) {
            //这里交互两个node在链表的位置；
            //1、交换prev
            LinkedNode<K, V> tmp = node1.prev;
            node1.prev = node2.prev;
            node2.prev = tmp;
            if (node1.prev == null) {
                //根节点
                first = node1;
            } else {
                node1.prev.next = node1;
            }

            if (node2.prev == null) {
                first = node2;
            } else {
                node2.prev.next = node2;
            }

            //2、交换next
            tmp = node1.next;
            node1.next = node2.next;
            node2.next = tmp;
            if (node1.next == null) {
                //根节点
                last = node1;
            } else {
                node1.next.prev = node1;
            }

            if (node2.next == null) {
                last = node2;
            } else {
                node2.next.prev = node2;
            }
        }
        LinkedNode<K,V> prev = node2.prev;
        LinkedNode<K,V> next = node2.next;

        if (prev == null) {//表示被删除的节点是头结点
            first = next;
        } else {
            prev.next = next;
        }

        if (next == null) {//删除的时尾节点
            last = prev;
        } else {
            next.prev = prev;
        }
    }

    /*
    * linked hash map可以采用链表的形式进行遍历；
    * */
    @Override
    public boolean containsValue(V value) {
        LinkedNode<K,V> node = first;
        while (node != null) {
            if (Objects.equals(value, node.value)) return true;
            node = node.next;
        }
        return false;
    }

    @Override
    public void traversal(Visitor<K, V> visitor) {
        //就是根据链表进行遍历
        if (visitor == null) return;
        LinkedNode<K, V> node = first;
        while (node != null) {
            if (visitor.visit(node.key, node.value)) return;
            node = node.next;
        }

    }

    @Override
    public void clear() {
        super.clear();
        //同时需要清空
        first = null;
        last = null;
    }

    private static class LinkedNode<K,V> extends Node<K, V> {
        LinkedNode<K, V> prev;
        LinkedNode<K, V> next;

        public LinkedNode(K key, V value, Node<K, V> parent) {
            super(key, value, parent);
        }
    }
}
