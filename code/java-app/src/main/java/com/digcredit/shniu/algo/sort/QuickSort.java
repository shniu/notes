package com.digcredit.shniu.algo.sort;

/**
 * Quick sort
 * <p>
 * 原理：
 * 1. 使用分治思想
 * 2. 如果要排序数组中下标从 p 到 r 之间的一组数据，我们选择p 到 r 之间的任意一个数据作为 pivot（分区点）
 * 遍历 p 到 r 之间的数据，小于 pivot 的放在左边，否则放在右边
 * <p>
 * 分析：
 * 1. 快速排序是原地排序函数，解决了归并排序占用大量内存的问题
 * 2. 快速排序不是稳定排序算法
 * 3. 最好和平均时间复杂度是 O(nlogn)，最坏情况下会退化到 O(n^2), 只有在分区极其不均衡的情况下
 * <p>
 * Created by shniu on 2018/12/30.
 */
public class QuickSort implements Sort {

    public void sort(int[] array) {
        assert array != null && array.length > 0;
        quickSort(array, 0, array.length - 1);
    }

    private void quickSort(int[] array, int startPos, int endPos) {
        if (startPos >= endPos) {
            return;
        }

        // pivot point
        int pivot = partition(array, startPos, endPos);
        quickSort(array, startPos, pivot - 1);
        quickSort(array, pivot + 1, endPos);
    }

    private int partition(int[] array, int startPos, int endPos) {
        int pivot = array[endPos];
        int i = startPos;

        for (int j = startPos; j < endPos; ++j) {
            if (array[j] < pivot) {
                swap(array, j, i);
                ++i;
            }
        }

        swap(array, endPos, i);

        return i;
    }

    private void swap(int[] array, int endPos, int i) {
        int tmp = array[i];
        array[i] = array[endPos];
        array[endPos] = tmp;
    }
}
