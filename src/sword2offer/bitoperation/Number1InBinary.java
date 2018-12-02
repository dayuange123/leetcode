package sword2offer.bitoperation;

public class Number1InBinary {
    public int _1numberOf1(int n) {

        int count = 0;
        while (n != 0) {
            if ((n & 1) == 1) {
                count++;
            }
            n = n >> 2;
        }
        return count;
    }

    public int _2numberOf1(int n) {

        int count = 0;
        int h = 1;
        while (n != 0) {
            if ((n & h) == h) {
                count++;
            }
            h = h << 1;
        }
        return count;
    }

    public int _3numberOf1(int n) {

        int count = 0;

        while (n != 0) {
            n = n & (n - 1);
            count++;
        }
        return count;
    }

    public static void main(String[] args) {

    }
}
