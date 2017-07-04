package berry.justin.spreadsheet.calculator.action;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(JUnit4.class)
public class ReferenceActionTest {
  private ReferenceAction referenceAction;
  private List<String[]> spreadsheet = new ArrayList<>();

  @Before
  public void setup() {
    String[] emptyRow = {" ", "", "", "", "", "" };
    String[] row = {" ", "", "", "", "", "58" };
    spreadsheet.add(emptyRow);
    spreadsheet.add(row);
    referenceAction = new ReferenceAction(spreadsheet, "B6");
  }

  @Test
  public void retrievesReferenceToValue() {
    assertThat(referenceAction.apply(), is(58.0));
  }

  @Test
  public void evaluatesAnExpression() {
    String[] row = {" 9 9 *", "" };
    spreadsheet.add(row);
    referenceAction = new ReferenceAction(spreadsheet, "A1");
    assertThat(referenceAction.apply(), is(81.0));
  }

  @Test
  public void setsPrecisionAtTwo() {
    String[] row = {" 3.3333333", "" };
    spreadsheet.add(row);
    assertThat(new ReferenceAction(spreadsheet, "A1").apply(), is(3.33));
  }
}
