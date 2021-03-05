/*
 * ************************************************************
 * 文件：HashMap_low.java
 * 模块：MyApplication.app
 * 项目：MyApplication
 * 当前修改时间：2021年01月30日 16:54:18
 * 上次修改时间：2021年01月30日 16:54:18
 * 作者：Havi
 * Copyright (c) 2021
 * ************************************************************
 *
 */

package com.example.mj.Map;

/*
 * ************************************************************
 * 文件：HashMap.java
 * 模块：MyApplication.app
 * 项目：MyApplication
 * 当前修改时间：2021年01月27日 08:41:59
 * 上次修改时间：2021年01月27日 08:41:59
 * 作者：Havi
 * Copyright (c) 2021
 * ************************************************************
 *
 * 这是一个精简版本的hashMap：每次的添加都是向右侧添加：效率比较差
 */

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.mj.printer.BinaryTreeInfo;
import com.example.mj.printer.BinaryTrees;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

@RequiresApi(api = Build.VERSION_CODES.KITKAT)
class HashMap_Low<K, V> implements Map<K, V> {

    private static final boolean RED = false;
    private static final boolean BLACK = true;
    private static final int DEFAULT_CAPACITY = 1<<4;

    /*
     * hash map:
     * 存放的红黑树的根节点
     * 数组的每个位置放的时红黑树的根节点；
     * */
    private Node<K, V>[] table;
    /*
     * hash map的数量
     * */
    private int size;

