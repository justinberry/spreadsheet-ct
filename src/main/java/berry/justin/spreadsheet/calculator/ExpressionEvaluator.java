package berry.justin.spreadsheet.calculator;

import berry.justin.spreadsheet.calculator.action.Action;
import berry.justin.spreadsheet.calculator.action.ActionFactory;

import java.util.List;
import java.util.Stack;

public class ExpressionEvaluator {
  private static final String WHITESPACE_REGEX = "\\s+";
  private static final String INVALID_CELL_MESSAGE =
      "Cell expression is invalid.";
  private static final String INSUFFICIENT_OPERANDS =
      "Insufficient operands available for operation.";

  private String expression;
  private Stack<Double> stack;
  private List<String[]> inputSpreadsheet;
  private ActionFactory actionFactory;

  public ExpressionEvaluator(List<String[]> anInputSpreadsheet,
                             String anExpression) {
    this(anInputSpreadsheet,
        anExpression,
        new Stack<>(),
        ActionFactory.getInstance());
  }

  ExpressionEvaluator(List<String[]> anInputSpreadsheet,
                      String anExpression,
                      Stack<Double> aStack,
                      ActionFactory anActionFactory) {
    inputSpreadsheet = anInputSpreadsheet;
    expression = anExpression;
    stack = aStack;
    actionFactory = anActionFactory;
  }

  public Double evaluate() {
    stack.clear();

    for (String token : expression.trim().split(WHITESPACE_REGEX)) {
      processSymbol(token);
    }

    if (stack.size() > 1) {
      throw new IllegalArgumentException(INVALID_CELL_MESSAGE);
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
    if (numberOfOperands > stack.size()) {
      throw new IllegalArgumentException(INSUFFICIENT_OPERANDS);
    }

    Double[] operands = new Double[numberOfOperands];
    for (int i = numberOfOperands - 1; i >= 0; --i) {
      operands[i] = stack.pop();
    }
    return operands;
  }
}
