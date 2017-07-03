package berry.justin.spreadsheet.calculator.action;

public interface Action {
  Double apply(Double... operands);
}
