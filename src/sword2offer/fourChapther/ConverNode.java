package sword2offer.fourChapther;

import sword2offer.tree.BinaryTreeNode;

public class ConverNode {
    public BinaryTreeNode conver(BinaryTreeNode pRoot) {
        //创建一个最小的结点
        BinaryTreeNode pLast = null;
        coverNode(pRoot, pLast);
        //这里需要返回最小的结点
        while (pLast != null) {
            pLast = pLast.pLeft;
        }
        return pLast;
    }
    private void coverNode(BinaryTreeNode pNode, BinaryTreeNode pLast) {
        if (pNode == null) return;
        if (pNode.pLeft != null)
            coverNode(pNode.pLeft, pLast);

        //让当前结点的左指向最小的结点
        pNode.pLeft = pLast;
        //如果最小结点不为null，让最小结点的右指向当前节点
        if (pLast != null)
            pLast.pRight = pNode;
        //保存这个结点
        pLast = pNode;
        if (pNode.pRight != null)
            coverNode(pNode.pRight, pLast);
    }
}