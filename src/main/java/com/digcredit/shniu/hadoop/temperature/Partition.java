package com.digcredit.shniu.hadoop.temperature;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

/**
 * Created by Administrator on 2017/8/7 0007.
 */
public class Partition extends Partitioner<KeyPair, Text> {

    /**
     *
     * @param keyPair  key
     * @param text   value
     * @param num  reduce的数量
     * @return
     */
    public int getPartition(KeyPair keyPair, Text text, int num) {
        return (keyPair.getYear() * 127) % num;
    }
}
