package sword2offer.highqualitycode;

public class RegularExpreMatching {

    public boolean match(String str, String pattern) {
        if (str == null || pattern == null)
            return false;
        return matchCore(str, pattern, 0, 0);
    }

    private boolean matchCore(String str, String pattern, int strIndex, int patternIndex) {
        if (strIndex == str.length() && patternIndex == pattern.length()) return true;
        if (strIndex != str.length() && patternIndex == pattern.length()) return false;

        if (patternIndex + 1<pattern.length()&&pattern.charAt(patternIndex + 1) == '*') {
            if (str.length() > strIndex && (pattern.charAt(patternIndex) == str.charAt(strIndex)
                    || pattern.charAt(patternIndex) == '.')) {
                return matchCore(str, pattern, strIndex + 1, patternIndex + 2) ||
                        matchCore(str, pattern, strIndex + 1, patternIndex) ||
                        matchCore(str, pattern, strIndex, patternIndex + 2);
            } else return matchCore(str, pattern, strIndex, patternIndex + 2);
        }
        if (str.length() > strIndex && (pattern.charAt(patternIndex) == str.charAt(strIndex)
                || pattern.charAt(patternIndex) == '.'))
            return matchCore(str, pattern, strIndex + 1, patternIndex + 1);

        return false;
    }


}