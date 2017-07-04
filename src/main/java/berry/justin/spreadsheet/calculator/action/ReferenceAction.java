package berry.justin.spreadsheet.calculator.action;

import berry.justin.spreadsheet.calculator.ExpressionEvaluator;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReferenceAction implements Action {
  public static final String REFERENCE_REGEX = "([A-Z])([0-9]+)";
  private static final int ASCII_A_INT = 65;

  private String referenceCoordinates;
  private List<String[]> spreadsheet;

  public ReferenceAction(List<String[]> aSpreadsheet,
                         String someReferenceCoords) {
    referenceCoordinates = someReferenceCoords;
    spreadsheet = aSpreadsheet;
  }

  @Override
  public Double apply(Double... operands) {
    Pattern pattern = Pattern.compile(REFERENCE_REGEX);
    Matcher matcher = pattern.matcher(referenceCoordinates);

    if (matcher.find()) {
      int column = (int) matcher.group(1).toUpperCase().charAt(0) - ASCII_A_INT;
      int row = Integer.valueOf(matcher.group(2)) - 1;
      return new ExpressionEvaluator(spreadsheet,
          spreadsheet.get(column)[row]).evaluate();
    } else {
      throw new RuntimeException(
          "Invalid reference coordinates:" + referenceCoordinates
      );
    }
  }

  @Override
  public int numberOfOperands() {
    return 0;
  }
}
