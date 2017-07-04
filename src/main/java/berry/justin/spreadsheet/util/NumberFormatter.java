package berry.justin.spreadsheet.util;

import java.math.BigDecimal;

public final class NumberFormatter {
  private static final int DEFAULT_PRECISION = 2;

  private NumberFormatter() { }

  public static String formatWithScaleTwo(double value) {
    return BigDecimal.valueOf(value)
        .setScale(DEFAULT_PRECISION, BigDecimal.ROUND_HALF_UP)
        .stripTrailingZeros()
        .toPlainString();
  }
}
