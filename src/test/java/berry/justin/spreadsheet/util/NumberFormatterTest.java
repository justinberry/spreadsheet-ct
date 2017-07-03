package berry.justin.spreadsheet.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(JUnit4.class)
public class NumberFormatterTest {
  @Test
  public void setsPrecision() {
    assertThat(NumberFormatter.roundToPrecision(1.3333333333, 2), is(1.33));
  }

  @Test
  public void roundsDownWhenOnLowerBound() {
    assertThat(NumberFormatter.roundToPrecision(1.44, 1), is(1.4));
  }

  @Test
  public void roundsUpWhenOnHalfBound() {
    assertThat(NumberFormatter.roundToPrecision(1.45, 1), is(1.5));
  }

  @Test
  public void roundsUpWhenOnUpperBound() {
    assertThat(NumberFormatter.roundToPrecision(1.46, 1), is(1.5));
  }
}
