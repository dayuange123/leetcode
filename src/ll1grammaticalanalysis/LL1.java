package ll1grammaticalanalysis;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class LL1 {
    //存储文法
    private static Map<Character, List<String>> map = new HashMap<>();
    //存储所有非终结符
    private static List<Character> characters = new ArrayList<>();
    //存储所有终结符
    private static List<Character> shutCharacters = new ArrayList<>();
    //存储非终结符被产生式引用的次数
    private static int[] charactersSize = new int[200];
    //文法开始符号
    private static char startC;

    public static char getStartC() {
        return startC;
    }

    public static Map<Character, List<String>> getMap() {
        return map;
    }

    public static List<Character> getCharacters() {
        return characters;
    }

    public static int[] getCharactersSize() {
        return charactersSize;
    }

    public static List<Character> getShutCharacters() {
        return shutCharacters;
    }

    public static void getGrammar() {
        InputStream ot = null;
        String sb = "";
        try {
            ot = new FileInputStream("src/ll1grammaticalanalysis/Garmmer");
            int i;
            byte[] byte1 = new byte[1024];
            while ((i = ot.read(byte1)) != -1) {
                sb += new String(byte1);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                ot.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        int i = 0, k = 0;
        List<String> list = new ArrayList<>();
        list.add("");
        while (i < sb.length()) {
            list.set(k, list.get(k) + sb.charAt(i));
            if (sb.charAt(i) == '\n') {
                k++;
                list.add("");
            }
            i++;
        }
        System.out.println("*******************从文件得到的原文法*********************");
        for (int j = 0; j < list.size(); j++) {
            String s = list.get(j);
            for (int h = 0; h < s.length(); h++) {
                if (s.charAt(h) == '-' && s.charAt(h + 1) == '>') {
                    char m = s.substring(0, h).trim().charAt(0);
                    String[] substring = s.substring(h + 2).trim().split("\\|");
                    int z = 0;
                    for (String s1 : substring) {
                        substring[z++] = s1.trim();
                        for (int i1 = 0; i1 < s1.trim().length(); ++i1) {
                            if (s1.trim().charAt(i1) >= 'A' && s1.trim().charAt(i1) <= 'Z') {
                                //维护非终结符出现次数
                                charactersSize[s1.trim().charAt(i1)]++;
                            } else {
                                shutCharacters.add(s1.trim().charAt(i1));
                            }
                        }
                    }
                    System.out.println(m + "\t" + new ArrayList<>(Arrays.asList(substring)));
                    map.put(m, new ArrayList<>(Arrays.asList(substring)));
                    characters.add(m);
                }
            }
        }
        startC = characters.get(0);
    }

    //提取左公因子
    public static void extractCommonFactor() {
        //将所有文法复制到新的map进行操作，。
        Map<Character, List<String>> surplus = new HashMap<>(map);
        int size = surplus.size(), modCount = 0;
        List<String>[] commonList = new List[256];

        while (modCount < size) {
            char mapIndex = (char) surplus.keySet().toArray()[0];
            List<String> list = surplus.get(mapIndex);
            //将相同的划分在一个数组索引对应的集合中
            List<String> newList = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).length() > 0) {

                    int mm = list.get(i).charAt(0);
                    if (mm == 949) {
                        newList.add("ε");
                        continue;
                    }
                    //    System.out.println(list.get(i));

                    if (commonList[mm] == null)
                        commonList[mm] = new ArrayList<>();
                    commonList[mm].add(list.get(i));
                } else newList.add("ε");
            }
            //更新map的值

            for (int i = 0; i < 256; i++) {
                if (commonList[i] == null || commonList[i].size() == 0) {
                } else if (commonList[i].size() == 1) {
                    newList.add(commonList[i].get(0));
                } else {

                    int minLength = commonList[i].get(0).length();
                    for (int i1 = 1; i1 < commonList[i].size(); i1++) {
                        minLength = minLength < commonList[i].get(i1).length() ?
                                minLength : commonList[i].get(i1).length();
                    }
                    int maxCommon = 0;
                    boolean f = false;
                    for (int i1 = 0; i1 < minLength; i1++) {
                        char ca = commonList[i].get(0).charAt(i1);
                        for (String s : commonList[i]) {
                            if (s.charAt(i1) != ca) {
                                f = true;
                                break;
                            }
                            maxCommon = i1;
                        }
                    }
                    if (!f) maxCommon = minLength;
                    //存在第一个字母为公因子
                    //提取公因子并 增加非终结符和文法产生式
                    Character terminator = CharacterUtils.getNonTerminator(characters);
                    //需要修改
                    newList.add(commonList[i].get(0).substring(0, maxCommon) + "" + terminator);
                    //将此非终结符代表的文法加入到未判断文法中。
                    List<String> newList1 = new ArrayList<>();
                    //      System.out.println((int) mapIndex);
                    for (int i1 = 0; i1 < commonList[i].size(); i1++) {
                        //去掉原公共产生是的第n个字符  n为最长公共前缀
                        newList1.add(i1, commonList[i].get(i1).substring(maxCommon));
                    }
                    //加入新产生的文法
                    surplus.put(terminator, newList1);
                    //新产生的文法 也加入到map中 尽管可能后面需要更新,
                    map.put(terminator, newList1);
                    size++;
                }
                //清理数组 为下次循环使用。
                if (commonList[i] != null)
                    commonList[i].clear();
            }
            //更新map，可能不变，因为 可能不存在左公因子
            map.put(mapIndex, newList);
            //移出判断过的文法
            surplus.remove(mapIndex);
            modCount++;
        }
        System.out.println("--------------------提取左公因式后的文法-----------------");
        for (Character s : map.keySet()) {
            System.out.println(s + "\t" + map.get(s));
        }
    }

}