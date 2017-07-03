package berry.justin.spreadsheet.calculator.action;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(JUnit4.class)
public class ActionFactoryTest {

  private ActionFactory actionFactory;

  @Before
  public void setup() {
    actionFactory = new ActionFactory();
  }

  @Test
  public void buildsAdditionAction() {
    assertThat(actionFactory.fromSymbol("+"), instanceOf(AdditionAction.class));
  }

  @Test
  public void buildsSubtractionAction() {
    assertThat(actionFactory.fromSymbol("-"),
        instanceOf(SubtractionAction.class));
  }

  @Test
  public void buildsMultiplicationAction() {
    assertThat(actionFactory.fromSymbol("*"),
        instanceOf(MultiplicationAction.class));
  }

  @Test
  public void buildsReferenceAction() {
    assertThat(actionFactory.fromSymbol("A10"),
        instanceOf(ReferenceAction.class));
  }

  @Test
  public void buildsDivisionAction() {
    assertThat(actionFactory.fromSymbol("/"),
        instanceOf(DivisionAction.class));
  }

  @Test
  public void buildsReturnValueAction() {
    assertThat(actionFactory.fromSymbol("99"),
        instanceOf(ReturnValueAction.class));
  }

  @Test
  public void returnValueActionContainsValue() {
    assertThat(actionFactory.fromSymbol("99").apply(),
        equalTo(99.0));
  }
}
