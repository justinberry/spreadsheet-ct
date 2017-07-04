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
    referenceAction = new ReferenceAction(spreadsheet, "F2");
  }

  @Test
  public void retrievesReferenceToValue() {
    String[] emptyRow = {"", "", "", "", "", ""};
    String[] row = {"", "", "", "", "", "58"};
    spreadsheet.add(emptyRow);
    spreadsheet.add(row);
    assertThat(referenceAction.apply(), is(58.0));
  }

  @Test
  public void evaluatesReferenceToAnExpression() {
    String[] row = {"9 9 *", ""};
    spreadsheet.add(row);
    referenceAction = new ReferenceAction(spreadsheet, "A1");
    assertThat(referenceAction.apply(), is(81.0));
  }

  @Test(expected = IndexOutOfBoundsException.class)
  public void throwsErrorIfReferenceColumnIsOutOfBounds() {
    String[] row = {"9 9 *", ""};
    spreadsheet.add(row);
    referenceAction = new ReferenceAction(spreadsheet, "Z1");
    assertThat(referenceAction.apply(), is(81.0));
  }

  @Test(expected = IndexOutOfBoundsException.class)
  public void throwsErrorIfReferenceRowIsOutOfBounds() {
    referenceAction = new ReferenceAction(spreadsheet, "A3");
    assertThat(referenceAction.apply(), is(81.0));
  }

  @Test(expected = IllegalArgumentException.class)
  public void throwsErrorIfRefernceIdIsInvalid() {
    String[] row = {"9 9 *", ""};
    spreadsheet.add(row);
    referenceAction = new ReferenceAction(spreadsheet, "Apple3");
    assertThat(referenceAction.apply(), is(81.0));
  }
}
