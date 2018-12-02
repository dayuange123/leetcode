package cn.xupt.find;

public class BinarySearch<T extends Comparable<T>> {

    public int binarySearch(T[] arr, T target) {
        //在arr[l....r]中查找target
        int l = 0, r = arr.length - 1;
        while (l <= r) {
            //这里可能会溢出
            //int mid = (l + r) / 2;
            int mid = l + (r - l) / 2;
            if (arr[mid].compareTo(target) == 0) {
                return mid;
            } else if (arr[mid].compareTo(target) > 0) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Integer[] data = {1, 2, 2, 3, 3, 3, 5, 6, 78, 100, 100, 100};
        //   System.out.println(new BinarySearch().binarySearch(data, 3));
        System.out.println(new BinarySearch().ceilBinary(data, 1000));
    }

    public int floorBinary(T[] arr, T target) {
        int l = -1, r = arr.length - 1;
        while (l < r) {
            // 使用向上取整避免死循环 比如 r为1 l为0 此时mid 为0 且 arr[mid]<target 这是就会成为死循环
            int mid = l + (r - l + 1) / 2;
            if (arr[mid].compareTo(target) >= 0)
                r = mid - 1;
            else
                l = mid;
        }
        assert l == r;
        // 如果该索引+1就是target本身, 该索引+1即为返回值
        if (l + 1 < arr.length && arr[l + 1] == target)
            return l + 1;
        // 否则, 该索引即为返回值
        return l;
    }

    public int ceilBinary(T[] arr, T target) {
        // 寻找比target大的最小索引值
        //这里r=arr.length 保证能边界情况能取到最后一个arr.length-1的值
        //因为这里的while条件为l<r 所以如果r=arr.length-1的话 mid永远取不到arr.length-1。
        int l = 0, r = arr.length;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (arr[mid].compareTo(target) <= 0) {
                l = mid + 1;
            } else r = mid;
        }
        assert l == r;
        // 如果该索引-1就是target本身, 该索引+1即为返回值
        if (r - 1 >= 0 && arr[r - 1] == target)
            return r - 1;
        return r;
    }
}