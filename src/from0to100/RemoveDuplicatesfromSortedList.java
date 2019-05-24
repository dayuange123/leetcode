package from0to100;

/**
 * Given a sorted linked list, delete all duplicates such that each element appear only once.
 *
 * Example 1:
 *
 * Input: 1->1->2
 * Output: 1->2
 * Example 2:
 *
 * Input: 1->1->2->3->3
 * Output: 1->2->3
 *
 * 很简单 两个指针。
 */
public class RemoveDuplicatesfromSortedList {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode pre = head;
        ListNode next = head;
        while (next != null) {
            if (next.val != pre.val) {
                pre.next = next;
                pre=next;
            }
            next = next.next;

        }
        pre.next=null;
        return head;
    }

    //[1,1,2,3,3]
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
        }
    }
}
