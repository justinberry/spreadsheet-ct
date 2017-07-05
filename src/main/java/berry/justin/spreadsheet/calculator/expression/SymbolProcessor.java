package berry.justin.spreadsheet.calculator.expression;

import berry.justin.spreadsheet.calculator.action.Action;
import berry.justin.spreadsheet.calculator.action.ActionFactory;

import java.util.List;
import java.util.Stack;

public class SymbolProcessor {
  private static SymbolProcessor instance;
  private ActionFactory actionFactory;

  public static final String INSUFFICIENT_OPERANDS =
      "Insufficient operands available for operation.";

  public static SymbolProcessor getInstance() {
    if (instance == null) {
      instance = new SymbolProcessor();
    }
    return instance;
  }

  SymbolProcessor() {
    this(ActionFactory.getInstance());
  }

  SymbolProcessor(ActionFactory anActionFactory) {
    actionFactory = anActionFactory;
  }

  public Stack<Double> process(List<String[]> spreadsheet,
                               Stack<Double> operands,
                               String symbol) {

    Stack<Double> newStack = new Stack<>();
    newStack.addAll(operands);

    Action action = actionFactory.fromSymbol(spreadsheet, symbol);
    int numberOfOperands = action.numberOfOperands();
    Double result = action.apply(popOperands(newStack, numberOfOperands));
    newStack.push(result);

    return newStack;
  }

  private Double[] popOperands(Stack<Double> stack, int numberOfOperands) {
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
