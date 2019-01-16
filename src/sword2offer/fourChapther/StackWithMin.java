package sword2offer.fourChapther;

import java.util.Stack;

public class StackWithMin<T extends Comparable<T>> {
    Stack<T> minStack = new Stack<>();
    Stack<T> dataStack = new Stack<>();

    public void push(T value) {
        dataStack.push(value);
        if (minStack.isEmpty() || minStack.peek().compareTo(value) > 0) {
            minStack.push(value);
        } else {
            minStack.push(minStack.peek());
        }
    }

    public void pop() {
        if (!minStack.isEmpty()) {
            minStack.pop();
            dataStack.pop();
        }
    }

    public T min() {
        if (minStack.isEmpty())
            return null;
        return minStack.peek();
    }
}