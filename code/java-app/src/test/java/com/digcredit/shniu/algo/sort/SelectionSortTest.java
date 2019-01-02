package com.digcredit.shniu.algo.sort;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Selection sort test
 * Created by shniu on 2018/12/31.
 */
public class SelectionSortTest {
    private static Logger logger = LoggerFactory.getLogger(SelectionSortTest.class);

    @Test
    public void sort() throws Exception {

        Sort selectionSort = new SelectionSort();
        int[] arr = new int[]{30, 5, 3, 44, 1, 40, 10, 100, 33, 9};
        selectionSort.sort(arr);

        logger.info("arr={}", arr);

    }

}