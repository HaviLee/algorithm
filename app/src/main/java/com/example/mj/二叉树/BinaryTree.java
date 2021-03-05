/*
 * ************************************************************
 * 文件：BinaryTree.java
 * 模块：MyApplication.app
 * 项目：MyApplication
 * 当前修改时间：2020年12月23日 10:57:02
 * 上次修改时间：2020年12月23日 10:57:02
 * 作者：Havi
 * Copyright (c) 2020
 * ************************************************************
 *
 */

package com.example.mj.二叉树;

/*
二叉树：
1、每个节点的 *度* 最大为 2；
2、左子树和右子树是由顺序的
3、即使二叉树只有一个子树，也是有顺序的；
4、是有序树：是左右顺序，
 */

/*
二叉树高度，深度：
将二叉树类比树根；
高度：是当前节点到根须末尾的节点个数
深度：是根点到当前节点
 */

/*
二叉树性质：
1、非空二叉树第i层，最多有2^(i-1)个节点（i>=1);
2、高度为h的二叉树，最多有个2^(h)-1个节点 （i>=1); 1 + 2 + 4 + 8
3、任何一个非空二叉树，如果叶子节点个数为n0,度为2（即非叶子节点）个数为n2;则 n0 = n2 + 1;
   -- 假设度为1的节点为n1,度为2的节点为n2,则总节点个数：n = n1 + n2 + n0;
   -- 二叉树的边数：度为1的节点有一个边，为2的两条边，为0没有边；从每个节点顶部有个边可以得 总边数有n-1
        因此T = 2*n2 + n1 = n - 1 = n1 + n2 + n0 - 1
 */

/*
真二叉树：所有节点的度为0或者2 ；（不存在度为1的节点）

满二叉树：
1、所有节点的度为0 或者 2；（不存在度为1的节点）
2、所有的叶子节点都必须在最后一层
特性：
1、第i层的节点数量是：2^(i-1)
2、叶子节点数量：2^(h-1)

满二叉树一定是真二叉树；真二叉树不一定是真二叉树；
 */

/*
完全二叉树：Complete Binary Tree
定义：
1、叶子节点之后出现在最后2层；
2、最后一层的叶子节点都靠左对齐；

性质：
1、度为1的节点只有左子树
2、度为1的节点要么为0个要么为1个；
3、同样节点数量的二叉树，完全二叉树的高度最小；
4、假设完全二叉树高度为h
 -- 至少有2^(h-1)个节点
 -- 最多有个2^h - 1个；满二叉树；
5、假设总节点数量为n
 -- 2^(h-1) <= n < 2^h 两边取对数
 -- h - 1 <= log2^n < h
 -- 可以得出：h = log2^n向下取整+1 = floor(log2^n) + 1

6、一个有n个节点的完全二叉树，从上到下，从左到右对节点从0开始进行编号，任意一个节点的编号：
 -- 如果i==0;它是根节点
 -- 如果i>0;它的父节点编号为floor((i-1)/2)
 -- 如果2i+1 <= n-1,他的左子节点编号为2i+1；
 -- 如果2i+2 <= n-1;他的右子节点编号2i+2；
 -- 如果2i+1 > n-1 ;他没有左子节点；
 --
 */

/*
完全二叉树结论：
1、假设叶子节点个数为n0,度为1的节点的个数为n1,度为2的节点个数为n2则：
  -- 总结点个数n = n0 + n1 + n2;并且 n0 = n2 + 1；
  -- n = 2n0 + n1 - 1

2、完全二叉树的n1要么为0个，要么为1个
  -- n1为1时，n = 2n0, n必然为偶数；
  -- 叶子节点个数 n0 = n / 2;非叶子节点个数 n1 + n2 = n/2
  -- n1为0时，n = 2n0 - 1, n必然是奇数
  -- 叶子节点个数 n0 = (n+1)/2;非叶子节点个数：n1+n2 = (n-1)/2
 */


