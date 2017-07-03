package berry.justin.spreadsheet.calculator.action;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(JUnit4.class)
public class DivisionActionTest {
  private DivisionAction divisionAction;

  @Before
  public void setup() {
    divisionAction = new DivisionAction();
  }

  @Test
  public void subtractsOperands() {
    assertThat(divisionAction.apply(2.0, 4.5), is(0.44));
  }

  @Test
  public void setsPrecisionAtTwo() {
    assertThat(divisionAction.apply(1.0, 3.0), is(0.33));
  }
}
