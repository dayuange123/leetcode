package from101to200;

/**
 * 链表的插入排序
 *
 * @author liuzhiyuan
 * @create 2019-10-16 11:09
 */
public class InsertionSortList {
    public ListNode insertionSortList(ListNode head) {
        //前节点，中节点，和后面的节点
        ListNode newHead = new ListNode(0);
        ListNode p = newHead.next;
        ListNode q = newHead;
        while (head != null) {
            ListNode temp = head.next;
            if(p==null){
                q.next=head;
                head.next=null;
            }else {
                while (true) {
                    //比当前节点大，把当前节点插入到前面
                    if (p.val >= head.val) {
                        q.next = head;
                        head.next = p;
                        break;
                    } else {
                        if (p.next == null) {
                            p.next = head;
                            head.next = null;
                            break;
                        } else {
                            p = p.next;
                            q=q.next;
                        }
                    }
                }
            }
            p = newHead.next;
            q = newHead;
            head = temp;
        }
        return newHead.next;
    }

}
