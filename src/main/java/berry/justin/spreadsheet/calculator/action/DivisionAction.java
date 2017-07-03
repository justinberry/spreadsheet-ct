package berry.justin.spreadsheet.calculator.action;

import static berry.justin.spreadsheet.util.NumberFormatter.roundToPrecision;

public class DivisionAction implements Action {
  @Override
  public Double apply(Double... operands) {
    return roundToPrecision(operands[0] / operands[1], DEFAULT_PRECISION);
  }

  @Override
  public int numberOfOperands() {
    return 2;
  }
}
