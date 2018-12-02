package sword2offer.tree;

import java.util.ArrayDeque;
import java.util.Queue;

public class RebuildTwoForkTree {

    public BinaryTreeNode Construct(int[] preoder, int[] inorder) {

        if (preoder == null || inorder == null)
            throw new NullPointerException();
        return ConstructCore(preoder, inorder, 0, preoder.length - 1, 0,
                inorder.length - 1);
    }

    private BinaryTreeNode ConstructCore(int[] peroder, int[] inorder,
                                         int startp, int endp, int starti, int endi) {

        int rootValue = peroder[startp];
        //创建新的结点
        BinaryTreeNode treeNode = new BinaryTreeNode(rootValue);
        //让其左右都为null
        treeNode.pLeft = treeNode.pRight = null;
        //代表该节点没有左右子树了
        if (startp == endp) {
            if (starti == endi && peroder[startp] == inorder[starti]) {
                return treeNode;
            } else throw new RuntimeException("输入错误");
        }
        //在中序中寻找该结点所在的索引。
        int index = starti - 1;
        for (int i = starti; i <= endi; i++) {
            if (inorder[i] == rootValue)
                index = i;
        }
        //没找到 抛异常
        if (index == starti - 1) throw new RuntimeException("输入错误");
        //计算出此节点左子树结点总数
        int leftChilds = index - starti;
        //递归创建左子树
        if (leftChilds > 0) {
            treeNode.pLeft = ConstructCore(peroder, inorder, startp + 1, startp + leftChilds,
                    starti, index - 1);

        }
        //判断其右子树是否有节点，有的话创建
        if (index < endi) {
            treeNode.pRight = ConstructCore(peroder, inorder, startp + leftChilds + 1, endp,
                    index + 1, endi);
        }
        return treeNode;
    }

    public static void printByRank(BinaryTreeNode treeNode) {

        Queue<BinaryTreeNode> queue = new ArrayDeque<>();
        queue.add(treeNode);
        while (!queue.isEmpty()) {
            BinaryTreeNode remove = queue.remove();
            System.out.print(remove.value+"\t");
            if (remove.pLeft != null)
                queue.add(remove.pLeft);
            if (remove.pRight != null)
                queue.add(remove.pRight);
        }
    }


    public static void main(String[] args) {
        BinaryTreeNode construct = new RebuildTwoForkTree().Construct(new int[]{1, 2, 4, 7, 3, 5, 6, 8},
                new int[]{4, 7, 2, 1, 5, 3, 8, 6});
        //层级打印
        RebuildTwoForkTree.printByRank(construct);
    }

}