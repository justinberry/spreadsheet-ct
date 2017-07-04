package berry.justin.spreadsheet.io;

import com.opencsv.CSVWriter;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class SpreadsheetWriterTest {
  private SpreadsheetWriter spreadsheetWriter;
  private CSVWriter mockCsvWriter;

  @Before
  public void setUp() throws IOException {
    CSVWriterFactory mockCsvWriterFactory = mock(CSVWriterFactory.class);
    mockCsvWriter = mock(CSVWriter.class);
    when(mockCsvWriterFactory.create("output.csv")).thenReturn(mockCsvWriter);
    spreadsheetWriter = new SpreadsheetWriter(
        mockCsvWriterFactory, "output.csv");
  }

  @Test
  public void delegatesToCsvWriter() throws IOException {
    ArrayList<String[]> expectedDataToWrite = new ArrayList<>();
    spreadsheetWriter.writeAll(expectedDataToWrite);
    verify(mockCsvWriter).writeAll(expectedDataToWrite);
  }

  @Test
  public void closesWriter() throws IOException {
    spreadsheetWriter.writeAll(new ArrayList<>());
    verify(mockCsvWriter).close();
  }
}
