package from300to383;

/**
 * 给定一个任意赎金票据字符串和另一个包含所有杂志字母的字符串，如果赎金票据可以从杂志中构建，则写一个函数将返回true; 否则，它将返回false。
 * <p>
 * 杂志字符串中的每个字母只能在赎金票据中使用一次。
 * <p>
 * 注意：
 * 您可以假设两个字符串仅包含小写字母。
 * <p>
 * canConstruct（“a”，“b”） - > false
 * canConstruct（“aa”，“ab”） - > false
 * canConstruct（“aa”，“aab”） - > true
 */
public class RansomNote {


    public static void main(String[] args) {
        System.out.println(new RansomNote().canConstruct("aa", "aab"));
    }

    public boolean canConstruct(String ransomNote, String magazine) {
        if (ransomNote.equals("")) return true;
        if (magazine.equals("")) return false;
        int[] array = new int[26];
        for (int i = 0; i < magazine.length(); i++) {
            char c = magazine.charAt(i);
            array[c - 'a']++;
        }
        for (int i = 0; i < ransomNote.length(); i++) {
            char c = ransomNote.charAt(i);
            if (array[c - 'a'] == 0) return false;
            else array[c - 'a']--;
        }
        return true;
    }
}