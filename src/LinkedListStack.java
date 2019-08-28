public class LinkedListStack<E> implements Stack<E> {

    private LinkedList<E> linkedList;

    public LinkedListStack(){
        linkedList = new LinkedList<>();
    }

    @Override
    public void pull(E e) {
        linkedList.addFirst(e);
    }

    @Override
    public E pop() {
        return linkedList.removeFirst();
    }

    @Override
    public E peek() {
        return linkedList.getFirst();
    }

    @Override
    public int getSize() {
        return linkedList.getSize();
    }

    @Override
    public boolean isEmpty() {
        return linkedList.isEmpty();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Stack: top ");
        stringBuilder.append(linkedList);
        return stringBuilder.toString();
    }

    public static void main(String[] args){
        Stack<Integer> stack = new LinkedListStack<>();
        for(int i=0;i<10;i++){
            stack.pull(i);
            System.out.println(stack);
        }
    }
}
