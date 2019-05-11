package from0to100;

import java.util.ArrayList;
import java.util.List;

public class PermutationSequence {
    public String getPermutation(int n, int k) {
        int[] a = {1};
        int[] bo = new int[n];
        return get("", k, n, a, bo);

    }

    public String get(String string, int k, int n, int[] index, int[] bo) {
        if (string.length() == n && index[0] == k)
            return string;
        if (string.length() == n)
            index[0]++;
        for (int i = 1; i <= n; i++) {
            if (bo[i - 1] == 1)
                continue;
            bo[i - 1] = 1;
            String s = get(string + i, k, n, index, bo);
            bo[i - 1] = 0;
            if (s != null) return s;
        }
        return null;
    }

    public static void main(String[] args) {
        String permutation = new PermutationSequence().getPermutation(4, 9);
        System.out.println(permutation);
    }

    public String getPermutation1(int n, int k) {
        int pos = 0;
        List<Integer> numbers = new ArrayList<>();
        int[] factorial = new int[n + 1];
        StringBuilder sb = new StringBuilder();
        int sum = 1;
        factorial[0] = 1;
        for (int i = 1; i <= n; i++) {
            sum *= i;
            factorial[i] = sum;
        }
        for (int i = 1; i <= n; i++) {
            numbers.add(i);
        }
        k--;
        for (int i = 1; i <= n; i++) {
            int index = k / factorial[n - i];
            sb.append(String.valueOf(numbers.get(index)));
            numbers.remove(index);
            k -= index * factorial[n - i];
        }
        return String.valueOf(sb);
    }


    public String getPermutation3(int n, int k) {
        StringBuilder res = new StringBuilder();
        List<Integer> list = new ArrayList();
        for (int i = 1; i <= n; i++)
            list.add(i);
        int fac = Factorial(--n);
        k--;
        while (n > 0) {
            res.append(list.remove(k / fac));
            k = k % fac;
            fac /= (n--);
        }
        res.append(list.remove(0));
        return res.toString();
    }

    public int Factorial(int n) {
        int fac = 1;
        for (int i = 2; i <= n; i++) {
            fac *= i;
        }
        return fac;
    }


}