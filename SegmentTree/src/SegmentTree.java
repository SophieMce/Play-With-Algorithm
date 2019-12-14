/**
 * 线段树的构成
 * 在将线段树看作是满二叉树的情况下，使用数组进行存储其中元素
 * @param <E>
 */
public class SegmentTree<E> {

    private E[] tree;

    private E[] data;

    private Merger<E> merger;

    public SegmentTree(E[] arr, Merger<E> merger){
        this.merger= merger;

        data = (E[]) new Object[arr.length];
        for (int i = 0; i < arr.length; i++) {
            data[i] = arr[i];
        }
        //创建一棵线段树
        tree = (E[])new Object[4 * arr.length];
        buildSegmentTree(0, 0, data.length - 1);
    }

    /**
     * 利用递归创建线段树,要创建线段树，需要判断极端情况，之后利用其左孩子和右孩子分别创建左右子树
     * @param treeIndex 当前需要创建线段树的节点的索引
     * @param l 最左边的索引
     * @param r 最右边的索引
     */
    private void buildSegmentTree(int treeIndex, int l, int r){
        //极端情况
        if (l == r){
            tree[treeIndex] = data[l];
            return;
        }

        int leftChildIndex = leftChild(treeIndex);
        int rightChildIndex = rightChild(treeIndex);

        int mid = l + (r - l) / 2;  //找寻其区间的中间索引
        buildSegmentTree(leftChildIndex, l, mid);
        buildSegmentTree(rightChildIndex, mid + 1, r);
        //最终的treeIndex处的内容应该依据具体业务，但应让使用者可在大多数场景下能够运用
        tree[treeIndex] = merger.merge(tree[leftChildIndex], tree[rightChildIndex]);
    }

    public int getSize(){
        return data.length;
    }

    public E get(int index){
        if (index < 0 || index >= data.length)
            throw new IllegalArgumentException("index is illegal.");
        return data[index];
    }

    //返回完全二叉树表示的数组中，一个索引所表示的元素的左孩子的索引
    private int leftChild(int index){
        return index * 2 + 1;
    }

    //返回完全二叉树表示的数组中，一个索引所表示的元素的右孩子的索引
    private int rightChild(int index){
        return index * 2 + 2;
    }

    //返回queryL和queryR的值
    public E query(int queryL, int queryR){
        if (queryL < 0 || queryL >= data.length || queryR < 0 || queryR >= data.length || queryL > queryR)
            throw new IllegalArgumentException("index is illegal.");
        return query(0, 0, data.length - 1, queryL, queryR);
    }

    //在以treeIndex为根的线段树中[l...r]的区间中，查询区间[queryL...queryR]的值
    private E query(int treeIndex, int l, int r, int queryL, int queryR){
        if (l == queryL && r == queryR){
            return tree[treeIndex];
        }
        int leftChildIndex = leftChild(treeIndex);
        int rightChildIndex = rightChild(treeIndex);
        int mid = l + (r - l) / 2;

        if (queryL >= mid + 1)
            return query(rightChildIndex, mid + 1, r, queryL, queryR);
        else if (queryR <= mid)
            return query(leftChildIndex, l, mid, queryL, queryR);

        E leftResult = query(leftChildIndex, l, mid, queryL, mid);
        E rightResult = query(rightChildIndex, mid + 1, r, mid + 1, queryR);

        return merger.merge(leftResult, rightResult);
    }

    //更新操作，将index处的值更新为e
    public void set(int index, E e){
        if (index < 0 || index >= data.length)
            throw new IllegalArgumentException("index is illegal.");

        data[index] = e;
        set(0, 0, data.length - 1, index, e);
    }

    //将以treeIndex为根的线段树中区间为[l...r]中索引为index的节点值更新为e
    private void set(int treeIndex, int l, int r, int index, E e){
        if (l == r){
            tree[treeIndex] = e;
            return;
        }

        int mid = l + (r - l) / 2;
        int leftChildIndex = leftChild(treeIndex);
        int rightChildIndex = rightChild(treeIndex);
        if (index >= mid + 1)
            set(rightChildIndex, mid + 1, r, index, e);
        else if (index <= mid)
            set(leftChildIndex, l, mid, index, e);
        //改变某个节点的值后，其对应的父节点，以及父节点的父节点，直至根节点都会收到影响，所以需要merger操作
        tree[treeIndex] = merger.merge(tree[leftChildIndex], tree[rightChildIndex]);
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append("[");
        for (int i = 0; i < tree.length; i++) {
            if (tree[i] != null)
                res.append(tree[i]);
            else
                res.append("null");

            if (i != tree.length - 1)
                res.append(", ");
        }
        res.append("]");
        return res.toString();
    }
}
