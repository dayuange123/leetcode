package cn.xupt.recursion;

public class Halfsetproblem {
    /**
     * 问题描述：
     * 给定一个自然数n，由n 开始可以依次产生半数集set(n)中的数如下。
     * (1) n∈set(n)；
     * (2) 在n 的左边加上一个自然数，但该自然数不能超过最近添加的数的一半；
     * (3) 按此规则进行处理，直到不能再添加自然数为止。
     * 例如，set(6)={6,16,26,126,36,136}。半数集set(6)中有6 个元素。
     * 注意半数集是多重集。
     * <p>
     * 输入自然数 n
     * 输出半数集元素个数
     */
    public static int halfSet(int n) {
        int max = 1;

        for (int i = 1; i <= n / 2; i++) {
            max += halfSet(i);
        }
        return max;
    }
    public static void main(String[] args) {
        System.out.println(Halfsetproblem.halfSet(1));
    }

}
