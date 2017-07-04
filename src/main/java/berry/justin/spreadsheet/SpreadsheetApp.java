package berry.justin.spreadsheet;

import berry.justin.spreadsheet.io.SpreadsheetWriter;

import java.io.IOException;
import java.util.List;

public final class SpreadsheetApp {
  private SpreadsheetApp() { }

  public static void main(String[] args) {
    if (args.length < 2) {
      System.err.println("Provide input and output file as arguments.");
      return;
    }

    String inputFile = args[0];
    String outputFile = args[1];

    try {
      List<String[]> evaluatedSpreadsheet =
          new SpreadsheetEvaluator(inputFile).evaluate();
      new SpreadsheetWriter(outputFile).writeAll(evaluatedSpreadsheet);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
