package com.digcredit.shniu.algo.sort;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Bubble sort test
 * Created by shniu on 2018/12/31.
 */
public class BubbleSortTest {

    private static Logger logger = LoggerFactory.getLogger(BubbleSortTest.class);

    @Test
    public void sort() throws Exception {

        Sort bubbleSort = new BubbleSort();
        int[] arr = new int[]{30, 5, 3, 44, 1, 40, 10, 100, 33, 9};
        bubbleSort.sort(arr);

        logger.info("arr={}", arr);

    }

}