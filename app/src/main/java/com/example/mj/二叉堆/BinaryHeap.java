/*
 * ************************************************************
 * 文件：BinaryHeap.java
 * 模块：MyApplication.app
 * 项目：MyApplication
 * 当前修改时间：2021年02月02日 08:39:55
 * 上次修改时间：2021年02月02日 08:39:55
 * 作者：Havi
 * Copyright (c) 2021
 * ************************************************************
 *
 */

package com.example.mj.二叉堆;

/*
* 二叉堆的逻辑就是一个完全二叉树，所以也叫完全二叉堆
* 底层数据结构采用数组实现；
* -- 索引i的规律：
* --- i == 0;表示根节点；
* --- i > 0 ,表示有父节点：并且索引是 floor((i-1)/2)
* --- 如果2i + 1 <= n-1;它的左子节点编号为2i+1;
* --- 如果2i + 1 > n-1 ;则他没有左子节点
*
* --- 如果2i + 2 <= n-1;他的右子节点编号：2i+2;
* --- 如果2i + 2 < n-1;没有右子节点；
*
* */

import com.example.mj.printer.BinaryTreeInfo;

import java.util.Comparator;

public class BinaryHeap<E> extends AbstractHeap<E> implements BinaryTreeInfo {

    private E[] elements;
    private static final int DEFAULT_CAPACITY = 10;

    //批量建堆

    public BinaryHeap(E[] elements, Comparator<E> comparator) {
        super(comparator);
        if (elements == null || elements.length == 0) {
            this.elements = (E[]) new Object[DEFAULT_CAPACITY];
        } else {
            //表示外部传入数组，需要对数据进行批量建堆 heapify
            //❌ this.elements = elements;内部指针会指向外部
            //你需要自己申请一段内存
            size = elements.length;
            int capacity = Math.max(DEFAULT_CAPACITY, elements.length);
            this.elements = (E[])new Object[capacity];
            for (int i = 0; i < elements.length; i++) {
                this.elements[i] = elements[i];
            }
        }
    }

    public BinaryHeap(E[] elements) {
        this(elements, null);
    }

    /*
    * 构造函数：带有比较的
    * */
    public BinaryHeap(Comparator<E> comparator) {
        this(null, comparator);
    }

