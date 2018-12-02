package cn.xupt.Sort;

import java.util.Random;

public class SituHeapSort<T extends Comparable<T>> {

    private void heapSort(T[] arr, int n) {
        //将数组变成最大堆
        for (int i = (n - 1) / 2; i >= 0; i--)
            _shiftDown(arr, n, i);
        //先交换 然后下沉再交换
        for (int i = n - 1; i > 0; i--) {
            swap(arr, i, 0);
            _shiftDown(arr, i, 0);
        }
    }

    private void swap(T[] arr, int i, int i1) {
        T temp = arr[i];
        arr[i] = arr[i1];
        arr[i1] = temp;
    }

    //下沉方法  while的条件就是 当前需要下沉的元素有子节点
    private void _shiftDown(T[] arr, int n, int i) {
        while (n > 2 * i + 1) {
            //取出左子节点的下标
            int j = 2 * i + 1;
            //比较左右节点
            if (j + 1 < n && arr[j].compareTo(arr[j + 1]) < 0)
                j++;
            if (arr[i].compareTo(arr[j]) >= 0) break;
            swap(arr, i, j);
            //进行下一次下沉
            i = j;
        }
    }

    public static void main(String[] args) {
        SituHeapSort<Integer> heapSort = new SituHeapSort<>();
        Integer[] integer = new Integer[1000000];
        for (int i = 0; i < integer.length; i++) {
            integer[i] = new Random(i).nextInt(2000);
        }
        long start = System.currentTimeMillis();
        heapSort.heapSort(integer, integer.length);
        long end = System.currentTimeMillis();
        System.out.println(end - start);

    }
}
