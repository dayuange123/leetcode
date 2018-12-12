package sword2offer.highqualitycode;

public class MergeLinkedList {

    public Node merge(Node pHead1, Node pHead2) {
        if (pHead1 == null) return pHead2;
        if (pHead2 == null) return pHead1;
        Node n;

        if (pHead1.value < pHead2.value) {
            n = pHead1;
            n.next = merge(pHead1.next, pHead2);
        } else {
            n = pHead2;
            n.next = merge(pHead1, pHead2.next);
        }
        return n;
    }
}