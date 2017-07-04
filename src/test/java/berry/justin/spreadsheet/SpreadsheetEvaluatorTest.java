package berry.justin.spreadsheet;

import berry.justin.spreadsheet.io.SpreadsheetReader;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static berry.justin.spreadsheet.SpreadsheetEvaluator.ERROR_STRING;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(JUnit4.class)
public class SpreadsheetEvaluatorTest {

  private SpreadsheetEvaluator spreadsheetEvaluator;
  private SpreadsheetReader spreadsheetReaderMock;
  private ArrayList<String[]> inputSpreadsheet;

  @Before
  public void setUp() throws IOException {
    spreadsheetReaderMock = mock(SpreadsheetReader.class);
    spreadsheetEvaluator = new SpreadsheetEvaluator(spreadsheetReaderMock);
    String[] row1 = {"1 1 +", "1", "2 2 *"};
    String[] row2 = {"3 1 -", "0", "4 2 *"};
    inputSpreadsheet = new ArrayList<String[]>() { {
      add(row1);
      add(row2);
    } };
    when(spreadsheetReaderMock.readAll()).thenReturn(inputSpreadsheet);
  }

  @Test
  public void delegatesToSpreadsheetReaderForInputFile() throws IOException {
    spreadsheetEvaluator.evaluate();
    verify(spreadsheetReaderMock).readAll();
  }

  @Test
  public void returnsSpreadsheetWithSameNumberOfRows() throws IOException {
    List<String[]> evaluatedSheet = spreadsheetEvaluator.evaluate();
    assertThat(evaluatedSheet.size(), is(2));
  }

  @Test
  public void returnsSpreadsheetWithSameNumberOfColumns() throws IOException {
    List<String[]> evaluatedSheet = spreadsheetEvaluator.evaluate();
    assertThat(evaluatedSheet.get(0).length, is(3));
    assertThat(evaluatedSheet.get(1).length, is(3));
  }

  @Test
  public void populatesCellsWithErrorIfExpressionHasError() throws IOException {
    String[] badRow = {"2 2", "*", "1 6 +"};
    List<String[]> spreadsheet = new ArrayList<String[]>() { {
      add(badRow);
    } };
    when(spreadsheetReaderMock.readAll()).thenReturn(spreadsheet);

    List<String[]> evaluatedSheet = spreadsheetEvaluator.evaluate();
    assertThat(evaluatedSheet.get(0)[0], is(ERROR_STRING));
    assertThat(evaluatedSheet.get(0)[1], is(ERROR_STRING));
    assertThat(evaluatedSheet.get(0)[2], is("7"));
  }
}
