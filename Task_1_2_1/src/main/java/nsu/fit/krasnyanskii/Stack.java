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
     * @throws StackOverflowError when stack is full
     */
    public void push(T x) throws StackOverflowError {
        if (top == capacity - 1) {
            throw new StackOverflowError("Stack overflow occurred");
        }
        arr[++top] = x;
    }

    /**
     * Pushes an array of elements to the stack
     * @param list is an array of elements
     * @throws StackOverflowError when stack is full
     */
    public void pushStack(T[] list) throws StackOverflowError {
        if (list.length + count() > capacity) {
            throw new StackOverflowError("Stack overflow occurred");
        }
        for (int i = 0; i < list.length; i++) {
            arr[++top] = list[i];
        }
    }

    /**
     * Pops top element from the stack and returns new top element value
     * @return - value of the element
     * @throws IndexOutOfBoundsException when stack is empty
     */
    public T pop() throws IndexOutOfBoundsException {
        if (top == -1) {
            throw new IndexOutOfBoundsException("Stack underflow occurred");
        }
        return arr[top--];
    }

    /**
     * Pops an array of elements from the stack with size n
     * @param n - size of array
     * @return - returns current top element value
     * @throws IndexOutOfBoundsException when stack does not contain enough elements to pop
     */
    public T popStack(int n) throws IndexOutOfBoundsException {
        if (count() - n < 0) {
            throw new IndexOutOfBoundsException("Stack does not contain enough elements to pop");
        }
        return arr[top - n];
    }
}
