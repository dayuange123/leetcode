package sword2offer.highqualitycode;

public class Print1ToN {
    public void Print1ToMaxOfNdigits1(int n) {
        if (n <= 0)
            return;
        char[] number = new char[n];
        for (int i = 0; i < n; ++i)
            number[i] = '0';
        while (!increment(number)) {
            print(number);
        }

    }

    private boolean increment(char[] number) {
        int changeNext = 0;
        int thisPosition;
        for (int i = number.length - 1; i >= 0; i--) {
            if (i == number.length - 1)
                changeNext = 1;
            thisPosition = number[i] - '0' + changeNext;

            if (thisPosition >= 10 && i == 0) return true;
            if (thisPosition >= 10) {
                thisPosition = thisPosition - 10;
                changeNext = 1;
                number[i] = (char) (thisPosition + '0');
            } else {
                number[i] = (char) (thisPosition + '0');
                return false;
            }
        }
        return false;
    }

    private void print(char number[]) {
        boolean flag = false;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < number.length; i++) {
            if (number[i] != '0' || flag) {
                sb.append(number[i]);
                flag = true;
            }
        }
        System.out.println(sb);
    }

    public void Print1ToMaxOfNdigits2(int n) {
        if (n <= 0)
            return;
        char[] number = new char[n];
        allArray(n, 0, number);
    }

    /**
     * @param length n的长度
     * @param index  当前索引
     */
    private void allArray(int length, int index, char[] number) {
        if (index == length) {
            print(number);
            return;
        }
        for (int i = 0; i < 10; i++) {
            number[index] = (char) ('0' + i);
            allArray(length, index + 1, number);
        }
    }

    public static void main(String[] args) {
        new Print1ToN().Print1ToMaxOfNdigits2(4);

    }
}