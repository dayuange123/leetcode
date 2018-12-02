package sword2offer.linkedlist;

import java.util.Stack;

public class PrintEnd2Start {
    class Node {
        String value;
        Node next;
    }

    public void StackPrint(Node node) {
        Stack<String> stack = new Stack<>();
        while (node != null) {
            stack.push(node.value);
            node = node.next;
        }
        while (!stack.empty()) {
            System.out.println(stack.pop());
        }

    }

    public void RecusionPrint(Node node) {
        if (node == null) return;
        RecusionPrint(node.next);
        System.out.println(node.value);
    }
}