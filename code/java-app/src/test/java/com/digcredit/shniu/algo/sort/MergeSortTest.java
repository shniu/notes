package com.digcredit.shniu.algo.sort;

import org.junit.Test;
import org.slf4j.LoggerFactory;

/**
 * Created by shniu on 2018/12/30.
 */
public class MergeSortTest {

    private static org.slf4j.Logger logger = LoggerFactory.getLogger(MergeSortTest.class);

    @Test
    public void sort() throws Exception {

        Sort mergeSort = new MergeSort();

        int[] arr = new int[]{30, 5, 3, 44, 1, 40, 10, 100, 33, 9};
        mergeSort.sort(arr);

        logger.info("arr={}", arr);
        assert arr[0] == 1;

    }

}