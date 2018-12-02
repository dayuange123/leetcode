package sword2offer.tree;

import java.util.ArrayDeque;
import java.util.Queue;

public class MiddleOrderNextNode {

    public BinaryTreeNode getNextNode(BinaryTreeNode node, int value) {
        Queue<BinaryTreeNode> queue = new ArrayDeque<>();
        BinaryTreeNode thisNode = null;
        queue.add(node);
        while (!queue.isEmpty()) {
            BinaryTreeNode remove = queue.remove();
            if (remove.value == value) {
                thisNode = remove;
                break;
            }
            if (remove.pLeft != null)
                queue.add(remove.pLeft);
            if (remove.pRight != null)
                queue.add(remove.pRight);
        }
        if (thisNode == null) throw new RuntimeException("不存在该节点");
        BinaryTreeNode thisNodeRight = thisNode.pRight;
        while (thisNodeRight != null) {
            if (thisNodeRight.pLeft == null)
                return thisNodeRight;
            thisNodeRight = thisNodeRight.pLeft;
        }
        BinaryTreeNode nodeParent = thisNode.pParent;
        while (nodeParent != null) {
            if (nodeParent.pLeft == thisNode)
                return nodeParent;
            thisNode = thisNode.pParent;
            nodeParent = nodeParent.pParent;
        }
        return null;
    }
}