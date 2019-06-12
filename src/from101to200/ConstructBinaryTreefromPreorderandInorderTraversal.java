package from101to200;

/**
 * Given preorder and inorder traversal of a tree, construct the binary tree.
 * You may assume that duplicates do not exist in the tree.
 * <p>
 * For example, given
 * <p>
 * preorder = [3,9,20,15,7]
 * inorder = [9,3,15,20,7]
 * Return the following binary tree:
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * <p>
 * 给中序和先序构建二叉树
 * 递归构建 先找到对应的头节点，然后将左子树和右子树的边界传入
 */
public class ConstructBinaryTreefromPreorderandInorderTraversal {
    private int preIndex = 0;
    private int[] pre;
    private int[] ino;
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        pre = preorder;
        ino = inorder;
        return build(0, preorder.length - 1, getInoIndex());
    }
    private TreeNode build(int left, int right, int mid) {
        if (preIndex == pre.length) return null;
        TreeNode node = new TreeNode(pre[preIndex++]);
        if (mid == left && mid == right) return node;
        if (mid == left) {
            node.right = build(mid + 1, right, getInoIndex());
        } else if (mid == right) {
            node.left = build(left, mid - 1, getInoIndex());
        } else {
            node.left = build(left, mid - 1, getInoIndex());
            node.right = build(mid + 1, right, getInoIndex());
        }
        return node;
    }
    public int getInoIndex() {
        for (int i = 0; i < ino.length; i++) {
            if (pre[preIndex] == ino[i]) {
                return i;
            }
        }
        return 0;
    }
}
