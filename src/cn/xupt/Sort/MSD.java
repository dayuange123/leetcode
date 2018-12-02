package cn.xupt.Sort;

import java.util.Arrays;

public class MSD {
    private static int R = 256;
    private static final int M = 5;
    private static String[] aux;

    private static int charAt(String s, int d) {
        if (d < s.length()) return s.charAt(d);
        else return -1;
    }

    public static void sort(String[] a) {
        int N = a.length;
        aux = new String[N];
        sort(a, 0, N - 1, 0);
    }

    public static void main(String[] args) {
        String[] aa = new String[]{"a123", "v123", "c1", "a12", "v12","a123","a123","a123","a123"
        ,"a1235","a1236","a1239","a12310","a12311","a12312","a12313","a12314","a12315"};
        MSD.sort(aa);
        Arrays.stream(aa).forEach(s -> {
            System.out.println(s);
        });
    }

    private static void sort(String[] a, int lo, int hi, int d) {
        //以第d个字符为键将a[lo] 至a[hi]进行排序
        if (lo >= hi)
            return;
        //对于范围小的 我们使用插入排序来提升效率
//        if (hi <= lo + M) {
//            Inserttion.sort(a, lo, hi, d);
//            return;
//        }

        //这里初始化频率数组  为什么要初始化为R+2，是因为其中第一位用来表示字符串到达末尾
        int[] count = new int[R + 2];
        //
        for (int i = lo; i <= hi; i++) {
            //这里使用 count[3]表示 ascll为1的字母的频率 而count[1]则代表 字符串为空的个数。
            count[charAt(a[i], d) + 2]++;
        }

        //count[0]为0  count[0]代表越界了的字符串的开始
        //count[1]代表 为ascll为0的开始索引  count[2]代表为1的开始索引
        for (int r = 0; r < R + 1; r++)
            count[r + 1] += count[r];

        //将数据进行分类

        //这里就可以看到 aux数组放的先是字符串d位置越界了的。也就是count[0]
        //显示越界了的 然后是从小到大的。
        for (int i = lo; i <= hi; i++) {
            aux[count[charAt(a[i], d) + 1]++] = a[i];
        }
        //重写回原数组
        //因为之前aux数组是从0开始写入的，这里是i-lo
        for (int i = lo; i <= hi; i++)
            a[i] = aux[i - lo];
        //然后递归的对字母相同的字符串进行后续排序
        //count[r]到count[r+1]也就是以r字符开头对应的所有字符串 索引区间。
        //如果没有这个字母开有的字符串 对应的 区间范围差就是0
        for (int r = 0; r < R; r++)
            sort(a, lo + count[r], lo + count[r + 1] - 1, d + 1);
    }

    static class Inserttion {
        public static void sort(String[] a, int lo, int hi, int d) {
            for (int i = lo; i <= hi; i++)
                for (int j = i; j > lo && less(a[j], a[j - 1], d); j--) {
                    exch(a, j, j - 1);
                }
        }

        private static void exch(String[] a, int j, int i) {
            String t = a[i];
            a[i] = a[j];
            a[j] = t;
        }

        private static boolean less(String s, String s1, int d) {
            return s.substring(d).compareTo(s1.substring(d)) < 0;
        }
    }
}