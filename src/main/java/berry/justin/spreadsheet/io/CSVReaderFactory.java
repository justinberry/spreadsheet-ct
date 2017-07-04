package berry.justin.spreadsheet.io;

import com.opencsv.CSVReader;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class CSVReaderFactory {
  public CSVReader create(String filename) throws FileNotFoundException {
    return new CSVReader(new FileReader(filename));
  }
}
