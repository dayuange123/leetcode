package from0to100;

import java.util.HashMap;
import java.util.Map;

public class InterleavingString {
    Map<String, String> map = new HashMap<>();

    public boolean isInterleave(String s1, String s2, String s3) {
        String s = map.get(s1);
        if (s != null && s.equals(s2)) {
            return false;
        }
        if (s1.length() + s2.length() != s3.length()) {
            map.put(s1, s2);
            return false;
        }
        if (s1.length() == 0) {
            if (s2.equals(s3)) return true;
            map.put(s1, s2);
            return false;
        }

        if (s2.length() == 0) {
            if (s1.equals(s3)) return true;
            map.put(s1, s2);
            return false;
        }
        int s1Index = 0;
        int s2Index = 0;
        for (int i = 0; i < s3.length(); i++) {
            if (s1Index < s1.length() && s2Index < s2.length() && s1.charAt(s1Index) == s2.charAt(s2Index)) {
                //判断和第三个是否相等。
                if (s1.charAt(s1Index) == s3.charAt(i)) {
                    boolean res = isInterleave(s1.substring(s1Index + 1), s2.substring(s2Index), s3.substring(i + 1)) || isInterleave(s1.substring(s1Index), s2.substring(s2Index + 1), s3.substring(i + 1));
                    if (res) return res;
                }
                map.put(s1, s2);
                return false;
            }
            if (s1Index < s1.length() && s1.charAt(s1Index) == s3.charAt(i)) {
                s1Index++;
            } else if (s2Index < s2.length() && s2.charAt(s2Index) == s3.charAt(i)) {
                s2Index++;
            } else {
                map.put(s1, s2);
                return false;
            }
        }
        return true;
    }
}
