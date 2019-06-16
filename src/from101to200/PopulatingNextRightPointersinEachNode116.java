package from101to200;

import java.util.LinkedList;

/**
 * 您将获得一个完美的二叉树  ，其中所有叶子都在同一级别，每个父级都有两个孩子。二叉树具有以下定义：
 * <p>
 * struct Node {
 * int val;
 * 节点*离开;
 * 节点*权利;
 * 节点*下一个;
 * }
 * 填充每个下一个指针以指向其下一个右侧节点。如果没有右下一个节点，则应将下一个指针设置为NULL。
 * <p>
 * 其实就是将每个结点的next指向这一层的下一个，如果是最后一个，指向null
 * <p>
 * 其实每一层的最后一个分别是 第 2的层数次方-1
 */
public class PopulatingNextRightPointersinEachNode116 {

    public Node connect(Node root) {
        if (root == null) return null;
        Node pre = null;
        LinkedList<Node> linkedList = new LinkedList<>();
        linkedList.add(root);
        int index = 1;
        while (!linkedList.isEmpty()) {
            Node node = linkedList.removeFirst();
            if (index != 1)
                if ((index & index - 1) == 0)
                    pre.next = null;
                else pre.next = node;
            pre = node;
            if (node.left != null) {
                linkedList.add(node.left);
                linkedList.add(node.right);
            }
            index++;
        }
        return root;
    }
}

class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};