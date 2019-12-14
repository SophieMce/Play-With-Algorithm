public class Main {

    public static void main(String[] args) {
        Trie trie = new Trie();

        trie.add("apple");
//        trie.contains("apple");   // 返回 true
        System.out.println(trie.contains("apple"));
//        trie.contains("app");     // 返回 false
        System.out.println(trie.contains("app"));
//        trie.isPrefix("app"); // 返回 true
        System.out.println(trie.isPrefix("app"));
        trie.add("app");
        //trie.contains("app");     // 返回 true
        System.out.println(trie.contains("app"));


    }
}
