package sword2offer.highqualitycode;

public class DeleteLinkListNode {
    /**
     * 删除下一个结点
     *
     * @param root
     * @param delete
     */
    public void deleteNode(Node root, Node delete) {
        if (delete == root) {
            root = null;

        } else if (delete.next != null) {
            delete.value = delete.next.value;
            delete.next = delete.next.next;
        } else {
            Node node = root;
            while (node.next != delete) {
                node = node.next;
            }
            node.next = null;
        }
    }

    //删除重复的结点
    public void deleteDuplication(Node root) {
        if (root == null)
            return;
        Node pre = null;
        Node pNode = root;
        while (pNode != null) {
            Node next = pNode.next;
            boolean needDelte = false;
            if (next != null && pNode.value == next.value)
                needDelte = true;
            if (!needDelte) {
                pre = pNode;
                pNode = pNode.next;
            } else {
                int value = pNode.value;
                Node pToBeDel = pNode;
                while (pToBeDel != null && value == pToBeDel.value)
                    pToBeDel = pToBeDel.next;
                if (pre == null)
                    root = pToBeDel;
                else
                    pre.next = pToBeDel;
                pNode = pToBeDel;
            }
        }
    }
}
