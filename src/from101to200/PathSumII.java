package from101to200;


import java.util.ArrayList;
import java.util.List;

/**
 * Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.
 * <p>
 * Note: A leaf is a node with no children.
 * <p>
 * Example:
 * <p>
 * Given the below binary tree and sum = 22,
 * <p>
 * 5
 * / \
 * 4   8
 * /   / \
 * 11  13  4
 * /  \    / \
 * 7    2  5   1
 * Return:
 * <p>
 * [
 * [5,4,11,2],
 * [5,8,4,5]
 * ]
 * <p>
 * 遍历保存即可
 */
public class PathSumII {
    private List<List<Integer>> lists = new ArrayList<>();
    private List<Integer> list = new ArrayList<>();

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        rescure(root, sum);
        return lists;
    }

    private void rescure(TreeNode node, int sum) {
        if (node == null) return;
        list.add(node.val);
        if (node.left == null && node.right == null && sum - node.val == 0) {
            lists.add(new ArrayList<>(list));
            list.remove(list.size() - 1);
            return;
        }
        rescure(node.left, sum - node.val);
        rescure(node.right, sum - node.val);
        list.remove(list.size() - 1);
    }
}
