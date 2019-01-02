package com.digcredit.shniu.algo.sort;

/**
 * Selection sort
 *
 * 1. 原地排序
 * 2. 不稳定
 * 3. O(n^2)
 *
 * Created by shniu on 2018/12/31.
 */
public class SelectionSort implements Sort {

    public void sort(int[] array) {
        assert array != null && array.length > 0;

        for (int i = 0; i < array.length; ++i) {
            int min = i;
            for (int j = i; j < array.length; ++j) {
                if (array[min] > array[j]) {
                    min = j;
                }
            }

            // swap
            if (min != i) {
                int tmp = array[min];
                array[min] = array[i];
                array[i] = tmp;
            }
        }
    }
}
