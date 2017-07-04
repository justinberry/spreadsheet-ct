package berry.justin.spreadsheet.io;

import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;

public class CSVWriterFactory {
  public CSVWriter create(String filename) throws IOException {
    return new CSVWriter(new FileWriter(filename));
  }
}
