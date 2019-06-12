package from101to200;

/**
 * 判断是否为平衡二叉树
 * 解决思路，判断左右子树高度大于2 直接返回false
 * 使用递归
 *
 * 这里注意一点，平衡二叉树的判断不是最小高度和最大高度的差，而是最大高度的差。
 * 所以不能是 数的最大高度-最小高度
 */
public class BalancedBinaryTree {

    int max;
    int min = Integer.MAX_VALUE;

    public boolean isBalanced(TreeNode root) {
        if (root == null) return true;

        hight(root.right, 0);
        return Math.abs(hight(root.left, 0) - hight(root.right, 0)) < 2 && isBalanced(root.left) && isBalanced(root.right);
    }

    private int hight(TreeNode node, int val) {
        if (node == null) {
            return val;
        }
        int left = hight(node.left, val + 1);
        int right = hight(node.right, val + 1);
        return Math.max(left, right);
    }

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(1);
        Integer[] integers = {1, 2, 2, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, null, null, 5, 5};
        TreeNode node = TreeUtils.buildTree(integers);
        System.out.println(new BalancedBinaryTree().isBalanced(node));

    }
}
