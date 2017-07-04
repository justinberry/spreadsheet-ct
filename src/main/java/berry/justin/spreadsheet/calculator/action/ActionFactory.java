package berry.justin.spreadsheet.calculator.action;

import java.util.HashMap;
import java.util.List;
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

  private static ActionFactory instance;

  private ActionFactory() { }

  public static ActionFactory getInstance() {
    if (instance == null) {
      instance = new ActionFactory();
    }
    return instance;
  }

  public Action fromSymbol(List<String[]> spreadsheet, String symbol) {
    String uppercaseSymbol = symbol.toUpperCase();
    Action action = BASIC_ARITHMETIC_ACTIONS.get(uppercaseSymbol);

    boolean isRef = uppercaseSymbol.matches(ReferenceAction.REFERENCE_REGEX);
    if (action == null && isRef) {
      action = new ReferenceAction(spreadsheet, uppercaseSymbol);
    }

    if (action == null) {
      action = new ReturnValueAction(Double.valueOf(uppercaseSymbol));
    }

    return action;
  }
}
