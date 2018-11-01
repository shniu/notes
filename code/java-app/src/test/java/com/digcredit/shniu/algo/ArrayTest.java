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
        integerArray.addLast(11);
        integerArray.addLast(13);
        integerArray.addLast(15);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void insert() throws Exception {

    }

    @Test
    public void delete() {
        integerArray.remove(0);
        assert integerArray.find(13) == 0;
    }

    @Test
    public void find() throws Exception {
        System.out.println(integerArray);
        assert integerArray.find(11) == 0;
    }

}