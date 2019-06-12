package from101to200;


/**
 * Given a binary tree, find its maximum depth.
 * <p>
 * The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
 * <p>
 * Note: A leaf is a node with no children.
 * <p>
 * Example:
 * <p>
 * Given binary tree [3,9,20,null,null,15,7],
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * return its depth = 3.
 */
public class MaximumDepthofBinaryTree {

    private int max = -1;
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        recure(root, 0);
        return max;
    }
    private void recure(TreeNode node, int depth) {
        if (node == null) {
            max = max < depth ? depth : max;
            return;
        }
        recure(node.left, depth + 1);
        recure(node.right, depth + 1);

    }
}

//  Definition for a binary tree node.
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}
