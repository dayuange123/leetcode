package from101to200;

/**
 * 给定非空二叉树，找到最大路径总和。
 * <p>
 * 对于此问题，路径定义为沿着父子连接从树中的某个起始节点到任何节点的任何节点序列。该路径必须至少包含一个节点，并且不需要通过根节点。
 * <p>
 * Input: [-10,9,20,null,null,15,7]
 * <p>
 * -10
 * / \
 * 9  20
 * /  \
 * 15   7
 * <p>
 * Output: 42
 * <p>
 * 说实话刚看到这道题目。感觉是有点难的，但是仔细想想也还好。
 * 我们对于每个节点，可以求出来他的左节点的最大路径，再求出右节点的最大路径，最后将 三个相加，然后和其他两个判断。维护一个最大值。
 * 所以我们需要使用后序遍历的方法，
 */
public class BinaryTreeMaximumPathSum {
    private int max = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        rescure(root);
        return max;
    }

    public int rescure(TreeNode root) {
        if (root == null) return Integer.MIN_VALUE;
        int left = rescure(root.left);
        int right = rescure(root.right);
        int temp = root.val;
        //之所以分开+是因为怕越界。
        if (left > 0) {
            temp += left;
        }
        if (right > 0) {
            temp += right;
        }
        if (right > left) {
            root.val = right > 0 ? root.val + right : root.val;
            max = Math.max(Math.max(Math.max(right, root.val), temp), max);
        } else {
            root.val = left > 0 ? root.val + left : root.val;
            max = Math.max(Math.max(Math.max(left, root.val), temp), max);
        }
        return root.val;
    }

    public static void main(String[] args) {
        Integer[] a = {-2, 1};
        TreeNode node = TreeUtils.buildTree(a);
        new BinaryTreeMaximumPathSum().maxPathSum(node);
    }
}