    /*
    * 默认为空的
    * */
    public BinaryHeap() {
        this(null, null);
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    /*

    批量建堆
     */

    private void heapify() {
        //自上而下的上滤:效率低
        /*
        for (int i = 1; i < size; i++) {
            siftUp(i);
        }
        */
        //自下而上的下滤
        //从第一个非叶子节点开始
        for (int i = (size >> 1)-1; i > 0 ; i--) {
            siftDown(i);
        }
    }

    /*
    * 往二叉堆中添加元素；
    * 循环执行以下操作：
    * 1、如果插入的node > 父节点，
    *  -- 则与父节点交换位置；
    * 2、如果node <= 父节点 || node没有父节点
    *  -- 退出循环
    * 3、时间复杂度O(logn)
    *
    * */
    @Override
    public void add(E element) {
        //因为具有可比较性，因此元素不能为空；
        elementNotNullCheck(element);
        //确保要有这么多容量
        ensureCapacity(size+1);
        //add的本质就是在最后的位置添加元素，并且让最后的元素上滤；
        elements[size++] = element;
        //上滤这个位置元素
        siftUp(size-1);
    }

    /*
     * 获取堆顶元素
     * */
    @Override
    public E get() {
        //检测是否为空，防止数组越界
        emptyCheck();
        return elements[0];
    }

    /*
    表示删除堆顶元素
    1、用最后一个节点的覆盖根节点
    2、删除最后一个节点
    3、循环操作下面的步骤：node表示最新的根节点
     1）如果node < 子节点
        与最大的子节点进行交换
     2）如果node >= 子节点，或者没有子节点
        退出循环

    这个过程称为下滤
     */
    @Override
    public E remove() {
        //先检测
        emptyCheck();
        //1、交换堆顶和最后的元素
        int lastIndex = --size;
        E element = elements[0];
        elements[0] = elements[lastIndex];
        elements[lastIndex] = null;
        //2、下滤
        siftDown(0);
        return element;
    }

    /*
    删除堆顶，并插入新的元素
     */
    @Override
    public E replace(E element) {
        elementNotNullCheck(element);
        E root = null;
        if (size == 0) {
            elements[0] = element;
            size++;
        } else {
            root = elements[0];
            elements[0] = element;
            siftDown(0);
        }
        return root;
    }

    private void siftDown(int index) {
        //循环条件：1、具有子节点；index 要小于第一个非叶子节点索引；（第一个叶子节点的索引，就是非叶子节点的数量）
        //因此 非叶子节点是数量是 = floor((n+1)/2) = ceiling(n/2);ceil：在英文中，是天花板的意思，有向上的意思，所以，此函数是向上取整
        //
        E element = elements[index];//要比较的
        int half = size >> 1;
        while (index < half) {
            //index:1、只有左子节点；2、具有左右子节点
            //因此默认用左子节点索引进行比较
            int childIndex = (index << 1)+1;//2*index+1
            E child = elements[childIndex];
            //取出右子节点；
            int rightIndex = childIndex+1;
            //选出左右子节点的最大值；右边比左边大
            if (rightIndex < size && compare(elements[rightIndex], child) > 0) {
                child = elements[childIndex = rightIndex];
            }
            //比子节点大的话，结束
            if (compare(element, child) >= 0) break;
            //将子节点放到index位置
            elements[index] = child;
            //重置index;进入下一轮循环
            index = childIndex;
        }
        //最后一步交换；
        elements[index] = element;
    }

    /*
    * 让index位置的元素上率；即：改位置元素循环和父节点进行比较；
    * */
    private void siftUp(int index) {
        E element = elements[index];
        /*
        while (index > 0 ) {
            //获取父节点索引： floor((i-1)/2)；编译器默认向下取整
            int parentIndex = (index-1) >> 1;
            E p = elements[parentIndex];
            if (compare(e, p) <= 0) return;//自己比父节点小，停止循环；
            //下面就需要进行交换
            E tmp = elements[index];
            elements[index] = p;
            elements[parentIndex] = tmp;
            //重新赋值index
            index = parentIndex;
        }
         */
        //优化
        while (index > 0 ) {
            //获取父节点索引： floor((i-1)/2)；编译器默认向下取整
            int parentIndex = (index-1) >> 1;
            E parent = elements[parentIndex];
            //reture直接返回；break停止while循环
            if (compare(element, parent) <= 0) break;//自己比父节点小，停止循环；
            //先将元素挪下来
            elements[index] = parent;
            //重新赋值index
            index = parentIndex;
        }
        elements[index] = element;
    }

    /*
    * 检测元素是否为空；
    * */
    private void elementNotNullCheck(E element) {
        if (element == null) {
            throw new IllegalArgumentException("element not null");
        }
    }

    /*
    * 扩容操作；
    * */
    private void ensureCapacity(int capacity) {
        //旧的容量
        int oldCapacity = elements.length;
        //旧的容量大于新的容量
        if (oldCapacity >= capacity) {
            return;
        }
        //此时进行扩容操作；使用右移操作效率高
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        E[] newElements = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newElements[i] = elements[i];
        }
        elements = newElements;
        System.out.println(oldCapacity +"扩容操作：" + newCapacity);
    }

    /*
    * 检测二叉堆是否为空的；
    * */
    private void emptyCheck() {
        if (size == 0) {
            throw  new IndexOutOfBoundsException("数组为空了");
        }
    }



    @Override
    public Object root() {
        return 0;
    }

    @Override
    public Object left(Object node) {
        Integer index = (Integer) node;
        index = (index << 1) + 1;
        return index >= size ? null : index;
    }

    @Override
    public Object right(Object node) {
        Integer index = (Integer) node;
        index = (index << 1) + 2;
        return index >= size ? null : index;
    }

    @Override
    public Object string(Object node) {
        Integer index = (Integer) node;
        return elements[index];
    }
}
