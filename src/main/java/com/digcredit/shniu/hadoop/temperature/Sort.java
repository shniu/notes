package com.digcredit.shniu.hadoop.temperature;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

/**
 * Created by Administrator on 2017/8/7 0007.
 */
public class Sort extends WritableComparator {
    public Sort() {
        super(KeyPair.class, true);
    }

    @Override
    public int compare(WritableComparable a, WritableComparable b) {
        KeyPair k1 = (KeyPair) a;
        KeyPair k2 = (KeyPair) b;

        int iRet = Integer.compare(k1.getYear(), k2.getYear());
        if (iRet != 0) {
            return iRet;
        }

        return Integer.compare(k1.getTemperature(), k2.getTemperature());
    }
}
