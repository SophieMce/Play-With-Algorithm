package Queue;

import java.util.Objects;

public class LoopQueue<E> implements Queue<E> {
    private E[] data;
    private int head, tail;
    private int size;

    public LoopQueue(int capacity){
        data = (E[]) new Object[capacity + 1];
        head = 0;
        tail = 0;
        size = 0;
    }

    public LoopQueue(){
        this(10);
    }

    public int getCapacity(){
        return data.length - 1;
    }

    @Override
    public boolean isEmpty(){
        return head == tail;
    }

    @Override
    public int getSize(){
        return size;
    }

    @Override
    public E dequeue(){
        if (isEmpty())
            throw new IllegalArgumentException("Cannot dequeue from an empty queue!");
        E ret = data[head];
        data[head] = null;
        head = (head + 1) % data.length;
        size--;
        //缩容
        if (size == getCapacity() / 4 && getCapacity() / 2 != 0)
            resize(getCapacity() / 2);
        return ret;
    }

    @Override
    public E getFront(){
        if (isEmpty())
            throw new IllegalArgumentException("Queue is empty");
        return data[head];
    }

    @Override
    public void enqueue(E e){
        if ((tail + 1) % data.length == head)
            resize(getCapacity() * 2);
        data[tail] = e;
        tail = (tail + 1) % data.length;
        size++;
    }

    private void resize(int newCapacity){
        E[] newData = (E[])new Object[newCapacity + 1];
        for (int i = 0; i < size; i++){
            newData[i] = data[(1 + head) % data.length];
        }

        data = newData;
        head = 0;
        tail = size;
    }

    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append(String.format("Queue:size = %d, capacity = %d\n", size, getCapacity()));
        res.append("front [");
        for (int i = head; i != tail; i = (i + 1) % data.length) {
            res.append(data[i]);
            if ((i + 1) % data.length != tail)
                res.append(", ");
        }
        res.append("] tail");
        return res.toString();
    }

    public static void main(String[] args) {
        LoopQueue<Integer> loopQueue = new LoopQueue<Integer>();
        for (int i = 0; i < 10; i++) {
            loopQueue.enqueue(i);
            System.out.println(loopQueue);

            if (i % 3 == 2){
                loopQueue.dequeue();
                System.out.println(loopQueue);
            }
        }
    }
}
