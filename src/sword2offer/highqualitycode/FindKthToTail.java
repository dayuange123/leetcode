package sword2offer.highqualitycode;

public class FindKthToTail {
    static class ListNode {
        int value;
        ListNode next;
    }

    ListNode findKthToTail(ListNode listNode, int k) {
        if (listNode == null || k == 0) return null;
        ListNode listNode1 = listNode;
        ListNode listNode2 = listNode;
        int index1 = 0;
        while (listNode1 != null) {
            listNode1 = listNode1.next;
            if (index1 == k - 1)
                break;
            index1++;
        }
        if (index1 != k - 1) return null;
        while (listNode1 != null) {
            listNode1 = listNode1.next;
            listNode2 = listNode2.next;
        }
        return listNode2;

    }

    public static void main(String[] args) {
        ListNode l = new ListNode();
        l.value = 0;
        l.next = new ListNode();
        l.next.value = 1;
        l.next.next = new ListNode();
        l.next.next.value = 2;
        ListNode kthToTail = new FindKthToTail().findKthToTail(l, 3);
        while (kthToTail != null) {
            System.out.println(kthToTail.value);
            kthToTail = kthToTail.next;
        }
    }

}