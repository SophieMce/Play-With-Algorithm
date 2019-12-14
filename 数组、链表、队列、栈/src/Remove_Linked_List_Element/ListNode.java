package Remove_Linked_List_Element;

public class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }

    // 链表节点的构造函数
    // 使用arr为参数，创建一个链表，当前的ListNode为链表头结点
    public ListNode(int[] arr){
        if (arr == null || arr.length == 0){
            throw new IllegalArgumentException("arr cannot be empty");
        }
        this.val = arr[0];     //将其赋给头节点的值
        ListNode cur = this;   //cur作为指向头节点的指针
        for (int i = 1; i < arr.length; i++) {
            cur.next = new ListNode(arr[i]);    //需创建一个新的节点
            cur = cur.next;
        }
    }

    //以当前结点为头结点的链表信息字符串
    public String toString(){
        StringBuilder res = new StringBuilder();
        ListNode cur = this;
        while (cur != null){
            res.append(cur.val + "->");
            cur = cur.next;
        }
        res.append("NULL");
        return res.toString();
    }
}
