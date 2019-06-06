package from0to100;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定仅包含数字的字符串，通过返回所有可能的有效IP地址组合来恢复它。
 * <p>
 * 例：
 * <p>
 * 输入： “25525511135”
 * 输出： ["255.255.11.135", "255.255.111.35"]
 * 每一段不能大于256
 * 如果当前是第n个点 剩余的数字不能大于 12-3*n, 不能小于 4-n
 */
public class RestoreIPAddresses {
    public List<String> restoreIpAddresses(String s) {
        ArrayList<String> strings = new ArrayList<>();
        if (s.length() > 12) {
            return strings;
        }
        rescue(s, 3, "", strings);
        return strings;
    }

    public void rescue(String s, int n, String res, List<String> list) {
        if (s.length() <= 0)
            return;
        if (n == 0) {
            Integer i = new Integer(s);
            //最后一段条件，大于=0小于 256 如果第一位位0 保证只有一位 否则不合法
            if (i >= 0 && i < 256 && (s.charAt(0) != '0' || s.length() == 1)) {
                res = res + s;
                list.add(res);
            }
            return;
        }
        //不符合后续的分配。直接return
        if (s.length() > 3 * (n + 1) || s.length() <= n)
            return;
        for (int i = 1; i <= 3; i++) {
            //如果是0开头。那么只能让0作为这一位。然后break。
            if (s.charAt(0) == '0') {
                rescue(s.substring(i), n - 1, res + s.substring(0, i) + ".", list);
                break;
            }
            //尝试取 后1 2 3最为该段
            if (s.length() > i) {
                Integer integer1 = new Integer(s.substring(0, i));
                if (integer1 < 256)
                    rescue(s.substring(i), n - 1, res + s.substring(0, i) + ".", list);
            }
        }
    }

    public static void main(String[] args) {
        List<String> list = new RestoreIPAddresses().restoreIpAddresses("010010");
        list.forEach(s -> {
            System.out.println(s);
        });
    }
}
