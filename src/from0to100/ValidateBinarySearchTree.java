package from0to100;

import java.util.Stack;

/**
 * 给定二叉树，确定它是否是有效的二叉搜索树（BST）。
 * <p>
 * 假设BST定义如下：
 * <p>
 * 节点的左子树仅包含键小于节点键的节点。
 * 节点的右子树仅包含键大于节点键的节点。
 * 左右子树也必须是二叉搜索树。
 * <p>
 * <p>
 * 例1：
 * <p>
 * 2
 * / \
 * 1 3 输入：  [2,1,3]
 * 输出： true
 */
public class ValidateBinarySearchTree {

    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;
        int value = 0;
        boolean f = true;
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || root != null) {
            if (root == null) {
                root = stack.pop();
                if (f) {
                    f = false;
                } else {
                    if (value >= root.val) return false;
                }
                value = root.val;
                root = root.right;
            } else {
                stack.push(root);
                root = root.left;
            }
        }
        return true;
    }

}
