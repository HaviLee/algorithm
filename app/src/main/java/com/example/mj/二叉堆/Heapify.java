/*
 * ************************************************************
 * 文件：Heapify.java
 * 模块：MyApplication.app
 * 项目：MyApplication
 * 当前修改时间：2021年02月28日 16:54:41
 * 上次修改时间：2021年02月28日 16:54:41
 * 作者：Havi
 * Copyright (c) 2021
 * ************************************************************
 *
 */

package com.example.mj.二叉堆;

/*

批量建堆：
1、自上而下的上滤：（最大堆）上滤：跟父节点比较，比父节点大，进行交换;本质就是添加操作
   -- 排序index:0
   -- 复杂度是素有节点的深度之和
2、自下而上的下滤：（最大堆）本质就是将左右子树先建堆；
   -- 可以排除叶子节点：下滤：是跟子节点比较，比子节点小，进行交换
   -- 复杂度是所有节点的高度之和
3、完全二叉树：
 -- 所有节点的深度之和：
   1）仅仅是叶子节点就有近n/2个，每个叶子的深度都是O(logn)
   2）叶子节点就达到了O(nlogn);
 -- 所有节点的高度之和：
   1)
 */

/*

Top K问题：
使用小顶堆进行；
1、新建一个小顶堆
2、扫秒n个整数；
    -- 先将前k个元素入堆；
    -- 从k+1个元素开始使用，如果大于堆顶元素，则使用replace操作替换；

 */


import java.util.Comparator;

public class Heapify {
    /*
    使用二叉堆求top k问题
     */
    private void findTopK() {
        int k = 5;
        Integer[] data = {88, 44, 53, 41, 16, 6, 70, 18, 85, 98, 81, 23, 36, 43, 37};
        //创建一个小顶堆
        BinaryHeap<Integer> heap = new BinaryHeap<>(data, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        //遍历所有的元素
        for (int i = 0; i < data.length; i++) {
            //前k个入堆
            if (heap.size < k) {
                heap.add(data[i]);
            //大于堆顶元素，进行替换
            } else if (data[i] > heap.get()){
                heap.replace(data[i]);
            }

        }
    }
}
