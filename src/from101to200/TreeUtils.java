package from101to200;

import java.util.LinkedList;

/**
 * 构建二叉树
 */
public class TreeUtils {

    public static TreeNode buildTree(Integer[] nums) {
        TreeNode root;
        root = new TreeNode(nums[0]);
        LinkedList<TreeNode> linkedList = new LinkedList<>();
        linkedList.add(root);
        int index = 1;
        while (index < nums.length) {
            TreeNode node = linkedList.removeFirst();
            if (nums[index++] != null) {
                node.left = new TreeNode(nums[index - 1]);
                linkedList.add(node.left);
            }
            if (index < nums.length) {
                if (nums[index++] != null) {
                    node.right = new TreeNode(nums[index - 1]);
                    linkedList.add(node.right);
                }
            }
        }
        return root;
    }


}
