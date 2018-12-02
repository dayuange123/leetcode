package from0to100;

import java.math.BigDecimal;
import java.util.Map;

public class PowXn {
    public double myPow(double x, int n) {
        if (n == 0) return 1;
        //递归进行求解
        int abs = Math.abs(n);
        double pow = pow(x, abs);
        //   System.out.println(pow);
        return n > 0 ? pow : 1 / pow;
    }

    public double pow(double x, int n) {
        if (n == 0) return 1;
        if (n == 1) return x;
        int i = n & 1;
        if (i == 0) {
            double pow = pow(x, n / 2);

            return pow * pow;
        } else {
            double pow = pow(x, n / 2);
            return pow * pow * x;
        }
    }

}