package from101to200;

/**
 * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
 * <p>
 * 说明：本题中，我们将空字符串定义为有效的回文串。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "A man, a plan, a canal: Panama"
 * 输出: true
 * 示例 2:
 * <p>
 * 输入: "race a car"
 * 输出: false
 * <p>
 * 这道题目。两个指针，
 */
public class Verifypalindromestrings125 {

    public boolean isPalindrome(String s) {
        int start = 0;
        int end = s.length() - 1;
        while (start < end) {
            char ss = s.charAt(start);
            char ee = s.charAt(end);
            if (!Character.isLetterOrDigit(ss)) {
                start++;
                continue;
            }
            if (!Character.isLetterOrDigit(ee)) {
                end--;
                continue;
            }
            if (Character.isDigit(ss) || Character.isDigit(ee)) {
                if (ss != ee)
                    return false;
            } else {
                if (Character.toLowerCase(ss) != Character.toLowerCase(ee))
                    return false;
            }
            start++;
            end--;
        }
        return true;
    }


    public static void main(String[] args) {
        new Verifypalindromestrings125().isPalindrome("A man, a plan, a canal: Panama");
    }
}