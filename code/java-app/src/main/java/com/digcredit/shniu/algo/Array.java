package com.digcredit.shniu.algo;

/**
 * 对 Java 基本数组的封装类
 * <p>
 * Created by shniu on 2018/10/28.
 */
public class Array<E> {
    /**
     * 数组
     */
    private E data[];

    /**
     * 长度
     */
    private int size;

    /**
     * 构造方法
     *
     * @param capacity 数组大小
     */
    @SuppressWarnings("unchecked")
    public Array(int capacity) {
        data = (E[]) new Object[capacity];
        size = 0;
    }

    /**
     * 默认无参数构造, 默认 10
     */
    public Array() {
        this(10);
    }

    /**
     * 获取数组容量
     *
     * @return 数组容量
     */
    public int getCapacity() {
        return data.length;
    }

    /**
     * 获取数组元素个数
     *
     * @return 数组元素个数
     */
    public int getCount() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 修改 index 处的元素
     *
     * @param index index
     * @param e     新元素
     */
    public void set(int index, E e) {
        rangeCheck(index);
        data[index] = e;
    }

    /**
     * 获取 index 处的元素
     *
     * @param index index
     * @return 返回对应位置的元素
     */
    public E get(int index) {
        rangeCheck(index);

        return data[index];
    }

    /**
     * 是否包含某个元素
     *
     * @param e 查找的元素, 泛型 E 需要有自己的 equals 的实现
     * @return 是否包含
     */
    public boolean contains(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e)) {
                return true;
            }
        }
        return false;
    }


    /**
     * 根据索引，删除元素
     */
    public E remove(int index) {
        rangeCheck(index);

        E e = data[index];
        for (int i = index + 1; i < size; i++) {
            data[i - 1] = data[i];
        }
        data[size] = null;
        size--;

        // resize
        if (size == data.length / 4 && data.length / 2 != 0) {
            resize(data.length / 2);
        }

        return e;
    }

    public E removeFirst() {
        return remove(0);
    }

    public E removeLast() {
        return remove(size);
    }

    public void removeElement(E e) {
        int index = find(e);
        if (index != 1) {
            remove(index);
        }
    }

    /**
     * 在 index 位置，插入元素e
     *
     * @param index index
     * @param e     e
     */
    public void add(int index, E e) {
        rangeCheck(index);

        if (size == data.length) {
            resize(2 * data.length);
        }

        for (int i = size - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }
        data[index] = e;
        size++;
    }

    /**
     * 在开头添加元素
     *
     * @param e e
     */
    public void addFirst(E e) {
        add(0, e);
    }

    public void addLast(E e) {
        add(size, e);
    }

    /**
     * 容量调整
     *
     * @param capacity 调整后的大小
     */
    @SuppressWarnings("unchecked")
    private void resize(int capacity) {
        E[] newE = (E[]) new Object[capacity];

        for (int i = 0; i < size; i++) {
            newE[i] = data[i];
        }
        data = newE;
    }

    /**
     * 访问指定下标数据
     *
     * @param e 待查找的元素
     * @return 存在就返回索引位置，不存在就返回-1
     */
    @SuppressWarnings("unchecked")
    public int find(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e)) {
                return i;
            }
        }

        return -1;
    }

    private void rangeCheck(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        }
    }

    private String outOfBoundsMsg(int index) {
        return "Index: " + index + ", Size: " + size;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("Array size = %d, capacity = %d \n", size, data.length));
        builder.append('[');
        for (int i = 0; i < size; i++) {
            builder.append(data[i]);
            if (i != size - 1) {
                builder.append(", ");
            }
        }
        builder.append(']');
        return builder.toString();
    }
}
