package sword2offer.fourChapther;

import sword2offer.tree.BinaryTreeNode;

import java.io.UnsupportedEncodingException;

public class IsSymmetrical {

    public boolean isSymmetrical(BinaryTreeNode pRoot) {
        return isSymmetrical(pRoot, pRoot);
    }

    private boolean isSymmetrical(BinaryTreeNode pRoot1, BinaryTreeNode pRoot2) {
        if (pRoot1 == null && pRoot2 == null) return true;
        if (pRoot1 == null || pRoot2 == null) return false;
        if (pRoot1.value != pRoot2.value) return false;
        return isSymmetrical(pRoot1.pLeft, pRoot2.pRight) &&
                isSymmetrical(pRoot1.pRight, pRoot2.pLeft);
    }

    public static void main(String[] args) throws UnsupportedEncodingException {


        String source = "\\xe9\\xb8\\xa1\\xe6\\xb6\\x8c\\xe5\\xae\\xb6\\xe5\\xa5\\x88\\xe5\\xa5\\x88\\xe9\\x85\\xb1";
        String sourceArr[] = source.split("\\\\"); // 分割拿到形如 xE9 的16进制数据
        byte[] byteArr = new byte[sourceArr.length - 1];
        for (int i = 1; i < sourceArr.length; i++) {
            Integer hexInt = Integer.decode("0" + sourceArr[i]);
            byteArr[i - 1] = hexInt.byteValue();
        }
        System.out.println(new String(byteArr, "UTF-8"));
    }
}