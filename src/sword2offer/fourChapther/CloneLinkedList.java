package sword2offer.fourChapther;

public class CloneLinkedList {

    public ComplexListNode cloneNodes(ComplexListNode pHead) {
        //复制链表节点
        ComplexListNode q = pHead;
        while (q != null) {
            ComplexListNode node = new ComplexListNode();
            node.nValue = q.nValue;
            ComplexListNode mid = q.pNext;
            node.pNext = mid;
            q.pNext = node;
            q = mid;
        }
        return pHead;
    }

    public ComplexListNode connectSiblingNodes(ComplexListNode pHead) {
        //维护复杂指针
        ComplexListNode q = pHead;
        while (q != null) {
            //获取q的pSibling
            ComplexListNode pSibling = q.pSibling;
            if(pSibling!=null){
                q.pNext.pSibling = pSibling.pNext;
            }

            q = q.pNext.pNext;
        }
        return pHead;
    }

    public ComplexListNode reconnectNodes(ComplexListNode pHead) {
        ComplexListNode pNewHead = pHead, pq = pHead;
        if (pHead == null) return null;
        while (pHead != null) {
            pq.pNext = pHead.pNext;
            pq = pq.pNext;
            pHead = pHead.pNext.pNext;
        }
        return pNewHead.pNext;
    }
}

class ComplexListNode {
    int nValue;
    ComplexListNode pNext;
    ComplexListNode pSibling;
}
