package berry.justin.spreadsheet.calculator.action;

public class SubtractionAction implements Action {
  @Override
  public Double apply(Double... operands) {
    return operands[0] - operands[1];
  }
}
