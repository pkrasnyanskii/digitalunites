package nsu.fit.krasnyanskii;

/**
 * Stack class implements stack data structure
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

    /**
     * Returns current stack size
     * @return is a stack size
     */
    public int count() {
        return top + 1;
    }

    /**
     * Pushes an element to the stack
     * @param x is a value of element
     */
    public void push(T x){
        if (top == capacity - 1) {
            System.out.println("Stack OverFlow");
            System.exit(1);
        }
        arr[++top] = x;
    }

    /**
     * Pushes an array of elements to the stack
     * @param list is an array of elements
     */
    public void pushStack(T[] list) {
        if (list.length + count() > capacity) {
            System.out.println("Stack OverFlow");
            System.exit(1);
        }
        for(int i=0; i < list.length; i++){
            arr[++top] = list[i];
        }
    }

    /**
     * Pops top element from the stack and returns new top element value
     * @return - value of the element
     */
    public T pop() {
        if (top == -1) {
            System.out.println("Stack empty");
            System.exit(1);
        }
        return arr[top--];
    }

    /**
     * Pops an array of elements from the stack with size n
     * @param n - size of array
     * @return - returns current top element value
     */
    public T popStack(int n) {
        if (count() - n < 0) {
            System.out.println("Number of elements to pop is greater or equals to number of stack values");
            System.exit(1);
        }
        return arr[top-n];
    }
}
