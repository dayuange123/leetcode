package from101to200;

import java.util.Map;

/**
 * Input: S = "rabbbit", T = "rabbit"
 * Output: 3
 * Explanation:
 * <p>
 * As shown below, there are 3 ways you can generate "rabbit" from S.
 * (The caret symbol ^ means the chosen letters)
 * <p>
 * rabbbit
 * ^^^^ ^^
 * rabbbit
 * ^^ ^^^^
 * rabbbit
 * ^^^ ^^^
 * 查找一个字符串有多少个不同的子序列
 * 动态规划
 * <p>
 * 使用一个二维数组。
 * 横坐标代表s的下标 ，纵坐标代表t的下标。
 */
public class DistinctSubsequences {


    public int numDistinct(String s, String t) {

        if (s.length() < t.length()) return 0;
        if (s.equals(t) || t.length() == 0) return 1;

        int[][] res = new int[t.length()][s.length()];
        if (s.charAt(s.length() - 1) == t.charAt(t.length() - 1))
            res[t.length() - 1][s.length() - 1] = 1;
        for (int i = t.length() - 1; i >= 0; --i) {
            for (int j = s.length() - 2; j >= 0; --j) {
                if (s.charAt(j) == t.charAt(i)) {
                    if (i == t.length() - 1)
                        res[i][j] = res[i][j + 1] + 1;
                    else res[i][j] = res[i + 1][j + 1] + res[i][j + 1];
                } else res[i][j] = res[i][j + 1];
            }
        }
        return res[0][0];
    }

    public static void main(String[] args) {
        new DistinctSubsequences().numDistinct("rabbbit", "rabbit");
    }
}