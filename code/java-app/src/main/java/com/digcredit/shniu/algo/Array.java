package com.digcredit.shniu.algo;

/**
 * Created by shniu on 2018/10/28.
 */
public class Array<T> {
    /**
     * 数组
     */
    private Object data[];

    /**
     * 长度
     */
    private int size;

    /**
     * 数组中的实际元素个数
     */
    private int count;

    /**
     * 构造方法
     *
     * @param capacity 数组大小
     */
    public Array(int capacity) {
        data = new Object[capacity];
        size = capacity;
        count = 0;
    }

    /**
     * 插入元素
     */
    public boolean insert(int index, T value) {
        // 数组已满
        if (count == size) {
            return false;
        }

        rangeCheck(index);

        for(int i = count; i > index; --i) {
            data[i] = data[i - 1];
        }
        data[index] = value;
        count++;
        return true;
    }

    /**
     * 访问指定下标数据
     */
    @SuppressWarnings("unchecked")
    public T find(int index) {
        rangeCheck(index);
        return (T) data[index];
    }

    private void rangeCheck(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        }
    }

    private String outOfBoundsMsg(int index) {
        return "Index: "+index+", Size: " + size;
    }
}
