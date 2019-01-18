package sword2offer.highqualitycode;

import java.util.ArrayDeque;
import java.util.Queue;

public class PrintFromTopToBottom {

    public void printFromTopToBottom(BinaryTreeNode pRoot) {
        if (pRoot == null) return;
        Queue<BinaryTreeNode> queue = new ArrayDeque<>();
        queue.add(pRoot);
        while (!queue.isEmpty()) {
            BinaryTreeNode remove = queue.remove();
            System.out.println(remove.mValue);
            if (remove.pLeft != null)
                queue.add(remove.pLeft);
            if (remove.pRight != null)
                queue.add(remove.pRight);
        }
    }

    public void printlnFromTopToBottom(BinaryTreeNode pRoot) {
        if (pRoot == null) return;
        Queue<BinaryTreeNode> queue = new ArrayDeque<>();
        int toBeLine=1,nextLine=0;

        queue.add(pRoot);
        while (!queue.isEmpty()) {
            BinaryTreeNode remove = queue.remove();
            System.out.print(remove.mValue);
            if (remove.pLeft != null){
                queue.add(remove.pLeft);
                nextLine++;
            }
            if (remove.pRight != null){
                queue.add(remove.pRight);
                nextLine++;
            }
            if(--toBeLine==0){
                System.out.println();
                toBeLine=nextLine;
                nextLine=0;
            }
        }
    }

}