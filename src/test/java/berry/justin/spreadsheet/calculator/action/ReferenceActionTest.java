package berry.justin.spreadsheet.calculator.action;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(JUnit4.class)
public class ReferenceActionTest {
  private ReferenceAction referenceAction;

  @Before
  public void setup() {
    String[][] spreadsheet = new String[10][10];
    spreadsheet[1][5] = "58";
    referenceAction = new ReferenceAction(spreadsheet, "B6");
  }

  @Test
  public void retrievesReferenceToValue() {
    assertThat(referenceAction.apply(), is(58.0));
  }

  @Test
  public void evaluatesAnExpression() {
    String[][] spreadsheet = new String[10][10];
    spreadsheet[0][0] = "9 9 *";
    referenceAction = new ReferenceAction(spreadsheet, "A1");
    assertThat(referenceAction.apply(), is(81.0));
  }

  @Test
  public void setsPrecisionAtTwo() {
    String[][] spreadsheet = new String[10][10];
    spreadsheet[0][0] = "3.3333333";
    assertThat(new ReferenceAction(spreadsheet, "A1").apply(), is(3.33));
  }
}
