public interface Stack<E> {

    void pull(E e);
    E pop();
    E peek();
    int getSize();
    boolean isEmpty();
}
