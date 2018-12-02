package cn.xupt.Sort;

import cn.xupt.myarray.MaxHeap;

import java.util.Random;

/**
 * 由最大堆实现的堆排序
 */
public class HeapSort {
    public static void main(String[] args) {
        MaxHeap maxHeap = new MaxHeap(100);
        for (int i = 0; i < 100; i++) {
            maxHeap.insert(new Random(i).nextInt(20));
        }
        //初始化一个数组将排序结果存入
        int[] arr = new int[100];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = maxHeap.extractMax();
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
}
