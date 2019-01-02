package com.digcredit.shniu.algo.sort;

/**
 * Bubble sort
 *
 * 1. 原地排序算法
 * 2. 稳定排序算法
 * 3. 最好复杂度 O(n) 最坏复杂度 O(n^2) 平均 O(n^2)
 *
 * Created by shniu on 2018/12/31.
 */
public class BubbleSort implements Sort {

    public void sort(int[] array) {
        assert array != null && array.length > 0;

        for (int i = 0; i < array.length; ++i) {

            boolean flag = false;

            for (int j = 0; j < array.length - i - 1; ++j) {

                // swap
                if (array[j] > array[j + 1]) {
                    int tmp = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = tmp;
                    flag = true;
                }
            }

            if (!flag) break;
        }
    }
}
