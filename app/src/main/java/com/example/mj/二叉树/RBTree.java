/*
 * ************************************************************
 * 文件：RBTree.java
 * 模块：MyApplication.app
 * 项目：MyApplication
 * 当前修改时间：2021年01月22日 14:23:26
 * 上次修改时间：2021年01月22日 14:23:26
 * 作者：Havi
 * Copyright (c) 2021
 * ************************************************************
 *
 */

package com.example.mj.二叉树;

/*
* 红黑树：也是一种自平衡二叉搜索树
* 红黑树条件：
* 1、节点是RED或者BLACK
* 2、根节点是BLACK
* 3、叶子节点（外部节点、空节点）都是BLACK
* 4、红色节点的子节点都是黑色
*  -- 红色节点的父节点都是黑色
*  -- 从根节点到叶子节点的所有路径不能有两个连续的RED节点；
* 5、从任一节点到叶子节点的所有路径都包含相同数据的BLACK节点；
* */

/*
* 红黑树，注意叶子节点
* 红黑树的转换：
* 1、红黑树和4阶B树具有等价性；
* 2、Black节点与它的Red节点融合在一起形成 一个B树节点；
* 3、红黑树的黑色节点个数和4阶B树节点总数相等
*
* */

import java.util.Comparator;

public class RBTree<E> extends BBST<E> {

    /*
    * 定义常量：
    * */
    private static final boolean RED = false;
    private static final boolean BLACK = true;

    public RBTree(Comparator<E> comparator) {
        super(comparator);
    }

    public RBTree() {
    }

    @Override
    protected Node createNode(Object element, Node parent) {
        return new RBNode(element,parent);
    }

    /*
    * 红黑树添加:四种情况
    * 1、新增节点的parent为BLACK;
    *   -- 依然是红黑树，不需要处理；
    * 2、Uncle节点不是RED
    *   --LL/RR情形
    *     1）parent染成Black,grand染成Red;
    *     2）grand进行单旋转；
    *   --LR/RL情形
    *     1）自己染成Black,grand染成Red;
    *     2）双旋转操作
    *       -- LR parent 左旋转，grand右旋转
    *       -- RL parent 右旋转，grand左旋转
    * 3、Uncle节点是Red;
    *   -- LL 上溢
    *     1）parent, uncle染成Black；
    *     2）grand向上合并；
    *       -- grand染成Red,并当做新添加的节点进行操作；
    *   -- RR 上溢
    *     1）parent, uncle染成Black；
     *     2）grand向上合并；
     *       -- grand染成Red,并当做新添加的节点进行操作；
     *  -- LR 上溢
     *  -- RL 上溢
     *     1）parent, uncle染成Black；
     *     2）grand向上合并；
     *       -- grand染成Red,并当做新添加的节点进行操作；
    * */

    /*
    * 添加之后进行恢复红黑树操作
    * */
    @Override
    protected void afterAdd(Node<E> node) {
        //传进来的时新添加节点
        super.afterAdd(node);
        Node<E> parent = node.parent;
        //1.先判断父节点：同时也是处理上溢到根节点；
        //如果是添加的时根节点
        if (parent == null) {
            black(node);
        }
        // 如果父节点是黑色，这种场景不用处理
        if (isBlack(parent)) return;
        //2.判断uncle节点
        Node<E> uncle = parent.sibling();
        Node<E> grand = parent.parent;
        if (isRed(uncle)) { //uncle节点是红色的，上溢;
            black(parent);
            black(uncle);
            //祖父节点作为新添加的节点
            afterAdd(red(grand));//再重新检查
            return;
        }
        //下面的时叔父节点不是红色的
        if (parent.isLeftChild()) { //L
            red(grand);
            if (node.isLeftChild()) { //LL
                black(parent);
            } else { //LR
                black(node);
                rotateLeft(parent);
            }
            rotateRight(grand);

        } else { //R
            red(grand);
            if (node.isLeftChild()) {//RL
                black(node);
                rotateRight(parent);
            } else {//RR
                black(parent);
            }
            rotateLeft(grand);

        }

    }

    /*
    * 删除之后的恢复红黑树操作
    * 1、删除Red节点
    *   -- 直接删除，不需要处理
    * 2、删除Black节点
    *   1)拥有两个RED子节点的Black节点；
    *   -- 不可能直接被删除，因为会找他的子节点替代删除；
    *   -- 不用考虑
    *   2）拥有一个Red节点的black节点
    *   -- 如何判断，通过判断取代删除的这个节点是Red;和颜色挂钩
    *   3）删除Black叶子节点
    * */

