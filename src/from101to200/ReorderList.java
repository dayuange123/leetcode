package from101to200;


/**
 * Given a singly linked list L: L0→L1→…→Ln-1→Ln, reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…
 * You may not modify the values in the list's nodes, only nodes itself may be changed.
 *
 * @author liuzhiyuan
 * @create 2019-10-15 17:35
 */
public class ReorderList {
    public void reorderList(ListNode head) {

        ListNode p = head;
        ListNode q = head;
        boolean flag = false;
        while (q != null) {
            if (q.next != null) {
                q = q.next.next;
                p = p.next;
            } else {
                flag = true;
                break;
            }
        }
        ListNode tail = null;
        if (flag) {
            tail = p;
            p = p.next;
            tail.next=null;
            //奇数个节点
        }
        //使用头插
        ListNode pHead = new ListNode(0);
        while (p != null) {
            ListNode temp = p.next;
            p.next = pHead.next;
            pHead.next = p;
            p = temp;
        }
        p = head;
        pHead = pHead.next;
        //1 2 3 6 5 4 phead=4
        //直接插入即可
        while (pHead != null) {
            q = p.next;
            ListNode nextPhead = pHead.next;
            p.next = pHead;
            if (nextPhead == null) {
                if (flag)
                    pHead.next = tail;
                else pHead.next = null;
                break;
            }
            pHead.next = q;
            pHead = nextPhead;
            p = q;
        }
    }

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(2);
        listNode.next.next = new ListNode(3);
        listNode.next.next.next = new ListNode(4);
        listNode.next.next.next.next = new ListNode(5);
        new ReorderList().reorderList(listNode);
    }
}
// 1 2 3 4