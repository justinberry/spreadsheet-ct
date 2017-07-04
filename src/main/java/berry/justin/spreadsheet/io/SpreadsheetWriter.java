package berry.justin.spreadsheet.io;

import com.opencsv.CSVWriter;

import java.io.IOException;
import java.util.List;

public class SpreadsheetWriter {
  private CSVWriterFactory csvWriterFactory;
  private String filename;

  public SpreadsheetWriter(String aFilename) {
    this(new CSVWriterFactory(), aFilename);
  }

  SpreadsheetWriter(CSVWriterFactory factory, String aFilename) {
    csvWriterFactory = factory;
    filename = aFilename;
  }

  public void writeAll(List<String[]> spreadsheet) throws IOException {
    CSVWriter csvWriter = csvWriterFactory.create(filename);
    csvWriter.writeAll(spreadsheet);
    csvWriter.close();
  }
}
