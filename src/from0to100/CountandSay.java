package from0to100;

import java.util.HashMap;
import java.util.Map;

public class CountandSay {

    public String countAndSay1(int n) {
        //记录结果的
        StringBuilder curr = new StringBuilder("1");
        StringBuilder prev;
        int count;
        char say;
        for (int i = 1; i < n; i++) {
            //从1开始
            prev = curr;
            curr = new StringBuilder();
            //记录相同数字出现的次数
            count = 1;
            //获得第一个
            say = prev.charAt(0);

            for (int j = 1, len = prev.length(); j < len; j++) {
                if (prev.charAt(j) != say) {
                    curr.append(count).append(say);
                    //如果不相同 从新开始
                    count = 1;
                    say = prev.charAt(j);
                } else count++;
            }
            curr.append(count).append(say);
        }
        return curr.toString();
    }


    public String countAndSay(int n) {
//        //创建一个map 键是1-n，值为其对应的结果！
//        Map<Integer, String> map = new HashMap<>();
//        //先将1放进去  因为我们要根据1去推后面的
//        map.put(1, "1");
        StringBuilder sb1 = new StringBuilder();
        sb1.append("1");
        for (int i = 2; i <= n; i++) {
            //从2开始 一直到我们求解的n
            //获取到上一个结果字符串
            String s = sb1.toString();
            sb1 = new StringBuilder();
            //创建一个中间字符串，就是将连续且相同的保存到这个中间字符串，然后写入下面的sb中
            StringBuilder sb = new StringBuilder();
            //创建一个保存结果的字符串

            for (int j = 0; j < s.length(); j++) {
                //开始遍历这个字符串
                //如果j为最后一位  或者j和j+1不同，我们就将中间字符串sb写入sb1中
                if (j + 1 == s.length() || s.charAt(j) != s.charAt(j + 1)) {
                    //先将j对应的放入sb中
                    sb.append(s.charAt(j));
                    int h = sb.toString().length();
                    sb1.append(h + "" + sb.charAt(0));
                    //如果最后一个 直接break
                    if (j + 1 == s.length())
                        break;
                    //不是最后一个 我们还需要进行后续的判断  因此我们需要清空这个字符串，
                    sb.setLength(0);
                } else {
                    ///不是最后一个 而且和后面的相等 那么将这个放入sb中
                    sb.append(s.charAt(j));
                }
            }
            //         map.put(i, sb1.toString());
        }
        //     return map.get(n);
        return sb1.toString();
    }


}