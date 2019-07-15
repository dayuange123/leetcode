package from101to200;

import com.sun.scenario.effect.impl.sw.sse.SSEColorAdjustPeer;

import java.util.*;
import java.util.stream.Collectors;

public class WordLadderII {
    int min = Integer.MAX_VALUE;
    //存储每个单词当前最短路径集合
    Map<String, List<List<String>>> map = new HashMap<>();
    List<List<String>> lists = new ArrayList<>();
    List<String> wordList;

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) return 0;
        this.wordList = wordList;
        int[] flag = new int[wordList.size()];
        LinkedList<String> linkedList = new LinkedList<>();
        rescure(beginWord, endWord, flag, linkedList);

        return min==Integer.MAX_VALUE?0:min;

    }

    private void rescure(String begin, String endword, int[] flag, LinkedList<String> list) {
        list.add(begin);
        if (isSame(endword, begin) && list.size() < min) {
            list.add(endword);
            LinkedList<String> strings = new LinkedList<>(list);
            if (strings.size() < min) {
                lists.clear();
                min = list.size();
            }
            lists.add(strings);
            list.removeLast();
            list.removeLast();
            return;
        } else if (min != Integer.MAX_VALUE && list.size() >= min) {
            list.removeLast();
            return;
        }

        for (int i = 0; i < wordList.size(); i++) {
            if (flag[i] == 0 && isSame(begin, wordList.get(i))) {
                flag[i] = 1;
                int size = lists.size();
                int minx = min;
                List<List<String>> listss = map.get(wordList.get(i));
                if (listss == null) {
                    rescure(wordList.get(i), endword, flag, list);
                    listss = new ArrayList<>();
                    if (min != minx) {
                        for (int j = lists.size() - 1; j >= 0; j--) {
                            List<String> list1 = lists.get(j);
                            listss.add(new ArrayList<>(list1.subList(list.size(), list1.size())));
                        }
                        map.put(wordList.get(i), listss);
                    } else {
                        if (size < lists.size()) {
                            listss = new ArrayList<>();
                            for (int j = lists.size() - 1; j > size - 1; j--) {
                                List<String> list1 = lists.get(j);
                                listss.add(new ArrayList<>(list1.subList(list.size(), list1.size())));
                            }
                            map.put(wordList.get(i), listss);
                        }
                    }
                } else {
                    for (List<String> strings : listss) {
                        LinkedList<String> linkedList = new LinkedList<>();
                        linkedList.addAll(list);
                        linkedList.addAll(strings);
                        if (linkedList.size() < min) {
                            lists.clear();
                            lists.add(linkedList);
                            min = linkedList.size();
                        } else if (linkedList.size() == min) {
                            lists.add(linkedList);
                        }

                    }
                }
                flag[i] = 0;
            }
        }
        list.removeLast();
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
        }
        return index == 1;
    }


}
