/**
 * 最大堆：每个节点的值都必须大于等于子节点的值
 * @param <E>
 */
public class MaxHeap<E extends Comparable<E>> {
    private Array<E> data;

    public MaxHeap(int capacity){
        data = new Array<>(capacity);
    }

    public MaxHeap(){
        data = new Array<>();
    }

    //传入一个数组，实现堆
    public MaxHeap(E[] arr){
        data = new Array<>(arr);
        //从最后一个叶子节点的父节点处开始遍历，实现最大堆的性质，进行下沉操作
        for (int i = parent(arr.length - 1); i >= 0; i--) {
            siftDown(i);
        }
    }
    //返回堆中堆元素个数
    public int getSize(){
        return data.getSize();
    }

    //返回一个布尔值，表示堆中是否为空
    public boolean isEmpty(){
        return data.isEmpty();
    }

    //返回完全二叉树堆数组表示中，一个索引所表示的元素的父亲节点的索引
    private int parent(int index){
        if (index == 0)
            throw new IllegalArgumentException("index-0 doesn't have parent child.");
        return (index - 1) / 2;
    }

    //返回完全二叉树堆数组表示中，一个索引所表示的元素的左孩子节点的索引
    private int leftChild(int index){
        return (index * 2) + 1;
    }

    //返回完全二叉树堆数组表示中，一个索引所表示的元素的右孩子节点的索引
    private int rightChild(int index){
        return (index * 2) + 2;
    }

    //向堆中添加元素
    public void add(E e){
        data.addLast(e);
        siftUp(data.getSize() - 1);
    }

    //上浮动作，当前节点的值比父节点的值大，就将此节点与父节点交换位置
    private void siftUp(int k){
        while (k > 0 && data.get(k).compareTo(data.get(parent(k))) > 0){
            data.swap(k, parent(k));
            k = parent(k);
        }
    }

    //看堆中的最大元素
    public E findMax(){
        if (data.getSize() == 0)
            throw new IllegalArgumentException("Cannot find a maximum num when heap is empty.");
        return data.get(0);
    }

    //取出堆中的最大元素
    //先找出最大值，然后将最大值和最后一个节点的值进行交换，接着删除最后一个值，最后进行下沉操作，恢复最大堆的特性
    public E extractMax(){
        E ret = findMax();
        data.swap(0, data.getSize() - 1);
        data.removeLast();
        siftDown(0);
        return ret;
    }

    //下沉操作
    private void siftDown(int k){
        //当前节点有左孩子时，才进行下沉操作
        while (leftChild(k) < data.getSize()){
            int j = leftChild(k);  //此循环中，data[k]和data[j]交换位置
            //data[j]时leftChild和rightChild中的较大值
            if (j + 1 < data.getSize() && data.get(j + 1).compareTo(data.get(j)) > 0)
                j ++;
            if (data.get(k).compareTo(data.get(j)) >= 0)
                break;
            data.swap(k, j);
            k = j;
        }
    }

    //取出堆中的最大值，并且替换成元素e
    public E replace(E e){
        E ret = findMax();
        data.set(0, e);
        siftDown(0);
        return ret;
    }
}
