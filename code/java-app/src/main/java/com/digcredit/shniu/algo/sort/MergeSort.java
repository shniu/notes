package com.digcredit.shniu.algo.sort;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Merge sort
 * <p>
 * 原理：
 * 1. 使用分治思想，将一个大问题分解成小的子问题来解决
 * 2. 使用递归来实现，分治是一种解决问题的思想，递归是一种编程技巧
 * 3. 使用递归，先分析出递推条件，再找到终止条件
 * <p>
 * 分析：
 * 1. 归并排序是一个稳定排序算法
 * 2. 时间复杂度, O(nlogn), 时间复杂度比较稳定，和数据本身是否有序无关
 * 3. 空间复杂度，归并排序不是原地排序算法，O(n)
 * <p>
 * Created by shniu on 2018/12/30.
 */
public class MergeSort implements Sort {

    private static Logger logger = LoggerFactory.getLogger(MergeSort.class);

    public void sort(int[] array) {
        assert array != null && array.length > 0;
        mergeSort(array, 0, array.length - 1);
    }

    private void mergeSort(int[] array, int startPos, int endPos) {
        logger.info("startPos={}, endPos={}", startPos, endPos);

        if (startPos >= endPos)
            return;

        int middle = startPos + (endPos - startPos) / 2;

        mergeSort(array, startPos, middle);
        mergeSort(array, middle + 1, endPos);

        merge(array, startPos, middle, endPos);
    }

    private void merge(int[] array, int startPos, int middle, int endPos) {
        logger.info("{}, {}, {}", startPos, middle, endPos);
        int i = startPos;
        int j = middle + 1;
        int k = 0;
        int[] tmp = new int[endPos - startPos + 1];

        while (i <= middle && j <= endPos) {
            if (array[i] <= array[j]) {
                tmp[k++] = array[i++];
            } else {
                tmp[k++] = array[j++];
            }
        }

        int start = i;
        int end = middle;
        if (j <= endPos) {
            start = j;
            end = endPos;
        }

        while (start <= end) {
            tmp[k++] = array[start++];
        }

        for (i = 0; i <= endPos - startPos; i++) {
            array[startPos + i] = tmp[i];
        }
    }


}
