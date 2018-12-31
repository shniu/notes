package com.digcredit.shniu.algo.sort;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Test quick sort
 * Created by shniu on 2018/12/30.
 */
public class QuickSortTest {

    private static Logger logger = LoggerFactory.getLogger(QuickSortTest.class);

    @Test
    public void sort() throws Exception {
        Sort quickSort = new QuickSort();

        int[] arr = new int[]{30, 5, 3, 44, 1, 40, 10, 100, 33, 9};
        quickSort.sort(arr);

        logger.info("arr={}", arr);
        assert arr[0] == 1;
    }

}