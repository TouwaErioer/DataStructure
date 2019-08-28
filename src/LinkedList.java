public class LinkedList<E> {

    private class Node<E>{
        public E e;
        public Node next;

        public Node(E e,Node next){
            this.e = e;
            this.next = next;
        }

        public Node(E e){
            this(e,null);
        }

        public Node(){
            this(null,null);
        }

        public E getE() {
            return e;
        }

        @Override
        public String toString() {
            return e.toString();
        }
    }

    private Node dummyHead;
    private int size;

    public LinkedList(){
        dummyHead = new Node(null,null);
        size = 0;
    }

    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public void add(int index,E e){

        if(index < 0 || index>size)
            throw new IllegalArgumentException("Add failed,Illegal index");

        // 把虚拟节点dummyHead赋值给新建节点prev，把他移动到需要插入的位置前一个的位置
        // 把要插入节点的下一个节点设为prev的下一个节点
        // prev的下一个节点为要插入的节点
        Node prev = dummyHead;
        for(int i = 0;i<index;i++)
            prev = prev.next;

        // Node node = new Node(e);
        // node.next = prev.next;
        // prev.next = node;

        prev.next = new Node(e,prev.next);
        size ++;
    }

    public void addFirst(E e){
        add(0,e);
    }

    public void addLast(E e){
        add(size,e);
    }

    public E get(int index){

        if(index < 0 || index >size)
            throw new IllegalArgumentException("Gew failed,Illegal index");
        Node cur = dummyHead.next;
        for (int i=0;i<index;i++)
            cur = cur.next;
        return (E) cur.e;
    }

    public E getFirst(){
        return get(0);
    }

    public E getLast(){
        return get(size);
    }

    public void set(int index,E e){

        if(index < 0 || index > size)
            throw new IllegalArgumentException("Set failed,Illegal index");
        Node cur = dummyHead.next;
        for(int i=0;i<index;i++)
            cur = cur.next;
        cur.e = e;
    }

    public boolean contains(E e){
        Node cur = dummyHead;
        while(cur != null){
            if(cur.equals(e))
                return true;
            cur = cur.next;
        }
        return false;
    }


     //思路
     //找到要删除的前一位元素的节点cur，要删除的元素为ret(cur.next)，把cur的next指向ret的下一位元素：cur.next=cur,next.next
     //把ret的next指向null，脱离链表，自动回收
    public E remove(int index){

        if(index < 0 || index >= size)
            throw new IllegalArgumentException("Remove failed,illegal index");
        Node cur = dummyHead;
        for(int i=0;i<index;i++)
            cur = cur.next;
        Node ret = cur.next;
        cur.next = ret.next;
        ret.next = null;
        size --;
        return (E) ret.e;
    }

    public E removeFirst(){
        return remove(0);
    }

    public E removeLast(){
        return remove(size-1);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        Node cur = dummyHead.next;
        while(cur != null){
            stringBuilder.append(cur+"->");
            cur = cur.next;
        }
        stringBuilder.append("null");
        return stringBuilder.toString();
    }

    public static void main(String[] args){
        LinkedList<Integer> linkedList = new LinkedList();
        for(int i=0;i<10;i++){
            linkedList.addFirst(i);
            System.out.println(linkedList);
        }

        linkedList.remove(2);
        System.out.println(linkedList);
        linkedList.removeFirst();
        System.out.println(linkedList);
        linkedList.removeLast();
        System.out.println(linkedList);
    }
}
