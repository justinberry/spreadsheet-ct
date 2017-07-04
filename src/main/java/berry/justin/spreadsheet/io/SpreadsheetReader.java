package berry.justin.spreadsheet.io;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class SpreadsheetReader {

  private CSVReaderBuilder csvReaderBuilder;

  public SpreadsheetReader(String filename) throws FileNotFoundException {
    csvReaderBuilder = new CSVReaderBuilder(new FileReader(filename));
  }

  SpreadsheetReader(CSVReaderBuilder builder) {
    csvReaderBuilder = builder;
  }

  public List<String[]> readAll() throws IOException {
    CSVReader csvReader = csvReaderBuilder.build();
    List<String[]> spreadsheet = csvReader.readAll();
    csvReader.close();
    return spreadsheet;
  }
}
