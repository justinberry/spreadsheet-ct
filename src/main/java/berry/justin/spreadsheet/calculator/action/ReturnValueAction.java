package berry.justin.spreadsheet.calculator.action;

import static berry.justin.spreadsheet.util.NumberFormatter.roundToPrecision;

public class ReturnValueAction implements Action {
  private Double value;

  public ReturnValueAction(Double aValue) {
    value = aValue;
  }

  @Override
  public Double apply(Double... operands) {
    return roundToPrecision(value, DEFAULT_PRECISION);
  }

  @Override
  public int numberOfOperands() {
    return 0;
  }
}
