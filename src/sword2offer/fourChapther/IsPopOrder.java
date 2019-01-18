package sword2offer.fourChapther;

import java.util.Stack;

public class IsPopOrder {
    public static void main(String[] args) {
        int[] a = {4};
        int[] b = {4};
        System.out.println(new IsPopOrder().isPopOrder(a, b));
    }

    public boolean isPopOrder(int[] pPush, int[] pPop) {
        if (pPop == null || pPush == null) throw new NullPointerException();
        if (pPop.length != pPush.length)
            return false;
        Stack<Integer> stack = new Stack<>();
        int index = 0, pi = 0;
        while (index != pPop.length) {
            int nowNumber = pPop[index];
            if (stack.isEmpty()||stack.peek() != nowNumber) {
                if (pi == pPush.length) return false;
                while (pPush[pi] != nowNumber) {
                    stack.push(pPush[pi++]);
                    if (pi == pPush.length) return false;
                }
                pi++;
            } else {
                stack.pop();
            }
            index++;
        }
        return true;
    }
}