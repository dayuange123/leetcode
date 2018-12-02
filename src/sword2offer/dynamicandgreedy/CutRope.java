package sword2offer.dynamicandgreedy;

public class CutRope {

    public int maxCutRope(int length) {
        if (length < 0) throw new RuntimeException("length<0:" + length);
        int[] product = new int[length + 1];
        product[0] = 0;
        product[1] = 0;
        product[2] = 1;
        product[3] = 2;
        if (length <= 3) return product[length];
        int max;
        for (int i = 4; i <= length; i++) {
            max = 0;
            for (int j = 1; j <= i / 2; j++) {
                max = max > product[j] * product[i - j] ? max : product[j] * product[i - j];
            }
            product[i] = max;
        }
        return product[length];
    }
}