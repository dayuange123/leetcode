package from0to100;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupAnagrams {

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<Integer, List<String>> map = new HashMap<>();
        List<List<String>> lists = new ArrayList<>();
        for (String str : strs) {
            int hash;
            int add = 0, mutily = 1;
            for (int i = 0; i < str.length(); i++) {
                add += str.charAt(i);
                mutily *= str.charAt(i);
            }
            hash = add + mutily;
            if (map.containsKey(hash)) {
                map.get(hash).add(str);
            } else {
                ArrayList<String> strings = new ArrayList<>();
                strings.add(str);
                lists.add(strings);
                map.put(hash, strings);
            }
        }
        return lists;
    }
}