    /*
    * node:被删除的节点
    * replacement：用来替换被删除的节点的：想想二叉搜索树的删除
    * */

    /*
        1、sibling节点是Black:可以借兄弟
          -- black删除后会导致B树节点下溢；
          -- 如果sibling至少有一个Red节点；向sibling借一个节点
          -- 进行选装操作；
            -- 旋转后中心节点继承parent的颜色
            -- 旋转后左右节点染为Black
        2、sibling节点是Black:没有Red节点；不可以借
          1）、parent是红色的；
          -- 将sibling染成Red,parent染成Black
          2）、parent是黑色的；
          -- 导致parent下溢
          -- 此时将parent当做删除节点处理

        3、sibling是红色节点：
          2）、sIbling染成Black,parent染成red,
          1）、强制把侄子变成兄弟：对parent进行右旋转

        总结：删除一个节点，
        1、如果为红色节点，不用任何处理
        2、如果删除黑色节点：
        1）、如果替代子节点是红色；
        2）、如果
        2、先判断兄弟节是否为黑色：如果兄弟为黑色并且有红色子节点（借兄弟），则通过旋转解决；如果黑兄弟节点没有红色节点（没法借）：1、如果父节点是红，则让
        父节点下来合并（父节点下溢）；2、如果父节点是黑，把父节点当做删除节点处理；
         */

    //优化：去掉replacement
    @Override
    protected void afterRemove(Node<E> node) {
        //1、如果删除的节点是红色，不用处理
//        if (isRed(node)) return;
        //2、如果用于取代的节点是红色；
        if (isRed(node)) {
            black(node);
            return;
        }
        Node<E> parent = node.parent;
        //3、删除的时根节点
        if (parent == null) return;

        //4、后面表示删除的black叶子节点
        //来到这里node必定是叶子节点
        // 判断被删除的node时左还是右,主要是在删除后，这个node,就没有办法从parent.left找到了

        boolean left = parent.left == null || node.isLeftChild();
        Node<E> sibling = left ? parent.right : parent.left;//先找到被删除节点兄弟
        if (left) { //被删除的节点在左边，兄弟节点在右边
            if (isRed(sibling)) { //兄弟节点是红色，要转换为兄弟节点为黑色
                black(sibling);
                red(parent);
                rotateLeft(parent);
                //更换兄弟
                sibling = parent.right;
            }
            //下面是兄弟节点为黑色的
            if (isBlack(sibling.left) && isBlack(sibling.right)) {
                //兄弟节点子节点都是黑色的。父节点向下合并；
                //先处理parent是不是黑色；
                boolean parentBlack = isBlack(parent);
                black(parent);
                red(sibling);
                if (parentBlack) {
                    //父节点是黑色的情况
                    afterRemove(parent);
                }

            } else {
                //表示兄弟节点至少有一个红色子节点,向兄弟节点借元素
                if (isBlack(sibling.right)) {
                    //兄弟节点的左侧是黑色的，先旋转兄弟
                    rotateRight(sibling);
                    //要修改sibling指向
                    sibling = parent.right;
                }
                //先染色
                color(sibling, colorOf(parent));//兄弟节点继承父节点颜色
                black(sibling.right);
                black(parent);
                //下面旋转父节点
                rotateLeft(parent);

            }
        } else {//被删除的节点在右边，兄弟节点在左边
            if (isRed(sibling)) { //兄弟节点是红色，要转换为兄弟节点为黑色
                black(sibling);
                red(parent);
                rotateRight(parent);
                //更换兄弟
                sibling = parent.left;
            }
            //下面是兄弟节点为黑色的
            if (isBlack(sibling.left) && isBlack(sibling.right)) {
                //兄弟节点子节点都是黑色的。父节点向下合并；
                //先处理parent是不是黑色；
                boolean parentBlack = isBlack(parent);
                black(parent);
                red(sibling);
                if (parentBlack) {
                    //父节点是黑色的情况
                    afterRemove(parent);
                }

            } else {
                //表示兄弟节点至少有一个红色子节点,向兄弟节点借元素
                if (isBlack(sibling.left)) {
                    //兄弟节点的左侧是黑色的，先旋转兄弟
                    rotateLeft(sibling);
                    //要修改sibling指向
                    sibling = parent.left;
                }
                //先染色
                color(sibling, colorOf(parent));//兄弟节点继承父节点颜色
                black(sibling.left);
                black(parent);
                //下面旋转父节点
                rotateRight(parent);

            }
        }


    }
    /*
    @Override
    protected void afterRemove(Node<E> node, Node<E> replacement) {
        super.afterRemove(node, replacement);
        //1、如果删除的节点是红色，不用处理
        if (isRed(node)) return;
        //2、如果用于取代的节点是红色；
        if (isRed(replacement)) {
            black(replacement);
            return;
        }
        Node<E> parent = node.parent;
        //3、删除的时根节点
        if (parent == null) return;

        //4、后面表示删除的black叶子节点
        //来到这里node必定是叶子节点
        // 判断被删除的node时左还是右,主要是在删除后，这个node,就没有办法从parent.left找到了

        boolean left = parent.left == null || node.isLeftChild();
        Node<E> sibling = left ? parent.right : parent.left;//先找到被删除节点兄弟
        if (left) { //被删除的节点在左边，兄弟节点在右边
            if (isRed(sibling)) { //兄弟节点是红色，要转换为兄弟节点为黑色
                black(sibling);
                red(parent);
                rotateLeft(parent);
                //更换兄弟
                sibling = parent.right;
            }
            //下面是兄弟节点为黑色的
            if (isBlack(sibling.left) && isBlack(sibling.right)) {
                //兄弟节点子节点都是黑色的。父节点向下合并；
                //先处理parent是不是黑色；
                boolean parentBlack = isBlack(parent);
                black(parent);
                red(sibling);
                if (parentBlack) {
                    //父节点是黑色的情况
                    afterRemove(parent, null);
                }

            } else {
                //表示兄弟节点至少有一个红色子节点,向兄弟节点借元素
                if (isBlack(sibling.right)) {
                    //兄弟节点的左侧是黑色的，先旋转兄弟
                    rotateRight(sibling);
                    //要修改sibling指向
                    sibling = parent.right;
                }
                //先染色
                color(sibling, colorOf(parent));//兄弟节点继承父节点颜色
                black(sibling.right);
                black(parent);
                //下面旋转父节点
                rotateLeft(parent);

            }
        } else {//被删除的节点在右边，兄弟节点在左边
            if (isRed(sibling)) { //兄弟节点是红色，要转换为兄弟节点为黑色
                black(sibling);
                red(parent);
                rotateRight(parent);
                //更换兄弟
                sibling = parent.left;
            }
            //下面是兄弟节点为黑色的
            if (isBlack(sibling.left) && isBlack(sibling.right)) {
                //兄弟节点子节点都是黑色的。父节点向下合并；
                //先处理parent是不是黑色；
                boolean parentBlack = isBlack(parent);
                black(parent);
                red(sibling);
                if (parentBlack) {
                    //父节点是黑色的情况
                    afterRemove(parent, null);
                }

            } else {
                //表示兄弟节点至少有一个红色子节点,向兄弟节点借元素
                if (isBlack(sibling.left)) {
                    //兄弟节点的左侧是黑色的，先旋转兄弟
                    rotateLeft(sibling);
                    //要修改sibling指向
                    sibling = parent.left;
                }
                //先染色
                color(sibling, colorOf(parent));//兄弟节点继承父节点颜色
                black(sibling.left);
                black(parent);
                //下面旋转父节点
                rotateRight(parent);

            }
        }


    }
     */

