package from0to100;

public class RotateList {

    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || k == 0) return head;
        ListNode current = head;
        ListNode order = head;
        int count = 0;
        while (current != null) {
            count++;
            if (current.next == null)
                break;
            current = current.next;
        }
        //current 为最后一个
        k = count - k % count;
        if (k == count) return order;
        //head移动到第 k-1
        for (int i = 0; i < k - 1; i++) {
            head = head.next;
        }
        ListNode newlast = head;
        head = head.next;
        newlast.next = null;
        current.next = order;
        return head;
    }

    public static void main(String[] args) {
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}

