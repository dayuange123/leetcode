package from0to100;

/**
 * 给定链表和值x，对其进行分区，使得小于x的所有节点都在大于或等于x的节点之前。
 * <p>
 * 您应该保留两个分区中每个分区中节点的原始相对顺序。
 * <p>
 * 例：
 * <p>
 * 输入： head = 1-> 4-> 3-> 2-> 5-> 2，x = 3
 * 输出： 1-> 2-> 2-> 4-> 3-> 5
 *  构造两个链表，一个比x小，和一个比x大的
 *  但是我们必须保留每个链表的起点。最后进行相连。
 *  这里很简单，就是给我们的每个链表构造一个头即可。
 *  
 *
 */
public class PartitionList {
    public ListNode partition(ListNode head, int x) {
        if(head == null || head.next == null) return head;
        ListNode lHead = new ListNode(-1), rHead = new ListNode(-1);
        ListNode l = lHead, r = rHead;
        while(head != null){
            if(head.val < x){
                l.next = head;
                l = head;
            }
            else{
                r.next = head;
                r = head;
            }
            head = head.next;
            l.next = null;
            r.next = null;
        }
        l.next = rHead.next;
        return lHead.next;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(2);
        head.next = new ListNode(3);
        head.next.next = new ListNode(1);
        new PartitionList().partition(head, 2);
    }
}
