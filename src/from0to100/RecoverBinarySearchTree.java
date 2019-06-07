package from0to100;

import java.util.Stack;

/**
 * 二进制搜索树（BST）的两个元素被错误地交换。
 *
 * 在不改变其结构的情况下恢复树。
 *
 * 例1：
 *
 * 输入： [1,3，null，null，2]
 *
 *    1
 *   /
 *  3
 *   \
 *    2
 *
 * 输出： [3,1，null，null，2]
 *
 *    3
 *   /
 *  1
 *   \
 *    2
 *
 * 有可能是顺序的两个节点被交换
 * 也有可能是 相隔。
 */
public class RecoverBinarySearchTree {
    public void recoverTree(TreeNode root) {
        if (root == null) return;
        TreeNode pre = null;
        TreeNode s = null;
        TreeNode first = null;
        TreeNode secound = null;
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || root != null) {
            if (root == null) {
                root = stack.pop();
                if (pre != null && pre.val >= root.val) {
                    if (first == null) {
                        first = pre;
                        s = root;
                    } else {
                        secound = root;
                        break;
                    }
                }
                pre = root;
                root = root.right;
            } else {
                stack.push(root);
                root = root.left;
            }
        }
        if (secound != null) {
            int t = first.val;
            first.val = secound.val;
            secound.val = t;
        } else {
            int t = first.val;
            first.val = s.val;
            s.val = t;
        }
    }

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(1);
        treeNode.left = new TreeNode(3);
        treeNode.left.right = new TreeNode(2);
        new RecoverBinarySearchTree().recoverTree(treeNode);
    }
}
