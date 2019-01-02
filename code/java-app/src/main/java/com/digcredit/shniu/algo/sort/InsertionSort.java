package com.digcredit.shniu.algo.sort;

/**
 * Insertion sort
 *
 * 1. 稳定排序算法
 * 2. 原地排序算法
 * 3. 最好 O(n) 最坏和平均 O(n^2)
 * Created by shniu on 2018/12/31.
 */
public class InsertionSort implements Sort {

    public void sort(int[] array) {
        assert array != null && array.length > 0;

        for (int i = 1; i < array.length; ++i) {
            int value = array[i];
            int j = i - 1;

            for (; j >= 0; --j) {
                if (array[j] > value) {
                    array[j + 1] = array[j];
                } else {
                    break;
                }
            }
            array[j + 1] = value;
        }
    }
}
