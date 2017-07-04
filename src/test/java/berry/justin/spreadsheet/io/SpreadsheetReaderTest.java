package berry.justin.spreadsheet.io;

import com.opencsv.CSVReader;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsSame.sameInstance;
import static org.mockito.Mockito.*;

@RunWith(JUnit4.class)
public class SpreadsheetReaderTest {

  private SpreadsheetReader spreadsheetReader;
  private CSVReader mockCsvReader;

  @Before
  public void setUp() throws FileNotFoundException {
    CSVReaderFactory mockCsvReaderFactory = mock(CSVReaderFactory.class);
    mockCsvReader = mock(CSVReader.class);
    when(mockCsvReaderFactory.create("file.csv")).thenReturn(mockCsvReader);
    spreadsheetReader = new SpreadsheetReader(mockCsvReaderFactory, "file.csv");
  }

  @Test
  public void readsEntireSpreadsheet() throws IOException {
    ArrayList<String[]> expectedData = new ArrayList<>();
    when(mockCsvReader.readAll()).thenReturn(expectedData);
    List<String[]> spreadsheet = spreadsheetReader.readAll();
    assertThat(spreadsheet, sameInstance(expectedData));
  }

  @Test
  public void closeReader() throws IOException {
    spreadsheetReader.readAll();
    verify(mockCsvReader).close();
  }
}
