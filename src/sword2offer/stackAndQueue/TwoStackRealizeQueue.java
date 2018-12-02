package sword2offer.stackAndQueue;

import java.util.Stack;

public class TwoStackRealizeQueue<T> {

    private Stack<T> stack1;
    private Stack<T> stack2;

    public TwoStackRealizeQueue() {
        this.stack1 = new Stack<>();
        this.stack2 = new Stack<>();
    }

    public void appendTail(T element) {
        stack1.push(element);
    }

    public T deleteHead() {
        if (!stack2.isEmpty()) {
            return stack2.pop();
        } else {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
            if(!stack2.isEmpty())
            return stack2.pop();
        }
        throw new RuntimeException("队列为空");
    }

}