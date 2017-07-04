package berry.justin.spreadsheet.calculator.action;

public class DivisionAction implements Action {
  @Override
  public Double apply(Double... operands) {
    return operands[0] / operands[1];
  }

  @Override
  public int numberOfOperands() {
    return 2;
  }
}
