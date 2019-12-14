package Queue;

public class Array<E> {
    private E[] data;
    private int size;
    //有参的构造函数
    public Array(int capacity){
        data = (E[])new Object[capacity];
        size = 0;  //初始有效元素个数为0
    }
    //无参构造函数
    public Array(){
        this(10);
    }
    //获取数组的容量
    public int getCapacity(){
        return data.length;
    }
    //获取数组的有效元素个数
    public int getSize(){
        return size;
    }
    //数组是否为空
    public boolean isEmpty(){
        return size == 0;
    }
    //向数组中末尾插入一个元素
    public void addLast(E e){
        addElement(size, e);
    }
    //在所有元素前添加一个元素
    public void addFirst(E e){
        addElement(0, e);
    }
    //在index位置插入一个新元素e
    public void addElement(int index, E e){
        if (size == data.length)
            resize(2 * data.length);
        if (index < 0 || index > size)
            throw new IllegalArgumentException("Add failed, index is illegal.");
        for (int i = size - 1; i >= index; i--){
            data[i + 1] = data[i];
        }
        data[index] = e;
        size++;
    }
    //获取index索引的位置的元素
    public E get(int index){
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("get failed, index is illegal.");
        return data[index];
    }
    //获取数组中最后一个元素
    public E getLast(){
        return get(size - 1);
    }
    //获取数组中第一个元素
    public E getFirst(){
        return get(0);
    }
    //修改index索引位置的元素为e
    public void set(int index, E e){
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("set failed, index is illegal.");
        data[index] = e;
    }
    //查找数组中是否有元素e
    public boolean contains(E e){
        for (int i = 0; i < size; i++) {
            if (data[i] == e)
                return true;
        }
        return false;
    }
    //查找数组中的元素e，若不存在，返回-1
    public int find(E e){
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e))
                return i;
        }
        return -1;
    }
    //删除第一个元素,并且返回元素
    public E removeFirst(){
        return remove(0);
    }
    //删除最后一个元素,并且返回元素
    public E removeLast(){
        return remove(size - 1);
    }
    //从数组中删除元素e
    public void removeElement(E e){
        int index = find(e);
        if (index != -1){
            remove(index);
        }
    }
    //从数组中删除索引为index的元素，并且返回元素
    public E remove(int index){
        if (index < 0 || index >= size){
            throw new IllegalArgumentException("remove failed.index is illegal.");
        }
        E ret = data[index];
        for (int i = index + 1; i < size; i++) {
            data[i - 1] = data[i];
        }
        size--;
        data[size] = null;  //非必要的，loitering object != memory leak

        if (size == data.length / 4 && data.length / 2 != 0)
            resize(data.length / 2);
        return ret;
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append(String.format("Array: size = %d, capacity = %d\n", size, data.length));
        res.append('[');
        for (int i = 0; i < size; i++) {
            res.append(data[i]);
            if (i != size - 1)
                res.append(", ");
        }
        res.append(']');
        return res.toString();
    }
    //将数组的容量变成newCapacity大小
    private void resize(int newCapacity){
        E[] newData = (E[])new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }
}

