package LinkedList;

public class LinkedList<E> {
    public class Node{
        public E e;        //节点存储的值
        public Node next;  //节点中存储的指向下一个节点的值

        public Node(E e, Node next){
            this.e = e;
            this.next = next;
        }

        public Node(E e){
            this(e, null);
        }

        public Node(){
            this(null, null);
        }
        @Override
        public String toString(){
            return e.toString();
        }
    }
    private Node dummyHead;
    private int size;

    public LinkedList(){
        dummyHead = new Node(null, null);
        size = 0;
    }
    //获取链表中的元素个数
    public int getSize(){
        return size;
    }
    //判断链表是否为空
    public boolean isEmpty(){
        return size == 0;
    }

    //在链表的index位置添加元素e
    public void add(int index, E e){
        if (index < 0 || index > size){
            throw new IllegalArgumentException("add failed.Illegal index.");
        }
        Node prev = dummyHead;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }
//            Node node = new Node(e);
//            node.next = prev.next;
//            prev.next = node;
        prev.next = new Node(e, prev.next);
        size ++;
        }

    //在链表的头部添加元素e
    public void addFirst(E e){
        add(0, e);
    }

    //在链表的末尾添加元素e
    public void addLast(E e){
        add(size, e);
    }
    //获取链表的第index位置的元素
    public E get(int index){
        if (index < 0 || index >= size){
            throw new IllegalArgumentException("find failed.index is illegal.");
        }

        Node cur = dummyHead.next;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        return cur.e;
    }
    //获取链表的第一个元素
    public E getFirst(){
        return get(0);
    }
    //获取链表的最后一个元素
    public E getLast(){
        return get(size - 1);
    }
    //修改链表中index位置的元素为e
    public void set(int index, E e){
        if (index < 0 || index >= size){
            throw new IllegalArgumentException("index is illegal.");
        }
        Node cur = dummyHead.next;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        cur.e = e;
    }
    //查找链表中是否存在元素e
    public boolean contains(E e){
        Node cur = dummyHead.next;
        while (cur != null){
            if (cur.e.equals(e))
                return true;
            cur = cur.next;
        }
        return false;
    }
    //打印链表操作
    public String toString(){
        StringBuilder res = new StringBuilder();
//        Node cur = dummyHead.next;
//        while (cur != null){
//            res.append("->");
//            cur = cur.next;
//        }
        for (Node cur = dummyHead.next; cur != null; cur = cur.next)
            res.append(cur + "->");
        res.append("NULL");
        return res.toString();
    }
    //删除index位置的元素，并返回删除的元素
    public E remove(int index){
        if (index < 0 || index >= size){
            throw new IllegalArgumentException("index is illegal.");
        }

        Node prev = dummyHead;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }
        Node retNode = prev.next;
        prev.next = retNode.next;
        retNode.next = null;
        size --;
        return retNode.e;
    }
    //删除链表的第一个元素
    public E removeFirst(){
        return remove(0);
    }
    //删除链表的最后一个元素
    public E removeLast(){
        return remove(size - 1);
    }
}
