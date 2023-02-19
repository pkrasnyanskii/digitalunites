package nsu.fit.krasnyanskii;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StackTest {
    Stack<Integer> stack = new Stack<Integer>(10);

    @Test
    void testForPushAndPop(){
        stack.push(9);
        assertEquals(9, stack.pop());
    }

    @Test
    void testForArrayPushAndPop(){
        Integer[] arr = {1, 4, 7, 90};
        Integer afterPop = 1;
        stack.pushStack(arr);
        assertEquals(afterPop, stack.popStack(3));
    }

    @Test
    void testForCount(){
        stack.push(10);
        stack.push(17);
        stack.push(23);
        int answer = stack.count();
        assertEquals(3, answer);
    }

    @Test
    public void testPushStackOverflow() {
        Stack<Integer> stack = new Stack<>(3);
        stack.push(1);
        stack.push(2);
        stack.push(3);

        assertThrows(StackOverflowError.class, () -> stack.push(4));
    }

    @Test
    public void testPopEmptyStack() {
        Stack<Integer> stack = new Stack<>(3);

        assertThrows(IndexOutOfBoundsException.class, stack::pop);
    }

    @Test
    public void testPopStackUnderflow() {
        Stack<Integer> stack = new Stack<>(3);
        stack.push(1);

        assertThrows(IndexOutOfBoundsException.class, () -> stack.popStack(2));
    }
}
