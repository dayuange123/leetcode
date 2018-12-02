package from600to700;

public class MergeTwoBinaryTrees {

    /**
     * 给出两个二叉树，并想象当你把其中一个覆盖另一个时，两棵树的一些节点重叠，而其他树则不重叠。
     * 您需要将它们合并到一个新的二叉树中。合并规则是，如果两个节点重叠，
     * 则将节点值加起来作为合并节点的新值。否则，NOT null节点将用作新树的节点。
     * Input:
     * Tree 1                     Tree 2
     * 1                         2
     * / \                       / \
     * 3   2                     1   3
     * /                           \   \
     * 5                             4   7
     * Output:
     * Merged tree:
     * 3
     * / \
     * 4   5
     * / \   \
     * 5   4   7
     */

    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        TreeNode treeNode = new TreeNode(1);
        if (t1 == null && t2 == null) return null;
        treeNode = pre(t1, t2, treeNode);
        return treeNode;

    }

    public TreeNode pre(TreeNode t1, TreeNode t2, TreeNode treeNode) {
        if (t1 == null && t2 == null) return treeNode;
        if (t1 == null) {
            treeNode = new TreeNode(t2.val);
            treeNode.left = pre(null, t2.left, treeNode.left);
            treeNode.right = pre(null, t2.right, treeNode.right);
        } else if (t2 == null) {
            treeNode = new TreeNode(t1.val);
            treeNode.left = pre(t1.left, null, treeNode.left);
            treeNode.right = pre(t1.right, null, treeNode.right);
        } else {
            treeNode = new TreeNode(t1.val + t2.val);
            treeNode.left = pre(t1.left, t2.left, treeNode.left);
            treeNode.right = pre(t1.right, t2.right, treeNode.right);
        }
        return treeNode;
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

}