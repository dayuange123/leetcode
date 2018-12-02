package sword2offer.highqualitycode;

public class PowAction {
    public double power1(double base, int exponent) {
        double result = 1.0;
        for (int i = 0; i < exponent; ++i) {
            result *= base;
        }
        return result;
    }

    public double power2(double base, int exponent) {
        double result = 1.0;
        boolean flag = false;
        if (exponent < 0) {
            //防止0当作除数
            if (base == 0) throw new RuntimeException("by /zero");
            flag = true;
            exponent = -exponent;
        }
        for (int i = 0; i < exponent; ++i) {
            result *= base;
        }
        if (flag) return 1.0 / result;
        return result;
    }

    public double power3(double base, int exponent) {
        if (exponent == 0)
            return 1;
        if (exponent == 1) return base;
        double result = power3(base, exponent >> 1);
        result *= result;
        if ((exponent & 1) == 1) {
            result *= base;
        }
        return result;
    }


}