package Stack;

public interface Stack<E> {
    void push(E e);
    E peek();
    E pop();
    int getSize();
    boolean isEmpty();
}
