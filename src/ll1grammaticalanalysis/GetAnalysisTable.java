package ll1grammaticalanalysis;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class GetAnalysisTable {
    private static Map<Character, List<Character>> getFirst() {
        Map<Character, List<String>> map = LL1.getMap();
        List<Character> characters = LL1.getCharacters();
        Map<Character, List<Character>> reMap = new HashMap<>();
        List<Character> charactersCopy = new ArrayList<>(characters);
        for (int i = 0; i < charactersCopy.size(); i++) {
            List<Character> characterList = new ArrayList<>();
            rescure1(map, charactersCopy.get(i), characterList, charactersCopy);
            reMap.put(charactersCopy.get(i), characterList);
        }
        return reMap;
    }

    private static void rescure1(Map<Character, List<String>> map, Character character,
                                 List<Character> characterList, List<Character> characters) {
        List<String> list = map.get(character);
        for (int i1 = 0; i1 < list.size(); i1++) {
            char c = list.get(i1).charAt(0);
            if (!characters.contains(c)) characterList.add(c);
            else rescure1(map, c, characterList, characters);
        }
    }

    public static Map<Character, List<Character>>[] getFirstAndFollow() {
        Map<Character, List<String>> map = LL1.getMap();
        List<Character> characters = LL1.getCharacters();
        Map<Character, List<Character>> first = GetAnalysisTable.getFirst();

        Map<Character, List<Character>> reMap = new HashMap<>();
        List<Character> charactersCopy = new ArrayList<>(characters);
        for (int i = 0; i < charactersCopy.size(); i++) {
            List<Character> characterList = new ArrayList<>();
            if (!characterList.contains('#'))
                characterList.add('#');
            rescure2(charactersCopy.get(i), characterList, charactersCopy, first);
            reMap.put(charactersCopy.get(i), characterList);
        }
        Map<Character, List<Character>>[] sss = new Map[2];
        sss[0] = first;
        sss[1] = reMap;
        return sss;
    }

    public static void rescure2(Character character,
                                List<Character> characterList, List<Character> characters, Map<Character, List<Character>> first) {
        //遍历所有的 产生式
        Map<Character, List<String>> map = LL1.getMap();
        for (int i = 0; i < characters.size(); i++) {
            List<String> list = map.get(characters.get(i));
            //遍历第i个文法 的所有产生式
            for (String s : list) {
                //包含这个非终结符
                if (s.contains(character + "")) {
                    for (int i1 = 0; i1 < s.length(); ++i1) {
                        if (s.charAt(i1) == character && i1 < s.length()) {
                            if (i1 < s.length() - 1) {
                                if (!characters.contains(s.charAt(i1 + 1)) && !characterList.contains(s.charAt(i1 + 1))) {
                                    characterList.add(s.charAt(i1 + 1));
                                    break;
                                }
                                if (!characters.contains(s.charAt(i1 + 1))) break;

                                first.get(s.charAt(i1 + 1)).forEach(character1 -> {
                                    if (!characterList.contains(character1) && character1 != 'ε') {
                                        characterList.add(character1);
                                    }
                                });
                            }
                            boolean flag = true;
                            for (int i2 = i1 + 1; i2 < s.length(); ++i2) {
                                //     System.out.println(first);
                                if (!first.get(s.charAt(i2)).contains('ε') || !characters.contains(s.charAt(i2))) {
                                    flag = false;
                                    break;
                                }
                            }
                            if (!flag) break;
                            if (characters.get(i) != character)
                                rescure2(characters.get(i), characterList, characters, first);
                        }
                        //递归
                    }
                }
            }
        }
    }

    public static Map<String, String> getTable() {
        Map<String, String> reMap = new HashMap<>();
        Map<Character, List<String>> map = LL1.getMap();
        List<Character> characters = LL1.getCharacters();

        Map<Character, List<Character>>[] firstAndFollow = GetAnalysisTable.getFirstAndFollow();
        Map<Character, List<Character>> first = firstAndFollow[0];
        Map<Character, List<Character>> fallow = firstAndFollow[1];

        List<Character> shutCharacters = LL1.getShutCharacters();
        shutCharacters.add('#');
        for (int i = 0; i < characters.size(); i++) {
            List<String> list = map.get(characters.get(i));
            for (int i1 = 0; i1 < shutCharacters.size(); i1++) {
                for (int j = 0; j < list.size(); ++j) {
                    char c = list.get(j).charAt(0);
                    //为空的话判断其Follow
                    if (c == 'ε') {
                        if (fallow.get(characters.get(i)).contains(shutCharacters.get(i1))) {
                            reMap.put(characters.get(i) + "" + shutCharacters.get(i1), "" + 'ε');
                            break;
                        } else continue;
                    }
                    //如果首字符为对应终结符，直接将此产生式put，然后break
                    if (shutCharacters.contains(c)) {
                        if (c == shutCharacters.get(i1)) {
                            reMap.put(characters.get(i) + "" + shutCharacters.get(i1), list.get(j));
                            break;
                        }
                    }
                    //first包括，直接将产生式put进
                    else if (first.get(c).contains(shutCharacters.get(i1))) {
                        reMap.put(characters.get(i) + "" + shutCharacters.get(i1), list.get(j));
                        break;
                    }
                    //First包括空，我们判断其Follow是否包括
                    else if (first.get(c).contains('ε') &&
                            fallow.get(characters.get(i)).contains(shutCharacters.get(i1))) {
                        // System.out.println("123");
                        reMap.put(characters.get(i) + "" + shutCharacters.get(i1), "" + 'ε');
                        break;
                    }
                }
                //如果没有对应的产生式，让其为ERROR
                if (!reMap.containsKey(characters.get(i) + "" + shutCharacters.get(i1))) {
                    reMap.put(characters.get(i) + "" + shutCharacters.get(i1), "ERROR");
                }
            }
        }
        return reMap;
    }
}