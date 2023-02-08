import java.util.List;
import java.util.Stack;

/**
 * Class to define a logic of calculator.
 */
public class Calculator {
    List<String> expression;
    Stack<Double> result = new Stack<>();

    /**
     * Constructor for calculator.
     *
     * @param input expression to calculate
     */
    public Calculator(List<String> input) {
        expression = input;
    }

    /**
     * Method to calculate an expression.
     *
     * @return result of calculation
     * @throws Exception exception in exceptional situations
     */
    public Double calculate() throws Exception {

        for (int i = expression.size() - 1; i >= 0; i--) {

            double a;
            double b;
            switch (expression.get(i)) {
                case ("+"):
                    a = result.pop();
                    b = result.pop();
                    a += b;
                    result.add(a);
                    break;
                case ("-"):
                    a = result.pop();
                    b = result.pop();
                    a -= b;
                    result.add(a);
                    break;
                case ("*"):
                    a = result.pop();
                    b = result.pop();
                    a *= b;
                    result.add(a);
                    break;
                case ("/"):
                    a = result.pop();
                    b = result.pop();
                    if (b == 0) {
                        throw new Exception("The zero division is not defined");
                    }
                    a /= b;
                    result.add(a);
                    break;
                case ("pow"):
                    a = result.pop();
                    b = result.pop();
                    a = Math.pow(a, b);
                    result.add(a);
                    break;
                case ("log"):
                    a = result.pop();
                    if (a == 0) {
                        throw new Exception("The logarithm of zero is not defined");
                    }
                    a = Math.log(a);
                    result.add(a);
                    break;
                case ("sqrt"):
                    a = result.pop();
                    if (a < 0) {
                        throw new Exception("The sqrt of negative number is not defined");
                    }
                    a = Math.sqrt(a);
                    result.add(a);
                    break;
                case ("sin"):
                    a = result.pop();
                    a = Math.sin(a);
                    result.add(a);
                    break;
                case ("cos"):
                    a = result.pop();
                    a = Math.cos(a);
                    result.add(a);
                    break;
                default:
                    boolean numeric = true;
                    double num = 0;
                    try {
                        num = Double.parseDouble(expression.get(i));
                    } catch (NumberFormatException e) {
                        numeric = false;
                    }
                    if (numeric) {
                        result.add(num);
                    } else {
                        throw new Exception("Incorrect input");
                    }
            }
        }
        return result.pop();
    }
}