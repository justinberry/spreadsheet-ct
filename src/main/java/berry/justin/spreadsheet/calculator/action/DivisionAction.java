package berry.justin.spreadsheet.calculator.action;

import java.math.BigDecimal;

public class DivisionAction implements Action {

  public static final int PRECISION = 2;

  @Override
  public Double apply(Double... operands) {
    return new BigDecimal(operands[0] / operands[1])
        .setScale(PRECISION, BigDecimal.ROUND_HALF_UP)
        .doubleValue();
  }
}
