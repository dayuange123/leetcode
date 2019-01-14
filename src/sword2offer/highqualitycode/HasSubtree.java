package sword2offer.highqualitycode;

public class HasSubtree {

    public boolean hasSubtree(BinaryTreeNode pRoot1, BinaryTreeNode pRoot2) {
        boolean f = false;
        if (pRoot1 != null && pRoot2 != null) {
            if (pRoot1.mValue == pRoot2.mValue) {
                //逐级判断
                f = isEquals(pRoot1, pRoot2);
                if (f) return true;
            }
            f = hasSubtree(pRoot1.pLeft, pRoot2);
            if (f) return true;
            f = hasSubtree(pRoot1.pRight, pRoot2);
        }
        return f;
    }

    public boolean isEquals(BinaryTreeNode pRoot1, BinaryTreeNode pRoot2) {
        if (pRoot2 == null) return true;
        if (pRoot1 == null) return false;
        if (pRoot1.mValue != pRoot2.mValue) return false;
        return isEquals(pRoot1.pLeft, pRoot2.pLeft) && isEquals(pRoot1.pRight, pRoot2.pRight);
    }

}

class BinaryTreeNode {
    double mValue;
    BinaryTreeNode pLeft;
    BinaryTreeNode pRight;
}


