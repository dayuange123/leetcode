package from0to100;

public class Sqrtx {
    /**
     * 求x的开平方
     *
     * @param x
     * @return
     */
    public int mySqrt(int x) {
        if (x == 0) return x;
        int r = x, result = x;
        int l = 0;
        while (true) {
            if (result == x / result)
                return result;
            if (result > x / result && (result - 1) < x / (result - 1))
                return result - 1;
            if (result < x / result && (result + 1) > x / (result + 1))
                return result;
            if (result < x / result) {
                l = result + 1;
                result = (l + r) / 2;
            }
            if (result > x / result) {
                r = result - 1;
                result = (l + r) / 2;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(new Sqrtx().mySqrt(5));
    }
}
