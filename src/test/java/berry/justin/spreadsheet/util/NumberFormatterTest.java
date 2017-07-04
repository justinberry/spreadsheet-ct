package berry.justin.spreadsheet.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(JUnit4.class)
public class NumberFormatterTest {
  @Test
  public void roundsDownWhenOnLowerBound() {
    assertThat(NumberFormatter.formatWithScaleTwo(1.444), is("1.44"));
  }

  @Test
  public void roundsUpWhenOnHalfBound() {
    assertThat(NumberFormatter.formatWithScaleTwo(1.445), is("1.45"));
  }

  @Test
  public void roundsUpWhenOnUpperBound() {
    assertThat(NumberFormatter.formatWithScaleTwo(1.446), is("1.45"));
  }
}
