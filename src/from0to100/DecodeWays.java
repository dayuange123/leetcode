package from0to100;


/**
 * A-Z使用以下映射将包含来自其中的字母的消息编码为数字：
 * <p>
 * 'A' - >
 * 1'B' - > 2
 * ......
 * 'Z' - > 26
 * 给定仅包含数字的非空字符串，确定解码它的方法总数。
 * 例1：
 * <p>
 * 输入： “12”
 * 输出： 2
 * 说明：  可以解码为“AB”（1 2）或“L”（12）。
 * 例2：
 * 输入： “226”
 * 输出： 3
 * 说明：  可解码为“BZ”（2 26），“VF”（22 6）或“BBF”（2 2 6）。
 * <p>
 * 动态规划
 * 前面的  可以由后两个的结果获取
 * 但是需要注意0的坑。贼难受
 *
 * 如果当前位为0 那么不用说，肯定结果为0
 * 如果当前和
 */
public class DecodeWays {
    public static void main(String[] args) {
        System.out.println(new DecodeWays().numDecodings("101"));
    }
    public int numDecodings(String s) {
        if ((s.length() == 1 && s.charAt(0) == '0') || s.length() == 0 || s.charAt(0) == '0') return 0;
        if (s.length() == 1) return 1;
        int[] res = new int[s.length() + 1];
        res[res.length - 1] = 1;
        if (s.charAt(s.length() - 1) == '0')
            res[s.length() - 1] = 0;
        else res[s.length() - 1] = 1;
        for (int i = s.length() - 2; i >= 0; i--) {
            int value = (s.charAt(i) - '0') * 10 + s.charAt(i + 1) - '0';
            if (s.charAt(i) == '0') res[i] = 0;
            else if (value <= 26) {
                if (s.charAt(i + 1) == '0')
                    res[i] = res[i + 2];
                else
                    res[i] = res[i + 1] + res[i + 2];
            } else res[i] = res[i + 1];
        }
        return res[0];
    }
}