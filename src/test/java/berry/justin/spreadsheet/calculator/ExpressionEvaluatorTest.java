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
  public void pushesValuesOntoStack() {
    Stack<Double> result = expressionEvaluator.evaluate(stack, "55.9");
    assertThat(result.pop(), is(55.9));
    assertThat(result.pop(), is(3.0));
  }

  @Test
  public void evaluatesSimpleAdditionProblem() {
    Stack<Double> result = expressionEvaluator.evaluate(stack, "+");
    assertThat(result.peek(), is(5.0));
  }

  @Test
  public void evaluatesSimpleSubtractionProblem() {
    Stack<Double> result = expressionEvaluator.evaluate(stack, "-");
    assertThat(result.peek(), is(-1.0));
  }

  @Test
  public void evaluatesSimpleMultiplicationProblem() {
    Stack<Double> result = expressionEvaluator.evaluate(stack, "*");
    assertThat(result.peek(), is(6.0));
  }

  @Test
  public void evaluatesSimpleDivisionProblem() {
    Stack<Double> result = expressionEvaluator.evaluate(stack, "/");
    assertThat(result.peek(), is(0.67));
  }

  @Test
  public void evaluatesMultipleOperations() {
    Stack<Double> result = new Stack<>();
    result = expressionEvaluator.evaluate(result, "1");
    result = expressionEvaluator.evaluate(result, "2");
    result = expressionEvaluator.evaluate(result, "+");
    result = expressionEvaluator.evaluate(result, "4");
    result = expressionEvaluator.evaluate(result, "*");
    assertThat(result.size(), is(1));
    assertThat(result.peek(), is(12.0));
  }
}
