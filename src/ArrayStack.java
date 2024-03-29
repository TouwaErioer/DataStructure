public class ArrayStack<E> implements Stack<E> {

    private Array<E> array;

    public ArrayStack(int capacity){
        array = new Array<>(capacity);
    }

    public ArrayStack(){
        this(10);
    }

    @Override
    public void pull(E e) {
        array.addLast(e);
    }

    @Override
    public E pop() {
        return array.removeLast();
    }

    @Override
    public E peek() {
        return array.getLast();
    }

    @Override
    public int getSize() {
        return array.getSize();
    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Stack:[");
        for(int i=0;i<array.getSize();i++){
            stringBuilder.append(array.get(i));
            if(i != array.getSize()-1){
                stringBuilder.append(",");
            }
        }
        stringBuilder.append("]top");
        return stringBuilder.toString();
    }
}
