import java.util.Random;

public class LoopQueue<E> implements Queue<E> {

    /**
     * front 队首的位置
     * tail 下一个入队元素应该存储的位置
     * size 存在的元素个数
     */
    private E[] data;
    private int front,tail;
    private int size;


    /**
     *
     * @param capacity 初始化队列能够存储的个数
     * 当队列为空的时候，front和tail都应该指向0
     */
    public LoopQueue(int capacity){
        data = (E[])new Object[capacity];
        front = 0;
        tail = 0;
        size = 0;
    }

    public LoopQueue(){
        this(10);
    }

    /**
     *当tail+1=front的时候，表示队列为满，因为循环队列所以对队数组长度求余：(tail+1)%data.length=front
     *入队操作，tail+1，因为循环队列所以对队数组长度求余:(tail+1)%data.length
     *front=0,tail=9,data.length=10->(9+1)%10=0
     *front=1,tail=0,data.length=10-> (0+1)%10=1
     *front=2,tail=1,data.length=10-> (1+1)%10=2
     */
    @Override
    public void enqueue(E e) {
        if((tail + 1) % data.length == front)
            resize(getCapacity() * 2);
        data[tail] = e;
        tail = (tail + 1) % data.length;
        size ++;
    }

    @Override
    public E dequeue() {
        if(isEmpty())
            throw new IllegalArgumentException("Cannot dequeue from an empty queue");
        E ret = data[front];
        data[front] = null;
        front = (front + 1) % data.length;
        size --;
        if(size == getCapacity() / 4 && getCapacity() / 2 != 0)
            resize(getCapacity() / 2);
        return ret;
    }

    @Override
    public E getFront() {
        if(isEmpty())
            throw new IllegalArgumentException("Queue is empty");
        return data[front];
    }

    private void resize(int newCapacity){
        E[] newData = (E[])new Object[newCapacity +1];
        for(int i = 0 ;i < size ; i ++)
            newData[i] = data[(i + front) % data.length];
        data = newData;
        front = 0;
        tail = size;
    }

    public int getCapacity(){
        return data.length-1;
    }

    @Override
    public boolean isEmpty() {
        return front == tail;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("LoopQueue: size = %d,capacity = %d\n",size,getCapacity()));
        stringBuilder.append("front [");
        for(int i = front ; i != tail ; i = (i + 1) % data.length) {
            stringBuilder.append(data[i]);
            if ((i + 1) % data.length != tail)
                stringBuilder.append(",");
        }
        stringBuilder.append("] tail");
        return stringBuilder.toString();
    }

    public static double testQueue(Queue<Integer> queue,int opCount){
        long startTime = System.nanoTime();

        Random random = new Random();
        for(int i = 0; i<opCount; i++)
            queue.enqueue(random.nextInt(Integer.MAX_VALUE));
        for(int i = 0; i<opCount; i++)
            queue.dequeue();
        long endTime = System.nanoTime();

        return (endTime - startTime) / 1000000000.0;
    }

    public static void main(String[] args){
        int opCount = 100000;
        System.out.println("ArrayQueue: "+testQueue(new ArrayQueue<>(),opCount)+" s");
        System.out.println("LoopQueue: "+testQueue(new LoopQueue<>(),opCount)+" s");
    }
}

