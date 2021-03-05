/*
 * ************************************************************
 * 文件：AVLTree.java
 * 模块：MyApplication.app
 * 项目：MyApplication
 * 当前修改时间：2021年01月17日 16:26:43
 * 上次修改时间：2021年01月17日 16:26:43
 * 作者：Havi
 * Copyright (c) 2021
 * ************************************************************
 *
 */

package com.example.mj.二叉树;

/*
* 平衡二叉搜索树；
* 改进二叉搜索树：二叉搜索树节点增加、删除会导致其直接退化为链表；因此为了防止其退化为链表；应该在增加、删除节点后进行恢复平衡；
* 合理的方案：用尽量少的调整次数达到适度平衡即可；
* 常见的平衡二叉搜索树：
* -- AVL树
* -- 红黑树
* */

/*
* AVL树如何保证平衡的：
* -- 平衡因子：某个节点左右子树的高度差
* -- AVL树特点：
*    1）每个节点的平衡因子只可能为1、0、-1，如果绝对值<=1,称为失衡；
*    2）每个节点的左右子树的高度差不超过1
*    3）搜索、添加、删除的时间复杂度是O(logn)
*
* */

/*
* 添加导致的失衡：
* -- 二叉搜索树添加时在叶子节点的；
* -- 最坏的情况会导致所有的**祖先节点**都失衡；
* -- 父节点和非祖先节点不会失衡；
* */

/*
* LL - 右旋转（单旋转）
* -- g.left = p.right
* -- p.right = g
* -- p称为整个树的根节点
* 需要维护的内容；
* -- T2/p/g的parent属性
* -- 更新g/p的高度
* */

/*
* RR - 左旋转（单旋转）
* -- g.right = p.left
* -- p.left = g
* -- p称为这棵子树的根节点
* -- 整个树达到平衡
* -- 仍然为一颗二叉搜索树
* 需要注意维护的：
* -- T1、p、g的parent属性
* -- 更新g、p的高度；
* */

/*
* LR - RR左旋转 LL右旋转（双旋）
* RL - LL右旋转 RR左旋转（双旋）
* */

import java.util.Comparator;

public class AVLTree<E> extends BBST<E> {
    /*无参数构造函数*/
    public AVLTree() {
        this(null);
    }

    /*
    * 带有比较器的构造函数
    * */
    public AVLTree(Comparator<E> comparator) {
        super(comparator);
    }

    /*
    * 添加操作之后的调整；
    * AVL树的添加：
    * 1、可能导致所有祖先节点都失衡，
    * 2、只需要高度最低的失衡节点恢复平衡即可，整棵树恢复平衡仅需要O(1)次调整；
    * */
    @Override
    protected void afterAdd(Node node) {
        super.afterAdd(node);
        //需要根据parent属性找到最低的失衡的节点；
        while ((node = node.parent) != null) {
            //判断这个node是否平衡
            if (isBalance(node)) {
                //平衡的节点；顺便把节点的高度更新
                updateHeight(node);
            } else {
                //进行平衡;来到这里表示找到第一个不平衡的
                rebalance(node);
                break;
            }
        }
    }

    /*
    * 删除操作
    * 1、删除只会导致父节点失衡或者祖先节点（只会导致一个节点失衡）
    * 2、让父节点恢复平衡后，可能导致更高节点祖先节点失衡，最多需要O(logn)次调整，
    * */

    @Override
    protected void afterRemove(Node<E> node) {
        super.afterRemove(node);
        //需要根据parent属性找到最低的失衡的节点；
        while ((node = node.parent) != null) {
            //判断这个node是否平衡
            if (isBalance(node)) {
                //平衡的节点；顺便把节点的高度更新
                updateHeight(node);
            } else {
                //进行平衡;来到这里表示找到第一个不平衡的
                //和增加的区别之处，要把所有的parent都找完
                rebalance(node);
            }
        }
    }

    //为了将二叉搜索树中的元素变为AVLNode;
    @Override
    protected Node createNode(Object element, Node parent) {
        return new AVLNode(element, parent);
    }

    /*
    * 判断一个节点是否为平衡的
    * */
    private boolean isBalance(Node<E> node) {
        //平衡因子绝对值小于等于1，表示这个节点是平衡的；
        return Math.abs(((AVLNode<E>)node).balanceFactor()) <= 1;
    }

