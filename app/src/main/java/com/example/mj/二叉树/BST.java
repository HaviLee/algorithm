/*
 * ************************************************************
 * 文件：BST.java
 * 模块：MyApplication.app
 * 项目：MyApplication
 * 当前修改时间：2021年01月16日 15:53:15
 * 上次修改时间：2021年01月16日 15:27:31
 * 作者：Havi
 * Copyright (c) 2021
 * ************************************************************
 *
 */

package com.example.mj.二叉树;

/*
二叉搜索树：
    -- 任意一个节点的值都大于其左子树所有的节点
    -- 任意一个节点的值都小于其右子树所有的节点
    -- 左右子树也是一颗二叉搜索树

    -- 二叉搜索树存储的元素必须具备可比较性
    -- 自定义类型需要制定比较方式
    -- 数据不允许为空；否则无法比较大小

    -- 和动态数组和链表比较少了索引的概念；

 前驱节点：中序遍历的前一个节点
 */


import java.util.Comparator;

public class BST<E> extends BinaryTree {

    //二叉搜索树特有的
    private Comparator<E> comparator;

    public BST(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    public BST() {
    }

    /*
     * 添加*/
    public void add(E element) {
        elementNotNullCheck(element);
        /*
         * 首先判断根节点是不是为空，为空则需要创建根节点*/
        if (root == null) {
            root = createNode(element, null);
            size++;
            //第一个是在这个地方添加节点：
            afterAdd(root);
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
                node = node.right;
            } else if (cmp < 0) {
                node = node.left;
            } else {
                //相等直接返回；尽量使用新的值覆盖；这个对自定义对象很有用；
                node.element = element;
                return;
            }
        }
        Node<E> newNode = createNode(element, parent);
        //看看插入到父节点位置
        if (cmp > 0) {
            parent.right = newNode;
        } else {
            parent.left = newNode;
        }
        size++;
        //新添加节点之后的处理
        afterAdd(newNode);
    }

    /*
     * 添加node之后调整：node是新添加的节点
     * 是给子类实现的
     *
     * */

    protected void afterAdd(Node<E> node) {

    }

    /*
     * 删除节点后导致失衡：
     * ：只会导致父节点失衡，因为其他节点的高度计算和这个节点无关
     * node:被删除的节点
     * replacement：取代这个删除的节点的
     * */
    protected void afterRemove(Node<E> node) {

    }

    /*
     * 移除某个元素
     * 1、删除度为0的节点；--叶子节点
     *    -- 直接删除就可以
     *    1）node == node.parent.left => node.parent.left = null
     *    2) node == node.parent.right => node.parent.right = null
     *    3) node.parent == null => root = null
     *
     * 2、删除度为1的节点；
     *    1）child是node.left || child 是node.right 用子节点替代原节点的位置
     *     -- 左子节点：child.parent = node.parent  &&  node.parent.left = child
     *     -- 右子节点：child.parent = node.parent && node.parent.right = child
     *    2) node是根节点
     *     -- root = child && child.parent = null
     * 3、删除度为2的节点；
     *    一般做法是从左右子树找一个节点替代；利用前驱或者后继节点代替源节点的值，然后删除前驱或者后继节点
     *    -- 如果一个节点度为2，那么他的前驱、后继节点只可能为1或者0；
     * */
    public void remove(E element) {
        remove(node(element));
    }

    public void remove(Node<E> node) {
        if (node == null) return;
        //首先处理度为2的节点
        if (node.hasTwoChildren()) { //代表度为2的节点
            Node<E> s = successor(node);//找到后继节点
            //用后继节点的值替换要删除的节点内容，
            node.element = s.element;
            //删除后继节点
            node = s;//接下来处理删除节点

        }
        //来到这个地方，节点的度一定为1或者0
        Node<E> replacement = node.left != null ? node.left : node.right;

        if (replacement != null) { //表示node为1的节点
            //更改replace parent
            replacement.parent = node.parent;
            if (node.parent == null) { //node度为1且为根节点
                root = replacement;
            } else if (node == node.parent.left) {
                node.parent.left = replacement;
            } else if (node == node.parent.right) {
                node.parent.right = replacement;
            }
            //对于删除节点，真正导致失衡是真正删除的节点，
            // 1、而不是被替换的节点；所以afterR
            // 2、要等删除的节点parent的left,right更改之后
            afterRemove(replacement);
        } else if (node.parent == null){
            //来到这里表示node是叶子节点,并且是根节点
            root = null;
            // 删除节点之后的处理
            afterRemove(node);
        } else {
            //这里是叶子节点，但不是根节点
            if (node == node.parent.left) {
                node.parent.left = null;
            } else {
                node.parent.right = null;
            }
            // 删除节点之后的处理
            afterRemove(node);
        }


        size--;
    }

    /*
    * 二叉搜索树根据元素查找节点*/
    private Node<E> node(E element) {
        Node<E> node = root;
        while (node != null) {
            int cmp = compare(element, node.element);
            if (cmp == 0) return node;//刚好是这个元素
            if (cmp > 0) {
                node = node.right;
            } else {
                node = node.left;//element比这个节点小，去左子树找
            }
        }
        return null;
    }

    /*
     * 是否包含某个元素*/
    public boolean contains(E element) {
        //是查找了node
        return node(element) != null;
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
        /*
        如果外部有传比较器：直接调用外部的比较器,
        如果没有比较器，对比较对象强制比较；
         */
        if (comparator != null) {
            return comparator.compare(e1,e2);
        }
        //这里强制检测元素是否具有比较性
        return ((Comparable<E>) e1).compareTo(e2);
    }

    /*
     * 检测元素是否为空*/
    private boolean elementNotNullCheck(E element) {
        if (element == null) {
            throw new IllegalArgumentException("element must not be null");
        }
        return false;
    }

    /*
    * 自己来做打印前序打印*/
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        toString(root, builder, "");
        return builder.toString();
    }

    private void toString(Node<E> node, StringBuilder sb, String prefix) {
        if (node == null) return;
        sb.append(prefix).append(node.element).append("\n");
        toString(node.left, sb, prefix + "---");
        toString(node.right, sb, prefix + "---");
    }
}
