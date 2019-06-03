package from0to100;

/**
 * 反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。
 *
 * 说明:
 * 1 ≤ m ≤ n ≤ 链表长度。
 *
 * 示例:
 *
 * 输入: 1->2->3->4->5->NULL, m = 2, n = 4
 * 输出: 1->4->3->2->5->NULL
 *
 * 对于这道题 只需要使用头插
 * 但是我们需要找到头部元素。
 * 还需要保留前一个。
 * 并且还要保留尾部的前一个。
 * 这里我是用left 和right right是left的下一个。
 * 然后创建了一个lh结点作为头部的前一个元素。
 *
 */
public class ReversalList2 {

    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (m == n) return head;
        ListNode lh = new ListNode(0);
        ListNode mid;
        lh.next = head;
        ListNode left = head;
        ListNode right = head;
        int index = 1;
        while (index < n) {
            right = right.next;
            if (index++ < m) {
                left = left.next;
                lh = lh.next;
            } else {
                mid = right;
                left.next = right.next;
                right = left;
                mid.next = lh.next;
                lh.next = mid;
            }
        }
        return m == 1 ? lh.next : head;

    }
}