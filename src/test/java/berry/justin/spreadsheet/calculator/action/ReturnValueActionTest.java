package berry.justin.spreadsheet.calculator.action;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ReturnValueActionTest {
  @Test
  public void setsPrecisionAtTwo() {
    assertThat(new ReturnValueAction(1.33333).apply(), is(1.33));
  }
}
