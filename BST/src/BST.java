public class BST<E extends Comparable<E>> {
    //搜索树的节点内部对于外部是屏蔽的
    private class Node{
        public E e;
        public Node left;
        public Node right;

        public Node(E e){
            this.e = e;
            left = null;
            right = null;
        }
    }

    private Node root;
    private int size;
    //初始化
    public BST(){
        root = null;
        size = 0;
    }

    public int Size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    //向二分搜索树中添加新的元素e
    public void add(E e){
        if (root == null){
            root = new Node(e);
            size ++;
        }
        else
            add(root, e);
    }
    //向以node为根节点的二分搜索树中插入元素e，递归算法
    private void add(Node node, E e){
        //三种不能再简化的最小的问题
        if (e.equals(root.e))
            return;
        else if (e.compareTo(node.e) < 0 && node.left == null){
            node.left = new Node(e);
            size ++;
            return;
        }else if (e.compareTo(node.e) > 0 && node.right == null){
            node.right = new Node(e);
            size ++;
            return;
        }
        //两种基本情况
        //e小于当前节点的值，插入左子树，若大于当前节点的值，插入右子树
        if (e.compareTo(node.e) < 0){
            add(node.left, e);
        }else if (e.compareTo(node.e) > 0){
            add(node.right, e);
        }
    }
}