    /*
    * 进行统一旋转处理；
    * */
    private void rebalance(Node<E> grand) {
        //统一旋转的操作就是找到a,b,c,d,e,f,g节点，最终四种旋转结果都是一样的；
        //找出插入节点的父节点
        Node<E> parent = ((AVLNode<E>)grand).tallerChild();
        //找出最后的节点
        Node<E> node = ((AVLNode<E>)parent).tallerChild();
        //接下来判断LL LR RL
        if (parent.isLeftChild()) { //L
            if (node.isLeftChild()) {
                //LL
                rotate(grand,
                        node.left, node, node.right,
                        parent,
                        parent.right, grand, grand.right);
            } else {
                //LR
                rotate(grand,parent.left, parent, node.left, node, node.right, grand, grand.right);
            }
        } else { //R
            if (node.isLeftChild()) {
                //RL
                rotate(grand,grand.left, grand, node.left, node, node.right, parent, parent.right);
            } else {
                //RR
                rotate(grand,grand.left, grand, parent.left, parent, node.left, node, node.right);
            }
        }
    }

    @Override
    protected void rotate(Node<E> r, Node<E> a, Node<E> b, Node<E> c, Node<E> d, Node<E> e, Node<E> f, Node<E> g) {
        super.rotate(r, a, b, c, d, e, f, g);
        //更新高度
        updateHeight(b);
        updateHeight(f);
        updateHeight(d);
    }

    /*
    * 恢复平衡：针对不同情况，有不同旋转
    * ie: 这里的node绝对高度最低的不平衡节点
    * */
    private void rebalance1(Node<E> grand) {
        //这里采用的方法从祖父往下依次确认出父节点
        //找出插入节点的父节点
        Node<E> parent = ((AVLNode<E>)grand).tallerChild();
        //找出最后的节点
        Node<E> node = ((AVLNode<E>)parent).tallerChild();
        //接下来判断LL LR RL
        if (parent.isLeftChild()) { //L
            if (node.isLeftChild()) {
                //LL
                rotateRight(grand);
            } else {
                //LR
                rotateLeft(parent);
                rotateRight(grand);
            }
        } else { //R
            if (node.isLeftChild()) {
                //RL
                rotateRight(parent);
                rotateLeft(grand);
            } else {
                //RR
                rotateLeft(grand);
            }
        }
    }

    /*
    * 左旋，右旋之后的操作
    * */
    @Override
    protected void afterRotate(Node<E> grand, Node<E> parent, Node<E> child) {
        super.afterRotate(grand, parent, child);
        //4、更新子树的高度,注意顺序
        updateHeight(grand);
        updateHeight(parent);
    }

    /*
    * 更新高度
    * */
    private void updateHeight(Node<E> node) {
        //把强制转的代码封装了
        AVLNode<E> avlNode = (AVLNode<E>) node;
        //自己的高度
        avlNode.updateHeight();
    }

    /*
    * AVL树需要一个height属性，因此添加新的node类给AVL
    * */
    private static class AVLNode<E> extends Node<E> {
        //保存树的高度；默认新添加的节点都是叶子节点，并且高度是1
        int height = 1;

        public AVLNode(E element, Node<E> parent) {
            super(element, parent);
        }

        /*
        * 获取每个节点的平衡因子
        * */
        public int balanceFactor() {
            int leftHeight = left == null ? 0: ((AVLNode<E>)left).height;
            int rightHeight = right == null ? 0: ((AVLNode<E>)right).height;
            return leftHeight - rightHeight;
        }
        /*
        * 每个节点都有一个更新自己的高度的方法
        * */
        public void updateHeight() {
            int leftHeight = left == null ? 0: ((AVLNode<E>)left).height;
            int rightHeight = right == null ? 0: ((AVLNode<E>)right).height;
            height = 1 + Math.max(leftHeight, rightHeight);
        }

        /*
        * 找到左右子树中，比较高的子树
        * 比较左右子树的高度，返回较高子树
        * */
        public Node<E> tallerChild() {
            int leftHeight = left == null ? 0: ((AVLNode<E>)left).height;
            int rightHeight = right == null ? 0: ((AVLNode<E>)right).height;
            if (leftHeight > rightHeight) return left;
            if (leftHeight < rightHeight) return right;
            // 如果两个子节点高度一样，这时候返回和祖父节点同侧的子树
            return isLeftChild() ? left : right;
        }

        /*
        * 打印器
        * */

        @Override
        public String toString() {
            return "AVLNode{" +
                    "height=" + height +
                    ", element=" + element +
                    '}';
        }
    }
}
















