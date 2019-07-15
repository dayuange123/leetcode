package from101to200;

import java.util.*;

/**
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 * <p>
 * Return all possible palindrome partitioning of s.
 * <p>
 * Example:
 * <p>
 * Input: "aab"
 * Output:
 * [
 * ["aa","b"],
 * ["a","a","b"]
 * ]
 * 将一个单词切分成回文。
 * 第一个想法是使用递归。
 *
 * 但是这个算法本身时间复杂度就是2^n，还在里面判断回文 有点不太合理
 * 可以改善，使用dp来判断是否问回文串
 * 代码如下，j代表起始，i代表终止位置。
 * 所以 i  j对应得结果 ：如果i和j相等，并且 i-j<=2的时候 是回文，如果大于2  就要判断 dp[j+1][i-1]是否是。
 * 因为j+1 i-1为上一行，所以我们已经判断过，有结果了。
 * 如果是i+1 j-1来表示肯定是不行的。除非遍历顺序发生变化
 *                 if(s.charAt(i) == s.charAt(j) && (i - j <= 2 || dp[j+1][i-1])) {
 *                     dp[j][i] = true;
 *                 }
 */
public class PalindromePartitioning {
    LinkedList<String> list = new LinkedList<>();
    List<List<String>> lists = new ArrayList<>();

    public List<List<String>> partition(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (isHuiWen(s.substring(0, i + 1))) {
                list.add(s.substring(0, i + 1));
                if (i + 1 == s.length()) {
                    lists.add(new ArrayList<>(list));

                }
                if (s.substring(i + 1).length() > 0)
                    partition(s.substring(i + 1));
                list.removeLast();
            }
        }
        return lists;
    }

    private boolean isHuiWen(String s) {
        if (s.length() == 0) return false;
        int mid = s.length() / 2 - 1;
        while (mid >= 0) {
            if (s.charAt(mid) != s.charAt(s.length() - mid - 1))
                return false;
            mid--;
        }
        return true;
    }

    public static void main(String[] args) {
        PalindromePartitioning palindromePartitioning = new PalindromePartitioning();
        System.out.println(palindromePartitioning.partition("a"));
    }
}
