package berry.justin.spreadsheet.calculator.action;

import java.util.HashMap;
import java.util.Map;

public class ActionFactory {
  private static final String ADDITION = "+";
  private static final String SUBTRACTION = "-";
  private static final String MULTIPLICATION = "*";
  private static final String DIVISION = "/";

  private static final Map<String, Action> BASIC_ARITHMETIC_ACTIONS
      = new HashMap<>();

  static {
    BASIC_ARITHMETIC_ACTIONS.put(ADDITION, new AdditionAction());
    BASIC_ARITHMETIC_ACTIONS.put(SUBTRACTION, new SubtractionAction());
    BASIC_ARITHMETIC_ACTIONS.put(MULTIPLICATION, new MultiplicationAction());
    BASIC_ARITHMETIC_ACTIONS.put(DIVISION, new DivisionAction());
  }

  public Action fromSymbol(String symbol) {
    return BASIC_ARITHMETIC_ACTIONS.get(symbol);
  }
}
