package cn.xupt.myarray;

import java.util.Random;

/**
 * 最大索引堆
 * 还可以加入反向查找技术优化
 * 因为 indexes数组记录的是堆中元素的位置 所以在改变或者取出堆中某个元素的过程中需要确定
 * 不能直接拿索引i取出data[i]  而indexes[i]才是堆中目标元素的位置，这就需要一个数组进行循环查找了。
 * 如果我们采用反向索引技术  定义一个reserve[]myarray 假如indexes[j]=i 就令reserve[i]=j
 * 这样我们可以得到结论 indexes[reverse[i]]=i; reserve[indexes[i]]=i 这就是反向索引 假如向找出indexes 中值为i的
 * 只需要寻找reverse[i]即可
 */
public class IndexMaxHeap {
    private int[] data;//堆数组
    private int count;//堆中元素个数
    private int capacity;//堆的大小 也就是数组大小
    private int[] indexes;

    public IndexMaxHeap(int capacity) {
        this.data = new int[capacity + 1];
        this.indexes = new int[capacity + 1];
        count = 0;
        this.capacity = capacity;
    }

    //对于一个完全无序的数组，我们可以通过此过程构建一个最大堆
    //就是
    public IndexMaxHeap(int[] arr, int n) {
        //因为数组是从1开始，所以为n+1
        this(n + 1);

        for (int i = 0; i < n; i++) {
            data[i + 1] = arr[i];
            indexes[i + 1] = i + 1;
        }
        count = n;
        for (int i = count / 2; i >= 1; i--)
            shiftDown(i);
    }

    public int size() {
        return count;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    //将i出的值变为newItem
    public void change(int i, int newItem) {
        i += 1;
        data[i] = newItem;
        for (int j = 1; j <= count; j++) {
            if (indexes[j] == i) {
                shiftUp(j);
                shiftDown(j);
                return;
            }
        }
    }

    public void insert(int item, int i) {
        //断言空间是否够,不够的话可以扩充数组
        assert (count + 1 <= capacity);
        assert (i + 1 >= 1 && i + 1 <= capacity);
        i += 1;
        data[i] = item;
        indexes[count + 1] = i;
        data[count + 1] = item;
        count++;
        shiftUp(count);
    }

    //将加入的元素放到合适的位置 维持最大堆的原则。 复杂度为O(n)
    private void shiftUp(int count) {
        while (count > 1 && data[indexes[count / 2]] < data[indexes[count]]) {
            swap(count / 2, count);
            count /= 2;
        }
    }

    //取出最大值
    public int extractMax() {
        assert (count > 0);
        int ret = data[indexes[1]];
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
            if (j + 1 <= count && data[indexes[j + 1]] > data[indexes[j]])
                j++;
            //如果k处的值大于j处 就不需要交换直接完事
            if (data[indexes[k]] >= data[indexes[j]])
                break;
            //交换j k 维持最大堆
            swap(k, j);
            k = j;
        }
    }

    private void swap(int datum, int datum1) {
        //    System.out.println("//");
        int temp = indexes[datum];
        indexes[datum] = indexes[datum1];
        indexes[datum1] = temp;
    }

    public static void main(String[] args) {
        IndexMaxHeap maxHeap = new IndexMaxHeap(100);
        int arr[] = new int[10];
        for (int i = 0; i < 10; i++) {
            arr[i] = new Random(i).nextInt(20);
            maxHeap.insert(arr[i], i);
        }
        maxHeap.print(maxHeap);
        int i = maxHeap.extractMax();
        System.out.println(i);
        IndexMaxHeap maxHeap1 = new IndexMaxHeap(arr, 10);
        maxHeap1.print(maxHeap1);

    }

    private void print(IndexMaxHeap maxHeap) {
        for (int i = 1; i <= 10; i++) {
            System.out.print(maxHeap.data[indexes[i]] + " ");
        }
        System.out.println();
    }
}
