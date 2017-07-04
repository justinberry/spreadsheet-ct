package berry.justin.spreadsheet.calculator;

import berry.justin.spreadsheet.calculator.action.Action;
import berry.justin.spreadsheet.calculator.action.ActionFactory;

import java.util.List;
import java.util.Stack;

public class ExpressionEvaluator {
  private static final String WHITESPACE_REGEX = "\\s+";

  private String expression;
  private Stack<Double> stack;
  private List<String[]> inputSpreadsheet;
  private ActionFactory actionFactory;

  public ExpressionEvaluator(List<String[]> anInputSpreadsheet,
                             String anExpression) {
    expression = anExpression;
    stack = new Stack<>();
    inputSpreadsheet = anInputSpreadsheet;
    actionFactory = ActionFactory.getInstance();
  }

  public Double evaluate() {
    for (String token : expression.trim().split(WHITESPACE_REGEX)) {
      processSymbol(token);
    }

    return stack.peek();
  }

  private void processSymbol(String nextSymbol) {
    Action action = actionFactory.fromSymbol(inputSpreadsheet, nextSymbol);
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
