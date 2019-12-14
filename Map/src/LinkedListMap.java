import java.util.ArrayList;

public class LinkedListMap<K, V> implements Map<K, V> {
    //基于链表实现的基本构造：节点
    private class Node{
        public K key;
        public V value;
        public Node next;

        public Node(K key, V value, Node next){
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public Node(K key, V value){
//            this.key = key;
//            this.value = value;
//            this.next = null;
            this(key, value, null);
        }

        public Node(){
            this(null, null, null);
        }

        @Override
        public String toString(){
            return key.toString() + ":" + value.toString();
        }
    }

    private Node dummyHead; //头指针
    private int size;       //节点个数

    public LinkedListMap(){
        dummyHead = new Node();
        size = 0;
    }

    @Override
    public int getSize(){
        return size;
    }

    @Override
    public boolean isEmpty(){
        return size == 0;
    }
    //私有的辅助函数
    //查找以key为键值的节点，并返回此节点
    private Node getNode(K key){
        Node cur = dummyHead.next;
        while (cur != null){
            if (cur.key.equals(key))
                return cur;
            cur = cur.next;
        }
        return null;
    }

    @Override
    //查找此键值对应的节点，查找到的节点若不为null，则返回true
    public boolean contains(K key){
        return getNode(key) != null;
    }

    @Override
    public V get(K key){
        Node node = getNode(key);
        return node == null ? null : node.value;
    }

    @Override
    public void add(K key, V value){
        Node node = getNode(key);
        //若找不到key为键值的节点，则创建一个新的节点
        if (node == null){
            dummyHead.next = new Node(key, value, dummyHead.next);
            size ++;
        }else
            node.value = value;
    }

    @Override //值修改
    public void set(K key, V newValue){
        //先找节点，找到这样的节点才对其进行值的修改
        Node node = getNode(key);
        if (node == null)
            throw new IllegalArgumentException("The node doesn't exists");
        node.value = newValue;
    }

    @Override
    public V remove(K key){
        Node prev = dummyHead;
        while (prev.next != null){
            if (prev.next.key.equals(key))
                break;
            prev = prev.next;
        }
        if (prev.next != null){
            Node delNode = prev.next;
            prev.next = delNode.next;
            delNode.next = null;
            size --;
            return delNode.value;
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println("Pride and Prejudice");
        ArrayList<String> words = new ArrayList<>();
        if(FileOperation.readFile("pride-and-prejudice.txt", words)) {
            System.out.println("Total words: " + words.size());
            LinkedListMap<String, Integer> map = new LinkedListMap<>();
            for (String word :
                    words) {
                if (map.contains(word)){
                    map.set(word, map.get(word) + 1);
                }else
                    map.add(word, 1);
            }
            System.out.println("Total different words: " + map.getSize());
            System.out.println("Frequency of PRIDE: " + map.get("pride"));
            System.out.println("Frequency of PREJUDICE: " + map.get("prejudice"));
        }
        System.out.println();
    }
}
