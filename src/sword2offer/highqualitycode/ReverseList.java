package sword2offer.highqualitycode;

public class ReverseList {
    public Node reserveList(Node pHead) {
        if (pHead == null || pHead.next == null) return pHead;
        Node pThis = pHead;
        Node pNext = pHead.next;
        pHead.next = null;
        Node nextN;
        while (true) {
            nextN = pNext.next;
            pNext.next = pThis;
            pThis = pNext;
            pNext = nextN;
            if (pNext == null) return pThis;
        }

    }

    public static void main(String[] args) {
        ReverseList reverseList = new ReverseList();
        Node node = new Node(1);
        Node p = node;
        for (int i = 2; i < 3; ++i) {
            p.next = new Node(i);
            p = p.next;
        }
        Node node1 = reverseList.reserveList(node);
        while (node1!=null){
            System.out.println(node1.value);
            node1=node1.next;
        }
    }
}