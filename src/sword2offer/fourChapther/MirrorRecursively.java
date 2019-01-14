package sword2offer.fourChapther;


import sword2offer.tree.BinaryTreeNode;

public class MirrorRecursively {


    public void mirrorRecursively(BinaryTreeNode p) {
        if (p == null) return;
        if (p.pLeft == null && p.pRight == null) return;
        BinaryTreeNode binaryTreeNode = p.pLeft;
        p.pLeft = p.pRight;
        p.pLeft = binaryTreeNode;
        if (p.pLeft != null)
            mirrorRecursively(p.pLeft);
        if (p.pRight != null)
            mirrorRecursively(p.pRight);
    }

}