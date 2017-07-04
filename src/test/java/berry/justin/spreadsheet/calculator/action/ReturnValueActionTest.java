package berry.justin.spreadsheet.calculator.action;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ReturnValueActionTest {
  @Test
  public void returnsValue() {
    assertThat(new ReturnValueAction("1.333").apply(), is(1.333));
  }

  @Test
  public void emptyValuesEvaluateToZero() {
    assertThat(new ReturnValueAction("").apply(), is(0.0));
  }
}
