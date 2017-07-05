package berry.justin.spreadsheet.calculator.expression;

import java.util.List;
import java.util.Stack;

public class ExpressionEvaluator {
  private static final String WHITESPACE_REGEX = "\\s+";
  private static final String INVALID_CELL =
      "Cell expression is invalid.";

  private String expression;
  private Stack<Double> stack;
  private List<String[]> inputSpreadsheet;
  private SymbolProcessor symbolProcessor;

  public ExpressionEvaluator(List<String[]> anInputSpreadsheet,
                             String anExpression) {
    this(anInputSpreadsheet,
        anExpression,
        new Stack<>(),
        SymbolProcessor.getInstance());
  }

  ExpressionEvaluator(List<String[]> anInputSpreadsheet,
                      String anExpression,
                      Stack<Double> aStack,
                      SymbolProcessor aSymbolProcessor) {
    inputSpreadsheet = anInputSpreadsheet;
    expression = anExpression;
    stack = aStack;
    symbolProcessor = aSymbolProcessor;
  }

  public Double evaluate() {
    stack.clear();

    for (String symbol : expression.trim().split(WHITESPACE_REGEX)) {
      stack = symbolProcessor.process(
          inputSpreadsheet,
          stack,
          symbol
      );
    }

    if (stack.size() > 1) {
      throw new IllegalArgumentException(INVALID_CELL);
    }

    return stack.peek();
  }
}
