//第一版UnionFind
public class UnionFind1 implements UF {

    private int[] id;  //第一版本质是一个数组

    public UnionFind1(int size){
        id = new int[size];
        //初始化，每一个id指向自己，没有合并的元素
        for (int i = 0; i < size; i++) {
            id[i] = i;
        }
    }

    @Override
    public int getSize(){
        return id.length;
    }

    //查找p所对应的集合编号
    //O(1)复杂度
    private int find(int p){
        return id[p];
    }

    @Override
    public boolean isConnected(int p, int q){
        return id[p] == id[q];
    }

    //合并元素p和q所属的集合
    @Override
    public void unionElements(int p, int q){
        int pID = id[p];
        int qID = id[q];
        //二者已属于同一集合，无需操作
        if (pID == qID)
            return;

        //合并操作，需要遍历一遍所有元素，将两个元素所属集合编号合并
        for (int i = 0; i < id.length; i++) {
            if (id[i] == pID)
                id[i] = qID;
        }
    }
}
