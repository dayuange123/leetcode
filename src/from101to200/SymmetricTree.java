package from101to200;

public class SymmetricTree {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return isSymmetric1(root.left, root.right);
    }
    public boolean isSymmetric1(TreeNode l, TreeNode r) {
        if (l == null && r == null) return true;
        if (l == null || r == null||r.val!=l.val) return false;
        return isSymmetric1(l.left, r.right) && isSymmetric1(l.right, r.left);
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}
