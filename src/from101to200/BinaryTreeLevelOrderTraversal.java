package from101to200;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).
 * <p>
 * For example:
 * Given binary tree [3,9,20,null,null,15,7],
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * return its level order traversal as:
 * [
 * [3],
 * [9,20],
 * [15,7]
 * ]
 * <p>
 * 层级打印二叉树
 * <p>
 * 可以使用两个队列 分别存储每一层的数据
 */
public class BinaryTreeLevelOrderTraversal {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> lists = new ArrayList<>();
        if (root == null) return lists;
        LinkedList<TreeNode> l1 = new LinkedList<>();
        LinkedList<TreeNode> l2 = new LinkedList<>();
        l1.add(root);
        List<Integer> list = new ArrayList<>();
        while (l1.size() > 0) {
            TreeNode treeNode = l1.removeFirst();
            list.add(treeNode.val);
            if (treeNode.left != null) {
                l2.add(treeNode.left);
            }
            if (treeNode.right != null) {
                l2.add(treeNode.right);
            }
            if (l1.size() == 0) {
                lists.add(list);
                list = new ArrayList<>();
                LinkedList<TreeNode> temp = l1;
                l1 = l2;
                l2 = temp;
            }
        }
        return lists;
    }
}