package berry.justin.spreadsheet.calculator;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ExpressionEvaluatorTest {
  @Test
  public void evaluatesSimpleAdditionProblem() {
    assertThat(new ExpressionEvaluator("2 3 +").evaluate(), is(5.0));
  }

  @Test
  public void evaluatesSimpleSubtractionProblem() {
    assertThat(new ExpressionEvaluator("2 3 -").evaluate(), is(-1.0));
  }

  @Test
  public void evaluatesSimpleMultiplicationProblem() {
    assertThat(new ExpressionEvaluator("2 3 *").evaluate(), is(6.0));
  }

  @Test
  public void evaluatesSimpleDivisionProblem() {
    assertThat(new ExpressionEvaluator("2 3 /").evaluate(), is(0.67));
  }

  @Test
  public void evaluatesMultipleOperations() {
    assertThat(new ExpressionEvaluator("1 2 + 4 *").evaluate(), is(12.0));
  }

  @Test
  public void isIdempotent() {
    ExpressionEvaluator instance = new ExpressionEvaluator("1 2 + 4 *");
    assertThat(instance.evaluate(), is(12.0));
    assertThat(instance.evaluate(), is(12.0));
  }
}
