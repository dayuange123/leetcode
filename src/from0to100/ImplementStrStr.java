package from0to100;

public class ImplementStrStr {

    public int strStr(String haystack, String needle) {
        if (needle.length() > haystack.length()) return -1;
        if (needle.length() == 0) return 0;
        int start = 0;
        int index;
        for (int i = 0; i < haystack.length(); i++) {
            if (needle.charAt(start) == haystack.charAt(i)) {
                index = i;
                while (needle.charAt(start) == haystack.charAt(i)) {
                    if (start == needle.length() - 1) return index;
                    start++;
                    if (++i >= haystack.length()) return -1;

                }
                i = index;
                start = 0;
            }
        }
        return -1;
    }
}