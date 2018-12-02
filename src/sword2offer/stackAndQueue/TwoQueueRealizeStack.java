package sword2offer.stackAndQueue;

import java.util.ArrayDeque;
import java.util.Queue;

public class TwoQueueRealizeStack<T> {

    private Queue<T> queue1;
    private Queue<T> queue2;

    public TwoQueueRealizeStack() {
        queue1 = new ArrayDeque<>();
        queue2 = new ArrayDeque<>();
    }

    public T deleteHead() {
        if (queue2.isEmpty() && queue1.isEmpty())
            throw new RuntimeException("栈为空");
        if (queue1.isEmpty()) {
            while (queue2.size() > 1) {
                queue1.add(queue2.remove());
            }
            return queue2.remove();
        } else {
            while (queue1.size() > 1) {
                queue2.add(queue1.remove());
            }
            return queue1.remove();
        }
    }

    public void appendTail(T element) {
        if (queue1.isEmpty()) {
            queue2.add(element);
        } else {
            queue1.add(element);
        }

    }

    public static void main(String[] args) {
        TwoQueueRealizeStack<Integer> stack = new TwoQueueRealizeStack<>();
        stack.appendTail(1);
        stack.appendTail(2);
        stack.appendTail(3);

        System.out.println(stack.deleteHead());
        System.out.println(stack.deleteHead());
        System.out.println(stack.deleteHead());
    }
}