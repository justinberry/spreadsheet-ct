package berry.justin.spreadsheet;

import berry.justin.spreadsheet.calculator.expression.ExpressionEvaluator;
import berry.justin.spreadsheet.io.SpreadsheetReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static berry.justin.spreadsheet.util.NumberFormatter.formatWithScaleTwo;

public class SpreadsheetEvaluator {

  public static final String ERROR_STRING = "#ERR";

  private SpreadsheetReader spreadsheetReader;

  public SpreadsheetEvaluator(String filename) {
    this(new SpreadsheetReader(filename));
  }

  SpreadsheetEvaluator(SpreadsheetReader aSpreadsheetReader) {
    spreadsheetReader = aSpreadsheetReader;
  }

  public List<String[]> evaluate() throws IOException {
    List<String[]> outputSpreadsheet = new ArrayList<>();
    List<String[]> inputSheet = spreadsheetReader.readAll();

    for (String[] inputRow : inputSheet) {
      outputSpreadsheet.add(new String[inputRow.length]);
      for (int j = 0; j < inputRow.length; j++) {
        String result;
        try {
          result = formatWithScaleTwo(evaluateRow(inputSheet, inputRow[j]));
        } catch (RuntimeException e) {
          result = ERROR_STRING;
        }
        outputSpreadsheet.get(outputSpreadsheet.size() - 1)[j] = result;
      }
    }

    return outputSpreadsheet;
  }

  private Double evaluateRow(List<String[]> inputSpreadsheet,
                             String expression) {
    return new ExpressionEvaluator(inputSpreadsheet, expression)
        .evaluate();
  }
}
