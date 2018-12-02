package ll1grammaticalanalysis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SolvingLeftRecursion {

    //消除直接左递归
    public static void removeDirectLeftRecursion(Map<Character, List<String>> map, List<Character> characters) {

        Map<Character, List<String>> map1 = new HashMap<>();
        for (Character s : map.keySet()) {
            List<String> list = map.get(s);
            List<String> sInclude = new ArrayList<>();
            List<String> sNoInclude = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).charAt(0) == s) {
                    sInclude.add(list.get(i));
                } else sNoInclude.add(list.get(i));
            }
            if (sInclude.size() == list.size())
                throw new IllegalArgumentException("文法含有不可消除的左递归" + s + "->" + map.get(s));
            if (sNoInclude.size() == list.size()) continue;

            Character terminator = CharacterUtils.getNonTerminator(characters);

            for (int i = 0; i < sNoInclude.size(); i++) {
                sNoInclude.set(i, sNoInclude.get(i) + terminator);
            }
            for (int i = 0; i < sInclude.size(); i++) {
                sInclude.set(i, sInclude.get(i).substring(1) + terminator);
            }
            map.replace(s, sNoInclude);
            sInclude.add("ε");
            map1.put(terminator, sInclude);
        }
        map.putAll(map1);
//        System.out.println("*******************消除直接左递归的文法*********************");
//        for (Character s : map.keySet()) {
//            System.out.println(s + "\t" + map.get(s));
//        }
    }

    public static void removeIndirectLeftRecursion(Map<Character, List<String>> map, List<Character> characters, int[] characterCopy) {
        List<Character> charactersCopy = new ArrayList<>(characters);
        for (int i = 0; i < charactersCopy.size(); i++) {
            for (int j = 0; j <= i - 1; j++) {
                //pi->pj...都进行改写
                //获取当前i对应的list集合 也就是所有产生式
                List<String> list = map.get(charactersCopy.get(i));
                List<String> list2 = new ArrayList<>();
                int h = list.size();
                for (int i1 = 0; i1 < h; i1++) {
                    //如果产生式第一个字符和 当前非终结符相等
                    if (list.get(i1).charAt(0) == charactersCopy.get(j)) {
                        //获取当前非终结符对应的文法的产生式集合
                        List<String> list1 = map.get(charactersCopy.get(j));
                        //遍历操作

                        for (int i2 = 0; i2 < list1.size(); i2++) {
                            //      System.out.println(list.get(i1).substring(1));
                            list2.add(list1.get(i2) + list.get(i1).substring(1));
                        }
                        list.remove(i1);
                        if (--characterCopy[charactersCopy.get(j)] == 0) {
                            characters.remove(charactersCopy.get(j));
                            map.remove(charactersCopy.get(j));
                        }
                        h--;
                    }
                }
                list.addAll(list2);
            }
            //对pi进行直接左递归
            Map<Character, List<String>> map1 = new HashMap<>();
            map1.put(charactersCopy.get(i), map.get(charactersCopy.get(i)));
            removeDirectLeftRecursion(map1, characters);
        }
    }
}
