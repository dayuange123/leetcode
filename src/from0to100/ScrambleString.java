package from0to100;

/**
 * 给定一个字符串 s1，我们可以把它递归地分割成两个非空子字符串，从而将其表示为二叉树。
 *
 * 下图是字符串 s1 = "great" 的一种可能的表示形式。
 *
 *     great
 *    /    \
 *   gr    eat
 *  / \    /  \
 * g   r  e   at
 *            / \
 *           a   t
 * 在扰乱这个字符串的过程中，我们可以挑选任何一个非叶节点，然后交换它的两个子节点。
 *
 * 例如，如果我们挑选非叶节点 "gr" ，交换它的两个子节点，将会产生扰乱字符串 "rgeat" 。
 *
 *     rgeat
 *    /    \
 *   rg    eat
 *  / \    /  \
 * r   g  e   at
 *            / \
 *           a   t
 * 我们将 "rgeat” 称作 "great" 的一个扰乱字符串。
 *
 * 同样地，如果我们继续将其节点 "eat" 和 "at" 进行交换，将会产生另一个新的扰乱字符串 "rgtae" 。
 *
 *     rgtae
 *    /    \
 *   rg    tae
 *  / \    /  \
 * r   g  ta  e
 *        / \
 *       t   a
 * 我们将 "rgtae” 称作 "great" 的一个扰乱字符串。
 *
 * 给出两个长度相等的字符串 s1 和 s2，判断 s2 是否是 s1 的扰乱字符串。
 *
 * 示例 1:
 *
 *
 * 讲道理，这道题本来是不会的，没有头绪。
 * 然后看了一个递归的解法。
 *  扰乱的概念在于我们可以将其转换回来。通过反转
 *  所以思想其实就是，如果s1 s2的前i个是扰乱，并且后i个也是。说明他们都是。
 *  或者 如果s1的前i个和s2的后i个扰乱 并且s1的后length-i个和s2的前length-i个扰乱，也成立。
 *  所以通过递归很容易解答。
 *  其实如此递归我们最终会判断每个拆分的字母。要么相邻 要门相等。但这里的相邻不是在字符串相邻。而是复杂的情况。
 *  如果我们能将问题的产生进行反向转化。是很容易解决问题的。
 */
public class ScrambleString {
    public boolean isScramble(String s1, String s2) {
        if( s1.equals(s2) )
            return true;
        int s1Array[] = new int[26];
        int s2Array[] = new int[26];
        for(int i = 0; i < s1.length(); i++) {
            s1Array[s1.charAt(i) - 'a']++;
            s2Array[s2.charAt(i) - 'a']++;
        }
        for(int i = 0; i < 26; i++)
            if( s1Array[i] != s2Array[i] )
                return false;
        for(int i = 1; i < s1.length(); i++) {
            if( isScramble(s1.substring(0, i), s2.substring(0, i))
                    && isScramble(s1.substring(i), s2.substring(i)) )
                return true;
            if( isScramble(s1.substring(0, i), s2.substring(s1.length() - i))
                    && isScramble(s1.substring(i), s2.substring(0, s1.length() - i)))
                return true;
        }
        return false;
    }
}
