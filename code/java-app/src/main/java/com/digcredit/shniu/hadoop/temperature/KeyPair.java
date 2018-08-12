/**
 * Created by Administrator on 2017/8/7 0007.
 */

package com.digcredit.shniu.hadoop.temperature;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class KeyPair implements WritableComparable<KeyPair> {

    private int year;
    private int temperature;

    public int getTemperature() {
        return temperature;
    }

    public int getYear() {
        return year;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int compare(int x, int y) {
        return (x < y) ? -1 : ((x == y) ? 0 : 1);
    }

    public int compareTo(KeyPair o) {
        int iRet = compare(year, o.getYear());
        if(iRet != 0) {
            return iRet;
        }

        return compare(temperature, o.getTemperature());
    }

    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeInt(year);
        dataOutput.writeInt(temperature);
    }

    public void readFields(DataInput dataInput) throws IOException {
        this.year = dataInput.readInt();
        this.temperature = dataInput.readInt();
    }

    @Override
    public String toString() {
        return year + "\t" + temperature;
    }

    @Override
    public int hashCode() {
        return new Integer(year+temperature).hashCode();
    }
}