import com.example.mj.printer.BinaryTreeInfo;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree<E> implements BinaryTreeInfo {

    protected int size;
    protected Node<E> root;

    /*
    * 暴露子类方法，创建节点
    * */
    protected Node<E> createNode(E element, Node<E> parent) {
        return new Node<>(element, parent);
    }

    /*
     * 元素个数*/
    public int size() {
        return size;
    }

    /*
     * 是否为空*/
    public boolean isEmpty() {
        return size == 0;
    }

    /*
     * 清空*/
    public void clear() {
        root = null;
        size = 0;
    }

    /*
    前序遍历：主要指的是根节点访问的顺序
    访问顺序：跟节点 -> 前序遍历左子树 -> 前序遍历右子树
     */
    public void preorderTravel(Visitor<E> visitor) {
        if (visitor == null) return;
        preorderTraversal(root, visitor);//从根节点开始
    }

    private void preorderTraversal(Node<E> node, Visitor<E> visitor) { //传进来的根节点
        if (node == null || visitor.stop) return;
        //先访问根节点
        visitor.stop = visitor.visit(node.element);
        //前序访问左子树
        preorderTraversal(node.left, visitor);
        //前序访问右子树
        preorderTraversal(node.right, visitor);
    }

    /*
     * 中序遍历
     * 遍历顺序：先中序遍历左子树，根节点、中序遍历右子树*/

    public void inorderTraversal(Visitor<E> visitor) {
        if (visitor == null) return;
        inorderTraversal(root, visitor);
    }

    private void inorderTraversal(Node<E> node, Visitor<E> visitor) {
        if (node == null || visitor.stop) return;
        //先中序左子树
        inorderTraversal(node.left, visitor);
        //根节点
        if (visitor.stop) return;
        visitor.stop = visitor.visit(node.element);
        //中序右子树
        inorderTraversal(node.right, visitor);
    }

    /*
     * 后续遍历
     * 遍历顺序：先后续遍历左子树、后序遍历右子树、根节点*/

    public void postorderTraversal(Visitor<E> visitor) {
        if (visitor == null) return;
        postorderTraversal(visitor, root);
    }
    private void postorderTraversal(Visitor<E> visitor, Node<E> node) {
        if (node == null || visitor.stop) return; //这里visitor.stop是为了停止递归调用
        //先后序
        postorderTraversal(visitor, node.left);
        postorderTraversal(visitor, node.right);
        if (visitor.stop) return; //这里是防止左子树里面是true,本身就不需要了
        visitor.stop = visitor.visit(node.element);
    }

    /*层序遍历：
     * 一层一层的遍历
     * 使用队列完成*/

    public void levelOrderTraversal(Visitor<E> visitor) {
        if (visitor == null || root == null) return;
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            //取出头部元素
            Node<E> node = queue.poll();
            //对外部的方法
            visitor.visit(node.element);
            if (node.left != null) queue.offer(node.left);
            if (node.right != null) queue.offer(node.right);
        }

    }



    /*
     * 前驱节点：
     * 中序遍历的前一个节点
     * -- 如果是二叉搜索树，就是前一个比她小的节点；
     * 1) node.left != null
     *    predecessor = node.left.right.right...
     *    终止条件：right == null
     * 2) node.left == null && node.parent != null
     *    predecessor = node.parent.parent...
     *    终止条件：node在parent的右子树中
     * 3）node.left == null && node.parent == null
     *    没有前驱节点
     *
     * */

    //protected自己和子类可以访问，外界无法访问
    protected Node<E> predecessor(Node<E> node) {
        if (node == null) return null;
        Node<E> p = node.left;
        //前驱节点在左子树中
        if (p != null) {
            while (p.right != null) {
                p = p.right;
            }
            //出来表示
            return p;
        }

        //左边为空，从父节点找
        while (node.parent != null && node == node.parent.left) {
            node = node.parent;
        }
        // node.parent == null
        // node == node.parent.right
        return node.parent;
    }

    /*
     * 后继节点:中序遍历的前一个节点
     * */
    protected Node<E> successor(Node<E> node) {
        if (node == null) return null;
        Node<E> p = node.right;
        //后继节点在右子树中
        if (p != null) {
            while (p.left != null) {
                p = p.left;
            }
            //出来表示
            return p;
        }

        //右边为空，从父节点找
        while (node.parent != null && node == node.parent.right) {
            node = node.parent;
        }
        // node.parent == null
        // node == node.parent.left
        return node.parent;
    }
    /*
    判断是否为完全二叉树：
    利用层序遍历一层一层的访问；
     -- 如果node.left != null && node.right != null;将这个节点的left,right 按序入队
     -- 如果node.left == null && node.right != null;不符合完全二叉树；
     -- 如果node.left != null && node.right == null 或者 node.left == null && node.right == null;表示下面的节点都是叶子节点才是完全二叉树
     技巧：如果想要构造一颗二叉树，最好使用层序遍历添加；
    * */

    public boolean isComplete() {
        if (root == null) return false;
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);
        boolean leaf = false;
        while (!queue.isEmpty()) {
            //出队队头
            Node<E> node = queue.poll();

            if (leaf && !node.isLeaf()) {
                return false;
            }
            if (node.left != null) {
                queue.offer(node.left);
            } else if (node.right != null) { //左边为空，右边不为空
                //node.left == null && node.right != null;不符合完全二叉树；
                return false;
            }
            if (node.right != null) {
                queue.offer(node.right);
            } else {
                //如果 node.left != null && node.right == null
                //或者 node.left == null && node.right == null;
                leaf = true;
            }

        }
        return true;
    }

    public boolean isComplete1() {
        if (root == null) return false;
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);
        boolean leaf = false;
        while (!queue.isEmpty()) {
            //出队队头
            Node<E> node = queue.poll();

            //判断是否为叶子节点；从下面开始要求后面的节点都必须是叶子节点
            if (leaf && !node.isLeaf()) {//第三种，要求后续都必须为叶子节点，但是这个节点发现不是
                return false;
            }
            if (node.hasTwoChildren()) { //有两个叶子节点，继续遍历
                queue.offer(node.left);
                queue.offer(node.right);
            } else if (node.left == null && node.right != null) {
                //如果node.left == null && node.right != null;不符合完全二叉树；
                return false;
            } else { //表示后面的遍历的节点都必须是叶子节点
                leaf = true;
                //左边不为空的还是要入队
                if (node.left != null) queue.offer(node.left);
            }

        }
        return false;
    }
    /*
     * 计算二叉树的高度
     * */

    //使用迭代,利用层序遍历
    public int height() {
        if (root == null) return 0;
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);
        int height = 0;
        int levelSize = 1;//存储每层元素的数量
        while (!queue.isEmpty()) {
            //取出队头元素
            Node<E> node = queue.poll();
            //每次从队列取出一个元素levelSize要--
            levelSize--;
            //左右子元素入队
            if (node.left != null) queue.offer(node.left);
            if (node.right != null) queue.offer(node.right);
            if (levelSize == 0) {
                //将要访问下一次；
                levelSize = queue.size();//并且下一次的元素个数正好是队列的个数
                height++;
            }
        }
        return height;
    }
    //使用递归
    public int height2() {
        return height(root);
    }
    /*
     * 获取某个节点的高度
     * 1、递归思想
     * 2、迭代
     * */
    private int height(Node<E> node) {
        if (node == null) return 0;
        //等于他自己+左右子树的最大值
        return 1 + Math.max(height(node.left), height(node.right));
    }
    /*自定义遍历接口
    接口只能定义方法
    如果需要定义变量，需要使用抽象类
    * 这个就是iOS的代理*/
    public static abstract class Visitor<E> {
        /*
        遍历是否停止
         */
        boolean stop;
        public abstract boolean visit(E element);
    }

    protected static class Node<E> {
        public E element; //存储的自己的元素
        public Node<E> left; //存储左右节点
        public Node<E> right;
        public Node<E> parent;

        public Node(E element, Node<E> parent) {
            //左右节点不一定存在
            this.element = element;
            this.parent = parent;
        }

        /*
         * 判断是否为叶子节点*/
        public boolean isLeaf() {
            return left == null && right == null;
        }

        /*有两个节点*/
        public boolean hasTwoChildren() {
            return left != null && right != null;
        }

        /*
        * 判断自己是左子树还是右子树
        * */
        public boolean isLeftChild() {
            return parent != null && this == parent.left;
        }

        /*
        * 判断是不是右子树
        * */
        public boolean isRightChild() {
            return parent != null && this == parent.right;
        }

        /*
        * 返回兄弟节点
        * */
        public Node<E> sibling() {
            if (isLeftChild()) {
                return parent.right;
            }
            if (isRightChild()) {
                return parent.left;
            }

            return null;
        }




    }

    ///////////////////////////////////////////

    @Override
    public Object root() {
        return root;
    }

    @Override
    public Object left(Object node) {
        return ((Node<E>)node).left;
    }

    @Override
    public Object right(Object node) {
        return ((Node<E>)node).right;
    }

    @Override
    public Object string(Object node) {
        return ((Node<E>)node).toString();
    }
}
