package berry.justin.spreadsheet.calculator;

import berry.justin.spreadsheet.calculator.action.Action;
import berry.justin.spreadsheet.calculator.action.ActionFactory;

import java.util.Stack;

public class ExpressionEvaluator {
  private ActionFactory actionFactory = new ActionFactory();

  public Stack<Double> evaluate(Stack<Double> stack, String nextSymbol) {
    Stack<Double> localStack = new Stack<>();
    localStack.addAll(stack);

    Action action = actionFactory.fromSymbol(nextSymbol);
    int numberOfOperands = action.numberOfOperands();
    Double result = action.apply(popOperands(localStack, numberOfOperands));
    localStack.push(result);

    return localStack;
  }

  private Double[] popOperands(Stack<Double> stack, int numberOfOperands) {
    Double[] operands = new Double[numberOfOperands];
    for (int i = numberOfOperands - 1; i >= 0; --i) {
      operands[i] = stack.pop();
    }
    return operands;
  }
}
