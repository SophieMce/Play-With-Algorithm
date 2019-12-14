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

    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    //向二分搜索树中添加新的元素e
    public void add(E e){
//        if (root == null){
//            root = new Node(e);
//            size ++;
//        }
//        else
//            add(root, e);
        root = add(root, e);
    }
    //向以node为根节点的二分搜索树中插入元素e，递归算法
    //返回插入新节点后二分搜索树的根
    //缩小为在一棵最小的二分搜索树中的解决方法，理清楚三种情况
    private Node add(Node node, E e){
        if (node == null){
            size ++;
            return new Node(e);
        }

        if (e.compareTo(node.e) < 0)
            node.left = add(node.left, e);
        else if (e.compareTo(node.e) > 0)
            node.right = add(node.right, e);
        return node;
    }

    //看二分搜索树中是否包含元素e
    public boolean contains(E e){
        return contains(root, e);
    }

    //看以node为根的二分搜索树中是否包含元素e，递归算法
    private boolean contains(Node node, E e){
        if (node == null){
            return false;
        }

        if (e.compareTo(node.e) == 0){
            return true;
        }else if (e.compareTo(node.e) < 0){
            return contains(node.left, e);
        }else {
            return contains(node.right, e);
        }
    }

    //二分搜索树的前序遍历
    public void preOrder(){
        preOrder(root);
    }
    //前序遍历以node为根的二分搜索树，递归算法
    private void preOrder(Node node){
        if (root == null)
            return;
        System.out.println(node.e);
        preOrder(node.left);
        preOrder(node.right);
    }

    //二分搜索树的中序遍历
    public void inOrder(){
        inOrder(root);
    }
    //中序遍历以node为根的二分搜索树，递归算法
    private void inOrder(Node node){
        if (root == null)
            return;

        inOrder(node.left);
        System.out.println(node.e);
        inOrder(node.right);
    }

    //二分搜索树的后序遍历
    public void postOrder(){
        postOrder(root);
    }
    //后序遍历以node为根二分搜索树，递归算法
    private void postOrder(Node node){
        if (root == null)
            return;

        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.e);
    }

    //从二分搜索树中删除元素为e节点
    public void remove(E e){
        root = remove(root, e);
    }

    //寻找二分搜索树的最小元素
    public E minimum(){
        if (size == 0)
            throw new IllegalArgumentException("BST is Empty");

        Node minNode = minimum(root);
        return minNode.e;
    }
    //返回以node为根的二分搜索树的最小值所在的节点
    private Node minimum(Node node){
        if (node.left == null)
            return node;
        return minimum(node.left);
    }

    //寻找二分搜索树的最大元素
    public E maximum(){
        if (size == 0)
            throw new IllegalArgumentException("BST is Empty");
        return maximum(root).e;
    }
    //返回以node为根的二分搜索树的最大值所在的节点
    private Node maximum(Node node){
        if (node.right == null)
            return  node;
        return maximum(node.right);
    }

    //从二分搜索树中删除最小值所在的节点,返回最小值
    public E removeMin(){
        E ret = minimum();
        root = removeMin(root);
        return ret;
    }

    //删除掉以node为根的二分搜索树中的最小节点
    //返回删除节点后新的二分搜索树的根
    private Node removeMin(Node node){
        if (node.left == null){
            Node rightNode = node.right;
            node.right = null;
            size --;
            return rightNode;
        }
        node.left = removeMin(node.left);
        return node;
    }

    //从二分搜索树中删除最大值所在的节点，返回最小值
    public E removeMax(){
        E ret = maximum();
        root = removeMax(root);
        return ret;
    }

    //删除掉以node为根的二分搜索树中的最小节点
    //返回删除节点后新的二分搜索树的根
    private Node removeMax(Node node){
        if (node.right == null){
            Node leftNode = node.left;
            node.left = null;
            size --;
            return leftNode;
        }
        node.right = removeMax(node.right);
        return node;
    }

    //删除掉以node为根的二分搜索树中值为e的节点，递归算法
    //返回删除节点后新的二分搜索树的根
    private Node remove(Node node, E e){
        if (node == null)
            return null;
        if (e.compareTo(node.e) < 0){
            node.left = remove(node.left, e);
            return node;
        }
        else if (e.compareTo(node.e) > 0){
            node.right = remove(node.right, e);
            return node;
        }
        else { //e.compareTo(node.e) = 0
            //待删除节点左子树为空的情况下
            if (node.left == null){
                Node rightNode = node.right;
                size --;
                node.right = null;
                return rightNode;
            }
            //待删除节点右子树为空的情况下
            if (node.right == null){
                Node leftNode = node.left;
                size --;
                node.left = null;
                return leftNode;
            }
            //待删除节点左右子树均不为空的情况下
            //找到比待删除节点大的最小节点，用此节点顶替待删除的节点的位置
            Node successor = minimum(node.right);
            successor.right = removeMin(node.right);
            successor.left = node.left;

            node.left = node.right = null;
            return successor;
        }
    }
}
