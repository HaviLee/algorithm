/*
 * ************************************************************
 * 文件：BBST.java
 * 模块：MyApplication.app
 * 项目：MyApplication
 * 当前修改时间：2021年01月22日 14:25:23
 * 上次修改时间：2021年01月22日 14:25:23
 * 作者：Havi
 * Copyright (c) 2021
 * ************************************************************
 *
 */

package com.example.mj.二叉树;

/*
* B树：是一种平衡多路搜索树，多用于文件系统、数据库实现
* 1、一个节点可以存储超过2个元素、可以拥有超过2个节点；
* 2、拥有二叉搜索树的一些性质
* 3、平衡，每个节点的所有子树高度一致
* 4、比较矮
* */

/*
* m (m>=2)阶B树：一个节点最多有m个子树；
* 1、假设一个节点存储的元素个数为x
*   -- 根节点： 1 <= x <= m-1
*   -- 非根节点：celling(m/2) <= x <= m-1:向上取整
*   -- 如果有子节点：
*  向兄弟节点借一个节点，就是旋转；
* */

/*
* 这一层是将平衡的操作拿出来；
* */

import java.util.Comparator;

public class BBST<E> extends BST<E> {

    public BBST(Comparator<E> comparator) {
        super(comparator);
    }

    public BBST() {
    }
    /*
    * 统一旋转处理
    * */

    protected void rotate(Node<E> r,//表示根节点
                        Node<E> a, Node<E> b, Node<E> c,
                        Node<E> d,
                        Node<E> e, Node<E> f, Node<E> g) {
        //1、首先将d成为子树的根节点
        d.parent = r.parent;
        if (r.isLeftChild()) {
            r.parent.left = d;
        } else if (r.isRightChild()) {
            r.parent.right = d;
        } else { //根节点处理
            root = d;
        }
        //2、处理a-b(root)-c
        b.left = a;
        if (a != null) {
            a.parent = b;
        }
        b.right = c;
        if (c != null) {
            c.parent = b;
        }

        //3、处理e-f(root)-g
        f.left = e;
        if (e != null) {
            e.parent = f;
        }
        f.right = g;
        if (g != null) {
            g.parent = f;
        }
        //4、处理b-d(root)-f
        d.left = b;
        d.right = f;
        b.parent = d;
        f.parent = d;

    }

    /*
     * 左旋转操作
     * */
    protected void rotateLeft(Node<E> grand) {
        //1、修改指向
        Node<E> parent = grand.right;
        Node<E> child = parent.left;
        grand.right = child;
        parent.left = grand;
        //2.更新parent
        afterRotate(grand, parent, child);
    }

    /*
     * 右旋转操作
     * */
    protected void rotateRight(Node<E> grand) {
        //1.找Parent&待旋转点
        //找到左字节点
        Node<E> parent = grand.left;
        //找到child节点
        Node<E> child = parent.right;
        //2、进行交换
        grand.left = child;
        parent.right = grand;
        afterRotate(grand, parent, child);
    }

    /*
     * 将旋转后的操作抽离出来；
     * */
    protected void afterRotate(Node<E> grand, Node<E> parent, Node<E> child) {
        //3、交换父节点
        parent.parent = grand.parent;
        if (grand.isLeftChild()) {
            grand.parent.left = parent;
        } else if (grand.isRightChild()) {
            grand.parent.right = parent;
        } else {
            //根节点的情况
            root = parent;
        }
        // 更新child的parent
        if (child != null) {
            child.parent = grand;
        }
        // 更新grand的parent
        grand.parent = parent;

    }
}
