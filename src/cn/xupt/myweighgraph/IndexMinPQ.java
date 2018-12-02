package cn.xupt.myweighgraph;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class IndexMinPQ<Key extends Comparable<Key>> implements Iterable<Integer> {
    private int maxN; //索引优先队列中元素的最大个数
    private int N; //当前索引优先队列中元素的个数
    private int[] pq;//使用一级索引的二叉堆
    private int[] qp;//pq的对称映射 qp[pq[i]] = pq[qp[i]] = i，用于映射key索引对应pq二叉堆中的位置
    private Key[] keys; //keys[i] = priority of i

    /**
     * 初始化索引区间为（0到maxN-1）的空索引优先队列
     * @param capacity
     */
    public IndexMinPQ(int capacity) {
        if (capacity <= 0)
            throw new IllegalArgumentException();
        maxN = capacity;
        N = 0;
        pq = new int[capacity + 1];
        qp = new int[capacity + 1];
        keys = (Key[]) new Comparable[capacity + 1];
        //初始每个索引都没用过
        for (int i = 0; i <= maxN; i++)
            qp[i] = -1;
    }

    public boolean isEmpty() {
        return N == 0;
    }


    public int size() {
        return N;
    }

    /**
     * 判断优先队列是否已存在索引i
     * @param i 索引i
     * @return 如果索引i之前已插入，返回true,否则false
     */
    public boolean contains(int i) {
        return qp[i] != -1;
    }

    /**
     * 返回最小值的索引号
     *
     * @return 最小值的索引号
     */
    public int minIndex() {
        if (isEmpty())
            throw new NoSuchElementException("IndexMinPQ underflow.");
        return pq[1];
    }

    /**
     * 返回优先队列最小值，即二叉堆根节点
     *
     * @return 优先队列最小值
     */
    public Key minKey() {
        if (isEmpty())
            throw new NoSuchElementException("IndexMinPQ underflow.");
        return keys[pq[1]];
    }

    /**
     * 索引i对应优先队列中的键值
     *
     * @param i 索引i
     * @return 索引i对应的键值
     */
    public Key keyOf(int i) {
        if (!contains(i))
            throw new NoSuchElementException("IndexMinPQ has not contains index i");
        return keys[i];
    }

    /**
     * 将索引i与键值key关联
     *
     * @param i   索引i
     * @param key 键值key
     */
    public void insert(int i, Key key) {
        if (i < 0 || i >= maxN)
            throw new IllegalArgumentException("index i out of boundary.");
        if (contains(i))
            throw new IllegalArgumentException("index i has allocted");
        N++;
        qp[i] = N;
        pq[N] = i;//pq,qp互为映射
        keys[i] = key;
        adjustUp(N);
    }

    /**
     * 删除最小键值并返回其对应的索引
     *
     * @return 最小键值对应的索引
     */
    public int delMin() {
        if (isEmpty())
            throw new NoSuchElementException("IndexMinPQ underflow.");
        int min = minIndex();
        delete(min);
        return min;
    }

    /**
     * 删除索引i以及其对应的键值
     *
     * @param i 待删除的索引i
     */
    public void delete(int i) {
        if (!contains(i))
            throw new NoSuchElementException("IndexMinPQ has not contains index i");
        int pqi = qp[i];
        swap(pqi, N--);
        adjustUp(pqi);
        adjustDown(pqi);
        qp[i] = -1;     //删除
        keys[i] = null; //便于垃圾收集
        pq[N + 1] = -1; //不是必须，但是加上便于理解
    }

    /**
     * 改变与索引i关联的键值
     *
     * @param i   待改变键值的索引
     * @param key 改变后的键值
     */
    public void changeKey(int i, Key key) {
        if (!contains(i))
            throw new NoSuchElementException("IndexMinPQ has not contains index i");
        if (keys[i].compareTo(key) == 0)
            throw new IllegalArgumentException("argument key equpal to the original value.");
        if (key.compareTo(keys[i]) > 0)
            increaseKey(i, key);//原键值增加
        else
            decreaseKey(i, key);//原键值减小
    }

    /**
     * 减小与索引i关联的键值到给定的新键值
     *
     * @param i   与待减小的键值关联的索引
     * @param key 新键值
     */
    public void decreaseKey(int i, Key key) {
        if (!contains(i))
            throw new NoSuchElementException("IndexMinPQ has not contains index i");
        if (key.compareTo(keys[i]) > 0)
            throw new IllegalArgumentException("argument key more than the original value.");
        keys[i] = key;
        int pqi = qp[i];
        adjustUp(pqi);
        adjustDown(pqi);
    }

    /**
     * 增加与索引i关联的键值到给定的新键值
     *
     * @param i   与待增加的键值关联的索引
     * @param key 新键值
     */
    public void increaseKey(int i, Key key) {
        if (!contains(i))
            throw new NoSuchElementException("IndexMinPQ has not contains index i");
        if (key.compareTo(keys[i]) < 0)
            throw new IllegalArgumentException("argument key less than the original value");
        keys[i] = key;
        int pqi = qp[i];
        adjustUp(pqi);
        adjustDown(pqi);
    }

    /***************************************************************************
     * General helper functions.
     ***************************************************************************/
    /**
     * 交换一级索引值，以及其对称映射中的值
     *
     * @param i 使用一级索引的二叉堆的索引i
     * @param j 使用一级索引的二叉堆的索引j
     */
    private void swap(int i, int j) {
        int t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
        int qpi = pq[i];
        int qpj = pq[j];
        qp[qpi] = i;
        qp[qpj] = j;
    }

    /**
     * 判断键值的大小关系
     *
     * @param i 使用一级索引的二叉堆的索引i
     * @param j 使用一级索引的二叉堆的索引j
     * @return keys[ki] < keys[kj] 返回true,否则返回false
     */
    private boolean less(int i, int j) {
        int ki = pq[i];
        int kj = pq[j];
        return keys[ki].compareTo(keys[kj]) < 0;
    }

    /***************************************************************************
     * Heap helper functions.
     ***************************************************************************/
    /**
     * 向下调整最小二叉堆
     *
     * @param i 一级索引的二叉堆的索引i，pq数组的数组位置
     */
    private void adjustDown(int i) {
        while (2 * i <= N) {
            int l = 2 * i;
            while (l < N && less(l + 1, l))
                l++;
            swap(l, i);
            i = l;
        }
    }

    /**
     * 向上调整最小二叉堆
     *
     * @param i 一级索引的二叉堆的索引i，pq数组的数组位置
     */
    private void adjustUp(int i) {
        while (i > 1) {
            int p = i / 2;
            if (less(p, i))
                break;
            swap(p, i);
            i = p;
            i = p;
        }
    }


    @Override
    public Iterator<Integer> iterator() {
        return new HeapIterator();
    }

    /***************************************************************************
     * Iterators.
     ***************************************************************************/
    /**
     * Returns an iterator that iterates over the keys on the
     * priority queue in ascending order.
     * The iterator doesn't implement <tt>remove()</tt> since it's optional.
     *
     * @return an iterator that iterates over the keys in ascending order
     */
    private class HeapIterator implements Iterator<Integer> {
        // create a new pq
        IndexMinPQ<Key> copy;

        // add all elements to copy of heap
        // takes linear time since already in heap order so no keys move
        public HeapIterator() {
            copy = new IndexMinPQ<Key>(maxN);
            for (int i = 1; i <= N; i++) {
                int ki = pq[i];
                Key key = keys[ki];
                copy.insert(ki, key);
            }
        }

        @Override
        public boolean hasNext() {
            return !copy.isEmpty();
        }

        @Override
        public Integer next() {
            if (!hasNext())
                throw new NoSuchElementException("IndexMinPQ underflow.");
            return copy.delMin();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("unsupported remove operation.");
        }
    }


}

