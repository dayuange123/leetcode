package from101to200;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right, then right to left for the next level and alternate between).
 * <p>
 * For example:
 * Given binary tree [3,9,20,null,null,15,7],
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * return its zigzag level order traversal as:
 * [
 * [3],
 * [20,9],
 * [15,7]
 * ]
 * 双层 从左到右。单层从右到左。
 * 使用 栈
 */
public class BinaryTreeZigzagLevelOrderTraversal {

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        List<List<Integer>> lists = new ArrayList<>();
        if (root == null) return lists;
        stack1.push(root);
        List<Integer> list = new ArrayList<>();
        while (!stack1.isEmpty()) {
            TreeNode pop = stack1.pop();
            list.add(pop.val);
            if ((lists.size() & 1) == 0) {
                if (pop.left != null)
                    stack2.push(pop.left);
                if (pop.right != null)
                    stack2.push(pop.right);
            } else {
                if (pop.right != null)
                    stack2.push(pop.right);
                if (pop.left != null)
                    stack2.push(pop.left);
            }
            if (stack1.isEmpty()) {
                lists.add(list);
                list = new ArrayList<>();
                Stack<TreeNode> temp = stack1;
                stack1 = stack2;
                stack2 = temp;
            }
        }
        return lists;
    }

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(3);
        treeNode.left = new TreeNode(9);
        treeNode.right = new TreeNode(20);
        treeNode.right.left = new TreeNode(15);
        treeNode.right.right = new TreeNode(7);
        new BinaryTreeZigzagLevelOrderTraversal().zigzagLevelOrder(treeNode);
    }
}