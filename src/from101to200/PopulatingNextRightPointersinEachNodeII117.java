package from101to200;

import java.util.LinkedList;

/**
 * 这个不是完全二叉树，所以不能判断哪个结点为null。
 * <p>
 * 开始想法比较天真，看来解这道题 需要记录层了。
 */
public class PopulatingNextRightPointersinEachNodeII117 {
    public Node connect(Node root) {
        if (root == null) return null;
        Node pre = null;
        LinkedList<Node> linkedList1 = new LinkedList<>();
        LinkedList<Node> linkedList2 = new LinkedList<>();
        linkedList1.add(root);
        while (!linkedList1.isEmpty()) {
            Node node = linkedList1.removeFirst();
            if (pre != null)
                pre.next = node;
            if (node.left != null)
                linkedList2.add(node.left);
            if (node.right != null)
                linkedList2.add(node.right);
            if (linkedList1.isEmpty()) {
                pre = null;
                LinkedList<Node> temp = linkedList1;
                linkedList1 = linkedList2;
                linkedList2 = temp;
            } else
                pre = node;
        }
        return root;
    }
}