package cn.xupt.myarray;

import java.util.Random;

public class MaxHeap {
    private int[] data;//堆数组
    private int count;//堆中元素个数
    private int capacity;//堆的大小 也就是数组大小

    public MaxHeap(int capacity) {
        this.data = new int[capacity];
        count = 0;
        this.capacity = capacity;
    }

    //对于一个完全无序的数组，我们可以通过此过程构建一个最大堆
    //就是
    public MaxHeap(int[] arr, int n) {
        //因为数组是从1开始，所以为n+1
        this(n + 1);
        for (int i = 0; i < n; i++)
            data[i + 1] = arr[i];
        count = n;
        for (int i = count / 2; i >= 1; i--)
            shiftDown(i);
    }

    int size() {
        return count;
    }

    boolean isEmpty() {
        return count == 0;
    }

    public void insert(int item) {
        //断言空间是否够,不够的话可以扩充数组
        assert (count + 1 <= capacity);
        data[count + 1] = item;
        count++;
        shiftUp(count);
    }

    //将加入的元素放到合适的位置 维持最大堆的原则。 复杂度为O(n)
    private void shiftUp(int count) {
        while (count > 1 && data[count / 2] < data[count]) {
            swap(count / 2, count);
            count /= 2;
        }
    }

    //取出最大值
    public int extractMax() {
        assert (count > 0);
        int ret = data[1];
        swap(1, count);
        count--;
        shiftDown(1);
        return ret;
    }

    //维持堆的有序 就是k索引处的元素放到正确的位置。
    private void shiftDown(int k) {
        while (2 * k <= count) {
            int j = 2 * k;
            //判断左右哪个最大 将最大的索引存储到j
            if (j + 1 <= count && data[j + 1] > data[j])
                j ++;
            //如果k处的值大于j处 就不需要交换直接完事
            if (data[k] >= data[j])
                break;
            //交换j k 维持最大堆
            swap(k, j);
            k = j;
        }
    }

    private void swap(int datum, int datum1) {
        //    System.out.println("//");
        int temp = data[datum];
        data[datum] = data[datum1];
        data[datum1] = temp;
    }

    public static void main(String[] args) {
        MaxHeap maxHeap = new MaxHeap(100);
        int arr[] = new int[10];
        for (int i = 0; i < 10; i++) {
            arr[i] = new Random(i).nextInt(20);
            maxHeap.insert(arr[i]);
        }
        maxHeap.print(maxHeap);
        int i = maxHeap.extractMax();
        System.out.println(i);
        MaxHeap maxHeap1 = new MaxHeap(arr, 10);
        maxHeap1.print(maxHeap1);

    }

    private void print(MaxHeap maxHeap) {
        for (int i = 1; i <= 10; i++) {
            System.out.print(maxHeap.data[i] + " ");
        }
        System.out.println();
    }
}
