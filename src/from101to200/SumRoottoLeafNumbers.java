package from101to200;

//先序遍历。
//然后传递上面的数值。
public class SumRoottoLeafNumbers {
    private int sum = 0;

    public int sumNumbers(TreeNode root) {
        if (root == null) return 0;
        rescure(root, root.val);
        return sum;
    }

    private void rescure(TreeNode node, int value) {
        if (node.left == null && node.right == null) {
            sum += value;
        }
        if (node.left != null)
            rescure(node.left, value * 10 + node.left.val);
        if (node.right != null)
            rescure(node.right, value * 10 + node.right.val);
    }
}
