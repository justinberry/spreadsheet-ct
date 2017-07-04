package berry.justin.spreadsheet.calculator.action;

public class ReturnValueAction implements Action {
  private Double value;

  public ReturnValueAction(String aValue) {
    if (aValue.isEmpty()) {
      value = 0.0;
    } else {
      value = Double.valueOf(aValue);
    }
  }

  @Override
  public Double apply(Double... operands) {
    return value;
  }

  @Override
  public int numberOfOperands() {
    return 0;
  }
}
