// 动态数组
public class Array<E> {

    // [0、1、2、3、4、5、size、x、x、x]
    // size为5，capacity为10，x为空位
    // size描述现已经装的元素个数，size为待添加元素的位置
    private E[] data;
    private int size;

    /**
     *
     * @param capacity 容量，描述一共可以装的元素个数（包括空位）
     */
    public Array(int capacity){
        data = (E[])new Object[capacity];
    }

    public Array(){
        this(10);
    }

    public int getSize(){
        return size;
    }

    public int getCapacity(){
        return data.length;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public void add(int index,E e){

        if(index < 0 || index > size)
            throw new IllegalArgumentException("Add failed,Require index < 0 || index > size");

        // 当已装个数等于能装个数（装满）的时候扩容为原来容量的2倍
        if(size == data.length)
            resize(2*data.length);

        // 把元素向后移动一位
        for(int i = size -1;i >= index;i--){
            data[i+1] = data[i];
        }

        //覆盖原来的元素
        data[index] = e;
        size++;
    }

    public void addLast(E e){
        add(size,e);
    }

    public void addFirst(E e){
        add(0,e);
    }

    /**
     *
     * @param index
     * @return Element
     */
    public E remove(int index){
        if(index < 0 || index>=size)
            throw new IllegalArgumentException("Remove failed,Index is illegal");
        E ret = data[index];
        // 将待删除元素的后面元素每个向前移动一位，覆盖待删除元素（相当于删除）
        for(int i = index+1;i<size;i++)
            data[i-1] = data[i];
        size--;
        // [0、1、2、3、4、5、size、x、x、x]
        // remove(2)
        // [0、2、3、4、5、y、size、x、x、x]
        // size--;
        // [0、2、3、4、5、size、x、x、x、x]
        // data[size] != null
        // 手动把data[size]设null，Java会自动垃圾回收
        data[size] = null;

        // 当已装元素数量等于总容量的四分之一的时候，缩容二分之一
        if(size == data.length/4 && data.length/2 != 0)//防止复杂度震荡
            resize(data.length/2);
        return ret;
    }

    public E removeFirst(){
        return remove(0);
    }

    public E removeLast(){
        return remove(size-1);
    }

    public boolean removeElement(E e){
        int index = find(e);
        if(index != -1) {
            remove(index);
            return true;
        }
        return false;
    }

    public void set(int index,E e){
        if(index < 0 || index>=size)
            throw new IllegalArgumentException("Get failed,Index is illegal");
        data[index] = e;
    }

    public E get(int index){
        if(index < 0 || index>=size)
            throw new IllegalArgumentException("Get failed,Index is illegal");
        return data[index];
    }

    public E getLast(){
        return get(size-1);
    }

    public E getFirst(){
        return get(0);
    }

    public boolean contains(E e){
        for(int i = 0;i<size;i++){
            if(data[i].equals(e))
                return true;
        }
        return false;
    }

    /**
     *
     * @param e Element
     * @return index 不存在返回-1
     */
    public int find(E e){
        for(int i = 0;i<size;i++){
            if(data[i].equals(e))
                return i;
        }
        return -1;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("Array: size = %d,capacity = %d\n",size,data.length));
        stringBuilder.append("[");
        for(int i = 0;i<size;i++) {
            stringBuilder.append(data[i]);
            if (i != size-1)
                stringBuilder.append(",");
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    private void resize(int newCapacity){
        E[] newArray = (E[]) new Object[newCapacity];
        for(int i=0;i<size;i++)
            newArray[i] = data[i];
        data = newArray;
    }
}