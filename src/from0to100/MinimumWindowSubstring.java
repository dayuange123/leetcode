package from0to100;

/**
 * Input: S = "ADOBECODEBANC", T = "ABC"
 * Output: "BANC"
 * 查找包括某个字符串的最小子串
 * 滑动窗口 记录开始位置和结束位置，然后相差为最小值。
 * 首先滑动右指针，知道包括整个子串，然后滑动左
 * 难点在于滑动条件的选择，我们要判断当前范围的子串包含我们目标串。
 * 这里通过两个数组进行条件的控制。
 */
public class MinimumWindowSubstring {


    public String minWindow(String s, String t) {
        int[] flag = new int[128];
        int[] flag2 = new int[128];
        int l = 0, r = 0;
        int lastLeft = 0, lastRight = 0;
        int minSize = Integer.MAX_VALUE;
        for (int i = 0; i < t.length(); i++) {
            flag[t.charAt(i)]++;
            flag2[t.charAt(i)]++;
        }
        int re = 0;
        int count = 0;
        for (int i = 0; i < flag.length; i++) {
            if (flag[i] > 0) re++;
            else {
                flag2[i] = s.length() + 1;
            }
        }
        while (r <= s.length()) {
            if (count == re) {
                if (r - l < minSize) {
                    minSize = r - l;
                    lastLeft = l;
                    lastRight = r;
                }
                if (flag2[s.charAt(l)] != (s.length() + 1) && ++flag[s.charAt(l)] == 1)
                    count--;
                l++;
            } else {
                if (r == s.length()) break;
                if (flag2[s.charAt(r)] != (s.length() + 1) && --flag[s.charAt(r)] == 0)
                    count++;
                r++;
            }
        }
        return s.substring(lastLeft, lastRight);
    }

    public static void main(String[] args) {
        System.out.println(new MinimumWindowSubstring().minWindow("ADOBECODEBANC", "ABC"));
    }
}



