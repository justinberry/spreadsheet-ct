package berry.justin.spreadsheet.calculator;

import berry.justin.spreadsheet.calculator.action.Action;
import berry.justin.spreadsheet.calculator.action.ActionFactory;

import java.util.Stack;

public class ExpressionEvaluator {
  private ActionFactory actionFactory = new ActionFactory();

  private String expression;
  private Stack<Double> stack;

  public ExpressionEvaluator(String anExpression) {
    expression = anExpression;
  }

  public Double evaluate() {
    stack = new Stack<>();
    for (String token : expression.split("\\s")) {
      processSymbol(token);
    }
    return stack.peek();
  }

  private void processSymbol(String nextSymbol) {
    Action action = actionFactory.fromSymbol(nextSymbol);
    int numberOfOperands = action.numberOfOperands();
    Double result = action.apply(popOperands(numberOfOperands));
    stack.push(result);
  }

  private Double[] popOperands(int numberOfOperands) {
    Double[] operands = new Double[numberOfOperands];
    for (int i = numberOfOperands - 1; i >= 0; --i) {
      operands[i] = stack.pop();
    }
    return operands;
  }
}
