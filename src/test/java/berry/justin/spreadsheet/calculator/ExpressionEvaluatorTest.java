package berry.justin.spreadsheet.calculator;

import org.junit.Before;
import org.junit.Test;

import java.util.Stack;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ExpressionEvaluatorTest {

  private ExpressionEvaluator expressionEvaluator;
  private Stack<Double> stack;

  @Before
  public void setup() {
    expressionEvaluator = new ExpressionEvaluator();
    stack = new Stack<>();
    stack.push(2.0);
    stack.push(3.0);
  }

  @Test
  public void calculatesSimpleAdditionProblem() {
    Stack<Double> result = expressionEvaluator.evaluate(stack, "+");
    assertThat(result.peek(), is(5.0));
  }

  @Test
  public void calculatesSimpleSubtractionProblem() {
    Stack<Double> result = expressionEvaluator.evaluate(stack, "-");
    assertThat(result.peek(), is(-1.0));
  }

  @Test
  public void calculatesSimpleMultiplicationProblem() {
    Stack<Double> result = expressionEvaluator.evaluate(stack, "*");
    assertThat(result.peek(), is(6.0));
  }

  @Test
  public void calculatesSimpleDivisionProblem() {
    Stack<Double> result = expressionEvaluator.evaluate(stack, "/");
    assertThat(result.peek(), is(0.67));
  }
}