    /*
     * 初始化方法
     * */
    public HashMap_Low() {
        table = new Node[DEFAULT_CAPACITY];
    }

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
        //如果没有元素不需要进行清空了
        if (size == 0) return;
        for (int i = 0; i < table.length; i++) {
            table[i] = null;
        }
        size = 0;
    }

    @Override
    public V put(K key, V value) {
        //1、计算
        int index = index(key);
        //2、判断当前位置有没有根节点；
        //取出当前index根节点；
        Node<K, V> root = table[index];
        if (root == null) {
            //这里是根节点的处理，
            root = new Node<K, V>(key, value, null);
            table[index] = root;
            size++;
            //修复红黑树；
            afterPut(root);
            return null;
        }
        //来到这里，表示根节点有值，意味着出现Hash冲突
        //因此添加新的节点到红黑树；
        // 1.找到父节点
        Node<K, V> parent = root;
        Node<K, V> node = root;
        int cmp = 0;
        //使用do-while是因为一进来我们就可以进行立面的比较操作；
        int h1 = key == null ? 0 : key.hashCode();
        K k1 = key;
        Node<K,V> result = null;
        boolean searched = false;//是否搜索过key
        do {
            //进行下一轮比较的时候，先保存父节点
            parent = node;
            K k2 = node.key;
            int h2 = node.hash;
            //下面的添加过程不能用之前的比较方法了
            if (Objects.equals(k1, k2)){
                cmp = 1;//表示只要两个key相等，就直接放到右边，不进行比较；
            } else if (searched){//后面还是进行先扫描，然后在根据内存地址左右走；
                cmp = System.identityHashCode(k1) - System.identityHashCode(k2);
            } else {//searched == true;扫描一遍没有发现key
                if ((node.left != null && (result = node(node.left, k1)) != null)
                        || (node.right != null && (result = node(node.right, k1)) != null)) {
                    //表示已经存在这个key;
                    cmp = 0;
                    node = result;
                } else {
                    searched = true;
                    cmp = 1;
                }
            }

            if (cmp > 0) {
                node = node.right;
            } else if (cmp < 0) {
                node = node.left;
            } else {
                //相等直接返回；尽量使用新的值覆盖；这个对自定义对象很有用；
                node.key = key;
                V oldValue = node.value;
                node.value = value;
                node.hash = h1;
                return oldValue;
            }
        } while (node != null);
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
        return node != null ? node.value : null;
    }

    @Override
    public V remove(K key) {
        return remove(node(key));
    }

    private V remove(Node<K,V> node) {
        if (node == null) return null;
        size--;
        V oldValue = node.value;
        //首先处理度为2的节点
        if (node.hasTwoChildren()) { //代表度为2的节点
            Node<K, V> s = successor(node);//找到后继节点
            //用后继节点的值替换要删除的节点内容，
            node.value = s.value;
            node.key = s.key;
            node.hash = s.hash;
            //删除后继节点
            node = s;//接下来处理删除节点

        }
        //来到这个地方，节点的度一定为1或者0
        Node<K, V> replacement = node.left != null ? node.left : node.right;
        int index = index(node);
        if (replacement != null) { //表示node为1的节点
            //更改replace parent
            replacement.parent = node.parent;
            if (node.parent == null) { //node度为1且为根节点
                table[index] = replacement;
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
            table[index] = null;
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

    @Override
    public boolean containsKey(K key) {
        return node(key) != null;
    }

    /*
     * 查看value是不是在hash map中
     * value没有办法比较；因此只能遍历数组中的所有红黑树
     * */
    @Override
    public boolean containsValue(V value) {
        if (size == 0) return false;
        Queue<Node<K, V>> queue = new LinkedList<>();
        for (int i = 0; i < table.length; i++) {
            //如果这个索引对应的红黑树为空，继续；
            if (table[i] == null) continue;
            //有红黑树，使用层序遍历
            queue.offer(table[i]);
            while (!queue.isEmpty()) {
                //取出队头，和元素比较
                Node<K, V> kvNode = queue.poll();
                //比较如果相等直接返回true
                if (Objects.equals(value, kvNode.value)) return true;
                if (kvNode.left != null) queue.offer(kvNode.left);
                if (kvNode.right != null) queue.offer(kvNode.right);
            }
        }
        return false;
    }

    public void print() {
        if (size == 0) return;
        for ( int i = 0; i < table.length; i++) {
            System.out.println("----------------------【index:】" + i);
            final Node<K,V> root = table[i];
            BinaryTrees.println(new BinaryTreeInfo() {
                @Override
                public Object root() {
                    return root;
                }

                @Override
                public Object left(Object node) {
                    return ((Node<K,V>)node).left;
                }

                @Override
                public Object right(Object node) {
                    return ((Node<K,V>)node).right;
                }

                @Override
                public Object string(Object node) {
                    return node.toString();
                }
            });
        }
    }

    /*
     * 遍历操作和查value操作是一样的；使用层序遍历
     * */
    @Override
    public void traversal(Visitor<K, V> visitor) {
        if (size == 0 || visitor == null) return;
        Queue<Node<K, V>> queue = new LinkedList<>();
        for (int i = 0; i < table.length; i++) {
            //如果这个索引对应的红黑树为空，继续；
            if (table[i] == null) continue;
            //有红黑树，使用层序遍历
            queue.offer(table[i]);
            while (!queue.isEmpty()) {
                //取出队头，和元素比较
                Node<K, V> kvNode = queue.poll();
                //发现外部遍历要求停止，直接return
                if (visitor.visit(kvNode.key, kvNode.value)) return;
                if (kvNode.left != null) queue.offer(kvNode.left);
                if (kvNode.right != null) queue.offer(kvNode.right);
            }
        }
    }

    /*
     * 根绝key生成索引(在桶中的位置)
     * */
    private int index(K key) {
        //这里我们定义如果key是空的，则返回 0；
        if (key == null) return 0;
        int hashCode = key.hashCode();
        //这里拿hashcode的高16位和低16位进行混合运算；为了使得hash 值分布比较均匀；
        hashCode = hashCode ^ (hashCode >>> 16);
        return hashCode & (table.length - 1);
    }

    private int index(Node<K, V> node) {
        //直接根据node计算来的
        return (node.hash ^ (node.hash >>> 16)) & (table.length - 1);
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

    /*
     * 根据传入的key,返回对应的节点
     * */
    private Node<K, V> node(K key) {
        Node<K,V> root = table[index(key)];
        return root == null ? null : node(root, key);
    }

    private Node<K, V> node(Node<K,V> node, K k1) {
        int h1 = k1 == null ? 0 : k1.hashCode();
        Node<K,V> result = null;
        int cmp = 0;//
        while (node != null) {
            int h2 = node.hash;
            K k2 = node.key;
            //1、比较Hash值，进行做还是右
            if (Objects.equals(k1, k2)) {
                //2、需要看下K1,K2的equal方法，判断两个key是不是同一个
                return node;

            } else if(node.right != null && (result = node(node.right, k1)) != null) {//表示从右子树找到
                //4、表示哈希值相等&&不具备可比较性&&不equals；进行遍历所有的节点
                return result;
            } else {//表示右边找不到；直接赋值就可以了
                node = node.left;
            }

//            else if(node.left != null && (result = node(node.left, k1)) != null) {//表示从左子树找到
//                //4、表示哈希值相等&&不具备可比较性&&不equals；进行遍历所有的节点
//                return result;
//            } else {
//                return null;
//            }
        }
        return null;
    }

    /*
    返回值 等于0：e1==e2
          < 0: e1 < e2
          > 0: e1 > e2
          e1, e2:存储的key
          h1, h2，key对应的hash值
          //错误的示范
     */
//    private int compare(K k1, K k2, int h1, int h2) {
//        /*
//        * 自定义实现的HashMap，比较是对key的比较；我们首先使用Hash值比较
//        * */
//        int result = h1 - h2;
//        // 1、hash值不相等，插入
//        if (result != 0) return result;
//        // 2、hash相等，并且key是相等的，直接替换；
//        if (Objects.equals(k1,k2)) return 0;
//        // 3、Hash值相等，但是key不相等；
//        //  3.1、继续比较；比较类名
//        if (k1 != null && k2 != null) {
//            String k1Cls = k1.getClass().getName();
//            String k2Cls = k2.getClass().getName();
//            result = k1Cls.compareTo(k2Cls);
//            if (result != 0) return result;
//            // 3.2、同一种类型，判断是否具有可比较性；
//            if (k1 instanceof Comparable) {
//                return ((Comparable) k1).compareTo(k2);
//            }
//        }
//        // 4、同一类型，不具有可比较性
//        // || 有一个k1 != null && k2 == null
//        // || k1 == null && k2 != null
//        // 解决：比较内存地址 identityHashCode,根据内存地址来的hash code
//        result = System.identityHashCode(k1) - System.identityHashCode(k2);
//
//        return result;
//    }

    /*
     * 进行删除操作之后，需要修复红黑树；
     * */
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
            table[index(grand)] = parent;
        }
        // 更新child的parent
        if (child != null) {
            child.parent = grand;
        }
        // 更新grand的parent
        grand.parent = parent;

    }

    //-----------------------------------------------------------------------染色操作

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
     * 这里直接将红黑树节点放到hash map中；
     * */
    private static class Node<K, V> {
        K key;
        V value;
        int hash;
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
            //为了避免多次计算同一个节点的hash值，将每个节点的hash值保存到这个节点中
            this.hash = key == null ? 0 : key.hashCode();
            this.value = value;
            this.parent = parent;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "key=" + key +
                    ", value=" + value +
                    '}';
        }
    }
}
