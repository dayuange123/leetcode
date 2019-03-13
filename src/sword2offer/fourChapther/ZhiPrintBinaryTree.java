package sword2offer.fourChapther;

import sword2offer.tree.BinaryTreeNode;

import java.util.Stack;

public class ZhiPrintBinaryTree {
    public void Print(BinaryTreeNode node) {
        Stack<BinaryTreeNode>[] stacks = new Stack[2];
        int count = 1;
        int current = 0;
        stacks[0].push(node);
        while (!stacks[current].isEmpty() || (!stacks[current + 1].isEmpty())) {
            BinaryTreeNode pop = stacks[current].pop();
            System.out.println(pop.value);

            if (current == 0) {
                //下一个为偶数层
                if (node.pLeft != null)
                    stacks[count].push(node.pLeft);
                if (node.pRight != null)
                    stacks[count].push(node.pRight);

            } else {
                //为奇数层
                if (node.pRight != null)
                    stacks[count].push(node.pRight);
                if (node.pLeft != null)
                    stacks[count].push(node.pLeft);
            }
            if (stacks[current].isEmpty()) {
                System.out.println();
                count = 1 - count;
                current = 1 - current;
            }
        }
    }
}