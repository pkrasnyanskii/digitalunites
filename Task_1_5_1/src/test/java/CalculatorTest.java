import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

class CalculatorTest {

    @Test
    public void additionTest() throws Exception {
        Calculator exp = new Calculator(Arrays.asList("+", "1", "2"));
        Assertions.assertEquals(3, exp.calculate());
    }

    @Test
    public void subtractionTest() throws Exception {
        Calculator exp = new Calculator(Arrays.asList("-", "1", "2"));
        Assertions.assertEquals(-1, exp.calculate());
    }

    @Test
    public void multiplyTest() throws Exception {
        Calculator exp = new Calculator(Arrays.asList("*", "4", "2"));
        Assertions.assertEquals(8, exp.calculate());
    }

    @Test
    public void divisionTest() throws Exception {
        Calculator exp = new Calculator(Arrays.asList("/", "2", "0"));
        Exception thrown = Assertions.assertThrows(Exception.class, exp::calculate);
        Assertions.assertEquals("The zero division is not defined", thrown.getMessage());

        exp = new Calculator(Arrays.asList("/", "2", "2"));
        Assertions.assertEquals(1, exp.calculate());
    }

    @Test
    public void powTest() throws Exception {
        Calculator exp = new Calculator(Arrays.asList("pow", "2", "3"));
        Assertions.assertEquals(8, exp.calculate());
    }

    @Test
    public void sqrtTest() throws Exception {
        Calculator exp = new Calculator(Arrays.asList("sqrt", "-1"));
        Exception thrown = Assertions.assertThrows(Exception.class, exp::calculate);
        Assertions.assertEquals("The sqrt of negative number is not defined", thrown.getMessage());

        exp = new Calculator(Arrays.asList("sqrt", "4"));
        Assertions.assertEquals(2, exp.calculate());
    }

    @Test
    public void logTest() throws Exception {
        Calculator exp = new Calculator(Arrays.asList("log", "0"));
        Exception thrown = Assertions.assertThrows(Exception.class, exp::calculate);
        Assertions.assertEquals("The logarithm of zero is not defined", thrown.getMessage());

        exp = new Calculator(Arrays.asList("log", "1"));
        Assertions.assertEquals(0, exp.calculate());
    }

    @Test
    public void sinTest() throws Exception {
        Calculator exp = new Calculator(Arrays.asList("sin", "0"));
        Assertions.assertEquals(0, exp.calculate());
    }

    @Test
    public void cosTest() throws Exception {
        Calculator exp = new Calculator(Arrays.asList("cos", "0"));
        Assertions.assertEquals(1, exp.calculate());
    }

    @Test
    public void incorrectInputTest() throws Exception {
        Calculator exp = new Calculator(Arrays.asList("asd", "asd"));
        Exception thrown = Assertions.assertThrows(Exception.class, exp::calculate);
        Assertions.assertEquals("Incorrect input", thrown.getMessage());
    }

    //complex test
    @Test
    public void complexTest() throws Exception {
        Calculator exp = new Calculator(Arrays.asList("sin", "cos", "+", "+", "sqrt", "2", "2", "-",
                "pow", "6", "4", "log", "5"));
        Assertions.assertEquals(-0.8116709407298298, exp.calculate());
    }
}