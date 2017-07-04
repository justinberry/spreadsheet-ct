package berry.justin.spreadsheet.calculator.action;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(JUnit4.class)
public class MultiplicationActionTest {
  private MultiplicationAction multiplicationAction;

  @Before
  public void setup() {
    multiplicationAction = new MultiplicationAction();
  }

  @Test
  public void multipliesOperands() {
    assertThat(multiplicationAction.apply(2.0, 4.5), is(9.0));
  }
}
