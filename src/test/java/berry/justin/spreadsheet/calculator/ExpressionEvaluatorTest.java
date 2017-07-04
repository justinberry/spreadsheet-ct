package berry.justin.spreadsheet.calculator;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ExpressionEvaluatorTest {

  private ArrayList<String[]> inputSpreadsheet;
  private ExpressionEvaluator evaluator;

  @Before
  public void setUp() {
    inputSpreadsheet = new ArrayList<>();
  }

  @Test
  public void evaluatesSimpleAdditionProblem() {
    evaluator = new ExpressionEvaluator(inputSpreadsheet, "2 3 +");
    assertThat(evaluator.evaluate(),is(5.0));
  }

  @Test
  public void evaluatesSimpleSubtractionProblem() {
    evaluator = new ExpressionEvaluator(inputSpreadsheet, "2 3 -");
    assertThat(evaluator.evaluate(), is(-1.0));
  }

  @Test
  public void evaluatesSimpleMultiplicationProblem() {
    evaluator = new ExpressionEvaluator(inputSpreadsheet, "2 3 *");
    assertThat(evaluator.evaluate(), is(6.0));
  }

  @Test
  public void evaluatesSimpleDivisionProblem() {
    evaluator = new ExpressionEvaluator(inputSpreadsheet, "2 3 /");
    assertThat(evaluator.evaluate(), is(0.67));
  }

  @Test
  public void evaluatesMultipleOperations() {
    evaluator = new ExpressionEvaluator(inputSpreadsheet, "1 2 + 4 *");
    assertThat(evaluator.evaluate(), is(12.0));
  }

  @Test
  public void trimsExtraWhitespacesInInput() {
    evaluator = new ExpressionEvaluator(inputSpreadsheet, "  1  \t2 +  4 *");
    assertThat(evaluator.evaluate(), is(12.0));
  }

  @Test
  public void isIdempotent() {
    evaluator = new ExpressionEvaluator(inputSpreadsheet, "1 2 + 4 *");
    assertThat(evaluator.evaluate(), is(12.0));
    assertThat(evaluator.evaluate(), is(12.0));
  }
}
