package berry.justin.spreadsheet.calculator.action;

public class ReturnValueAction implements Action {
  private Double value;

  public ReturnValueAction(Double aValue) {
    value = aValue;
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
