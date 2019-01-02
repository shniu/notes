package com.digcredit.shniu.algo.sort;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Insertion sort test
 * Created by shniu on 2018/12/31.
 */
public class InsertionSortTest {
    private static Logger logger = LoggerFactory.getLogger(InsertionSortTest.class);

    @Test
    public void sort() throws Exception {

        Sort insertionSort = new InsertionSort();
        int[] arr = new int[]{30, 5, 3, 44, 1, 40, 10, 100, 33, 9};
        insertionSort.sort(arr);

        logger.info("arr={}", arr);
    }

}