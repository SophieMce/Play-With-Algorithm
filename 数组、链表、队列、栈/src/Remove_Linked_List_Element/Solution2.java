package Remove_Linked_List_Element;

public class Solution2 {
    public ListNode removeElements(ListNode head, int val){
        ListNode dummyHead = new ListNode(-1);    //此处并不会用到dummyHead中的值，故随意给值
        dummyHead.next = head;

        ListNode prev = dummyHead;
        while (prev.next != null){
            if (prev.next.val == val){
//                ListNode delNode = prev.next;
//                prev = delNode.next;
//                delNode.next = null;
                prev.next = prev.next.next;   //此处意即跳过需要删除的节点
            }else
                prev = prev.next;
        }
        return dummyHead.next;
    }
}
