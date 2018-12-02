package cn.xupt.String;

import java.util.Stack;

public class LongestBracket {
    /**
     * 给定一个只包含字符的字符串，'('并')'找出最长有效（格式良好）的括号子字符串的长度。
     * 例1：
     * 输入： “（（）”
     * 输出： 2
     * 说明：最长的有效括号子字符串是"()"
     * 例2：
     * 输入： “ )()())”
     * 输出： 4
     * 说明：最长的有效括号子字符串是"()()"
     *
     * 如果 是(  索引入栈  如果是有括号 判断 栈是否为空  为空的话将有括号索引入栈   不为空 判断栈顶元素是否为 左括号  如果是 pop
     * 如果不是 则 该有括号索引入栈
     *
     * 最后 从栈中找出 两两之间相差最大的数字  注意 栈底的最后一个数字 应与0相比较
     *
     */
    public int longestValidParentheses(String s) {
        int length = s.length();
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < length; i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                if (!stack.empty()) {
                    if (s.charAt(stack.peek()) == '(') {
                        stack.pop();
                    } else stack.push(i);
                } else stack.push(i);
            }
        }
        if (stack.empty()) return length;
        int a = length;
        int b = 0;
        int max = 0;
        while (!stack.empty()) {
            b = stack.pop();
            max = Math.max(a - b - 1, max);
            a = b;
        }
        max = Math.max(max, a);
        return max;
    }

    public static void main(String[] args) {
        new LongestBracket().longestValidParentheses("())");
    }
}
