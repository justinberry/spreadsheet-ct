package berry.justin.spreadsheet.calculator;

import java.math.BigDecimal;
import java.util.Stack;

public class ExpressionEvaluator {
  private static final String ADDITION = "+";
  private static final String SUBTRACTION = "-";
  private static final String MULTIPLICATION = "*";
  private static final String DIVISION = "/";
  private static final int DECIMAL_PRECISION = 2;

  public Stack<Double> evaluate(Stack<Double> stack, String nextSymbol) {
    Stack<Double> localStack = new Stack<>();
    localStack.addAll(stack);

    Double operand2;
    Double operand1;
    switch (nextSymbol) {
      case ADDITION:
        operand2 = localStack.pop();
        operand1 = localStack.pop();
        localStack.push(operand1 + operand2);
        break;
      case SUBTRACTION:
        operand2 = localStack.pop();
        operand1 = localStack.pop();
        localStack.push(operand1 - operand2);
        break;
      case MULTIPLICATION:
        operand2 = localStack.pop();
        operand1 = localStack.pop();
        localStack.push(operand1 * operand2);
        break;
      case DIVISION:
        operand2 = localStack.pop();
        operand1 = localStack.pop();
        Double result = operand1 / operand2;
        localStack.push(BigDecimal
            .valueOf(result)
            .setScale(DECIMAL_PRECISION, BigDecimal.ROUND_HALF_UP)
            .doubleValue());
        break;
      default:
        localStack.push(Double.parseDouble(nextSymbol));
    }

    return localStack;
  }
}
