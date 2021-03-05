/*
 * ************************************************************
 * 文件：_321_拼接最大数.java
 * 模块：app
 * 项目：MyApplication
 * 当前修改时间：2020年12月03日 09:20:36
 * 上次修改时间：2020年12月02日 17:13:59
 * 作者：Havi
 * Copyright (c) 2020
 * ************************************************************
 *
 */

package com.example.leetcode.other;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class _321_拼接最大数 {

    /*
    没有通过测试
     */

    public int[] maxNumber(final int[] nums1, int[] nums2, int k) {
        if (nums1.length == 0) {
            int[] tmp = new int[k];
            for (int i = 0; i < k; i++) {
                tmp[i] = nums2[i];
            }
        }
        if (nums2.length == 0) {
            int[] tmp = new int[k];
            for (int i = 0; i < k; i++) {
                tmp[i] = nums1[i];
            }
        }
        ArrayList<Integer> arr1 = new ArrayList<>(nums1.length);
        ArrayList<Integer> arr2 = new ArrayList<>(nums2.length);
        int[] result = new int[k];
        int currentIndex1 = -1;
        int currentIndex2 = -1;
        for (int i = 0; i < nums1.length; i++) {
            arr1.add(nums1[i]);
        }
        for (int i = 0; i < nums2.length; i++) {
            arr2.add(nums2[i]);
        }

        for (int i = 0; i < k; i++) {
            ArrayList<Integer> tmp1 = (ArrayList<Integer>) arr1.clone();
            ArrayList<Integer> tmp2 = (ArrayList<Integer>) arr2.clone();
            do {
                Integer num1 = Collections.max(tmp1);
                Integer num2 = Collections.max(tmp2);
                if (num1 >= num2) {
                    result[i] = num1;
                    currentIndex1 = arr1.indexOf(num1);
                    if (((arr1.size() + arr2.size()) - ((currentIndex1>=0?(currentIndex1+1):0) + (currentIndex2>=0?(currentIndex2+1):0)) < (k-i-1))) {
                        tmp1.remove(num1);
                        continue;
                    }
                    arr1.set(currentIndex1, -1);
                    for (int j = 0; j < currentIndex1; j++) {
                        arr1.set(j, -1);
                    }
                } else {
                    result[i] = num2;
                    currentIndex2 = arr2.indexOf(num2);
                    if ((arr1.size() + arr2.size()) - ((currentIndex1>=0?(currentIndex1+1):0) + (currentIndex2>=0?(currentIndex2+1):0)) < (k-i-1)) {
                        tmp2.remove(num2);
                        continue;
                    }
                    arr2.set(currentIndex2, -1);
                    for (int j = 0; j < currentIndex2; j++) {
                        arr2.set(j, -1);
                    }
                }
            } while (((arr1.size() + arr2.size()) - ((currentIndex1>=0?(currentIndex1+1):0) + (currentIndex2>=0?(currentIndex2+1):0)) < (k-i-1)));

        }
        return result;
    }
    


//    public int[] maxNumber(final int[] nums1, int[] nums2, int k) {
//        int[] sorted1 = sortArr(nums1);
//        int[] sorted2 = sortArr(nums2);
//        System.out.println(Arrays.toString(sorted1));
//        System.out.println(Arrays.toString(sorted2));
//        int i = 0,j = 0;
//        int[] result = new int[k];
//        for (int l = 0; l < k; l++) {
//            if (i > sorted1.length-1) {
//                result[l] = sorted2[j];
//                j++;
//                continue;
//            }
//
//            if (j > sorted2.length-1) {
//                result[l] = sorted1[i];
//                i++;
//                continue;
//            }
//            if (sorted1[i] >= sorted2[j]) {
//                result[l] = sorted1[i];
//                i++;
//            } else {
//                result[l] = sorted2[j];
//                j++;
//            }
//        }
//        return result;
//    }

    public int[] sortArr(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    int tmp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = tmp;
                }
            }
        }
        return arr;
    }


}
