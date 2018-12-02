package cn.xupt.Sort;

/**
 * 低位优先的字符串排序
 */
public class LSD {
    public static void sort(String[] a, int w) {
        int N = a.length;
        int R = 256;
        String[] aux = new String[N];
        for (int d = w - 1; d >= 0; d--) {
            //根据第d个字符用建索引计数法排序
            //
            int[] count = new int[R + 1];
            //这里+1 意思就是 编号为1的位于count[2] 因为ascii 0为null 也就没有  所以这里count[1]为0
            for (int i = 0; i < N; i++) {
                count[a[d].charAt(i) + 1]++;
            }
            //将频率转化为索引
            //这里注意。转换后count[1]代表的就是 编号为1的字符的开始索引，count[2]就是2的开始索引。
            //这里可能有个转弯需要大家理解，之前是count[2]存储1所有的个数，假如有3个 那么对应的索引就是0-2. 保证了索引是从0开始的
            for (int i = 0; i < R; i++) {
                count[i + 1] += count[i];
            }
            //将元素进行分类。按顺序拿出字符对应的索引然后写入了aux数组。
            for (int i = 0; i < N; i++) {
                int ct = count[a[i].charAt(d)];
                aux[ct] = a[i];
                //自增是为了保证下次碰到相同的字符 给其写入正确的位置！
                count[a[i].charAt(d)]++;

                //这里我分开写了 便于大家理解
                //其实可以简写 比如 ：  aux[count[a[i].charAt(d)]++]=a[i];
            }
            //重写写回a中
            for (int i = 0; i < N; i++) {
                a[i] = aux[i];
            }
        }
    }
}