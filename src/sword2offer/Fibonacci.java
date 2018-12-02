package sword2offer;

public class Fibonacci {


    public long recursionFibonacci(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        return recursionFibonacci(n - 1) + recursionFibonacci(n - 2);
    }

    public long loopFibonacci(int n) {
        if (n < 2) return n;
        long fn1 = 1;
        long fn0 = 0;
        for (int i = 2; i <= n; i++) {
            long ftemp = fn1;
            fn1 += fn0;
            fn0 = ftemp;
        }
        return fn1;
    }

    public static void main(String[] args) {
        System.out.println(new Fibonacci().loopFibonacci(10));
        System.out.println(new Fibonacci().recursionFibonacci(2));
    }
}