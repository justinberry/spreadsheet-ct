package berry.justin.spreadsheet.calculator.action;

import static berry.justin.spreadsheet.util.NumberFormatter.roundToPrecision;

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
    return roundToPrecision(value, DEFAULT_PRECISION);
  }

  @Override
  public int numberOfOperands() {
    return 0;
  }
}
