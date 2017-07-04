package berry.justin.spreadsheet.io;

import com.opencsv.CSVReader;

import java.io.IOException;
import java.util.List;

public class SpreadsheetReader {
  private CSVReaderFactory csvReaderFactory;
  private String filename;

  public SpreadsheetReader(String aFilename) {
    this(new CSVReaderFactory(), aFilename);
  }

  SpreadsheetReader(CSVReaderFactory factory, String aFilename) {
    csvReaderFactory = factory;
    filename = aFilename;
  }

  public List<String[]> readAll() throws IOException {
    CSVReader csvReader = csvReaderFactory.create(filename);
    List<String[]> spreadsheet = csvReader.readAll();
    csvReader.close();
    return spreadsheet;
  }
}
