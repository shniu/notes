package com.digcredit.shniu.algo.skiplist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

/**
 * Skip list node
 * Created by shniu on 2019/1/12.
 */
public class SkipList {

    private static Logger logger = LoggerFactory.getLogger(SkipList.class);
    private static final int MAX_LEVEL = 16;
    private Random random = new Random();

    // SkipList height
    private int level;
    // Header node of SkipList
    private Node header;

    class Node {
        // 关键词
        private int key;
        // 节点实际存储的值，暂且用 int 表示，可以扩展为其他复杂类型
        private int value;
        private Node[] forwards = new Node[MAX_LEVEL];
        private int nodeLevel;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public int getKey() {
            return key;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "key=" + key +
                    ", value=" + value +
                    ", nodeLevel=" + nodeLevel +
                    '}';
        }
    }

    public SkipList() {
        level = 1;
        // 头节点
        header = new Node(-1, -1);
    }

    public int getLevel() {
        return level;
    }

    /**
     * 将数据插入到 SkipList 中
     */
    public void insert(int key, int value) {
        logger.debug("in SkipList.insert");

        int newNodeMaxLevel = randomLevel();
        Node newNode = new Node(key, value);
        newNode.nodeLevel = newNodeMaxLevel;

        Node update[] = new Node[newNodeMaxLevel];
        for (int i = 0; i < update.length; ++i) {
            update[i] = header;
        }

        Node p = header;
        for (int i = newNodeMaxLevel - 1; i >= 0; --i) {
            logger.debug("find level {}", i + 1);
            while (p.forwards[i] != null && p.forwards[i].getKey() < key) {
                logger.debug("iterator level {}, find key={}, value={}",
                        i + 1, p.forwards[i].getKey(), p.forwards[i].getValue());
                p = p.forwards[i];
            }
            update[i] = p;
            logger.debug("update[{}] = {}", i, p);
        }

        for (int i = 0; i < newNodeMaxLevel; ++i) {
            newNode.forwards[i] = update[i].forwards[i];
            update[i].forwards[i] = newNode;
            logger.debug("newNode.forwards[{}]={}", i, newNode.forwards[i]);
            logger.debug("update[{}].forwards[{}]={}", i, i, update[i].forwards[i]);
        }

        if (level < newNodeMaxLevel) {
            level = newNodeMaxLevel;
        }

    }

    private int randomLevel() {
        logger.debug("in randomLevel");
        int level = 1;
        for (int i = 0; i < MAX_LEVEL; ++i) {
            if (random.nextInt() % 2 == 1) {
                level ++;
            }
        }
        logger.debug("random level = {}", level);
        return level;
    }

    /**
     * 查找
     */
    public Node find(int key) {
        logger.debug("in SkipList.find");

        // 从最上层开始查找
        Node p = header;
        // 按层遍历，在当前层找到合适的位置
        for (int i = level - 1; i >= 0; --i) {
            logger.debug("find level {}", i + 1);
            while (p.forwards[i] != null && p.forwards[i].key < key) {
                logger.debug("iterator level {}, find key={}, value={}",
                        i + 1, p.forwards[i].getKey(), p.forwards[i].getValue());
                p = p.forwards[i];
            }
        }

        logger.debug("iterator end, find node={}", p);

        // 判断最底层的节点是否是要找的节点
        if (p.forwards[0] != null && p.forwards[0].key == key) {
            logger.debug("done and found");
            return p;
        }

        logger.debug("done and not found");
        return null;
    }

    /**
     * 删除
     */
    public void delete() {

    }

    public void printAll() {
        Node p = header;
        while (p.forwards[0] != null) {
            logger.info(p.forwards[0] + " ");
            p = p.forwards[0];
        }
        logger.info("\n");
    }

}
