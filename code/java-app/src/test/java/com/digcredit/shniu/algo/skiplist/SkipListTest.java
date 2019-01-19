package com.digcredit.shniu.algo.skiplist;

import org.junit.Before;
import org.junit.Test;

/**
 * Test SkipList
 * Created by shniu on 2019/1/12.
 */
public class SkipListTest {

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testSkipNode() {
        SkipList skipList = new SkipList();
        skipList.insert(12, 50000000);
        skipList.insert(2, 60000000);
        skipList.insert(40, 9999999);
        skipList.insert(25, 4499999);
        skipList.insert(300, 8999999);

        SkipList.Node node1 = skipList.find(2);
        SkipList.Node node2 = skipList.find(3);
        assert node1.getValue() == 60000000;
        assert node2 == null;
    }

    @Test
    public void testFind_return_null() {
        SkipList skipList = new SkipList();
        assert skipList.find(11) == null;
    }

    @Test
    public void testInsert_succeed() {
        SkipList skipList = new SkipList();
        skipList.insert(111, 9999);
        skipList.insert(33, 444444);
        skipList.insert(55, 55555);
        skipList.insert(88888, 999);
        //assert skipList.getLevel() == 1;

        skipList.printAll();
    }

}