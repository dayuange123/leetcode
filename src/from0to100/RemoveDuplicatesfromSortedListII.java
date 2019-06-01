package from0to100;

/**
 * Input: 1->2->3->3->4->4->5
 * Output: 1->2->5
 *
 * 使用三个指针， current在程序中指的是我们最终链表保存的所有节点。也就是使用current构建最终链表结构
 * 其他两个一个为前一个节点，一个是当前遍历节点。
 * 我们判断前节点和遍历节点之间的距离
 * 如果不相等，并且距离为0，说明我们需要将当前的pre节点保存，因为pre在链表中只有一个
 * 如果不相等，但是距离不为0，说明pre不只一个，所以不需要保存，我们直接更新 距离为0，并且将pre指向 next 继续遍历
 * 如果相等 距离++，next往后移动，pre不需要动。
 *
 * 需要注意的是 最后情况的处理。
 * 因为while循环结束，我们的链表还是要维护。这时候分为两种情况
 * 1.distance为0，说明 最后一个节点和前面的 不想等，时候我们需要将最后一个节点加入链表中。
 * 2.不为0，说明后面和前面的相等，我们要维护当前链表的终点，让其为null
 */
public class RemoveDuplicatesfromSortedListII {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode pre = head;
        ListNode next = head.next;
        ListNode current = head;
        boolean first = true;
        int distance = 0;
        while (next != null) {
            if (pre.val == next.val) {
                distance++;
            } else {
                if (distance == 0) {
                    if (first) {
                        current = pre;
                        head = current;
                        first = false;
                    } else {
                        current.next = pre;
                        current = current.next;
                    }
                } else {
                    distance = 0;
                }
                pre = next;
            }
            next = next.next;
        }
        if (distance == 0) {
            if (first) head = pre;
            else current.next = pre;
        } else {
            if (first) head = null;
            else current.next = null;
        }
        return head;
    }
    //1->2->3->3->4->4->5
    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(1);
        listNode.next.next = new ListNode(2);
//        listNode.next.next.next = new ListNode(2);
//        listNode.next.next.next.next = new ListNode(3);
        ListNode listNode1 = new RemoveDuplicatesfromSortedListII().deleteDuplicates(listNode);
    }
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}


