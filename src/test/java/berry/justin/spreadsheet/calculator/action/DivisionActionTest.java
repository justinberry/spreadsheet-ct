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
    assertThat(divisionAction.apply(15.0, 10.0), is(1.5));
  }
}
