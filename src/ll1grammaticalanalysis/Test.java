package ll1grammaticalanalysis;

import java.util.List;
import java.util.Map;
import java.util.Stack;

public class Test {

    public static void main(String[] args) {
        //System.out.println((int) '+');
        //读取文件中的文法 并构造 存储的数据结构
        LL1.getGrammar();
        //解决左递归问题
        SolvingLeftRecursion.removeDirectLeftRecursion(LL1.getMap(), LL1.getCharacters());
        SolvingLeftRecursion.removeIndirectLeftRecursion(LL1.getMap(), LL1.getCharacters(), LL1.getCharactersSize());
        System.out.println("-----------------解决递归后的文法--------------");
        for (Character character : LL1.getMap().keySet()) {
            System.out.println(character + "\t" + LL1.getMap().get(character));
        }
        //提取左公因子
        LL1.extractCommonFactor();
        Map<Character, List<String>> map = LL1.getMap();

        System.out.println("-----------------最终文法结果--------------");
        for (Character character : map.keySet()) {
            System.out.println(character + "\t" + map.get(character));
        }
        Map<Character, List<Character>>[] firstAndFollow = GetAnalysisTable.getFirstAndFollow();
        Map<Character, List<Character>> first = firstAndFollow[0];
        Map<Character, List<Character>> fallow = firstAndFollow[1];
        System.out.println("------------first----------------");
        for (Character character : first.keySet()) {
            System.out.println(character + "\t" + first.get(character));
        }
        System.out.println("-------------follow-------------");
        for (Character character : fallow.keySet()) {
            System.out.println(character + "\t" + fallow.get(character));
        }
        //    System.out.println(LL1.getStartC());
        System.out.println(LL1.getShutCharacters());
        Map<String, String> table = GetAnalysisTable.getTable();
        System.out.println(table);

        System.out.println();
        if (TatalControll.totalControl("i+i*i+i")) {
            System.out.println("分析成功，句子语法正确");
        } else System.out.println("失败，语法错误!");

    }
}