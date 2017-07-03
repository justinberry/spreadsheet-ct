package berry.justin.spreadsheet.calculator.action;

public interface Action {
  int DEFAULT_PRECISION = 2;
  Double apply(Double... operands);
}
