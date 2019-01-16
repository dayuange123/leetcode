package cn.xupt.Sort;

public class Quick3string {
    private static int charAt(String s, int d) {
        if (d < s.length()) return s.charAt(d);
        else return -1;
    }

    public static void sort(String[] a) {
        sort(a, 0, a.length - 1, 0);
    }

    private static void sort(String[] a, int lo, int hi, int d) {
        if (hi <= lo) return;
        //使用lt 和gt 记录开始结束下标
        int lt = lo, gt = hi;
        //获取到第一个字符串第一个字母的ASCII  如果为末尾字符串 返回-1
        int v = charAt(a[lo], d);
        int i = lo + 1;
        //循环判断并交换 从lo+1开始
        while (i <= gt) {
            //获取到第i个字符串第一个字母的ASCII  如果为末尾字符串 返回-1
            int t = charAt(a[i], d);
            //和第一个进行比较 如果小于第一个 交换   并将三分的第一个标记 lt++；i++
            if (t < v) exch(a, lt++, i++);
                //如果比第一个大  将其换到最后面 并让三分的第二个标记 gt--
            else if (t > v) exch(a, i, gt--);
                //等于的话  i++；
            else i++;
        }
        //至此循环结束后  lo-lt-1位置都是排在v前面的 ，lt-gt是等于v的，gt+1以后的都是排在v后面的
        //然后我们对左边的再进行三向切分排序，还是从d开始
        sort(a, lo, lt - 1, d);
        //如果不是末尾字符串 进行 d+1层 的排序
        if (v >= 0) sort(a, lt, gt, d + 1);
        //对后面的进行排序
        sort(a, gt + 1, hi, d);
    }

    private static void exch(String[] a, int j, int i) {
        String t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
}