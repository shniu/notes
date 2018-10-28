package com.digcredit.shniu.algo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by shniu on 2018/10/28.
 */
public class ArrayTest {

    private Array<Integer> integerArray;

    @Before
    public void setUp() throws Exception {
        integerArray = new Array<Integer>(10);
        integerArray.insert(0, 10);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void insert() throws Exception {

    }

    @Test
    public void find() throws Exception {
        System.out.println(integerArray);
        integerArray.find(0);
    }

}