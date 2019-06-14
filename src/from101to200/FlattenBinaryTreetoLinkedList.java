package from101to200;


import java.util.LinkedList;

/**
 * Given a binary tree, flatten it to a linked list in-place.
 * <p>
 * For example, given the following tree:
 * <p>
 * 1
 * / \
 * 2   5
 * / \   \
 * 3   4   6
 * The flattened tree should look like:
 * <p>
 * 1
 * \
 * 2
 * \
 * 3
 * \
 * 4
 * \
 * 5
 * \
 * 6
 * 把树变成链表.
 * <p>
 * 将所有的右节点保存。然后将所有节点的 左插入到右，然后
 */
public class FlattenBinaryTreetoLinkedList {
    LinkedList<TreeNode> linked = new LinkedList<>();

    public void flatten(TreeNode root) {
        rescure(root);
    }

    private void rescure(TreeNode node) {
        if (node == null) return;
        if (node.right == null) {
            node.right = node.left;
            node.left = null;
            rescure(node.right);
            return;
        }
        TreeNode temp = node.right;
        rescure(node.left);
        node.right = node.left;
        node.left = null;
        while (node.right != null) {
            node = node.right;
        }
        rescure(temp);
        node.right = temp;
    }
}
