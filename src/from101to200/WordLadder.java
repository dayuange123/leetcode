package from101to200;

import java.util.*;

public class WordLadder {
    Map<String, Integer> map = new HashMap<>();

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) return 0;
        if (isSame(endWord, beginWord)) return 2;
        wordList.remove(beginWord);
        wordList.remove(endWord);
        LinkedList<String> list = new LinkedList<>();
        list.add(endWord);
        map.put(endWord, 1);
        while (!list.isEmpty()) {
            String s = list.removeFirst();
            for (int i = 0; i < wordList.size(); i++) {
                if (map.get(wordList.get(i)) == null && isSame(wordList.get(i), s)) {
                    map.put(wordList.get(i), map.get(s) + 1);
                    list.add(wordList.get(i));
                    if (isSame(wordList.get(i), beginWord))
                        return map.get(wordList.get(i)) + 1;
                }
            }
        }
        return 0;
    }

    public boolean isSame(String s1, String s2) {
        int index = 0;
        int start = 0;
        int end = s1.length() - 1;
        while (start <= end) {
            if (start == end) {
                if (s1.charAt(start) != s2.charAt(start))
                    index++;
                start++;
            } else {
                if (s1.charAt(start) != s2.charAt(start))
                    index++;
                if (s1.charAt(end) != s2.charAt(end))
                    index++;

                start++;
                end--;
            }
            if (index > 1) return false;
        }
        return index == 1;
    }

    public static void main(String[] args) {
        ArrayList<String> strings = new ArrayList<>(Arrays.asList("hot", "dot", "dog", "lot", "log", "cog"));
        new WordLadder().ladderLength("hit", "cog", strings);
    }
}
