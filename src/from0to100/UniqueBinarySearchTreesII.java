package from0to100;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Input: 3
 * Output:
 * [
 * [1,null,3,2],
 * [3,2,null,1],
 * [3,1,null,null,2],
 * [2,1,3],
 * [1,null,2,null,3]
 * ]
 * Explanation:
 * The above output corresponds to the 5 unique BST's shown below:
 * <p>
 * 1         3     3      2      1
 * \       /     /      / \      \
 * 3     2     1      1   3      2
 * /     /       \                 \
 * 2     1         2                 3
 * 动态规划
 我们只需要将添加的节点作为原来节点的头节点
 或者遍历节点的右节点。可以将增加的节点添加到每个遍历的右节点的右边。
 最后将所有结果放入list即可。
 */
public class UniqueBinarySearchTreesII {
    public List<TreeNode> generateTrees(int n) {

        LinkedList<TreeNode> list = new LinkedList<>();
        if(n==0) return list;
        recure(n, list);
        return list;
    }

    public void recure(int n, LinkedList<TreeNode> list) {
        if (n == 1) {
            list.add(new TreeNode(n));
            return;
        }
        recure(n - 1, list);
        //分为两种情况，一种是插到头部。一种是插到右边，
        //遍历右子树的所有右节点。插到右节点的右边就可以。但如果右节点的右不为空。 将插入的新节点的左指向原右节点的右。
        // 将生成的节点加入到list
        int size = list.size();
        for (int i = 0; i < size; i++) {
            TreeNode treeNode = list.removeFirst();
            TreeNode newNode = new TreeNode(n);
            newNode.left = treeNode;
            list.add(newNode);
            TreeNode head = treeNode;
            //找到所有的右节点对应的val， 然后重新遍历树。进行构建，构建方法
            while (treeNode != null) {
                TreeNode node = new TreeNode(n);
                list.add(copyAndCreate(head, treeNode.val, node));
                treeNode = treeNode.right;
            }
        }
    }
    //之所以有这个方法是因为，我们要重新构造树。不能使用原来的节点，因为使用原来的节点会有影响
    //所以这个方法就是 构建新的节点
    public TreeNode copyAndCreate(TreeNode head, int val, TreeNode node) {
        if (head == null) return null;
        TreeNode newHead = new TreeNode(head.val);
        if (val != head.val) {
            newHead.left = copyAndCreate(head.left, val, node);
            newHead.right = copyAndCreate(head.right, val, node);
        } else {
            newHead.right = node;
            newHead.left = copyAndCreate(head.left, val, node);
            node.left=copyAndCreate(head.right, val, node);
        }
        return newHead;
    }
}
