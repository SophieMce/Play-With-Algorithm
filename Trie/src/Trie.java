import java.util.TreeMap;

public class Trie {
    private class Node{
        public boolean isWord;      //记录当前节点是否包含相应的节点
        public TreeMap<Character, Node> next;

        public Node(boolean isWord){
            this.isWord = isWord;
            next = new TreeMap<>();
        }

        public Node(){
            this(false);
        }
    }
    //首节点是为空的节点
    private Node root;
    private int size;

    public Trie(){
        root = new Node();
        size = 0;
    }

    //获得Trie中的单词数量
    public int getSize(){
        return size;
    }

    //向Trie中添加一个新的单词word
    //将当前词汇拆分成一个个字符，依次遍历，由根节点开始依次查找trie中是否有此词汇，没有的话就创建单词
    public void add(String word){
        Node cur = root;  //由根节点开始查询是否由当前词汇
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (cur.next.get(c) == null)
                cur.next.put(c, new Node());
            cur = cur.next.get(c);  //继续指向下一个节点，因get获取的是存储的node值
        }
        if (!cur.isWord){
            cur.isWord = true;
            size ++;
        }
    }

    //在trie中查询某个单词
    public boolean contains(String word){
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (cur.next.get(c) == null)
                return false;
            cur = cur.next.get(c);
        }
        return cur.isWord;
    }

    //查找在Trie中是否有单词以prefix为前缀
    public boolean isPrefix(String word){
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (cur.next.get(c) == null)
                return false;
            cur = cur.next.get(c);
        }
        return true;
    }
}
