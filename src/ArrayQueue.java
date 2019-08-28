public class ArrayQueue<E> implements Queue<E> {

    private Array<E> array;

    public ArrayQueue(int capacity){
        array = new Array<>(capacity);
    }

    public ArrayQueue(){
        this(10);
    }

    @Override
    public void enqueue(E e) {
        array.addLast(e);
    }

    @Override
    public E dequeue() {
        return array.removeFirst();
    }

    @Override
    public E getFront() {
        return array.getFirst();
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
        stringBuilder.append("Queue: front[");
        for(int i=0;i<getSize();i++){
            stringBuilder.append(array.get(i));
            if(i != getSize()-1){
                stringBuilder.append(",");
            }
        }
        stringBuilder.append("] tail");
        return stringBuilder.toString();
    }

    public static void main(String[] args){
        Queue<Integer> queue = new ArrayQueue<>();
        for(int i = 0;i<10;i++){
            queue.enqueue(i);
            System.out.println(queue);
        }
        queue.dequeue();
        System.out.println(queue);
    }
}
