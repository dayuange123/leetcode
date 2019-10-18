package from101to200;

import java.util.Stack;

/**
 * 后缀表达式
 *
 * @author liuzhiyuan
 * @create 2019-10-17 11:15
 */
public class EvaluateReversePolishNotation {

    public static void main(String[] args) {
        String[] tokens = {"2", "1", "+", "3", "*"};
        System.out.println(new EvaluateReversePolishNotation().evalRPN(tokens));
    }

    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < tokens.length; i++) {
            if (isCharacter(tokens[i])) {
                switch (tokens[i]) {
                    case "+": {
                        Integer pop = stack.pop();
                        Integer pop1 = stack.pop();
                        stack.push(pop1 + pop);
                        break;
                    }

                    case "-": {
                        Integer pop = stack.pop();
                        Integer pop1 = stack.pop();
                        stack.push(pop1 - pop);
                        break;
                    }
                    case "*": {
                        Integer pop = stack.pop();
                        Integer pop1 = stack.pop();
                        stack.push(pop1 * pop);
                        break;
                    }
                    case "/": {
                        Integer pop = stack.pop();
                        Integer pop1 = stack.pop();
                        stack.push(pop1 / pop);
                        break;
                    }
                }
            } else {
                stack.push(new Integer(tokens[i]));
            }
        }
        return stack.pop();
    }

    public boolean isCharacter(String s) {
        if (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/")) {
            return true;
        }
        return false;
    }
}
