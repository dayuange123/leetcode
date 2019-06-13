package from101to200;

/**
 * 给定二叉树和求和，确定树是否具有根到叶路径，使得沿路径的所有值相加等于给定的总和。
 * <p>
 * 注意：  叶子是没有子节点的节点。
 * <p>
 * 例：
 * <p>
 * 鉴于以下二叉树和sum = 22，
 * <p>
 * 5
 * / \
 * 4   8
 * /   / \
 * 11  13  4
 * /  \      \
 * 7    2      1
 * <p>
 * <p>
 * 遍历每一条路径。
 * 但不一定要遍历结束才返回，如果当前总和大于，就没必要再遍历了。(不能这样优化，因为存在负数，但是可以注意的一点就是，我们以后对于这种叠加 判断的，可以直接使用原数减为0即可 没必要作比较，)
 */
public class PathSum {
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) return false;
        return rescure(root, sum, 0);
    }

    private boolean rescure(TreeNode node, int sum, int mySum) {
        if (node == null) return false;
        if (node.left == null && node.right == null) {
            return mySum + node.val == sum;
        }
        return rescure(node.left, sum, mySum + node.val) || rescure(node.right, sum, mySum + node.val);

    }
}
