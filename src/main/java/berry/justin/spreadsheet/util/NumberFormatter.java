package berry.justin.spreadsheet.util;

import java.math.BigDecimal;

public final class NumberFormatter {
  private NumberFormatter() { }

  public static Double roundToPrecision(double value, int precision) {
    return BigDecimal.valueOf(value)
        .setScale(precision, BigDecimal.ROUND_HALF_UP)
        .doubleValue();
  }
}
