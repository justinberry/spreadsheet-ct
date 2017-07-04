package berry.justin.spreadsheet.calculator.action;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(JUnit4.class)
public class AdditionActionTest {
  private AdditionAction additionAction;

  @Before
  public void setup() {
    additionAction = new AdditionAction();
  }

  @Test
  public void addsOperands() {
    assertThat(additionAction.apply(2.0, 4.5), is(6.5));
  }
}
