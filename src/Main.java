public class Main {

    public static void main(String[] args) {
        ArrayStack<Integer> stack = new ArrayStack<>();
        for(int i=0;i<10;i++){
            stack.pull(i);
            System.out.println(stack);
        }
    }
}
