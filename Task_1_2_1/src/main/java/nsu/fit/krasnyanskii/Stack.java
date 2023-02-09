package nsu.fit.krasnyanskii;

/** Stack class implements stack data structure
 *
 * @param <T> Array of given data type
 */
public class Stack<T> {
    private T[] arr;
    private int top;
    private int capacity;

    Stack(int size) {
        arr = (T[]) new Object[size];
        capacity = size;
        top = -1;
    }

    public int count() {
        return top + 1;
    }

    public void push(T x){
        if (top == capacity - 1) {
            System.out.println("Stack OverFlow");
            System.exit(1);
        }
        arr[++top] = x;
    }

    public void pushStack(T[] list) {
        if (list.length + count() > capacity) {
            System.out.println("Stack OverFlow");
            System.exit(1);
        }
        for(int i=0; i < list.length; i++){
            arr[++top] = list[i];
        }
    }

    public T pop() {
        if (top == -1) {
            System.out.println("Stack empty");
            System.exit(1);
        }
        return arr[top--];
    }

    public T popStack(int n) {
        if (count() - n < 0) {
            System.out.println("Number of elements to pop is greater or equals to number of stack values");
            System.exit(1);
        }
        return arr[top-n];
    }
}
