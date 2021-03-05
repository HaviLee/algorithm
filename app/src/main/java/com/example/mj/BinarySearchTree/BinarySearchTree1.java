/*
 * ************************************************************
 * 文件：BinarySearchTree1.java
 * 模块：MyApplication.app
 * 项目：MyApplication
 * 当前修改时间：2020年12月30日 09:05:30
 * 上次修改时间：2020年12月30日 09:05:24
 * 作者：Havi
 * Copyright (c) 2020
 * ************************************************************
 *
 */

package com.example.mj.BinarySearchTree;

/*
二叉搜索树：
    -- 任意一个节点的值都大于其左子树所有的节点
    -- 任意一个节点的值都小于其右子树所有的节点
    -- 左右子树也是一颗二叉搜索树

    -- 二叉搜索树存储的元素必须具备可比较性
    -- 自定义类型需要制定比较方式
    -- 数据不允许为空；否则无法比较大小

    -- 和动态数组和链表比较少了索引的概念；
 */

/*
解决比较的问题：
这是第一个方法，可以自定义一个比较接口，bst里面的元素必须实现这个比较接口的方法；
缺点：比较逻辑不灵活；如果对于同一个person可能需要比较不同的属性；
 */
public class BinarySearchTree1<E extends Comparable> {

    private int size;
    private Node<E> root;
    /*
    * 元素个数*/
    public int size() {
        return 0;
    };

    /*
    * 是否为空*/
    public boolean isEmpty() {
        return false;
    }

    /*
    * 清空*/
    public void clear() {

    }

    /*
    * 添加*/
    public void add(E element) {
        elementNotNullCheck(element);
        /*
        * 首先判断根节点是不是为空，为空则需要创建根节点*/
        if (root == null) {
            root = new Node<E>(element,null);
            size++;
            return;
        }
        /*
        * 进入这里表示添加的不是根节点
        * 思路：
        * 首先找到父节点：parent;
        * 创建新的节点node；插入：parent.left = node 或 parent.right = node
        * 相等值？
        * 需要单独处理
        * */
        // 1.找到父节点
        Node<E> parent = root;
        Node<E> node = root;
        int cmp = 0;
        while (node != null) {
            cmp = compare(element, node.element);
            //进行下一轮比较的时候，先保存父节点
            parent = node;
            if (cmp > 0) {
                node = root.right;
            } else if (cmp < 0) {
                node = root.left;
            } else {
                //相等直接返回；
                return;
            }
        }
        Node<E> newNode = new Node<>(element, parent);
        //看看插入到父节点位置
        if (cmp > 0) {
            parent.right = newNode;
        } else {
            parent.left = newNode;
        }
        size++;

    }

    /*
    * 移除某个元素*/
    public void remove(E element) {

    }

    /*
    * 是否包含某个元素*/
    public boolean contains(E element) {
        return false;
    }

    /*
    返回值 等于0：e1==e2
          < 0: e1 < e2
          > 0: e1 > e2
     */
    private int compare(E e1, E e2) {
        /*
        对与自定义对象，可以自定义一个可比较的接口
         */
        return 0;
    }

    /*
    * 检测元素是否为空*/
    private boolean elementNotNullCheck(E element) {
        if (element == null) {
            throw new IllegalArgumentException("element must not be null");
        }
        return false;
    }

    private static class Node<E> {
        E element; //存储的自己的元素
        Node<E> left; //存储左右节点
        Node<E> right;
        Node<E> parent;

        public Node(E element, Node<E> parent) {
            //左右节点不一定存在
            this.element = element;
            this.parent = parent;
        }
    }
}
