package berry.justin.spreadsheet.calculator.expression;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Stack;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ExpressionEvaluatorTest {

  private ArrayList<String[]> inputSpreadsheet;
  private ExpressionEvaluator evaluator;
  private SymbolProcessor symbolProcessor;

  @Before
  public void setUp() {
    inputSpreadsheet = new ArrayList<>();
    symbolProcessor = mock(SymbolProcessor.class);
  }

  @Test
  public void evaluatesSimpleAdditionProblem() {
    evaluator = new ExpressionEvaluator(inputSpreadsheet, "2 3 +");
    assertThat(evaluator.evaluate(), is(5.0));
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
    evaluator = new ExpressionEvaluator(inputSpreadsheet, "7 5 /");
    assertThat(evaluator.evaluate(), is(1.4));
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

  @Test(expected = IllegalArgumentException.class)
  public void throwsErrorIfExpressionNotResolvedAfterEvaluation() {
    evaluator = new ExpressionEvaluator(inputSpreadsheet, "1 2");
    evaluator.evaluate();
  }

  @Test(expected = IllegalArgumentException.class)
  public void throwsErrorIfSymbolCannotBeProcessed() {
    Stack<Double> operands = new Stack<>();
    when(symbolProcessor.process(
        eq(inputSpreadsheet), eq(operands), anyString()))
        .thenThrow(IllegalArgumentException.class);
    evaluator = new ExpressionEvaluator(inputSpreadsheet,
        "1 2 +",
        operands,
        symbolProcessor);
    evaluator.evaluate();
  }
}
