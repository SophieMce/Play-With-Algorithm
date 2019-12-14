public class LinkedListSet<E> implements Set<E> {
    private LinkedList<E> list;

    public LinkedListSet(){
        list = new LinkedList<>();
    }

    @Override
    public int getSize(){
        return list.getSize();
    }

    @Override
    public boolean isEmpty(){
        return list.isEmpty();
    }

    @Override
    public boolean contains(E e){
        return list.contains(e);
    }

    @Override
    //Set中不可包含重复的元素
    public void add(E e){
        if(!list.contains(e)){
            list.addFirst(e);    //链表在头部添加元素e是O(1)级别,且此处调用的链表有头指针，无尾指针
        }
    }

    @Override
    public void remove(E e){
        list.removeElement(e);
    }
}
