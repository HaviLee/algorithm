/*
 * ************************************************************
 * 文件：TreeMap.java
 * 模块：MyApplication.app
 * 项目：MyApplication
 * 当前修改时间：2021年01月25日 12:48:14
 * 上次修改时间：2021年01月25日 12:48:14
 * 作者：Havi
 * Copyright (c) 2021
 * ************************************************************
 *
 */

package com.example.mj.Map;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

/*
* TreeMap：添加、搜索、删除：时间复杂度：O(lgn)
* 特点：
* -- 利用红黑树存储的
* -- key必须具有可比较性
* -- 元素的分布式具有顺序性的
*
* */

public class TreeMap<K, V> implements Map<K, V> {

    private static final boolean RED = false;
    private static final boolean BLACK = true;

    private int size;
    private Node<K, V> root;

    private Comparator<K> comparator;

    public TreeMap(Comparator<K> comparator) {
        this.comparator = comparator;
    }

    public TreeMap() {
    }

    /*
    * 这里采用自己实现一颗<K,V>的红黑树
    * */

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public V put(K key, V value) {
        keyNotNullCheck(key);
        /*
         * 首先判断根节点是不是为空，为空则需要创建根节点*/
        if (root == null) {
            root = new Node<>(key, value, null);
            size++;
            //第一个是在这个地方添加节点：
            afterPut(root);
            return null;
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
        Node<K, V> parent = root;
        Node<K, V> node = root;
        int cmp = 0;
        while (node != null) {
            cmp = compare(key, node.key);
            //进行下一轮比较的时候，先保存父节点
            parent = node;
            if (cmp > 0) {
                node = node.right;
            } else if (cmp < 0) {
                node = node.left;
            } else {
                //相等直接返回；尽量使用新的值覆盖；这个对自定义对象很有用；
                node.key = key;
                V oldValue = node.value;
                node.value = value;
                return oldValue;
            }
        }
        Node<K, V> newNode = new Node<>(key, value, parent);
        //看看插入到父节点位置
        if (cmp > 0) {
            parent.right = newNode;
        } else {
            parent.left = newNode;
        }
        size++;
        //新添加节点之后的处理
        afterPut(newNode);
        return null;
    }

    @Override
    public V get(K key) {
        Node<K, V> node = node(key);
        return node.value != null ? node.value : null;
    }

    @Override
    public V remove(K key) {
        return remove(node(key));
    }

    @Override
    public boolean containsKey(K key) {
        return node(key) != null;
    }

    @Override
    public boolean containsValue(V value) {
        if (root == null) return false;
        Queue<Node<K, V>> queue = new LinkedList<>();
        Node<K, V> node = root;
        queue.offer(root);
        while (!queue.isEmpty()) {
            Node<K, V> kvNode = queue.poll();//出队
            if (valueEquals(value, kvNode.value)) return true;

            if (kvNode.left != null) queue.offer(kvNode.left);
            if (kvNode.right != null) queue.offer(kvNode.right);
        }
        return false;
    }

    @Override
    public void traversal(Visitor<K, V> visitor) {
        if (visitor == null) return;
        traversal(root, visitor);
    }

    private void traversal(Node<K, V> node, Visitor<K, V> visitor) {
        if (node == null || visitor.stop) return;
        traversal(node.left, visitor);

        if (visitor.stop) return;
        visitor.visit(node.key, node.value);

        traversal(node.right, visitor);
    }

    private V remove(Node<K, V> node) {
        if (node == null) return null;
        size--;
        V oldValue = node.value;
        //首先处理度为2的节点
        if (node.hasTwoChildren()) { //代表度为2的节点
            Node<K, V> s = successor(node);//找到后继节点
            //用后继节点的值替换要删除的节点内容，
            node.value = s.value;
            node.key = s.key;
            //删除后继节点
            node = s;//接下来处理删除节点

        }
        //来到这个地方，节点的度一定为1或者0
        Node<K, V> replacement = node.left != null ? node.left : node.right;

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

        return oldValue;
    }

    /*
    * 判断两个元素是否相等
    * */
    private boolean valueEquals(V v1, V v2) {
        return (v1 == null) ? (v2 == null) : (v1.equals(v2));
    }

    /*
     * 后继节点:中序遍历的前一个节点
     * */
    private Node<K, V> successor(Node<K, V> node) {
        if (node == null) return null;
        Node<K, V> p = node.right;
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

    private void afterRemove(Node<K, V> node) {
        //1、如果删除的节点是红色，不用处理
//        if (isRed(node)) return;
        //2、如果用于取代的节点是红色；
        if (isRed(node)) {
            black(node);
            return;
        }
        Node<K, V> parent = node.parent;
        //3、删除的时根节点
        if (parent == null) return;

        //4、后面表示删除的black叶子节点
        //来到这里node必定是叶子节点
        // 判断被删除的node时左还是右,主要是在删除后，这个node,就没有办法从parent.left找到了

        boolean left = parent.left == null || node.isLeftChild();
        Node<K, V> sibling = left ? parent.right : parent.left;//先找到被删除节点兄弟
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
     * 二叉搜索树根据元素查找节点*/
    private Node<K, V> node(K key) {
        Node<K, V> node = root;
        while (node != null) {
            int cmp = compare(key, node.key);
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
    * 修复红黑树平衡
    * */
    private void afterPut(Node<K, V> node) {
        Node<K, V> parent = node.parent;
        //1.先判断父节点：同时也是处理上溢到根节点；
        //如果是添加的时根节点
        if (parent == null) {
            black(node);
        }
        // 如果父节点是黑色，这种场景不用处理
        if (isBlack(parent)) return;
        //2.判断uncle节点
        Node<K, V> uncle = parent.sibling();
        Node<K, V> grand = parent.parent;
        if (isRed(uncle)) { //uncle节点是红色的，上溢;
            black(parent);
            black(uncle);
            //祖父节点作为新添加的节点
            afterPut(red(grand));//再重新检查
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
     * 左旋转操作
     * */
    private void rotateLeft(Node<K, V> grand) {
        //1、修改指向
        Node<K, V> parent = grand.right;
        Node<K, V> child = parent.left;
        grand.right = child;
        parent.left = grand;
        //2.更新parent
        afterRotate(grand, parent, child);
    }

    /*
     * 右旋转操作
     * */
    private void rotateRight(Node<K, V> grand) {
        //1.找Parent&待旋转点
        //找到左字节点
        Node<K, V> parent = grand.left;
        //找到child节点
        Node<K, V> child = parent.right;
        //2、进行交换
        grand.left = child;
        parent.right = grand;
        afterRotate(grand, parent, child);
    }

    /*
     * 将旋转后的操作抽离出来；
     * */
    private void afterRotate(Node<K, V> grand, Node<K, V> parent, Node<K, V> child) {
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

    /*
     * 红黑树添加、删除之后会导致红黑树的性质不符合，这时候需要进行染色处理；
     * 将染完色的节点返回
     * */
    private Node<K, V> color(Node<K, V> node, boolean color) {
        if (node == null) return node;
        node.color = color;
        return node;
    }

    /*
     * 将节点染为红色
     * */
    private Node<K, V> red(Node<K, V> node) {
        return color(node, RED);
    }

    /*
     * 将节点染为黑色
     * */
    private Node<K, V> black(Node<K, V> node) {
        return color(node, BLACK);
    }

    /*
     * 获取一个节点的颜色
     * */
    private boolean colorOf(Node<K, V> node) {
        return node == null ? BLACK : node.color;
    }

    /*
     * 判断节点是否为黑色
     * */
    private boolean isBlack(Node<K, V> node) {
        return colorOf(node) == BLACK;
    }

    /*
     * 节点颜色是否为红色
     * */
    private boolean isRed(Node<K, V> node) {
        return colorOf(node) == RED;
    }

    /*
    返回值 等于0：e1==e2
          < 0: e1 < e2
          > 0: e1 > e2
     */
    private int compare(K e1, K e2) {
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
        return ((Comparable<K>) e1).compareTo(e2);
    }

    /*
     * 检测元素是否为空*/
    private boolean keyNotNullCheck(K key) {
        if (key == null) {
            throw new IllegalArgumentException("key must not be null");
        }
        return false;
    }


    // Tree Map节点
    public static class Node<K, V> {
        K key;
        V value;
        boolean color = RED;
        public Node<K, V> left; //存储左右节点
        public Node<K, V> right;
        public Node<K, V> parent;

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
        public Node<K, V> sibling() {
            if (isLeftChild()) {
                return parent.right;
            }
            if (isRightChild()) {
                return parent.left;
            }

            return null;
        }
        public Node(K key, V value, Node<K, V> parent) {
            this.key = key;
            this.value = value;
            this.parent = parent;
        }
    }
}
