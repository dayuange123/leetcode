package from0to100;

import java.util.ArrayList;
import java.util.List;

public class MultiplyStrings {


    public String multiply(String num1, String num2) {

        int m1 = num1.length();
        int m2 = num2.length();
        int[] res = new int[m1 + m2];

        for (int i = m1 - 1; i >= 0; i--) {
            for (int j = m2 - 1; j >= 0; j--) {
                int mid = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                int sum = mid + res[i + j + 1];
                res[i + j + 1] = sum % 10;
                res[i + j] += sum / 10;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int p : res)
            if (!(sb.length() == 0 && p == 0))
                sb.append(p);
        return sb.length() == 0 ? "0" : sb.toString();
    }


}