package from101to200;

import java.util.ArrayList;
import java.util.List;

/**
 * 139
 *
 * @author liuzhiyuan
 * @create 2019-10-14 11:36
 */
public class WordBreak {

    //使用递归超时
    //应该使用dp

    public boolean wordBreak(String s, List<String> wordDict) {
        //return rescure(s, wordDict, 0);
        //从s的第一个字符出发，判断wordDict是否有合适的。

        int[] isTrue = new int[s.length()];
        for (int i = 0; i < s.length(); ++i) {
            if(wordDict.contains(s.substring(0,i+1))){
                isTrue[i] = 1;
            }else {
                for(int j=i-1;j>=0;j--){
                    if(isTrue[j]==1){
                        if(wordDict.contains(s.substring(j+1,i+1))){
                            isTrue[i]=1;
                            break;
                        }
                    }
                }
            }
        }
        return  isTrue[s.length()-1]==1;
    }

    public boolean rescure(String s, List<String> wordDict, int length) {
        if (length == s.length()) {
            return true;

        }
        for (int i = 0; i < wordDict.size(); ++i) {
            if (wordDict.get(i).length() + length <= s.length()) {
                String substring = s.substring(length, length + wordDict.get(i).length());
                if (substring.equals(wordDict.get(i))) {
                    if (rescure(s, wordDict, length + wordDict.get(i).length())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        List<String> strings = new ArrayList<>();
        strings.add("leet");
        strings.add("code");

        System.out.println(new WordBreak().wordBreak("leetcode", strings));
    }
}
