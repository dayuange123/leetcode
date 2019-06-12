package from101to200;


/**
 *
 * 给定二叉树，找到它的最小深度。
 *
 * 最小深度是沿从根节点到最近的叶节点的最短路径上的节点数。
 *
 * 注意：  叶子是没有子节点的节点。
 *
 * 需要注意最后的注意。
 *
 * 不是求最低高度，
 *
 * 到达的节点的左右节点都为null。才算叶子节点。并不是 只有一个为null
 *
 */
public class MinimumDepthofBinaryTree {

    public int minDepth(TreeNode root) {

        if (root == null) return 0;
        if (root.left == null && root.right == null) return 1;
        if (root.left == null) return minDepth(root.right) + 1;
        if (root.right == null) return minDepth(root.left) + 1;
        return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
    }

}
