package java.com.krasnyanskii;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class CalculatorTest {
    @Test
    public void SimpleTest(){
        Calculator calculator = new Calculator();
        String expr = "sin + - 1 2 1";
        calculator.evaluate(expr);
    }


}
