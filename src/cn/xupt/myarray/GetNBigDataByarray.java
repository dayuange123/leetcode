package cn.xupt.myarray;

public class GetNBigDataByarray {
    /**
     * o(n)复杂度 的解决方案
     * 每次快速将 快排的比较数和要比较的数进行 比较
     */
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        quickSort(arr, 0, 6, 4);
//        for(int i=0;i<arr.length;i++){
//            System.out.println(arr[i]);
//        }
    }

    public static void quickSort(int arr[], int l, int r, int fmax) {
        if (r <= l)
            return;
        int v = arr[l];
        int lt = l;
        int i = l + 1;
        int rt = r;
        while (i <= rt) {
            int t = v - arr[i];
            if (t > 0) {
                int temp = arr[i];
                arr[i] = arr[lt];
                arr[lt] = temp;
                i++;
                lt++;
            } else if (t < 0) {
                int temp = arr[i];
                arr[i] = arr[rt];
                arr[rt] = temp;
                rt--;
            } else i++;
        }
//        quickSort(arr, l, lt - 1, fmax);
//        quickSort(arr, rt + 1, r, fmax - lt);
        //    System.out.println(arr[lt]);
        if (fmax == lt) {
            System.out.println(arr[lt] + "666");
            return;
        } else if (fmax < lt) {
            quickSort(arr, l, lt - 1, fmax);
        } else {
            quickSort(arr, rt + 1, r, fmax);
        }

    }

}
