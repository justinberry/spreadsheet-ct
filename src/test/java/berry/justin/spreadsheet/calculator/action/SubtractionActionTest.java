package berry.justin.spreadsheet.calculator.action;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(JUnit4.class)
public class SubtractionActionTest {
  private SubtractionAction subtractionAction;

  @Before
  public void setup() {
    subtractionAction = new SubtractionAction();
  }

  @Test
  public void subtractsOperands() {
    assertThat(subtractionAction.apply(2.0, 4.5), is(-2.5));
  }

  @Test
  public void setsPrecisionAtTwo() {
    assertThat(subtractionAction.apply(2.33333, 1.0), is(1.33));
  }
}
