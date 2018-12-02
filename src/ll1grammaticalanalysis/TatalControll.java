package ll1grammaticalanalysis;

import java.util.Map;
import java.util.Stack;

public class TatalControll {
    public static boolean totalControl(String inputString) {
        if (inputString.charAt(inputString.length() - 1) != '#') {
            inputString += "#";
        }
        Map<String, String> table = GetAnalysisTable.getTable();
        if (inputString == null) throw new NullPointerException("inputString=空输入");
        Stack<Character> stack = new Stack<>();
        stack.push('#');
        stack.push(LL1.getStartC());
        char a;
        boolean flag = true;
        int index = 0;
        //读取串的字符
        a = inputString.charAt(index++);
        while (flag) {
            //   if (index == inputString.length()) return false;
            //读取栈顶元素，并移出
            Character cX = stack.pop();
            String s = table.get(cX + "" + a);
            //cX为非终结符，直接和a或者#比较
            if (s == null) {
                if (cX == '#') {
                    if (cX == a) return true;
                    else return false;
                } else if (cX == a) {
                    a = inputString.charAt(index++);
                }
            }
            //如果不是ERROR
            else if (!s.equals("ERROR")) {
                //为空。
                if (s.equals('ε' + "")) System.out.println("所用产生式" + cX + "->" + s);
                else if (s.equals(a + "")) {
                    a = inputString.charAt(index++);
                    System.out.println("所用产生式" + cX + "->" + s);
                } else {
                    System.out.println("所用产生式" + cX + "->" + s);
                    for (int j = s.length() - 1; j >= 0; --j) {
                        stack.push(s.charAt(j));
                    }
                }
            } else {
                return false;
            }
        }
        return true;
    }
}