package com.digcredit.shniu.hadoop.temperature;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

/**
 * Created by Administrator on 2017/8/7 0007.
 */
public class Group extends WritableComparator {

    public Group() {
        super(KeyPair.class, true);
    }

    // reduce的二次排序阶段，根据year值进行分组
    public int compare(WritableComparable a, WritableComparable b) {
        KeyPair k1 = (KeyPair) a;
        KeyPair k2 = (KeyPair) b;

        return Integer.compare(k1.getYear(), k2.getYear());
    }
}