    /*
    * 红黑树添加、删除之后会导致红黑树的性质不符合，这时候需要进行染色处理；
    * 将染完色的节点返回
    * */
    private Node<E> color(Node<E> node, boolean color) {
        if (node == null) return node;
        ((RBNode<E>)node).color = color;
        return node;
    }

    /*
    * 将节点染为红色
    * */
    private Node<E> red(Node<E> node) {
        return color(node, RED);
    }

    /*
    * 将节点染为黑色
    * */
    private Node<E> black(Node<E> node) {
        return color(node, BLACK);
    }

    /*
    * 获取一个节点的颜色
    * */
    private boolean colorOf(Node<E> node) {
        return node == null ? BLACK : ((RBNode<E>)node).color;
    }

    /*
    * 判断节点是否为黑色
    * */
    private boolean isBlack(Node<E> node) {
        return colorOf(node) == BLACK;
    }

    /*
    * 节点颜色是否为红色
    * */
    private boolean isRed(Node<E> node) {
        return colorOf(node) == RED;
    }

    /*
    * 红黑树每个节点具有颜色，因此需要重新定义Node
    * */

    private static class RBNode<E> extends Node<E> {

        /*
        * 定义每个节点的颜色
        * */
        boolean color = RED;

        public RBNode(E element, Node<E> parent) {
            super(element, parent);
        }

        @Override
        public String toString() {
            return "RBNode{" +
                    "color=" + (color == RED ? "R":"B") +
                    ", element=" + element +
                    '}';
        }
    }
}
