package berry.justin.spreadsheet.calculator;

import berry.justin.spreadsheet.calculator.action.Action;
import berry.justin.spreadsheet.calculator.action.ActionFactory;
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

  @Before
  public void setUp() {
    inputSpreadsheet = new ArrayList<>();
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
  public void throwsErrorIfOperatorDemandExhaustsAvailableOperands() {
    ActionFactory mockActionFactory = mock(ActionFactory.class);
    Action mockAction = mock(Action.class);
    when(mockAction.numberOfOperands()).thenReturn(2);
    when(mockActionFactory.fromSymbol(eq(inputSpreadsheet), anyString()))
        .thenReturn(mockAction);
    evaluator = new ExpressionEvaluator(inputSpreadsheet,
        "1 +",
        new Stack<>(),
        mockActionFactory);
    evaluator.evaluate();
  }

  @Test(expected = IllegalArgumentException.class)
  public void throwsErrorIfExpressionNotResolvedAfterEvaluation() {
    evaluator = new ExpressionEvaluator(inputSpreadsheet, "1 2");
    evaluator.evaluate();
  }

  @Test(expected = IllegalArgumentException.class)
  public void throwsErrorIfActionThrowsAnError() {
    ActionFactory mockActionFactory = mock(ActionFactory.class);
    when(mockActionFactory.fromSymbol(eq(inputSpreadsheet), anyString()))
        .thenThrow(IllegalArgumentException.class);
    evaluator = new ExpressionEvaluator(inputSpreadsheet,
        "1 2 +",
        new Stack<>(),
        mockActionFactory);
    evaluator.evaluate();
  }
}
