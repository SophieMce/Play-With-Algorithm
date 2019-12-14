public class UnionFInd2 implements UF {
    //parent[i]表示的是第一个元素所指向的父节点
    private int[] parent;

    public UnionFInd2(int size){

        parent = new int[size];
        for (int i = 0; i < size; i++) {
            parent[i] = i;
        }
    }

    @Override
    public int getSize(){
        return parent.length;
    }

    //查找过程，查找p对应的(根节点的)集合编号
    //O(h)复杂度，h为树的高度
    public int find(int p){
        if (p < 0 || p >= parent.length)
            throw new IllegalArgumentException("p is out of bound");
        while (p != parent[p])
            p = parent[p];
        return p;
    }

    //查看p，q是否同属于同一个集合
    //O(h)为复杂度，h为树的高度
    @Override
    public boolean isConnected(int p, int q){
        return find(p) == find(q);
    }

    //合并集合p和q的所属的集合
    //O(h)为复杂度，h为树的高度
    @Override
    public void unionElements(int p, int q){
        int pRoot = find(p);
        int qRoot = find(q);

        if (pRoot == qRoot)
            return;

        parent[pRoot] = qRoot;
    }

}
